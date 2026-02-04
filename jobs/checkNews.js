import { selectNews } from "../services/newsDb.js";
import { formatNewsList } from "../services/news.js";

const NEWS_LIMIT = 9;

async function showTodayNews() {
  try {
    console.log("🔍 최신 뉴스를 검색 중입니다...");
    const newsList = await selectNews(NEWS_LIMIT);
    const formattedNews = formatNewsList(newsList);

    console.log("\n📰 [오늘의 최신 뉴스 이슈]");
    console.log(formattedNews);
  } catch (error) {
    console.error("❌ 뉴스 가져오기 실패:", error);
  }
}

showTodayNews();
