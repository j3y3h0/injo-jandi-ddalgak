// src/utils/logger.js

/**
 * 간단한 로깅 유틸리티 모듈이다.
 * 실제 애플리케이션에서는 Winston 또는 Pino와 같은 더 강력한 로깅 라이브러리를 사용하는 것이 좋다.
 */
const logger = {
    /**
     * 정보 메시지를 콘솔에 출력한다.
     * @param {string} message - 출력할 메시지이다.
     */
    info: (message) => {
        console.log(`[INFO] ${new Date().toISOString()} - ${message}`);
    },

    /**
     * 에러 메시지를 콘솔에 출력한다.
     * @param {string} message - 출력할 메시지이다.
     * @param {Error} [error] - 함께 기록할 에러 객체이다.
     */
    error: (message, error) => {
        console.error(`[ERROR] ${new Date().toISOString()} - ${message}`);
        if (error) {
            console.error(error);
        }
    }
};

module.exports = logger;
