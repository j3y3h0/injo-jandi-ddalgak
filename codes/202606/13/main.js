// main.js
// 뉴스 요약 애플리케이션의 주 진입점입니다.

const { newsArticle } = require('./newsData');
const { splitIntoSentences, tokenizeAndClean } = require('./textProcessor');
const { generateSummary } = require('./summarizer');

function main() {
    console.log("--- 원본 뉴스 기사 ---");
    console.log(newsArticle.trim());
    console.log("
");

    // 1. 텍스트를 문장으로 분리
    const sentences = splitIntoSentences(newsArticle);
    console.log("--- 문장 분리 결과 ---");
    sentences.forEach((s, i) => console.log(`${i + 1}: ${s}`));
    console.log("
");

    // 2. 각 문장을 토큰화하고 전처리
    const tokenizedSentences = sentences.map(sentence => tokenizeAndClean(sentence));
    console.log("--- 토큰화 및 전처리 결과 (일부) ---");
    tokenizedSentences.slice(0, 3).forEach((tokens, i) => console.log(`${i + 1}: ${tokens.join(', ')}`));
    console.log("
");


    // 3. 요약 생성 (예: 2개 문장으로 요약)
    const summaryLength = 2; // 요약할 문장 수
    const summary = generateSummary(sentences, tokenizedSentences, summaryLength);

    console.log(`--- 뉴스 요약 (상위 ${summaryLength} 문장) ---`);
    console.log(summary.trim());
    console.log("
--- 요약 끝 ---");
}

// 애플리케이션 실행
main();
