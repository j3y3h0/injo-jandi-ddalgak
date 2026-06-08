// dataProcessor.js
// 데이터 처리 로직을 담고 있는 유틸리티 파일입니다.

/**
 * 주어진 데이터 배열에서 특정 카테고리에 해당하는 항목만 필터링합니다.
 * @param {Array<Object>} data - 처리할 데이터 배열입니다. 각 객체는 'category' 속성을 가져야 합니다.
 * @param {string} category - 필터링할 카테고리입니다.
 * @returns {Array<Object>} 필터링된 데이터 배열입니다.
 */
function filterByCategory(data, category) {
  // 입력된 데이터가 배열인지 확인합니다.
  if (!Array.isArray(data)) {
    console.error("오류: 데이터는 배열이어야 합니다.");
    return [];
  }
  // 카테고리 필터링을 수행하고 결과를 반환합니다.
  return data.filter(item => item.category === category);
}

/**
 * 주어진 데이터 배열의 'value' 속성을 합산합니다.
 * @param {Array<Object>} data - 처리할 데이터 배열입니다. 각 객체는 숫자형 'value' 속성을 가져야 합니다.
 * @returns {number} 합산된 총 가치입니다.
 */
function aggregateValues(data) {
  // 입력된 데이터가 배열인지 확인합니다.
  if (!Array.isArray(data)) {
    console.error("오류: 데이터는 배열이어야 합니다.");
    return 0;
  }
  // 'value' 속성을 합산하고 결과를 반환합니다.
  return data.reduce((sum, item) => sum + item.value, 0);
}

// 모듈로 내보내어 다른 파일에서 사용할 수 있도록 합니다.
module.exports = {
  filterByCategory,
  aggregateValues
};
