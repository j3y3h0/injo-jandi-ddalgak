// textUtils.js

/**
 * 텍스트를 문장 단위로 분리하는 유틸리티 함수이다.
 * 간단한 구두점(., ?, !)을 기준으로 문장을 나눈다.
 *
 * @param {string} text - 분리할 원본 텍스트이다.
 * @returns {string[]} - 문장들의 배열이다.
 */
function splitIntoSentences(text) {
    // 마침표, 물음표, 느낌표 뒤에 공백이 오는 경우를 기준으로 분리한다.
    // 분리된 문장에서 앞뒤 공백을 제거한다.
    return text.split(/([.!?]\s*)/g)
               .filter(s => s.trim().length > 0)
               .map(s => s.trim())
               .reduce((acc, part, index, array) => {
                   if (/[.!?]/.test(part) && index > 0 && !/[.!?]/.test(array[index - 1])) {
                       acc[acc.length - 1] += part;
                   } else if (!/[.!?]/.test(part)) {
                       acc.push(part);
                   }
                   return acc;
               }, []);
}

/**
 * 텍스트를 단어 단위로 토큰화하는 유틸리티 함수이다.
 * 모든 구두점과 특수문자를 제거하고 소문자로 변환한다.
 *
 * @param {string} text - 토큰화할 원본 텍스트이다.
 * @returns {string[]} - 단어들의 배열이다.
 */
function tokenize(text) {
    // 모든 구두점과 특수문자를 제거하고 소문자로 변환한 후 공백을 기준으로 나눈다.
    return text.toLowerCase()
               .replace(/[^a-z가-힣\s]/g, '') // 알파벳, 한글, 공백 제외 모두 제거
               .split(/\s+/)
               .filter(word => word.length > 0);
}

// 간단한 불용어 리스트 (예시). 실제 프로젝트에서는 더 광범위한 리스트를 사용한다.
const STOP_WORDS = new Set([
    '은', '는', '이', '가', '을', '를', '에', '에서', '와', '과', '도', '만', '으로', '고', '다', '습니다', '이다', '것', '수', '점', '때', '같은', '있습니다'
]);

/**
 * 불용어를 제거하는 유틸리티 함수이다.
 *
 * @param {string[]} words - 단어들의 배열이다.
 * @returns {string[]} - 불용어가 제거된 단어들의 배열이다.
 */
function removeStopWords(words) {
    return words.filter(word => !STOP_WORDS.has(word));
}

module.exports = {
    splitIntoSentences,
    tokenize,
    removeStopWords
};
