// main.js
// 애플리케이션의 주 진입점입니다.
// sample_data와 dataProcessor 모듈을 사용하여 데이터 분석을 수행합니다.

const { sampleCsvData } = require('./sample_data');
const { parseCsv, calculateColumnStatistics } = require('./dataProcessor');

function main() {
    console.log("데이터 분석을 시작합니다...
");

    // 1. 샘플 CSV 데이터 파싱
    const parsedData = parseCsv(sampleCsvData);
    console.log("파싱된 데이터:");
    console.log(parsedData);
    console.log("
");

    // 2. 'Score' 컬럼에 대한 통계 계산
    const scoreColumnName = 'Score';
    const scoreStats = calculateColumnStatistics(parsedData, scoreColumnName);

    if (scoreStats) {
        console.log(`'${scoreColumnName}' 컬럼 통계:`);
        console.log(`  합계: ${scoreStats.sum}`);
        console.log(`  평균: ${scoreStats.average.toFixed(2)}`); // 소수점 둘째 자리까지 표시
    } else {
        console.log(`'${scoreColumnName}' 컬럼에 대한 통계를 계산할 수 없습니다.`);
    }

    console.log("
데이터 분석이 완료되었습니다.");
}

// main 함수 실행
main();