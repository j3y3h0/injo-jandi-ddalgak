// reportGenerator.js
/**
 * 분석 결과를 보기 좋게 출력합니다.
 * @param {Array<Object>} recommendations - 투자 권고 배열
 * @param {Array<Object>} recentData - 최근 분석된 주식 데이터 배열 (이동 평균 포함)
 */
export function generateReport(recommendations, recentData) {
    console.log("=".repeat(50));
    console.log("            주식 시장 분석 보고서            ");
    console.log("=".repeat(50));
    console.log("
[최근 5일간의 데이터 및 분석 결과]");
    console.log("날짜         종가     5일이평    권고");
    console.log("---------------------------------------");
    recentData.forEach(data => {
        const rec = recommendations.find(r => r.date === data.date);
        const recommendationText = rec ? rec.recommendation : 'N/A';
        console.log(`${data.date}  ${data.price.toFixed(2).padStart(6)}  ${data.movingAverage !== null ? data.movingAverage.toFixed(2).padStart(8) : 'N/A'.padStart(8)}  ${recommendationText.padEnd(4)}`);
    });

    const lastRecommendation = recommendations[recommendations.length - 1];
    if (lastRecommendation) {
        console.log("
[오늘의 투자 권고]");
        console.log(`날짜: ${lastRecommendation.date}`);
        console.log(`권고: ${lastRecommendation.recommendation}`);
    } else {
        console.log("
[오늘의 투자 권고]: 데이터를 분석할 수 없습니다.");
    }

    console.log("
" + "=".repeat(50));
    console.log("이 보고서는 모의 데이터 기반의 단순 분석입니다.");
    console.log("투자 결정은 신중하게 직접 판단하시기 바랍니다.");
    console.log("=".repeat(50));
}
