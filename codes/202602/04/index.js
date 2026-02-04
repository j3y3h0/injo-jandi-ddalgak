// index.js

const { getRandomInt, formatNumberWithCommas } = require('./utils');

const SIMULATION_MONTHS = 12;
const EXPECTED_INCREASE_ADP = 25000; // Expected ADP employment increase based on the news context (adjusted slightly for simulation)
const ADP_RANGE_MIN = 15000; // Minimum for simulated ADP increase
const ADP_RANGE_MAX = 35000; // Maximum for simulated ADP increase

console.log(`--- ADP 민간고용 변화 시뮬레이션 (${SIMULATION_MONTHS}개월) ---`);
console.log(`예상치: ${formatNumberWithCommas(EXPECTED_INCREASE_ADP)}명 증가`);
console.log('----------------------------------------------------');

let totalVariance = 0;
let monthsAboveExpectation = 0;
let monthsBelowExpectation = 0;

for (let i = 1; i <= SIMULATION_MONTHS; i++) {
    const simulatedIncrease = getRandomInt(ADP_RANGE_MIN, ADP_RANGE_MAX);
    const variance = simulatedIncrease - EXPECTED_INCREASE_ADP;

    let result = '';
    if (variance > 0) {
        result = `예상치보다 ${formatNumberWithCommas(variance)}명 많음 (상회)`;
        monthsAboveExpectation++;
    } else if (variance < 0) {
        result = `예상치보다 ${formatNumberWithCommas(Math.abs(variance))}명 적음 (하회)`;
        monthsBelowExpectation++;
    } else {
        result = `예상치와 동일`;
    }

    console.log(
        `${i}월: 시뮬레이션 결과 ${formatNumberWithCommas(simulatedIncrease)}명 증가. ${result}`
    );
    totalVariance += variance;
}

console.log('----------------------------------------------------');
console.log('--- 시뮬레이션 요약 ---');
console.log(`총 시뮬레이션 기간: ${SIMULATION_MONTHS}개월`);
console.log(`총 예상치 대비 증감: ${formatNumberWithCommas(totalVariance)}명 ${totalVariance >= 0 ? '증가' : '감소'}`);
console.log(`예상치 상회 월수: ${monthsAboveExpectation}개월`);
console.log(`예상치 하회 월수: ${monthsBelowExpectation}개월`);
console.log(`예상치와 동일 월수: ${SIMULATION_MONTHS - monthsAboveExpectation - monthsBelowExpectation}개월`);
console.log('----------------------------------------------------');