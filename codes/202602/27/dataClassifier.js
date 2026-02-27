// dataClassifier.js
// 간단한 규칙 기반으로 데이터를 분류하는 유틸리티.

/**
 * 주어진 값을 임계값을 기준으로 분류한다.
 * @param {number} value 분류할 숫자 값.
 * @param {Object} thresholds 임계값 객체. 예: { high: 80, low: 20 }.
 * @returns {string} 분류 결과 ('높음', '중간', '낮음').
 */
function classifyByThresholds(value, thresholds) {
    const { high, low } = thresholds;

    if (value >= high) {
        return '높음';
    } else if (value <= low) {
        return '낮음';
    } else {
        return '중간';
    }
}

module.exports = {
    classifyByThresholds
};
