// src/routes/index.js
const express = require('express');
const router = express.Router();
const greetingController = require('../controllers/greetingController');
const logger = require('../utils/logger');

// 모든 요청에 대해 로그를 남기는 미들웨어이다.
router.use((req, res, next) => {
    logger.info(`요청 수신: ${req.method} ${req.url}`);
    next();
});

/**
 * @route GET /api/hello
 * @description 사용자에게 환영 메시지를 반환한다.
 * @param {string} [name] - 선택적 쿼리 파라미터로, 환영 메시지에 사용될 이름이다.
 */
router.get('/hello', greetingController.sayHello);

/**
 * @route GET /api/time
 * @description 현재 서버 시간을 반환한다.
 */
router.get('/time', greetingController.getCurrentTime);

module.exports = router;
