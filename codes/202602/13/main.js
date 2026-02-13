// main.js
// 프로그램의 진입점입니다. CSV 파일을 읽고 처리합니다.

const fs = require('fs');
const path = require('path');
const { logMessage } = require('./utils');
const { parseCsv } = require('./csvParser');
const { calculateAverage } = require('./dataProcessor');

/**
 * 프로그램의 메인 실행 함수입니다.
 * @param {Array<string>} args - 명령줄 인자 배열. (node main.js 이후의 인자들)
 */
async function main(args) {
    if (args.length < 2) {
        logMessage('사용법: node main.js <CSV_파일_경로> <평균을_계산할_컬럼_이름>');
        process.exit(1);
    }

    const csvFilePath = args[0];
    const columnNameForAverage = args[1];
    const absoluteCsvFilePath = path.resolve(process.cwd(), csvFilePath);

    logMessage(`CSV 파일 경로: ${absoluteCsvFilePath}`);
    logMessage(`평균을 계산할 컬럼: ${columnNameForAverage}`);

    try {
        // 파일 존재 여부 확인
        if (!fs.existsSync(absoluteCsvFilePath)) {
            logMessage(`오류: 지정된 CSV 파일이 존재하지 않습니다: ${absoluteCsvFilePath}`);
            process.exit(1);
        }

        // 파일 내용 읽기
        const csvContent = await fs.promises.readFile(absoluteCsvFilePath, 'utf8');
        logMessage('CSV 파일 내용을 성공적으로 읽었습니다.');

        // CSV 파싱
        const parsedData = parseCsv(csvContent);
        if (parsedData.length === 0) {
            logMessage('오류: 파싱된 데이터가 없습니다. CSV 파일이 비어 있거나 형식이 잘못되었습니다.');
            process.exit(1);
        }

        // 데이터 처리 (평균 계산)
        const average = calculateAverage(parsedData, columnNameForAverage);

        if (average !== null) {
            logMessage(`최종 결과: '${columnNameForAverage}' 컬럼의 평균은 ${average} 입니다.`);
        } else {
            logMessage(`'${columnNameForAverage}' 컬럼의 평균을 계산할 수 없었습니다.`);
        }

    } catch (error) {
        logMessage(`예상치 못한 오류가 발생했습니다: ${error.message}`);
        console.error(error); // 개발 시 상세 오류 확인용
        process.exit(1);
    }
}

// 명령줄 인자를 받아 main 함수 실행
main(process.argv.slice(2));
