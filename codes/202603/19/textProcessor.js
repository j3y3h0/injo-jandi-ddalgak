// textProcessor.js

const { cleanText } = require('./utils'); // utils.js에서 cleanText 함수를 가져온다.

/**
 * 텍스트를 분석하여 단어 빈도수와 총 단어 수를 반환하는 함수이다.
 * @param {string} text - 분석할 입력 텍스트이다.
 * @returns {{wordCount: number, wordFrequencies: Object}} - 총 단어 수와 각 단어의 빈도수를 포함하는 객체이다.
 */
function analyzeText(text) {
    // 텍스트를 정제한다.
    const cleaned = cleanText(text);

    // 정제된 텍스트를 공백 기준으로 단어로 분리한다.
    // 빈 문자열은 필터링하여 유효한 단어만 남긴다.
    const words = cleaned.split(' ').filter(word => word.length > 0);

    // 단어 빈도를 저장할 객체를 초기화한다.
    const wordFrequencies = {};
    // 총 단어 수를 계산한다.
    const totalWordCount = words.length;

    // 각 단어의 빈도를 계산한다.
    for (const word of words) {
        wordFrequencies[word] = (wordFrequencies[word] || 0) + 1;
    }

    return {
        wordCount: totalWordCount,
        wordFrequencies: wordFrequencies
    };
}

/**
 * 단어 빈도수 맵에서 가장 빈번하게 나오는 단어를 찾아 반환하는 함수이다.
 * @param {Object} wordFrequencies - 단어와 그 빈도수를 매핑한 객체이다.
 * @param {number} topN - 반환할 상위 N개의 단어이다.
 * @returns {Array<{word: string, frequency: number}>} - 빈도수가 높은 순서대로 정렬된 단어 객체의 배열이다.
 */
function getTopNWords(wordFrequencies, topN = 5) {
    // 단어 빈도수 객체를 배열로 변환하고 빈도수를 기준으로 내림차순 정렬한다.
    const sortedWords = Object.entries(wordFrequencies)
        .map(([word, frequency]) => ({ word, frequency }))
        .sort((a, b) => b.frequency - a.frequency);

    // 상위 N개의 단어만 반환한다.
    return sortedWords.slice(0, topN);
}


// 다른 파일에서 analyzeText와 getTopNWords 함수를 사용할 수 있도록 내보낸다.
module.exports = {
    analyzeText,
    getTopNWords
};
