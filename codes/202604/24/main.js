// main.js

/**
 * @module main
 * @description 비동기 데이터 처리 및 시각화 프로젝트의 메인 진입점입니다.
 *              dataFetcher, dataProcessor, visualization 모듈을 결합하여 데이터를 가져오고,
 *              가공하고, 차트로 시각화하는 전체 흐름을 제어합니다.
 */

import { fetchData } from './dataFetcher.js';
import { processData } from './dataProcessor.js';
import { renderChart } from './visualization.js';

document.addEventListener('DOMContentLoaded', () => {
    const loadDataBtn = document.getElementById('loadDataBtn');
    const statusDiv = document.getElementById('status');
    const chartCanvas = document.getElementById('myChart');

    if (!loadDataBtn || !statusDiv || !chartCanvas) {
        console.error("필수 DOM 엘리먼트를 찾을 수 없습니다. HTML 구조를 확인하세요.");
        return;
    }

    loadDataBtn.addEventListener('click', async () => {
        statusDiv.textContent = '데이터를 불러오는 중...';
        loadDataBtn.disabled = true; // 버튼 비활성화

        try {
            // 1. 데이터 가져오기
            const rawData = await fetchData();
            statusDiv.textContent = '데이터 가져오기 완료. 가공 중...';

            // 2. 데이터 가공
            const processedChartData = processData(rawData);
            statusDiv.textContent = '데이터 가공 완료. 시각화 중...';

            // 3. 데이터 시각화
            renderChart(chartCanvas, processedChartData);
            statusDiv.textContent = '데이터 시각화 완료!';

        } catch (error) {
            console.error("데이터 처리 중 오류 발생:", error);
            statusDiv.textContent = `오류 발생: ${error.message}`;
            // 에러 시 기존 차트 파괴 (선택 사항)
            if (chartCanvas && chartCanvas.chart) {
                chartCanvas.chart.destroy();
            }
        } finally {
            loadDataBtn.disabled = false; // 버튼 다시 활성화
        }
    });

    // 초기 로드 시 차트 영역 초기화 또는 안내 메시지
    statusDiv.textContent = '데이터 불러오기 버튼을 클릭하세요.';
});
