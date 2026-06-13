// summarizer.js
// 뉴스 요약 기능을 제공합니다.
// 여기서는 간단한 단어 빈도 기반의 extractive summarization을 구현합니다.

/**
 * 단어 빈도를 계산합니다.
 * @param {string[][]} tokenizedSentences - 토큰화된 문장들의 배열 (각 문장은 단어 배열)
 * @returns {Map<string, number>} - 각 단어의 빈도를 담은 Map
 */
function calculateWordFrequencies(tokenizedSentences) {
    const frequencies = new Map();
    for (const sentenceTokens of tokenizedSentences) {
        for (const word of sentenceTokens) {
            frequencies.set(word, (frequencies.get(word) || 0) + 1);
        }
    }
    return frequencies;
}

/**
 * 각 문장의 중요도 점수를 계산합니다.
 * 점수는 문장에 포함된 단어들의 빈도 합으로 결정됩니다.
 * @param {string[]} sentences - 원본 문장 배열
 * @param {string[][]} tokenizedSentences - 토큰화된 문장들의 배열
 * @param {Map<string, number>} wordFrequencies - 단어 빈도 Map
 * @returns {Array<{sentence: string, score: number}>} - 문장과 점수 객체 배열
 */
function scoreSentences(sentences, tokenizedSentences, wordFrequencies) {
    return sentences.map((sentence, index) => {
        let score = 0;
        for (const word of tokenizedSentences[index]) {
            score += wordFrequencies.get(word) || 0;
        }
        return { sentence, score };
    });
}

/**
 * 주어진 텍스트에서 요약을 생성합니다.
 * @param {string[]} originalSentences - 원본 문장 배열
 * @param {string[][]} tokenizedSentences - 토큰화된 문장들의 배열
 * @param {number} summaryLength - 요약에 포함할 문장 수
 * @returns {string} - 요약된 텍스트
 */
function generateSummary(originalSentences, tokenizedSentences, summaryLength) {
    if (summaryLength <= 0 || summaryLength > originalSentences.length) {
        summaryLength = Math.max(1, Math.floor(originalSentences.length * 0.3)); // 기본 30% 요약
    }

    const wordFrequencies = calculateWordFrequencies(tokenizedSentences);
    const scoredSentences = scoreSentences(originalSentences, tokenizedSentences, wordFrequencies);

    // 점수가 높은 순서대로 정렬 (내림차순)
    scoredSentences.sort((a, b) => b.score - a.score);

    // 상위 N개 문장을 선택하고, 원본 순서대로 재정렬하여 요약문을 만듭니다.
    const topSentences = scoredSentences.slice(0, summaryLength);

    // 원본 순서를 유지하기 위해 원래 인덱스를 기반으로 정렬
    const summarySentences = originalSentences.filter(originalSentence =>
        topSentences.some(topSentence => topSentence.sentence === originalSentence)
    );

    return summarySentences.join(' ');
}

module.exports = {
    generateSummary
};
