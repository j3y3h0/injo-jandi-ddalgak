// utils.js

/**
 * 텍스트를 정제하는 함수이다.
 * 모든 텍스트를 소문자로 변환하고, 알파벳 문자와 공백을 제외한 모든 문자를 제거한다.
 * @param {string} text - 정제할 입력 텍스트이다.
 * @returns {string} - 정제된 텍스트이다.
 */
function cleanText(text) {
    // 모든 텍스트를 소문자로 변환한다.
    let cleaned = text.toLowerCase();
    // 알파벳 문자(a-z)와 공백을 제외한 모든 문자를 제거한다.
    cleaned = cleaned.replace(/[^a-z\s]/g, '');
    // 여러 개의 공백을 하나의 공백으로 바꾼다.
    cleaned = cleaned.replace(/\s+/g, ' ').trim();
    return cleaned;
}

// 다른 파일에서 cleanText 함수를 사용할 수 있도록 내보낸다.
module.exports = {
    cleanText
};
