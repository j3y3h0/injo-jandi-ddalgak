// summarizer.js

const { tokenize, removeStopWords, splitIntoSentences } = require('./textUtils');

/**
 * 텍스트 요약을 위한 Summarizer 클래스이다.
 * 단어 빈도수를 기반으로 문장의 중요도를 평가하여 요약문을 생성한다.
 */
class Summarizer {
    constructor() {
        this.wordFrequencies = new Map(); // 단어별 빈도수를 저장한다.
    }

    /**
     * 주어진 텍스트에서 단어 빈도수를 계산한다.
     * 불용어를 제거하고 각 단어의 출현 횟수를 센다.
     *
     * @param {string} text - 단어 빈도수를 계산할 원본 텍스트이다.
     */
    _calculateWordFrequencies(text) {
        this.wordFrequencies.clear(); // 이전 요약의 빈도수 초기화
        const words = tokenize(text);
        const filteredWords = removeStopWords(words);

        filteredWords.forEach(word => {
            this.wordFrequencies.set(word, (this.wordFrequencies.get(word) || 0) + 1);
        });
    }

    /**
     * 각 문장의 중요도 점수를 계산한다.
     * 문장 내 단어들의 빈도수를 합산하여 점수를 매긴다.
     *
     * @param {string[]} sentences - 문장들의 배열이다.
     * @returns {Array<Object>} - 각 문장과 해당 점수를 포함하는 객체 배열이다.
     */
    _scoreSentences(sentences) {
        const scoredSentences = [];
        sentences.forEach((sentence, index) => {
            const words = tokenize(sentence);
            const filteredWords = removeStopWords(words);
            let score = 0;
            filteredWords.forEach(word => {
                score += this.wordFrequencies.get(word) || 0;
            });
            scoredSentences.push({ sentence, score, originalIndex: index });
        });
        return scoredSentences;
    }

    /**
     * 텍스트를 요약한다.
     *
     * @param {string} text - 요약할 원본 텍스트이다.
     * @param {number} sentenceCount - 요약문에 포함할 문장의 수이다.
     * @returns {string} - 요약된 텍스트이다.
     */
    summarize(text, sentenceCount) {
        // 1. 단어 빈도수 계산
        this._calculateWordFrequencies(text);

        // 2. 텍스트를 문장으로 분리
        const sentences = splitIntoSentences(text);

        // 3. 각 문장의 중요도 점수 계산
        const scoredSentences = this._scoreSentences(sentences);

        // 4. 점수가 높은 문장들을 선택
        scoredSentences.sort((a, b) => b.score - a.score); // 점수 기준으로 내림차순 정렬

        // 요청된 문장 수만큼 선택 (원본 순서 유지를 위해 인덱스 저장)
        const topSentences = scoredSentences.slice(0, sentenceCount);

        // 5. 선택된 문장들을 원본 텍스트 순서대로 정렬하여 요약문 생성
        topSentences.sort((a, b) => a.originalIndex - b.originalIndex);

        return topSentences.map(item => item.sentence).join(' ');
    }
}

module.exports = Summarizer;
