// src/app.js
const express = require('express');
const app = express();
const port = 3000;
const apiRoutes = require('./routes/index');
const logger = require('./utils/logger');

// JSON 요청 본문을 파싱하기 위한 미들웨어이다.
app.use(express.json());

// API 라우트를 등록한다. 모든 API 요청은 /api 경로 아래에서 처리된다.
app.use('/api', apiRoutes);

// 정의되지 않은 경로에 대한 404 에러 핸들러이다.
app.use((req, res, next) => {
    logger.error(`404 Not Found: ${req.method} ${req.originalUrl}`);
    res.status(404).json({ message: '요청하신 리소스를 찾을 수 없습니다.' });
});

// 일반적인 에러 핸들러 미들웨어이다.
app.use((err, req, res, next) => {
    logger.error(`서버 에러 발생: ${err.message}`, err);
    res.status(500).json({ message: '서버 내부 오류가 발생했습니다.', error: err.message });
});

// 서버를 시작한다.
app.listen(port, () => {
    logger.info(`서버가 http://localhost:${port} 에서 실행 중이다.`);
    logger.info(`환영 메시지: http://localhost:${port}/api/hello?name=Gemini 로 접속해 보세요.`);
    logger.info(`현재 시간: http://localhost:${port}/api/time 로 접속해 보세요.`);
});
