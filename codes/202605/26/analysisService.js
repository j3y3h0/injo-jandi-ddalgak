// analysisService.js
/**
 * 주식 데이터에 대한 기술적 분석을 수행합니다.
 * 현재는 이동 평균을 계산합니다.
 * @param {Array<Object>} stockData - 날짜와 가격을 포함하는 주식 데이터 배열
 * @returns {Array<Object>} 이동 평균이 추가된 주식 데이터 배열
 */
export function analyzeData(stockData) {
    const period = 5; // 5일 이동 평균
    const analyzed = stockData.map((entry, index, arr) => {
        if (index < period - 1) {
            return { ...entry, movingAverage: null }; // 이동 평균 계산에 필요한 데이터가 부족함
        }
        const sum = arr.slice(index - period + 1, index + 1).reduce((acc, curr) => acc + curr.price, 0);
        const movingAverage = sum / period;
        return { ...entry, movingAverage: parseFloat(movingAverage.toFixed(2)) };
    });
    return analyzed;
}

/**
 * 분석된 데이터를 기반으로 간단한 투자 권고를 생성합니다.
 * 현재 종가가 이동 평균보다 높으면 '매수', 낮으면 '매도' 권고를 합니다.
 * @param {Array<Object>} analyzedData - 이동 평균이 포함된 분석된 주식 데이터 배열
 * @returns {Array<Object>} 날짜와 권고를 포함하는 배열
 */
export function getRecommendations(analyzedData) {
    const recommendations = [];
    for (let i = 0; i < analyzedData.length; i++) {
        const current = analyzedData[i];
        if (current.movingAverage === null) {
            recommendations.push({ date: current.date, recommendation: "데이터 부족" });
            continue;
        }

        let action = "관망"; // 기본적으로 관망
        if (current.price > current.movingAverage) {
            action = "매수";
        } else if (current.price < current.movingAverage) {
            action = "매도";
        }
        recommendations.push({ date: current.date, recommendation: action });
    }
    return recommendations;
}
