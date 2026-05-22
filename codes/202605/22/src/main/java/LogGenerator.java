package com.gemini.loganalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 샘플 로그 파일을 생성하는 클래스입니다.
 * Log4j2를 사용하여 다양한 레벨의 로그 메시지를 `application.log` 파일에 기록합니다.
 */
public class LogGenerator {

    // Log4j2 로거 인스턴스 생성
    private static final Logger logger = LogManager.getLogger(LogGenerator.class);

    /**
     * 지정된 이름의 로그 파일을 생성하고 다양한 로그 메시지를 기록합니다.
     * @param logFileName 생성할 로그 파일의 이름
     */
    public void generateLogs(String logFileName) {
        System.setProperty("logFilename", logFileName); // Log4j2 설정에 파일 이름 전달
        // Log4j2 설정이 초기화되도록 보장하기 위해 LogManager를 다시 호출할 수 있습니다.
        // 또는, log4j2.xml에서 직접 파일 이름을 지정할 수도 있습니다.
        // 여기서는 System Property를 사용하여 동적으로 파일 이름을 설정하는 예시를 보여줍니다.

        logger.info("로그 파일 생성을 시작합니다: {}", logFileName);

        // 다양한 레벨의 로그 메시지 생성
        logger.info("애플리케이션이 성공적으로 시작되었습니다.");
        logger.debug("디버그 메시지: 사용자 'admin'이 로그인 시도.");
        logger.warn("경고 메시지: 데이터베이스 연결 풀이 거의 고갈되었습니다.");
        logger.info("새로운 세션이 생성되었습니다: SessionID-12345");
        logger.error("오류 메시지: 사용자 인증 실패. 원인: 잘못된 비밀번호.");
        logger.info("백그라운드 작업이 완료되었습니다.");
        logger.warn("경고 메시지: 특정 API 호출이 시간 초과되었습니다.");
        logger.error("심각한 오류: NullPointerException 발생. 스택 트레이스 확인 필요.");
        logger.info("애플리케이션이 정상적으로 종료됩니다.");
        logger.trace("트레이스 메시지: 함수 'processData' 진입.");

        logger.info("로그 파일 생성이 완료되었습니다.");
    }
}
