// main.js
// 프로젝트의 진입점입니다. sample_data.json을 읽고 dataProcessor를 사용하여 데이터를 분석합니다.

const fs = require('fs');
const path = require('path');
const { filterByCategory, aggregateValues } = require('./dataProcessor');

// 데이터 파일 경로 정의
const DATA_FILE_PATH = path.join(__dirname, 'sample_data.json');

/**
 * 메인 함수입니다. 데이터 파일을 읽고 처리 로직을 실행합니다.
 */
function main() {
  try {
    // 데이터 파일 읽기
    const rawData = fs.readFileSync(DATA_FILE_PATH, 'utf8');
    const data = JSON.parse(rawData);

    console.log("--- 원본 데이터 ---");
    console.log(data);
    console.log("
");

    // 'sales' 카테고리 데이터 필터링
    const salesData = filterByCategory(data, 'sales');
    console.log("--- 'sales' 카테고리 데이터 ---");
    console.log(salesData);

    // 'sales' 카테고리의 총 가치 집계
    const totalSalesValue = aggregateValues(salesData);
    console.log(`'sales' 카테고리의 총 가치: ${totalSalesValue}
`);

    // 'marketing' 카테고리 데이터 필터링
    const marketingData = filterByCategory(data, 'marketing');
    console.log("--- 'marketing' 카테고리 데이터 ---");
    console.log(marketingData);

    // 'marketing' 카테고리의 총 가치 집계
    const totalMarketingValue = aggregateValues(marketingData);
    console.log(`'marketing' 카테고리의 총 가치: ${totalMarketingValue}
`);

    // 모든 데이터의 총 가치 집계
    const totalValueAllCategories = aggregateValues(data);
    console.log(`모든 카테고리의 총 가치: ${totalValueAllCategories}
`);

  } catch (error) {
    console.error("데이터 처리 중 오류가 발생했습니다:", error.message);
  }
}

// main 함수 실행
main();
