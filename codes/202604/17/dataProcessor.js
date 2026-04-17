// dataProcessor.js
// 데이터 처리 및 통계 계산 로직을 포함하는 모듈입니다.

/**
 * CSV 형식의 문자열을 파싱하여 객체 배열 형태로 변환합니다.
 * 첫 번째 행은 헤더로 간주됩니다.
 * @param {string} csvString 파싱할 CSV 데이터 문자열.
 * @returns {Array<Object>} 각 행이 객체로 변환된 데이터 배열.
 */
function parseCsv(csvString) {
    const lines = csvString.trim().split('
');
    if (lines.length === 0) {
        return [];
    }

    const headers = lines[0].split(',').map(header => header.trim());
    const data = [];

    for (let i = 1; i < lines.length; i++) {
        const values = lines[i].split(',').map(value => value.trim());
        const rowObject = {};
        headers.forEach((header, index) => {
            // 숫자로 변환 가능한 값은 숫자로 저장
            rowObject[header] = isNaN(Number(values[index])) ? values[index] : Number(values[index]);
        });
        data.push(rowObject);
    }
    return data;
}

/**
 * 파싱된 데이터에서 특정 컬럼의 합계와 평균을 계산합니다.
 * @param {Array<Object>} data 파싱된 데이터 배열.
 * @param {string} columnName 통계를 계산할 컬럼의 이름.
 * @returns {{sum: number, average: number}|null} 합계와 평균을 포함하는 객체 또는 컬럼을 찾을 수 없으면 null.
 */
function calculateColumnStatistics(data, columnName) {
    if (!data || data.length === 0) {
        return null;
    }

    const columnValues = data.map(row => row[columnName]).filter(value => typeof value === 'number' && !isNaN(value));

    if (columnValues.length === 0) {
        console.warn(`경고: 컬럼 '${columnName}'에서 유효한 숫자 데이터를 찾을 수 없습니다.`);
        return null;
    }

    const sum = columnValues.reduce((acc, current) => acc + current, 0);
    const average = sum / columnValues.length;

    return { sum, average };
}

module.exports = {
    parseCsv,
    calculateColumnStatistics
};