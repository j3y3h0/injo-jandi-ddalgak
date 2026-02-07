import fs from "fs";
import path from "path";
import logger from "../logger.js";
import { selectNews } from "../services/newsDb.js";
import { formatNewsList } from "../services/news.js";
import { generateCodeWithGemini } from "../services/gemini.js";
import { getSaveDir } from "../services/file.js";
import { runBatchScript } from "../services/batch.js";

const NEWS_LIMIT = 9;

/** DB 최신 뉴스 → Gemini CLI 코드 생성(해당 경로에 직접 파일 생성) → 푸시까지 오케스트레이션. */
export async function saveRandomCodeDiary() {
  try {
    const newsList = await selectNews(NEWS_LIMIT);
    const newsContent = formatNewsList(newsList);
    logger.info(`뉴스 본문 => \n ${newsContent}`);

    const saveDir = getSaveDir();
    const { response, saveDir: dir } = await generateCodeWithGemini(
      newsContent,
      saveDir
    );
    logger.info(`Gemini 응답 길이 => ${response?.length ?? 0}`);
    const files = fs.readdirSync(dir, { withFileTypes: true });
    const names = files.map((f) => f.name).join(", ");
    logger.info(`코드 다이어리 디렉터리 파일 목록: ${names}`);

    const isTest = process.env.IS_TEST === "true";
    if (!isTest) {
      const batchFilePath = path.join(process.cwd(), "push.bat");
      runBatchScript(batchFilePath);
    } else {
      logger.info("IS_TEST=true, git push 생략");
    }
  } catch (error) {
    console.log("saveRandomCodeDiary ERROR: ", error);
    logger.error(`saveRandomCodeDiary: ${error}`);
  }
}
