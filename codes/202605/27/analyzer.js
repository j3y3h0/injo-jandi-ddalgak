// analyzer.js
// 판매 데이터를 분석하는 함수들을 정의한다.

/**
 * 판매 데이터를 카테고리별로 집계하여 총 판매액을 계산한다.
 * @param {Array<Object>} sales - 판매 기록 배열. 각 객체는 product, category, price, quantity 속성을 포함해야 한다.
 * @returns {Object} 카테고리별 총 판매액을 담은 객체. (예: { "전자기기": 1500000, "가구": 230000 })
 */
export function analyzeSalesByCategory(sales) {
    const categorySales = {};

    for (const sale of sales) {
        const revenue = sale.price * sale.quantity;
        if (categorySales[sale.category]) {
            categorySales[sale.category] += revenue;
        } else {
            categorySales[sale.category] = revenue;
        }
    }
    return categorySales;
}
