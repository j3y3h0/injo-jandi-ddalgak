import axios from "axios";

/** 뉴스 API에서 데이터 수집만 담당. */
export async function fetchNews(apiUrl) {
  try {
    const response = await axios.get(apiUrl);
    return response.data.results;
  } catch (error) {
    console.error("Error fetching news:", error);
    throw error;
  }
}

/** 뉴스 배열을 다이어리용 문자열로 포맷. 출력 형식 변경 시 이 함수만 수정. */
export function formatNewsList(newsList) {
  return newsList
    .map((news, index) => {
      const title = `${news.title}\n`;
      const subTitle = news.sub_title ? `- ${news.sub_title}\n\n` : "\n";
      return `${index + 1}. ${title}${subTitle}`;
    })
    .join("");
}
