// csvParser.js
// CSV 형식의 문자열을 파싱하는 기능을 담당합니다.

const { logMessage } = require('./utils');

/**
 * CSV 문자열을 파싱하여 객체 배열로 변환합니다.
 * 첫 번째 줄은 헤더로 간주합니다.
 * @param {string} csvString - 파싱할 CSV 형식의 문자열.
 * @returns {Array<Object>} 파싱된 데이터의 객체 배열.
 */
function parseCsv(csvString) {
    logMessage('CSV 문자열 파싱을 시작합니다.');
    const lines = csvString.trim().split('
');
    if (lines.length === 0) {
        logMessage('파싱할 CSV 데이터가 없습니다.');
        return [];
    }

    const headers = lines[0].split(',').map(header => header.trim());
    const data = [];

    for (let i = 1; i < lines.length; i++) {
        const values = lines[i].split(',').map(value => value.trim());
        if (values.length !== headers.length) {
            logMessage(`경고: ${i + 1}번째 줄의 컬럼 수가 헤더와 일치하지 않습니다. 이 줄은 건너뜁니다.`);
            continue;
        }

        const row = {};
        for (let j = 0; j < headers.length; j++) {
            row[headers[j]] = values[j];
        }
        data.push(row);
    }

    logMessage(`CSV 파싱 완료. 총 ${data.length}개의 레코드를 처리했습니다.`);
    return data;
}

module.exports = {
    parseCsv
};
