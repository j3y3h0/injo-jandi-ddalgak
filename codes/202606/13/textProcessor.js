// textProcessor.js
// 텍스트 전처리 기능을 제공합니다.

// 간단한 한국어 불용어 목록입니다.
// 실제 프로젝트에서는 더 풍부한 불용어 사전을 사용해야 합니다.
const stopWords = new Set(['은', '는', '이', '가', '을', '를', '과', '와', '로', '으로', '에', '에서', '의', '다', '습니다', '고', '하다', '되고', '있다', '것', '수', '점']);

/**
 * 텍스트를 문장 단위로 분리합니다.
 * @param {string} text - 원본 텍스트
 * @returns {string[]} - 문장 배열
 */
function splitIntoSentences(text) {
    // 마침표, 물음표, 느낌표를 기준으로 문장을 분리합니다.
    // 한국어 문장의 특성을 고려하여 뒤에 공백이 있는 경우를 포함합니다.
    return text.split(/(?<=[.?!])\s+/).filter(sentence => sentence.trim().length > 0);
}

/**
 * 문장을 단어 토큰으로 분리하고 전처리합니다.
 * - 소문자 변환 (불필요, 한국어는 해당 없음)
 * - 구두점 제거
 * - 불용어 제거
 * @param {string} sentence - 원본 문장
 * @returns {string[]} - 전처리된 단어 배열
 */
function tokenizeAndClean(sentence) {
    // 구두점 및 특수문자 제거
    const cleanedSentence = sentence.replace(/[.,/#!$%^&*;:{}=\-_`~()]/g, "")
                                    .replace(/\s{2,}/g, " ") // 여러 공백을 하나로
                                    .trim();

    // 단어를 공백 기준으로 분리
    const words = cleanedSentence.split(' ').filter(word => word.length > 0);

    // 불용어 제거
    return words.filter(word => !stopWords.has(word));
}

module.exports = {
    splitIntoSentences,
    tokenizeAndClean
};
