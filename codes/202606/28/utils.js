// utils.js
const fs = require('fs');

/**
 * 파일이 존재하는지 확인합니다.
 * @param {string} filePath - 확인할 파일 경로.
 * @returns {boolean} 파일이 존재하면 true, 그렇지 않으면 false.
 */
function fileExists(filePath) {
    try {
        fs.accessSync(filePath, fs.constants.F_OK);
        return true;
    } catch (e) {
        return false;
    }
}

/**
 * 주어진 문자열이 비어있거나 null/undefined인지 확인합니다.
 * @param {string} str - 확인할 문자열.
 * @returns {boolean} 비어있거나 null/undefined이면 true, 그렇지 않으면 false.
 */
function isEmptyString(str) {
    return str === null || str === undefined || typeof str !== 'string' || str.trim() === '';
}

module.exports = {
    fileExists,
    isEmptyString
};
