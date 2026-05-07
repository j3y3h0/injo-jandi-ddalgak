// stock_api.js

const axios = require('axios');

// 실제 Alpha Vantage API 키를 여기에 입력하세요.
// 개발 목적으로 테스트 시에는 가상의 데이터를 반환할 수 있습니다.
const ALPHA_VANTAGE_API_KEY = process.env.ALPHA_VANTAGE_API_KEY || 'YOUR_ALPHA_VANTAGE_API_KEY';

/**
 * 특정 주식 심볼에 대한 과거 데이터를 Alpha Vantage API에서 가져옵니다.
 * 실제 API 호출 시 API 키와 적절한 엔드포인트가 필요합니다.
 *
 * @param {string} symbol 주식 심볼 (예: "IBM", "AAPL")
 * @returns {Promise<Array<Object>>} 날짜와 종가를 포함하는 주식 데이터 배열
 */
async function fetchStockData(symbol) {
    try {
        // 실제 API 호출 (주석 처리됨):
        // const response = await axios.get(`https://www.alphavantage.co/query`, {
        //     params: {
        //         function: 'TIME_SERIES_DAILY',
        //         symbol: symbol,
        //         apikey: ALPHA_VANTAGE_API_KEY,
        //         outputsize: 'compact' // 'full' 또는 'compact'
        //     }
        // });

        // // Alpha Vantage 응답 구조에 따라 데이터 파싱
        // const timeSeries = response.data['Time Series (Daily)'];
        // if (!timeSeries) {
        //     throw new Error('API 응답에서 시계열 데이터를 찾을 수 없습니다.');
        // }

        // const data = Object.entries(timeSeries).map(([date, dailyData]) => ({
        //     date: date,
        //     close: parseFloat(dailyData['4. close'])
        // }));
        // return data.reverse(); // 최신 데이터가 마지막에 오도록 정렬

        // 개발 및 테스트를 위한 가상 데이터 반환
        console.log(`[stock_api] 가상 데이터를 반환합니다. 심볼: ${symbol}`);
        const mockData = [
            { date: '2024-04-29', close: 170.00 },
            { date: '2024-04-30', close: 171.50 },
            { date: '2024-05-01', close: 172.25 },
            { date: '2024-05-02', close: 170.75 },
            { date: '2024-05-03', close: 173.00 },
            { date: '2024-05-06', close: 174.50 },
            { date: '2024-05-07', close: 175.25 },
            { date: '2024-05-08', close: 176.00 },
            { date: '2024-05-09', close: 175.50 },
            { date: '2024-05-10', close: 177.00 },
            { date: '2024-05-13', close: 178.25 },
            { date: '2024-05-14', close: 179.00 },
            { date: '2024-05-15', close: 178.50 },
            { date: '2024-05-16', close: 180.00 },
            { date: '2024-05-17', close: 181.25 },
            { date: '2024-05-20', close: 182.00 },
            { date: '2024-05-21', close: 181.75 },
            { date: '2024-05-22', close: 183.00 },
            { date: '2024-05-23', close: 184.50 },
            { date: '2024-05-24', close: 185.00 },
        ];
        return mockData;

    } catch (error) {
        console.error(`주식 데이터를 가져오는 중 오류 발생: ${error.message}`);
        return [];
    }
}

module.exports = {
    fetchStockData
};