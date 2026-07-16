// reporter.js
// 분석 결과를 포맷팅하여 보고서를 생성하는 모듈입니다.
// 콘솔 출력 또는 파일 저장 등 다양한 형태로 보고서를 만들 수 있습니다.

/**
 * 분석 결과를 바탕으로 보고서 문자열을 생성합니다.
 * @param {string} title 보고서 제목.
 * @param {string} dataDescription 분석된 데이터에 대한 설명.
 * @param {Object} analysisResults 분석 결과 객체 (예: { average: 10, min: 1, max: 20 }).
 * @returns {string} 포맷팅된 보고서 문자열.
 */
function generateReport(title, dataDescription, analysisResults) {
  let report = `## ${title}

`;
  report += `### 데이터 개요
`;
  report += `- ${dataDescription}

`;

  report += `### 분석 결과
`;
  for (const key in analysisResults) {
    if (Object.hasOwnProperty.call(analysisResults, key)) {
      report += `- ${key}: ${analysisResults[key]}
`;
    }
  }
  report += `
`;
  report += `보고서 생성 시간: ${new Date().toLocaleString()}
`;

  return report;
}

/**
 * 보고서를 콘솔에 출력합니다.
 * @param {string} reportContent 출력할 보고서 내용.
 */
function printReportToConsole(reportContent) {
  console.log('--- 분석 보고서 시작 ---
');
  console.log(reportContent);
  console.log('--- 분석 보고서 종료 ---
');
}

module.exports = {
  generateReport,
  printReportToConsole,
};
