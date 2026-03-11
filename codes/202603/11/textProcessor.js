// textProcessor.js
// 텍스트 토큰화 및 정제 기능을 제공합니다.

/**
 * 텍스트를 문장 단위로 분리합니다.
 * @param {string} text - 처리할 원문 텍스트.
 * @returns {string[]} 문장 배열.
 */
function tokenizeSentences(text) {
    // 마침표, 물음표, 느낌표를 기준으로 문장을 분리합니다.
    // 각 문장의 시작과 끝의 공백을 제거합니다.
    // 빈 문자열은 필터링합니다.
    return text.split(/(?<=[.?!])\s+/).map(s => s.trim()).filter(s => s.length > 0);
}

/**
 * 문장을 단어 단위로 분리하고, 불용어 제거 및 소문자 변환 등의 정제 작업을 수행합니다.
 * @param {string} sentence - 처리할 문장.
 * @returns {string[]} 정제된 단어 배열.
 */
function tokenizeWords(sentence) {
    // 구두점 제거 및 소문자 변환 후 공백을 기준으로 단어를 분리합니다.
    // 불용어 목록은 간단하게 정의합니다. 실제 산업에서는 더 포괄적인 불용어 사전을 사용합니다.
    const stopwords = new Set(['은', '는', '이', '가', '을', '를', '에', '에서', '와', '과', '하다', '이다', '입니다', '하고', '그리고', '그러나', '하지만', '또는', '즉', '등', '등에', '같은', '같은데', '것', '것은', '것이', '것을', '수', '있는', '할', '합니다', '될', '되었습니다', '위해', '통해', '있습니다', '없습니다', '또한']);

    return sentence
        .replace(/[^\w\sㄱ-ㅎ가-힣]/g, '') // 알파벳, 숫자, 한글을 제외한 모든 문자 제거
        .toLowerCase() // 소문자 변환
        .split(/\s+/) // 공백 기준으로 분리
        .filter(word => word.length > 1 && !stopwords.has(word)); // 한 글자 단어 및 불용어 제거
}

module.exports = {
    tokenizeSentences,
    tokenizeWords
};
