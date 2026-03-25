// src/controllers/greetingController.js
const logger = require('../utils/logger');

/**
 * 환영 메시지를 반환하는 컨트롤러 함수이다.
 * @param {Object} req - Express 요청 객체이다.
 * @param {Object} res - Express 응답 객체이다.
 */
exports.sayHello = (req, res) => {
    logger.info('sayHello 컨트롤러 호출');
    const name = req.query.name || '방문객';
    res.status(200).json({ message: `안녕하세요, ${name}님! Simple Express App에 오신 것을 환영합니다.` });
};

/**
 * 현재 시간을 반환하는 컨트롤러 함수이다.
 * @param {Object} req - Express 요청 객체이다.
 * @param {Object} res - Express 응답 객체이다.
 */
exports.getCurrentTime = (req, res) => {
    logger.info('getCurrentTime 컨트롤러 호출');
    const currentTime = new Date().toLocaleString('ko-KR');
    res.status(200).json({ currentTime: currentTime });
};
