// main.js
import { loadStockData } from './dataLoader.js';
import { analyzeData, getRecommendations } from './analysisService.js';
import { generateReport } from './reportGenerator.js';

async function main() {
    console.log("주식 시장 데이터 분석 프로젝트를 시작합니다.
");

    // 1. 데이터 로드
    console.log("1. 주식 데이터 로드 중...");
    const stockData = await loadStockData();
    if (!stockData || stockData.length === 0) {
        console.error("데이터 로드에 실패했습니다. 프로그램을 종료합니다.");
        return;
    }
    console.log(`총 ${stockData.length}일의 데이터가 로드되었습니다.
`);

    // 2. 데이터 분석 (이동 평균 계산 등)
    console.log("2. 데이터 분석 (이동 평균 계산) 중...");
    const analyzedData = analyzeData(stockData);
    console.log("데이터 분석 완료.
");

    // 3. 투자 권고 생성
    console.log("3. 투자 권고 생성 중...");
    const recommendations = getRecommendations(analyzedData);
    console.log("투자 권고 생성 완료.
");

    // 4. 결과 보고서 생성 및 출력
    console.log("4. 분석 결과 보고서 생성 중...");
    generateReport(recommendations, analyzedData.slice(-5)); // 최근 5일 데이터 포함
    console.log("
프로젝트가 성공적으로 완료되었습니다.");
}

main();
