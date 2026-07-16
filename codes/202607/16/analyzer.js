// analyzer.js
// 데이터 분석을 담당하는 모듈입니다.
// 간단한 통계 분석 (평균, 최소, 최대)을 수행합니다.

/**
 * 주어진 데이터 배열에서 특정 키의 평균을 계산합니다.
 * @param {Array<Object>} data 분석할 데이터 배열.
 * @param {string} key 평균을 계산할 데이터 속성 키.
 * @returns {number} 계산된 평균값.
 */
function calculateAverage(data, key) {
  if (!data || data.length === 0) {
    return 0;
  }
  const sum = data.reduce((acc, item) => acc + (item[key] || 0), 0);
  return sum / data.length;
}

/**
 * 주어진 데이터 배열에서 특정 키의 최소값을 찾습니다.
 * @param {Array<Object>} data 분석할 데이터 배열.
 * @param {string} key 최소값을 찾을 데이터 속성 키.
 * @returns {number} 최소값.
 */
function findMinimum(data, key) {
  if (!data || data.length === 0) {
    return null;
  }
  return Math.min(...data.map(item => item[key]).filter(val => typeof val === 'number'));
}

/**
 * 주어진 데이터 배열에서 특정 키의 최대값을 찾습니다.
 * @param {Array<Object>} data 분석할 데이터 배열.
 * @param {string} key 최대값을 찾을 데이터 속성 키.
 * @returns {number} 최대값.
 */
function findMaximum(data, key) {
  if (!data || data.length === 0) {
    return null;
  }
  return Math.max(...data.map(item => item[key]).filter(val => typeof val === 'number'));
}

module.exports = {
  calculateAverage,
  findMinimum,
  findMaximum,
};
