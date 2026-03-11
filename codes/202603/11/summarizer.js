// summarizer.js
// 텍스트 요약 기능을 제공합니다.

const { tokenizeSentences, tokenizeWords } = require('./textProcessor');

/**
 * 주어진 텍스트에서 단어 빈도수를 계산합니다.
 * @param {string[]} words - 단어 배열.
 * @returns {Map<string, number>} 각 단어의 빈도수를 담은 Map 객체.
 */
function calculateWordFrequencies(words) {
    const wordFrequencies = new Map();
    for (const word of words) {
        wordFrequencies.set(word, (wordFrequencies.get(word) || 0) + 1);
    }
    return wordFrequencies;
}

/**
 * 각 문장의 중요도를 점수화합니다.
 * 중요도는 문장 내의 단어들이 전체 텍스트에서 얼마나 자주 등장하는지에 비례합니다.
 * @param {string[]} sentences - 원문 텍스트에서 분리된 문장 배열.
 * @param {Map<string, number>} wordFrequencies - 전체 텍스트의 단어 빈도수.
 * @returns {Map<string, number>} 각 문장과 그 점수를 담은 Map 객체.
 */
function scoreSentences(sentences, wordFrequencies) {
    const sentenceScores = new Map();
    for (const sentence of sentences) {
        const wordsInSentence = tokenizeWords(sentence);
        let score = 0;
        for (const word of wordsInSentence) {
            score += wordFrequencies.get(word) || 0; // 문장 내 단어의 전체 텍스트 빈도수 합산
        }
        sentenceScores.set(sentence, score);
    }
    return sentenceScores;
}

/**
 * 텍스트를 요약합니다.
 * 가장 높은 점수를 받은 문장들을 선택하여 요약을 생성합니다.
 * @param {string} text - 요약할 원문 텍스트.
 * @param {number} numSentences - 요약에 포함할 문장의 수.
 * @returns {string} 요약된 텍스트.
 */
function getSummary(text, numSentences) {
    const sentences = tokenizeSentences(text);
    if (sentences.length <= numSentences) {
        return text; // 원문이 요약 문장 수보다 적으면 원문 전체 반환
    }

    const allWords = sentences.flatMap(sentence => tokenizeWords(sentence));
    const wordFrequencies = calculateWordFrequencies(allWords);
    const sentenceScores = scoreSentences(sentences, wordFrequencies);

    // 점수를 기준으로 문장을 내림차순 정렬
    const sortedSentences = Array.from(sentenceScores.entries())
        .sort((a, b) => b[1] - a[1]) // 점수가 높은 문장이 앞으로 오도록 정렬
        .slice(0, numSentences) // 지정된 수의 문장만 선택
        .map(entry => entry[0]); // 문장 텍스트만 추출

    // 원문에서의 순서를 유지하기 위해 원래 문장 순서로 다시 정렬
    const originalOrderSummary = sentences.filter(s => sortedSentences.includes(s));

    return originalOrderSummary.join(' ');
}

module.exports = {
    getSummary
};
