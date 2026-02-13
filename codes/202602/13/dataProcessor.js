// dataProcessor.js
// 파싱된 데이터에 대한 비즈니스 로직을 수행합니다.

const { logMessage } = require('./utils');

/**
 * 주어진 데이터 배열에서 특정 컬럼의 평균을 계산합니다.
 * 컬럼의 값은 숫자로 변환 가능한 형태여야 합니다.
 * @param {Array<Object>} data - CSV 파싱 결과인 객체 배열.
 * @param {string} columnName - 평균을 계산할 숫자 컬럼의 이름.
 * @returns {number | null} 컬럼의 평균값. 계산할 수 없으면 null 반환.
 */
function calculateAverage(data, columnName) {
    logMessage(`${columnName} 컬럼의 평균 계산을 시작합니다.`);
    if (!data || data.length === 0) {
        logMessage('데이터가 비어 있어 평균을 계산할 수 없습니다.');
        return null;
    }

    let sum = 0;
    let count = 0;

    for (const row of data) {
        const value = parseFloat(row[columnName]);
        if (!isNaN(value)) {
            sum += value;
            count++;
        } else {
            logMessage(`경고: '${row[columnName]}' (컬럼: ${columnName}) 값은 숫자가 아니므로 평균 계산에서 제외됩니다.`);
        }
    }

    if (count === 0) {
        logMessage(`'${columnName}' 컬럼에 유효한 숫자 값이 없어 평균을 계산할 수 없습니다.`);
        return null;
    }

    const average = sum / count;
    logMessage(`'${columnName}' 컬럼의 평균: ${average}`);
    return average;
}

module.exports = {
    calculateAverage
};
