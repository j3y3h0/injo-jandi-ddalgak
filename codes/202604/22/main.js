// main.js
// 웹 스크래퍼의 메인 실행 파일입니다.
const { fetchAndParse, extractProductInfo } = require('./scraper');
const { saveDataToFile } = require('./utils');

// 이 URL은 실제 존재하는 웹 페이지여야 합니다.
// 예시로 'http://quotes.toscrape.com/'과 같은 간단한 페이지를 사용할 수 있으나,
// 이 프로젝트는 '.product-item' 셀렉터에 맞춰져 있으므로, 해당 구조를 가진 페이지가 필요합니다.
// 여기서는 예시를 위한 가상의 URL로 설정하며, 실제로 동작시키려면 유효한 URL로 변경해야 합니다.
const TARGET_URL = 'https://example.com/products'; // 실제 스크래핑할 웹 페이지 URL로 변경하세요.

async function main() {
  console.log('--- 웹 스크래퍼 시작 ---');
  try {
    // 가상의 웹 페이지 구조를 위한 더미 HTML입니다.
    // TARGET_URL이 실제로 동작하지 않는 경우를 대비합니다.
    const dummyHtmlContent = `
      <!DOCTYPE html>
      <html lang="ko">
      <head>
          <title>상품 목록 예시</title>
      </head>
      <body>
          <h1>상품 목록</h1>
          <div class="product-item">
              <span class="product-name">노트북 Pro</span>
              <span class="product-price">1,500,000원</span>
          </div>
          <div class="product-item">
              <span class="product-name">스마트폰 Ultra</span>
              <span class="product-price">1,200,000원</span>
          </div>
          <div class="product-item">
              <span class="product-name">무선 이어폰</span>
              <span class="product-price">250,000원</span>
          </div>
          <div class="non-product-item">
              <span>관련 없는 항목</span>
          </div>
      </body>
      </html>
    `;

    // 실제 웹 페이지에서 데이터를 가져오거나, 테스트를 위해 더미 HTML을 사용합니다.
    // 만약 TARGET_URL이 유효하다면 아래 코드를 활성화하고 dummyHtmlContent 부분을 제거할 수 있습니다.
    // const $ = await fetchAndParse(TARGET_URL);

    // 더미 HTML을 Cheerio로 로드합니다.
    const cheerio = require('cheerio');
    const $ = cheerio.load(dummyHtmlContent);


    const products = extractProductInfo($);

    if (products.length > 0) {
      await saveDataToFile('products.json', products);
      console.log('스크래핑된 데이터:', products);
    } else {
      console.log('추출된 상품 정보가 없습니다.');
    }

  } catch (error) {
    console.error('--- 웹 스크래퍼 실행 중 오류 발생 ---', error);
  } finally {
    console.log('--- 웹 스크래퍼 종료 ---');
  }
}

main();
