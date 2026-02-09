// anomalyDetector.js

/**
 * 주어진 현재 값과 이동 평균을 비교하여 이상 징후를 탐지하는 함수입니다.
 *
 * @param {number} currentValue 현재 센서 데이터 값
 * @param {number} movingAverage 계산된 이동 평균 값
 * @param {number} threshold 이상 징후로 판단할 임계값 (절대 차이)
 * @returns {boolean} 이상 징후가 탐지되었으면 true, 아니면 false
 */
function detectAnomaly(currentValue, movingAverage, threshold) {
    // 현재 값과 이동 평균 간의 절대 차이가 임계값을 초과하는지 확인합니다.
    return Math.abs(currentValue - movingAverage) > threshold;
}

// 모듈 외부로 함수를 노출합니다.
module.exports = {
    detectAnomaly
};
