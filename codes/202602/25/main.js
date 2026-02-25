/**
 * @file main.js
 * @description 텍스트 분석 및 요약 도구의 메인 실행 파일입니다.
 *              샘플 텍스트를 사용하여 텍스트 처리 기능을 시연합니다.
 */

const { analyzeText, summarizeText } = require('./textProcessor');

// 분석 및 요약할 샘플 텍스트입니다. 실제 뉴스 기사나 긴 문서를 대신할 수 있습니다.
const sampleText = `
최근 인공지능 기술의 발전은 여러 산업 분야에 혁신적인 변화를 가져오고 있습니다.
특히 자연어 처리(NLP) 분야에서는 대규모 언어 모델(LLM)의 등장이 정보 검색, 번역, 텍스트 생성 등 다양한 응용 분야에서 놀라운 성능을 보여주고 있습니다.
이는 비즈니스 효율성을 높이고 새로운 서비스 창출을 가능하게 하며, 사용자 경험을 개선하는 데 크게 기여하고 있습니다.
하지만 이러한 기술 발전과 함께 데이터 프라이버시, 알고리즘 편향성, 그리고 일자리 대체와 같은 사회적 문제에 대한 논의도 활발하게 이루어지고 있습니다.
따라서 기술 개발자들과 정책 입안자들은 이러한 도전을 해결하기 위한 균형 잡힌 접근 방식을 모색해야 할 것입니다.
미래 사회에서 인공지능이 긍정적인 영향을 미치기 위해서는 기술적 진보뿐만 아니라 윤리적, 사회적 고려가 필수적입니다.
`;

console.log("--- 텍스트 분석 및 요약 도구 ---");
console.log("
원본 텍스트:");
console.log(sampleText);

// 1. 단어 빈도 분석
console.log("
--- 단어 빈도 분석 결과 ---");
const wordFrequencies = analyzeText(sampleText);
// 빈도수 기준으로 정렬하여 상위 N개 단어만 출력합니다.
const sortedWords = Object.entries(wordFrequencies).sort(([, freqA], [, freqB]) => freqB - freqA);
console.log("상위 10개 단어:");
for (let i = 0; i < Math.min(10, sortedWords.length); i++) {
    console.log(`- ${sortedWords[i][0]}: ${sortedWords[i][1]}회`);
}

// 2. 텍스트 요약
console.log("
--- 텍스트 요약 결과 (상위 3문장) ---");
const summary = summarizeText(sampleText, 3);
console.log(summary);

console.log("
--- 작업 완료 ---");
