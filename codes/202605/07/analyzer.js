// analyzer.js

/**
 * 주어진 주식 데이터 배열에서 단순 이동 평균(SMA)을 계산합니다.
 *
 * @param {Array<Object>} data 주식 데이터 배열. 각 객체는 'close' 속성을 가져야 합니다.
 *                               예: [{ date: '...', close: 100 }, { date: '...', close: 105 }]
 * @param {number} period 이동 평균을 계산할 기간 (예: 5일 이동 평균)
 * @returns {Array<Object>} 각 날짜의 이동 평균을 포함하는 배열
 */
function calculateSMA(data, period) {
    if (period <= 0) {
        throw new Error("이동 평균 기간은 0보다 커야 합니다.");
    }
    if (data.length < period) {
        console.warn(`[analyzer] 데이터 길이가 이동 평균 기간(${period})보다 짧습니다. SMA를 계산할 수 없습니다.`);
        return [];
    }

    const smaResults = [];

    for (let i = 0; i <= data.length - period; i++) {
        const slice = data.slice(i, i + period);
        const sum = slice.reduce((acc, current) => acc + current.close, 0);
        const average = sum / period;
        
        // 이동 평균은 해당 기간의 마지막 날짜에 할당하는 것이 일반적이다.
        smaResults.push({
            date: data[i + period - 1].date,
            sma: parseFloat(average.toFixed(2)) // 소수점 둘째 자리까지 반올림
        });
    }

    return smaResults;
}

module.exports = {
    calculateSMA
};