import logger from "../logger.js";
import { selectNews } from "../services/newsDb.js";
import { formatNewsList } from "../services/news.js";
import { generateCodeWithGemini } from "../services/gemini.js";
import { createSavePath, saveToFile } from "../services/file.js";
import { runBatchScript } from "../services/batch.js";

const NEWS_LIMIT = 9;

/** DB 최신 뉴스 → Gemini CLI 코드 생성 → 저장 → 푸시까지의 흐름만 오케스트레이션. 단일 책임: 작업 순서 조합. */
export async function saveRandomCodeDiary() {
  try {
    const newsList = await selectNews(NEWS_LIMIT);
    const newsContent = formatNewsList(newsList);
    logger.info(`newsContent: \n ${newsContent}`);

    const generatedCode = await generateCodeWithGemini(newsContent);
    const filePath = createSavePath();
    saveToFile(filePath, generatedCode);

    const batchFilePath = `${process.cwd()}/push.bat`;
    runBatchScript(batchFilePath);
  } catch (error) {
    console.log("saveRandomCodeDiary ERROR: ", error);
    logger.error(`saveRandomCodeDiary: ${error}`);
  }
}
