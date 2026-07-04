const { readJsonFile, writeJsonFile } = require('./utils');
const { filterByCondition, transformData, aggregateData } = require('./dataProcessor');

const INPUT_FILE = 'sample.json';
const OUTPUT_FILE = 'processed_data.json';

async function main() {
    console.log('--- JSON 데이터 처리 유틸리티 시작 ---');

    try {
        // 1. sample.json 파일 읽기
        console.log(`
1. ${INPUT_FILE} 파일 읽기...`);
        const originalData = readJsonFile(INPUT_FILE);
        console.log('원본 데이터:', JSON.stringify(originalData, null, 2));

        // 2. 'category'가 '전자기기'인 상품만 필터링
        console.log("
2. 'category'가 '전자기기'인 상품 필터링...");
        const electronics = filterByCondition(originalData, 'category', '전자기기');
        console.log('필터링된 전자기기 상품:', JSON.stringify(electronics, null, 2));

        // 3. 각 상품의 'price'를 10% 인상하는 변환 적용 (예시)
        console.log("
3. 각 상품의 'price'를 10% 인상하는 변환 적용...");
        const transformedData = transformData(originalData, item => ({
            ...item,
            price: Math.round(item.price * 1.10) // 10% 인상 및 반올림
        }));
        console.log('가격 인상 후 데이터:', JSON.stringify(transformedData, null, 2));

        // 4. 'category'별 'stock' 합계 집계
        console.log("
4. 'category'별 'stock' 합계 집계...");
        const stockByCategory = aggregateData(originalData, 'category', 'stock');
        console.log('카테고리별 재고 합계:', JSON.stringify(stockByCategory, null, 2));

        // 5. 처리된 데이터(예: 필터링된 전자기기 상품)를 새 파일에 저장
        console.log(`
5. 처리된 데이터(전자기기)를 ${OUTPUT_FILE} 파일에 저장...`);
        writeJsonFile(OUTPUT_FILE, electronics);
        console.log(`
처리된 데이터가 ${OUTPUT_FILE}에 성공적으로 저장되었습니다.`);

    } catch (error) {
        console.error('
데이터 처리 중 오류 발생:', error.message);
    }

    console.log('
--- JSON 데이터 처리 유틸리티 종료 ---');
}

main();
