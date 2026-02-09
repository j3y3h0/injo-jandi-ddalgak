// dataProcessor.js

/**
 * 주어진 숫자 배열의 이동 평균을 계산하는 함수입니다.
 *
 * @param {number[]} dataArray 이동 평균을 계산할 숫자 배열
 * @returns {number} 계산된 이동 평균
 */
function calculateMovingAverage(dataArray) {
    if (!Array.isArray(dataArray) || dataArray.length === 0) {
        return 0; // 또는 오류 처리
    }

    const sum = dataArray.reduce((acc, val) => acc + val, 0);
    return sum / dataArray.length;
}

// 모듈 외부로 함수를 노출합니다.
module.exports = {
    calculateMovingAverage
};
