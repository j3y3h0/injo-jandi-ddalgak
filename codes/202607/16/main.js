// main.js
// 데이터 분석 프로젝트의 메인 엔트리 포인트입니다.
// 데이터 로드, 분석, 보고서 생성을 통합합니다.

const { loadData } = require('./dataLoader');
const { calculateAverage, findMinimum, findMaximum } = require('./analyzer');
const { generateReport, printReportToConsole } = require('./reporter');

// 샘플 CSV 데이터
const sampleCsvData = `
Category,Value,Date
A,10,2023-01-01
B,25,2023-01-02
A,15,2023-01-03
C,30,2023-01-04
B,20,2023-01-05
C,35,2023-01-06
A,12,2023-01-07
`;

async function main() {
  console.log('데이터 분석 프로젝트를 시작합니다...
');

  // 1. 데이터 로드
  const loadedData = loadData(sampleCsvData);
  console.log('데이터 로드 완료. 샘플 데이터:', loadedData.slice(0, 3), '...'); // 처음 3개 항목만 표시

  if (loadedData.length === 0) {
    console.log('로드된 데이터가 없습니다. 분석을 종료합니다.');
    return;
  }

  // 2. 데이터 분석
  const analysisKey = 'Value'; // 'Value' 컬럼을 분석 대상으로 지정
  console.log(`
'${analysisKey}' 컬럼에 대한 분석을 수행합니다.`);

  const average = calculateAverage(loadedData, analysisKey);
  const min = findMinimum(loadedData, analysisKey);
  const max = findMaximum(loadedData, analysisKey);

  const analysisResults = {
    '평균 값': average.toFixed(2), // 소수점 두 자리까지
    '최소 값': min,
    '최대 값': max,
    '데이터 개수': loadedData.length
  };
  console.log('분석 결과:', analysisResults);

  // 3. 보고서 생성 및 출력
  const reportTitle = '판매 데이터 분석 보고서';
  const dataDescription = `기간: 2023년 1월 1일 ~ 1월 7일, 분석 항목: ${analysisKey}`;
  const reportContent = generateReport(reportTitle, dataDescription, analysisResults);

  printReportToConsole(reportContent);

  console.log('데이터 분석 프로젝트를 완료했습니다.');

  // 추가: 시각화 라이브러리(예: Chart.js)를 사용한 시각화 계획
  // 실제 시각화 코드는 브라우저 환경이나 전용 렌더링 엔진이 필요하므로,
  // 여기서는 콘솔 출력만 수행합니다.
  // 만약 웹 환경이라면, Chart.js를 사용하여 다음과 같이 데이터를 시각화할 수 있을 것입니다:
  // const labels = loadedData.map(item => item.Date);
  // const values = loadedData.map(item => item.Value);
  // console.log('
--- 시각화 계획 ---');
  // console.log('Chart.js 등을 사용하여 "날짜별 Value" 라인 차트를 그릴 수 있습니다.');
  // console.log('레이블:', labels);
  // console.log('값:', values);
}

main().catch(console.error);
