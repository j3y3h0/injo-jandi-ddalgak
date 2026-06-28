// main.js
const path = require('path');
const { parseCsvFile, calculateMean, calculateMedian } = require('./data_processor');
const { fileExists, isEmptyString } = require('./utils');

async function main() {
    // 명령줄 인자 파싱: node main.js <filePath> <columnName>
    const args = process.argv.slice(2);
    if (args.length < 2) {
        console.log('사용법: node main.js <CSV_파일_경로> <분석할_컬럼_이름>');
        console.log('예시: node main.js data.csv 매출');
        process.exit(1);
    }

    const filePath = path.resolve(args[0]);
    const columnName = args[1];

    if (!fileExists(filePath)) {
        console.error(`오류: 지정된 파일 '${filePath}'을(를) 찾을 수 없습니다.`);
        process.exit(1);
    }

    if (isEmptyString(columnName)) {
        console.error('오류: 분석할 컬럼 이름을 지정해야 합니다.');
        process.exit(1);
    }

    console.log(`파일 '${filePath}'에서 컬럼 '${columnName}' 데이터 분석을 시작합니다.`);

    try {
        const data = await parseCsvFile(filePath);

        if (data.length === 0) {
            console.log('CSV 파일에 데이터가 없습니다.');
            return;
        }

        // 컬럼 존재 여부 확인
        const firstRow = data[0];
        if (!firstRow.hasOwnProperty(columnName)) {
            console.error(`오류: CSV 파일에 '${columnName}' 컬럼이 존재하지 않습니다. 존재하는 컬럼: ${Object.keys(firstRow).join(', ')}`);
            process.exit(1);
        }

        const mean = calculateMean(data, columnName);
        const median = calculateMedian(data, columnName);

        console.log('
--- 분석 결과 ---');
        console.log(`대상 컬럼: ${columnName}`);
        console.log(`평균: ${mean.toFixed(2)}`);
        console.log(`중앙값: ${median.toFixed(2)}`);

    } catch (error) {
        console.error(`데이터 처리 중 오류 발생: ${error.message}`);
        process.exit(1);
    }
}

main();
