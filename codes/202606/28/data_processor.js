// data_processor.js
const Papa = require('papaparse');
const fs = require('fs');

/**
 * CSV 파일에서 데이터를 파싱합니다.
 * @param {string} filePath - CSV 파일 경로.
 * @returns {Promise<Array<Object>>} 파싱된 데이터 객체 배열.
 */
async function parseCsvFile(filePath) {
    return new Promise((resolve, reject) => {
        const file = fs.createReadStream(filePath);
        Papa.parse(file, {
            header: true, // 첫 줄을 헤더로 사용
            dynamicTyping: true, // 숫자, 불리언 등을 자동으로 타입 변환
            complete: (results) => {
                if (results.errors.length) {
                    reject(new Error(`CSV 파싱 오류: ${results.errors.map(e => e.message).join(', ')}`));
                } else {
                    resolve(results.data);
                }
            },
            error: (err) => {
                reject(new Error(`파일 읽기 오류: ${err.message}`));
            }
        });
    });
}

/**
 * 주어진 데이터 배열과 컬럼 이름으로 평균을 계산합니다.
 * @param {Array<Object>} data - 데이터 객체 배열.
 * @param {string} column - 평균을 계산할 숫자 컬럼 이름.
 * @returns {number} 계산된 평균.
 */
function calculateMean(data, column) {
    const numbers = data
        .map(row => row[column])
        .filter(value => typeof value === 'number' && !isNaN(value));

    if (numbers.length === 0) {
        return 0;
    }

    const sum = numbers.reduce((acc, val) => acc + val, 0);
    return sum / numbers.length;
}

/**
 * 주어진 데이터 배열과 컬럼 이름으로 중앙값을 계산합니다.
 * @param {Array<Object>} data - 데이터 객체 배열.
 * @param {string} column - 중앙값을 계산할 숫자 컬럼 이름.
 * @returns {number} 계산된 중앙값.
 */
function calculateMedian(data, column) {
    const numbers = data
        .map(row => row[column])
        .filter(value => typeof value === 'number' && !isNaN(value))
        .sort((a, b) => a - b);

    if (numbers.length === 0) {
        return 0;
    }

    const mid = Math.floor(numbers.length / 2);
    return numbers.length % 2 === 0
        ? (numbers[mid - 1] + numbers[mid]) / 2
        : numbers[mid];
}

module.exports = {
    parseCsvFile,
    calculateMean,
    calculateMedian
};
