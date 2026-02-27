// dataParser.js
// CSV 형식의 문자열 데이터를 파싱하여 JavaScript 객체 배열로 변환하는 유틸리티.

/**
 * CSV 형식의 문자열을 객체 배열로 파싱한다.
 * 첫 번째 줄은 헤더로 간주된다.
 * @param {string} csvString 파싱할 CSV 형식의 문자열.
 * @returns {Array<Object>} 파싱된 데이터를 담은 객체 배열.
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
        if (values.length !== headers.length) {
            console.warn(`경고: ${i + 1}번째 줄의 컬럼 수가 헤더와 일치하지 않습니다. 이 줄은 건너뜁니다.`);
            continue;
        }

        const row = {};
        for (let j = 0; j < headers.length; j++) {
            // 숫자 형식으로 변환 가능한 경우 변환 시도
            const numValue = Number(values[j]);
            row[headers[j]] = isNaN(numValue) ? values[j] : numValue;
        }
        data.push(row);
    }
    return data;
}

module.exports = {
    parseCsv
};
