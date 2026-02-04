/** 하위 호환용 re-export. 신규 코드는 services/*, utils/date.js 직접 import 권장. */
import { openai } from "./config/openai.js";
import { fetchNews, formatNewsList } from "./services/news.js";
import { createSavePath, saveToFile } from "./services/file.js";
import { runBatchScript } from "./services/batch.js";
import { getyyyyMMdd } from "./utils/date.js";

async function generateCodeWithGpt(newsContent) {
  const { generateCodeWithGpt: fn } = await import("./services/gpt.js");
  return fn(openai, newsContent);
}

export {
  openai,
  fetchNews,
  formatNewsList,
  generateCodeWithGpt,
  createSavePath,
  saveToFile,
  runBatchScript,
  getyyyyMMdd,
};
