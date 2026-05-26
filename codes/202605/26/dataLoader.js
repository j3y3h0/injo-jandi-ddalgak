// dataLoader.js
/**
 * @typedef {Object} StockEntry
 * @property {string} date - 날짜 (YYYY-MM-DD)
 * @property {number} price - 종가
 */

/**
 * 모의 주식 데이터를 비동기적으로 로드합니다.
 * 실제 환경에서는 API 호출 또는 파일 시스템에서 데이터를 읽어옵니다.
 * @returns {Promise<StockEntry[]>} 주식 데이터 배열
 */
export async function loadStockData() {
    // 실제 데이터 대신 모의 데이터를 사용합니다.
    // 이 부분은 실제 주식 데이터 API 호출 로직으로 대체될 수 있습니다.
    const mockData = [
        { date: "2023-01-02", price: 100 }, { date: "2023-01-03", price: 102 },
        { date: "2023-01-04", price: 101 }, { date: "2023-01-05", price: 105 },
        { date: "2023-01-06", price: 103 }, { date: "2023-01-09", price: 107 },
        { date: "2023-01-10", price: 109 }, { date: "2023-01-11", price: 108 },
        { date: "2023-01-12", price: 112 }, { date: "2023-01-13", price: 115 },
        { date: "2023-01-16", price: 113 }, { date: "2023-01-17", price: 118 },
        { date: "2023-01-18", price: 120 }, { date: "2023-01-19", price: 122 },
        { date: "2023-01-20", price: 121 }, { date: "2023-01-23", price: 125 },
        { date: "2023-01-24", price: 128 }, { date: "2023-01-25", price: 127 },
        { date: "2023-01-26", price: 130 }, { date: "2023-01-27", price: 133 },
        { date: "2023-01-30", price: 131 }, { date: "2023-01-31", price: 135 },
        { date: "2023-02-01", price: 138 }, { date: "2023-02-02", price: 136 },
        { date: "2023-02-03", price: 140 }, { date: "2023-02-06", price: 142 },
        { date: "2023-02-07", price: 145 }, { date: "2023-02-08", price: 143 },
        { date: "2023-02-09", price: 147 }, { date: "2023-02-10", price: 150 },
    ];
    return new Promise(resolve => {
        setTimeout(() => resolve(mockData), 500); // 비동기 로딩 시뮬레이션
    });
}
