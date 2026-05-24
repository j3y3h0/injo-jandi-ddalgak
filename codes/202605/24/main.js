// main.js
// 사용자 리뷰 감성 분석 애플리케이션의 메인 진입점이다.

const { loadSampleReviews } = require('./dataLoader');
const { analyzeSentiment } = require('./sentimentAnalyzer');

/**
 * 애플리케이션을 실행하는 메인 함수이다.
 */
function runSentimentAnalysis() {
  console.log("사용자 리뷰 감성 분석을 시작합니다.
");

  const reviews = loadSampleReviews();

  reviews.forEach((review, index) => {
    const sentiment = analyzeSentiment(review);
    console.log(`[리뷰 ${index + 1}]`);
    console.log(`  텍스트: "${review}"`);
    console.log(`  감성: ${sentiment}
`);
  });

  console.log("감성 분석이 완료되었습니다.");
}

// 애플리케이션 실행
runSentimentAnalysis();
