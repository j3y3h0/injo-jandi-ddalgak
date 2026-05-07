// main.js

const { fetchStockData } = require('./stock_api');
const { calculateSMA } = require('./analyzer');

/**
 * 메인 함수. 주식 데이터를 가져오고 이동 평균을 계산하여 출력합니다.
 */
async function main() {
    const symbol = "IBM"; // 분석할 주식 심볼
    const smaPeriod = 5; // 5일 이동 평균

    console.log(`[메인] ${symbol} 주식 데이터 가져오기 및 ${smaPeriod}일 이동 평균 분석 시작...`);

    try {
        const stockData = await fetchStockData(symbol);

        if (stockData.length === 0) {
            console.log(`[메인] ${symbol}에 대한 주식 데이터를 가져오지 못했습니다.`);
            return;
        }

        console.log(`[메인] 총 ${stockData.length}일치 데이터 가져옴.`);
        //console.log("원본 데이터:", stockData); // 디버깅을 위해 주석 처리

        const smaResults = calculateSMA(stockData, smaPeriod);

        if (smaResults.length > 0) {
            console.log(`
[메인] ${symbol}의 ${smaPeriod}일 이동 평균 결과:`);
            smaResults.forEach(result => {
                console.log(`날짜: ${result.date}, SMA: ${result.sma}`);
            });
        } else {
            console.log(`[메인] ${symbol}에 대한 이동 평균을 계산할 수 없습니다.`);
        }

    } catch (error) {
        console.error(`[메인] 애플리케이션 실행 중 오류 발생: ${error.message}`);
    }
}

// 애플리케이션 시작
main();
