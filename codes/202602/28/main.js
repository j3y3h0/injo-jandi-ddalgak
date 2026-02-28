// main.js
// 프로그램의 시작점입니다. 데이터 로드 및 처리 흐름을 제어합니다.

import { DataProcessor } from './dataProcessor.js';
import { Reporter } from './reporter.js';

// 샘플 매출 데이터입니다. 실제 애플리케이션에서는 데이터베이스나 API를 통해 로드될 수 있습니다.
const sampleTransactions = [
    { id: 'T001', date: '2026-01-05', amount: 15000, product: '상품A' },
    { id: 'T002', date: '2026-01-10', amount: 25000, product: '상품B' },
    { id: 'T003', date: '2026-01-15', amount: 10000, product: '상품A' },
    { id: 'T004', date: '2026-01-20', amount: 30000, product: '상품C' },
    { id: 'T005', date: '2026-01-25', amount: 5000, product: '상품A' },
    { id: 'T006', date: '2026-02-01', amount: 20000, product: '상품B' },
    { id: 'T007', date: '2026-02-05', amount: 12000, product: '상품D' },
    { id: 'T008', date: '2026-02-10', amount: 18000, product: '상품C' },
    { id: 'T009', date: '2026-02-15', amount: null, product: '상품E' }, // 유효하지 않은 데이터
    { id: 'T010', date: '2026-02-20', amount: 7000, product: '상품A' },
];

async function runAnalysis() {
    console.log('매출 데이터 분석을 시작합니다...
');

    // 데이터 처리 인스턴스를 생성합니다.
    const dataProcessor = new DataProcessor(sampleTransactions);

    // 데이터를 정제합니다.
    const cleanedData = dataProcessor.cleanData();
    console.log(`정제된 트랜잭션 수: ${cleanedData.length}개
`);

    // 총 매출을 계산합니다.
    const totalSales = dataProcessor.calculateTotalSales(cleanedData);

    // 평균 매출을 계산합니다.
    const averageSales = dataProcessor.calculateAverageSales(cleanedData);

    // 2026년 2월의 매출을 필터링합니다.
    const febSales = dataProcessor.filterByDateRange(cleanedData, '2026-02-01', '2026-02-28');
    const totalFebSales = dataProcessor.calculateTotalSales(febSales);

    // 보고서를 생성하고 출력합니다.
    const reportData = {
        totalTransactions: cleanedData.length,
        totalSales: totalSales,
        averageSales: averageSales,
        febSalesCount: febSales.length,
        totalFebSales: totalFebSales
    };

    Reporter.generateReport(reportData);

    console.log('
매출 데이터 분석이 완료되었습니다.');
}

// 분석 함수를 실행합니다.
runAnalysis();
