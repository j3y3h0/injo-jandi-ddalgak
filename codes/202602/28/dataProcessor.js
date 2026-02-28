// dataProcessor.js
// 매출 데이터를 정제하고 분석하는 핵심 로직을 포함합니다.

import { Utils } from './utils.js';

export class DataProcessor {
    constructor(transactions) {
        this.transactions = transactions;
    }

    /**
     * 매출 데이터를 정제합니다.
     * - amount가 유효한 숫자인지 확인하고, 유효하지 않으면 제외합니다.
     * - date가 유효한 날짜 형식인지 확인합니다.
     * @returns {Array} 정제된 트랜잭션 배열입니다.
     */
    cleanData() {
        console.log('데이터를 정제하는 중입니다...');
        return this.transactions.filter(transaction => {
            // amount가 숫자이고 양수인지 확인합니다.
            const isValidAmount = typeof transaction.amount === 'number' && !isNaN(transaction.amount) && transaction.amount > 0;
            // date가 유효한 날짜 문자열 형식인지 간단히 확인합니다. (YYYY-MM-DD)
            const isValidDate = transaction.date && Utils.isValidDateFormat(transaction.date);
            
            return isValidAmount && isValidDate;
        }).map(transaction => ({
            ...transaction,
            // 날짜를 Date 객체로 변환하여 이후 처리를 용이하게 합니다.
            date: new Date(transaction.date)
        }));
    }

    /**
     * 주어진 트랜잭션 배열의 총 매출을 계산합니다.
     * @param {Array} transactions - 트랜잭션 배열입니다.
     * @returns {number} 총 매출액입니다.
     */
    calculateTotalSales(transactions) {
        console.log('총 매출액을 계산하는 중입니다...');
        return transactions.reduce((sum, transaction) => sum + transaction.amount, 0);
    }

    /**
     * 주어진 트랜잭션 배열의 평균 매출을 계산합니다.
     * 트랜잭션 수가 0인 경우 0을 반환합니다.
     * @param {Array} transactions - 트랜잭션 배열입니다.
     * @returns {number} 평균 매출액입니다.
     */
    calculateAverageSales(transactions) {
        console.log('평균 매출액을 계산하는 중입니다...');
        if (transactions.length === 0) {
            return 0;
        }
        return this.calculateTotalSales(transactions) / transactions.length;
    }

    /**
     * 특정 날짜 범위 내의 트랜잭션을 필터링합니다.
     * @param {Array} transactions - 트랜잭션 배열입니다.
     * @param {string} startDateString - 시작 날짜 (YYYY-MM-DD 형식 문자열).
     * @param {string} endDateString - 종료 날짜 (YYYY-MM-DD 형식 문자열).
     * @returns {Array} 필터링된 트랜잭션 배열입니다.
     */
    filterByDateRange(transactions, startDateString, endDateString) {
        console.log(`${startDateString}부터 ${endDateString}까지의 매출을 필터링하는 중입니다...`);
        const startDate = new Date(startDateString);
        const endDate = new Date(endDateString);

        return transactions.filter(transaction => {
            const transactionDate = new Date(transaction.date);
            // 거래 날짜가 시작 날짜보다 같거나 늦고, 종료 날짜보다 같거나 이른 경우를 포함합니다.
            return transactionDate >= startDate && transactionDate <= endDate;
        });
    }
}
