package com.gemini.loganalyzer;

import java.util.List;

/**
 * 애플리케이션의 메인 클래스입니다.
 * 로그 생성 및 분석 워크플로우를 담당합니다.
 */
public class Main {
    private static final String LOG_FILE_NAME = "application.log"; // 생성 및 분석할 로그 파일 이름

    public static void main(String[] args) {
        System.out.println("로그 분석기 애플리케이션을 시작합니다.");

        // 1. 로그 파일 생성
        LogGenerator logGenerator = new LogGenerator();
        System.out.println("
[로그 생성 단계]");
        logGenerator.generateLogs(LOG_FILE_NAME);

        // 2. 로그 파일 분석
        LogProcessor logProcessor = new LogProcessor();
        System.out.println("
[로그 분석 단계]");
        List<LogEntry> logEntries = logProcessor.parseLogFile(LOG_FILE_NAME);

        // 3. 분석 결과 출력
        if (!logEntries.isEmpty()) {
            logProcessor.analyzeLogs(logEntries);
        } else {
            System.out.println("
분석할 로그 항목이 없습니다.");
        }

        System.out.println("
로그 분석기 애플리케이션을 종료합니다.");
    }
}
