/**
 * @file textProcessor.js
 * @description 텍스트 데이터의 분석 및 요약을 위한 핵심 로직을 포함하는 파일입니다.
 */

const { cleanText } = require('./utils');

/**
 * 텍스트 내 단어 빈도를 분석합니다.
 * 텍스트를 정제하고 단어별 출현 횟수를 계산하여 반환합니다.
 *
 * @param {string} text - 분석할 원본 텍스트.
 * @returns {Object<string, number>} 각 단어와 그 단어의 빈도를 포함하는 객체.
 */
function analyzeText(text) {
    const cleanedText = cleanText(text);
    // 텍스트를 공백 기준으로 단어로 분리합니다.
    const words = cleanedText.split(/\s+/).filter(word => word.length > 0);

    const wordFrequency = {};
    for (const word of words) {
        wordFrequency[word] = (wordFrequency[word] || 0) + 1;
    }
    return wordFrequency;
}

/**
 * 텍스트를 기본적인 방식으로 요약합니다.
 * 현재는 텍스트의 첫 N개 문장을 추출하여 반환합니다.
 *
 * @param {string} text - 요약할 원본 텍스트.
 * @param {number} [sentenceCount=3] - 추출할 문장의 개수. 기본값은 3입니다.
 * @returns {string} 요약된 텍스트.
 */
function summarizeText(text, sentenceCount = 3) {
    // 문장 구분을 위해 마침표, 물음표, 느낌표를 사용합니다.
    // 한국어 문장 구분을 위해 정규식 패턴을 조정했습니다.
    const sentences = text.split(/(?<=[.?!])\s+/).filter(s => s.trim().length > 0);

    if (sentences.length === 0) {
        return "";
    }

    // 요청된 문장 개수만큼 추출합니다.
    const summarySentences = sentences.slice(0, Math.min(sentenceCount, sentences.length));
    return summarySentences.join(' ');
}

module.exports = {
    analyzeText,
    summarizeText
};
