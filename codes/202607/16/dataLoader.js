// dataLoader.js
// 데이터 로딩을 담당하는 모듈입니다.
// 실제 환경에서는 'papaparse'와 같은 라이브러리를 사용하여 CSV 파일을 파싱할 수 있습니다.

/**
 * 주어진 CSV 형식의 문자열 데이터를 파싱합니다.
 * @param {string} csvString 파싱할 CSV 형식의 문자열.
 * @returns {Array<Object>} 파싱된 데이터 객체 배열.
 */
function loadData(csvString) {
  // 실제 papaparse 라이브러리를 사용한다면 다음과 같이 할 수 있습니다:
  // const Papa = require('papaparse');
  // const results = Papa.parse(csvString, {
  //   header: true,
  //   dynamicTyping: true,
  //   skipEmptyLines: true
  // });
  // return results.data;

  // 여기서는 간단한 구현으로 대체합니다.
  const lines = csvString.trim().split('
');
  if (lines.length === 0) {
    return [];
  }

  const headers = lines[0].split(',').map(h => h.trim());
  const data = [];

  for (let i = 1; i < lines.length; i++) {
    const values = lines[i].split(',').map(v => v.trim());
    const row = {};
    headers.forEach((header, index) => {
      // 숫자 형식의 데이터는 숫자로 변환합니다.
      row[header] = isNaN(Number(values[index])) ? values[index] : Number(values[index]);
    });
    data.push(row);
  }
  return data;
}

module.exports = { loadData };
