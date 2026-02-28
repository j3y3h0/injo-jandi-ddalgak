// reporter.js
// 분석된 데이터를 바탕으로 보고서를 생성하는 로직을 포함합니다.

import { Utils } from './utils.js';

export class Reporter {
    /**
     * 분석된 데이터를 바탕으로 콘솔에 보고서를 생성하여 출력합니다.
     * @param {object} reportData - 보고서에 필요한 데이터 객체입니다.
     *   - totalTransactions: 총 정제된 트랜잭션 수
     *   - totalSales: 총 매출액
     *   - averageSales: 평균 매출액
     *   - febSalesCount: 2월 매출 트랜잭션 수
     *   - totalFebSales: 2월 총 매출액
     */
    static generateReport(reportData) {
        console.log('
--- 매출 분석 보고서 ---');
        console.log(`총 트랜잭션 수: ${reportData.totalTransactions}건`);
        console.log(`총 매출액: ${Utils.formatCurrency(reportData.totalSales)}`);
        console.log(`평균 매출액 (트랜잭션당): ${Utils.formatCurrency(reportData.averageSales)}`);
        console.log(`
--- 2월 매출 요약 ---`);
        console.log(`2월 트랜잭션 수: ${reportData.febSalesCount}건`);
        console.log(`2월 총 매출액: ${Utils.formatCurrency(reportData.totalFebSales)}`);
        console.log('--------------------');
    }
}
