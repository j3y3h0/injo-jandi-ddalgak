/**
 * @file utils.js
 * @description 텍스트 데이터 처리에 사용되는 유틸리티 함수들을 모아 놓은 파일입니다.
 */

/**
 * 텍스트를 정제합니다.
 * 구두점을 제거하고 모든 문자를 소문자로 변환합니다.
 *
 * @param {string} text - 정제할 원본 텍스트.
 * @returns {string} 정제된 텍스트.
 */
function cleanText(text) {
    // 텍스트에서 모든 구두점을 제거합니다.
    // 영문 알파벳, 한글, 숫자, 공백을 제외한 모든 문자를 제거하는 정규식
    const cleaned = text.replace(/[^\p{L}\p{N}\s]/gu, '');
    // 모든 문자를 소문자로 변환합니다.
    return cleaned.toLowerCase();
}

module.exports = {
    cleanText
};
