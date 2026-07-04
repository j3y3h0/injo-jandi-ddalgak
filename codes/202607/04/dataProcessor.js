/**
 * 특정 조건에 따라 데이터 배열을 필터링한다.
 * @param {Array<Object>} data - 필터링할 객체 배열.
 * @param {string} key - 필터링 기준으로 사용할 속성(키).
 * @param {*} value - 필터링 기준으로 사용할 값.
 * @returns {Array<Object>} - 필터링된 객체 배열.
 */
function filterByCondition(data, key, value) {
    // 배열의 각 항목을 순회하며 조건에 맞는 항목만 필터링한다.
    return data.filter(item => item[key] === value);
}

/**
 * 데이터 배열의 각 항목에 변환 함수를 적용한다.
 * @param {Array<Object>} data - 변환할 객체 배열.
 * @param {Function} transformationFunction - 각 항목에 적용할 변환 함수. (item => newItem) 형태.
 * @returns {Array<Object>} - 변환된 객체 배열.
 */
function transformData(data, transformationFunction) {
    // 배열의 각 항목에 대해 변환 함수를 적용하고 새로운 배열을 반환한다.
    return data.map(transformationFunction);
}

/**
 * 특정 키를 기준으로 데이터를 집계한다 (예: 합계).
 * 이 함수는 숫자 값을 가진 키에 대한 합계를 기본으로 한다.
 * @param {Array<Object>} data - 집계할 객체 배열.
 * @param {string} groupByKey - 그룹화할 키.
 * @param {string} aggregateByKey - 집계(합계)할 숫자 값을 가진 키.
 * @returns {Object} - 그룹화된 키를 속성으로 갖고, 그 값으로 집계된 합계를 가진 객체.
 */
function aggregateData(data, groupByKey, aggregateByKey) {
    // reduce를 사용하여 데이터를 그룹화하고 집계한다.
    return data.reduce((accumulator, currentItem) => {
        const group = currentItem[groupByKey];
        const valueToAggregate = currentItem[aggregateByKey];

        // 해당 그룹이 accumulator에 없으면 초기화한다.
        if (!accumulator[group]) {
            accumulator[group] = 0;
        }
        // 값을 더한다.
        accumulator[group] += valueToAggregate;
        return accumulator;
    }, {}); // 빈 객체로 시작한다.
}

// 모듈 외부로 함수들을 내보낸다.
module.exports = {
    filterByCondition,
    transformData,
    aggregateData
};
