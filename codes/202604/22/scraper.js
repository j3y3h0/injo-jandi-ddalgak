// scraper.js
// 웹 스크래핑 로직을 담당하는 모듈입니다.
const axios = require('axios');
const cheerio = require('cheerio');

/**
 * 주어진 URL에서 HTML 내용을 가져와 Cheerio 객체로 반환합니다.
 * @param {string} url - 스크래핑할 웹 페이지의 URL.
 * @returns {Promise<object>} Cheerio 객체 (HTML 문서).
 */
async function fetchAndParse(url) {
  try {
    console.log(`[스크래퍼] ${url} 에서 HTML 내용을 가져오는 중...`);
    const { data } = await axios.get(url);
    return cheerio.load(data);
  } catch (error) {
    console.error(`[스크래퍼 오류] ${url} HTML 가져오기 실패:`, error.message);
    throw error;
  }
}

/**
 * Cheerio 객체에서 상품 정보를 추출합니다.
 * 이 함수는 예시 웹 페이지 구조에 맞춰 작성되었습니다.
 * 실제 웹 페이지에 따라 셀렉터는 변경되어야 합니다.
 * @param {object} $ - Cheerio 객체.
 * @returns {Array<object>} 추출된 상품 정보 배열 (이름, 가격 포함).
 */
function extractProductInfo($) {
  const products = [];
  // 예시: '.product-item' 클래스를 가진 각 상품 항목을 찾습니다.
  // 실제 웹 페이지의 HTML 구조에 맞춰 셀렉터를 조정해야 합니다.
  $('.product-item').each((i, element) => {
    const name = $(element).find('.product-name').text().trim();
    const price = $(element).find('.product-price').text().trim();

    if (name && price) {
      products.push({ name, price });
    }
  });
  console.log(`[스크래퍼] ${products.length} 개의 상품 정보를 추출했습니다.`);
  return products;
}

module.exports = {
  fetchAndParse,
  extractProductInfo
};
