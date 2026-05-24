// sentimentAnalyzer.js
// 텍스트의 감성을 분석하는 모듈이다.
// 'natural' 라이브러리를 사용하려 했으나, 한국어 감성 분석을 위한 복잡한 설정 없이
// 실행 가능한 예시를 위해 간단한 키워드 기반 감성 분석을 구현한다.
// 실제 산업에서는 훈련된 AI/ML 모델(예: BERT, KoBERT 등)을 활용한다.

/**
 * 텍스트의 감성을 분석하여 '긍정', '부정', '중립' 중 하나를 반환한다.
 * 매우 기본적인 키워드 매칭 방식을 사용한다.
 * @param {string} text - 분석할 텍스트 문자열
 * @returns {'긍정' | '부정' | '중립'} - 분석된 감성
 */
function analyzeSentiment(text) {
  const positiveKeywords = ["훌륭합니다", "만족합니다", "좋습니다", "추천합니다", "최고예요", "추천", "개선"];
  const negativeKeywords = ["실망스럽습니다", "좋지 않습니다", "아까워요", "별로입니다", "문제", "나쁘지 않지만"];

  let positiveScore = 0;
  let negativeScore = 0;

  // 긍정 키워드 검색
  positiveKeywords.forEach(keyword => {
    if (text.includes(keyword)) {
      positiveScore++;
    }
  });

  // 부정 키워드 검색
  negativeKeywords.forEach(keyword => {
    if (text.includes(keyword)) {
      negativeScore++;
    }
  });

  if (positiveScore > negativeScore) {
    return '긍정';
  } else if (negativeScore > positiveScore) {
    return '부정';
  } else {
    // 점수가 같거나, 아무 키워드도 발견되지 않은 경우
    return '중립';
  }
}

module.exports = {
  analyzeSentiment
};

/*
// [참고] 'natural' 라이브러리를 사용한 영어 감성 분석 예시 (한국어는 별도 모델 필요)
const natural = require('natural');
const Analyzer = natural.SentimentAnalyzer;
const stemmer = natural.PorterStemmer;
const analyzer = new Analyzer("English", stemmer, "afinn");

function analyzeEnglishSentiment(text) {
  // 텍스트를 토큰화한 후 감성 점수 계산
  const tokens = new natural.WordTokenizer().tokenize(text);
  const sentimentScore = analyzer.getSentiment(tokens);

  if (sentimentScore > 0.5) {
    return '긍정';
  } else if (sentimentScore < -0.5) {
    return '부정';
  } else {
    return '중립';
  }
}
*/
