const fs = require('fs');

/**
 * JSON 파일을 읽어 JavaScript 객체로 파싱한다.
 * @param {string} filePath - 읽을 JSON 파일의 경로.
 * @returns {Array<Object> | Object} - 파싱된 JavaScript 객체 또는 배열.
 * @throws {Error} - 파일 읽기 또는 파싱 실패 시 에러를 발생시킨다.
 */
function readJsonFile(filePath) {
    try {
        // 파일 내용을 동기적으로 읽는다.
        const fileContent = fs.readFileSync(filePath, 'utf8');
        // JSON 문자열을 JavaScript 객체로 파싱한다.
        return JSON.parse(fileContent);
    } catch (error) {
        console.error(`Error reading or parsing JSON file at ${filePath}: ${error.message}`);
        throw error;
    }
}

/**
 * JavaScript 객체나 배열을 JSON 문자열로 변환하여 파일에 쓴다.
 * @param {string} filePath - JSON 데이터를 쓸 파일의 경로.
 * @param {Array<Object> | Object} data - JSON으로 변환하여 쓸 JavaScript 객체 또는 배열.
 * @throws {Error} - 파일 쓰기 실패 시 에러를 발생시킨다.
 */
function writeJsonFile(filePath, data) {
    try {
        // JavaScript 객체를 JSON 문자열로 변환한다 (가독성을 위해 2칸 들여쓰기).
        const jsonString = JSON.stringify(data, null, 2);
        // 파일에 동기적으로 쓴다.
        fs.writeFileSync(filePath, jsonString, 'utf8');
        console.log(`Successfully wrote data to ${filePath}`);
    } catch (error) {
        console.error(`Error writing JSON file to ${filePath}: ${error.message}`);
        throw error;
    }
}

// 모듈 외부로 함수들을 내보낸다.
module.exports = {
    readJsonFile,
    writeJsonFile
};
