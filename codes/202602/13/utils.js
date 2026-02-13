// utils.js
// 유틸리티 함수들을 모아둔 파일입니다.

/**
 * 콘솔에 메시지를 로깅합니다.
 * @param {string} message - 로깅할 메시지.
 */
function logMessage(message) {
    console.log(`[정보] ${message}`);
}

module.exports = {
    logMessage
};
