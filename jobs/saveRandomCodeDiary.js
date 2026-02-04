import path from "path";
import logger from "../logger.js";
import { selectNews } from "../services/newsDb.js";
import { formatNewsList } from "../services/news.js";
import { generateCodeWithGemini } from "../services/gemini.js";
import {
  getSaveDir,
  saveCodeFilesFromResponse,
  saveToFile,
} from "../services/file.js";
import { runBatchScript } from "../services/batch.js";

const NEWS_LIMIT = 9;

/** DB 최신 뉴스 → Gemini CLI 코드 생성 → 저장 → 푸시까지의 흐름만 오케스트레이션. 단일 책임: 작업 순서 조합. */
export async function saveRandomCodeDiary() {
  try {
    const newsList = await selectNews(NEWS_LIMIT);
    const newsContent = formatNewsList(newsList);
    logger.info(`뉴스 본문 => \n ${newsContent}`);

    const generatedCode = await generateCodeWithGemini(newsContent);
    logger.info(`생성 코드 => \n ${generatedCode}`);

    const saveDir = getSaveDir();
    const written = saveCodeFilesFromResponse(saveDir, generatedCode);
    if (written && written.length > 0) {
      logger.info(
        `코드 다이어리 저장: ${written.length}개 파일 - ${written.join(", ")}`
      );
    } else {
      const readmePath = path.join(saveDir, "README.md");
      saveToFile(readmePath, generatedCode);
      logger.info(
        `코드 다이어리: 파싱된 섹션 없음, README.md 단일 파일로 저장`
      );
    }

    const batchFilePath = `${process.cwd()}/push.bat`;
    runBatchScript(batchFilePath);
  } catch (error) {
    console.log("saveRandomCodeDiary ERROR: ", error);
    logger.error(`saveRandomCodeDiary: ${error}`);
  }
}
