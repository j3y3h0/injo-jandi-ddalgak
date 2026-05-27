// main.js
// 애플리케이션의 진입점. 데이터 로드, 분석, 시각화를 총괄한다.

import salesData from './data.js';
import { analyzeSalesByCategory } from './analyzer.js';
import { generateBarChart } from './visualizer.js';

// 1. 판매 데이터 로드 (data.js에서 import)
console.log("--- 판매 데이터 분석 시작 ---");
console.log(`총 ${salesData.length}개의 판매 기록을 로드했습니다.`);

// 2. 카테고리별 판매액 분석
const categorySales = analyzeSalesByCategory(salesData);
console.log("
--- 카테고리별 판매액 분석 결과 ---");
for (const category in categorySales) {
    console.log(`${category}: ${categorySales[category].toLocaleString()}원`);
}

// 3. 분석 결과 시각화
const salesChart = generateBarChart(categorySales, "카테고리별 총 판매액");
console.log(salesChart);

console.log("--- 판매 데이터 분석 완료 ---");
