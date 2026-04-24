// visualization.js

/**
 * @module visualization
 * @description 가공된 데이터를 Chart.js를 사용하여 시각화하는 모듈입니다.
 */

// Chart.js 인스턴스를 저장할 변수
let myChartInstance = null;

/**
 * Chart.js를 사용하여 데이터를 막대 차트로 시각화합니다.
 * @param {HTMLCanvasElement} canvasElement - 차트를 그릴 캔버스 DOM 엘리먼트입니다.
 * @param {Object} chartData - dataProcessor에서 반환된 차트 데이터 객체입니다.
 *                             형식: { labels: Array<string>, datasets: Array<{ label: string, data: Array<number> }> }
 */
export function renderChart(canvasElement, chartData) {
    console.log("차트 렌더링 중:", chartData);

    const ctx = canvasElement.getContext('2d');

    // 기존 차트 인스턴스가 있다면 파괴하여 메모리 누수를 방지합니다.
    if (myChartInstance) {
        myChartInstance.destroy();
    }

    myChartInstance = new Chart(ctx, {
        type: 'bar', // 막대 차트
        data: chartData,
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                title: {
                    display: true,
                    text: '카테고리별 데이터 시각화'
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: '값'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: '카테고리'
                    }
                }
            }
        }
    });

    console.log("차트 렌더링 완료.");
}
