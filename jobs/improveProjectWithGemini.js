import fs from "fs";
import path from "path";
import logger from "../logger.js";
import { getTargetProjectDir } from "../services/file.js";
import {
  buildSeedProjectPrompt,
  buildImproveProjectPrompt,
  runGeminiInDir,
} from "../services/gemini.js";
import { runBatchScript } from "../services/batch.js";
import { selectNews } from "../services/newsDb.js";
import { formatNewsList } from "../services/news.js";

const PROJECT_MD = "PROJECT.md";

const DEFAULT_MIN_INTERVAL_MS = 180000;
const DEFAULT_DELAY_BEFORE_MS = 10000;

let lastRunAt = 0;

function sleep(ms) {
  return new Promise((r) => setTimeout(r, ms));
}

/** PROJECT.md 읽기. 없으면 null. */
function readProjectMd(dir) {
  const filePath = path.join(dir, PROJECT_MD);
  if (!fs.existsSync(filePath)) return null;
  return fs.readFileSync(filePath, "utf-8");
}

/** 디렉터리 내 파일 목록(이름만, 1depth). */
function listDirNames(dir) {
  if (!fs.existsSync(dir)) return [];
  return fs.readdirSync(dir, { withFileTypes: true }).map((f) => f.name);
}

/** 타겟 디렉터리 확보 → PROJECT.md 유무에 따라 시딩/개선 → (선택) push. 오케스트레이션만 담당. */
export async function improveProjectWithGemini() {
  try {
    const minIntervalMs =
      Number(process.env.GEMINI_MIN_INTERVAL_MS) || DEFAULT_MIN_INTERVAL_MS;
    const delayBeforeMs =
      Number(process.env.GEMINI_DELAY_BEFORE_MS) || DEFAULT_DELAY_BEFORE_MS;
    const now = Date.now();
    if (lastRunAt > 0 && now - lastRunAt < minIntervalMs) {
      logger.info(
        `improveProjectWithGemini: 스킵 (최소 간격 ${minIntervalMs}ms 미만, ${
          now - lastRunAt
        }ms 경과)`
      );
      return;
    }
    lastRunAt = now;
    await sleep(delayBeforeMs);
    logger.info(`improveProjectWithGemini: ${delayBeforeMs}ms 대기 후 실행`);

    const targetDir = getTargetProjectDir();
    logger.info(`improveProjectWithGemini: targetDir=${targetDir}`);

    const projectMdContent = readProjectMd(targetDir);

    if (!projectMdContent) {
      const prompt = buildSeedProjectPrompt(targetDir);
      const { response, saveDir } = await runGeminiInDir(targetDir, prompt);
      logger.info(
        `시딩 완료, 응답 길이=${response?.length ?? 0}, dir=${saveDir}`
      );
    } else {
      const fileList = listDirNames(targetDir);
      let newsContent = "";
      try {
        const newsList = await selectNews(9);
        newsContent = formatNewsList(newsList);
        logger.info(`뉴스 ${newsList.length}건 로드 → 개선 프롬프트에 반영`);
      } catch (e) {
        logger.warn("뉴스 로드 실패, 뉴스 없이 개선 진행", e?.message);
      }
      const prompt = buildImproveProjectPrompt(
        projectMdContent,
        targetDir,
        fileList,
        newsContent
      );
      const { response, saveDir } = await runGeminiInDir(targetDir, prompt);
      logger.info(
        `개선 완료, 응답 길이=${response?.length ?? 0}, dir=${saveDir}`
      );
    }

    const isTest = process.env.IS_TEST === "true";
    if (!isTest) {
      const batchFilePath = path.join(process.cwd(), "push.bat");
      runBatchScript(batchFilePath);
    } else {
      logger.info("IS_TEST=true, git push 생략");
    }
  } catch (error) {
    console.error("improveProjectWithGemini ERROR:", error);
    logger.error(`improveProjectWithGemini: ${error}`);
  }
}
