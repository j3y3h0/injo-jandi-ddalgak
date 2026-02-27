// dataAnalyzer.js
// 숫자 데이터 배열을 받아 기본적인 통계 지표를 계산하는 유틸리티.

/**
 * 숫자 배열의 평균을 계산한다.
 * @param {Array<number>} numbers 숫자 배열.
 * @returns {number} 평균값.
 */
function calculateMean(numbers) {
    if (numbers.length === 0) {
        return 0;
    }
    const sum = numbers.reduce((acc, current) => acc + current, 0);
    return sum / numbers.length;
}

/**
 * 숫자 배열의 중앙값을 계산한다.
 * @param {Array<number>} numbers 숫자 배열.
 * @returns {number} 중앙값.
 */
function calculateMedian(numbers) {
    if (numbers.length === 0) {
        return 0;
    }
    const sortedNumbers = [...numbers].sort((a, b) => a - b);
    const mid = Math.floor(sortedNumbers.length / 2);
    if (sortedNumbers.length % 2 === 0) {
        return (sortedNumbers[mid - 1] + sortedNumbers[mid]) / 2;
    } else {
        return sortedNumbers[mid];
    }
}

/**
 * 숫자 배열의 최댓값을 찾는다.
 * @param {Array<number>} numbers 숫자 배열.
 * @returns {number} 최댓값.
 */
function findMax(numbers) {
    if (numbers.length === 0) {
        return 0; // 또는 -Infinity를 반환할 수도 있음
    }
    return Math.max(...numbers);
}

/**
 * 숫자 배열의 최솟값을 찾는다.
 * @param {Array<number>} numbers 숫자 배열.
 * @returns {number} 최솟값.
 */
function findMin(numbers) {
    if (numbers.length === 0) {
        return 0; // 또는 +Infinity를 반환할 수도 있음
    }
    return Math.min(...numbers);
}

module.exports = {
    calculateMean,
    calculateMedian,
    findMax,
    findMin
};
