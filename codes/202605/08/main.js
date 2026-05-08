// main.js
// 애플리케이션의 진입점입니다.

// 샘플 리뷰 데이터를 불러옵니다.
const sampleReviews = require('./data.js');
// 데이터 처리 모듈을 불러옵니다.
const { processReviews } = require('./dataProcessor.js');

console.log("=== 상품 리뷰 감성 분석 시작 ===");

// 샘플 리뷰 데이터를 처리합니다.
const analyzedResults = processReviews(sampleReviews);

// 분석 결과를 출력합니다.
analyzedResults.forEach(result => {
    console.log(`
리뷰 ID: ${result.id}`);
    console.log(`리뷰 내용: "${result.review}"`);
    console.log(`감성 분석 결과: ${result.sentiment}`);
});

console.log("
=== 상품 리뷰 감성 분석 완료 ===");
