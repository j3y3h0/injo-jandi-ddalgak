package com.gemini.loganalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 로그 파일을 읽고 파싱하며, 간단한 분석을 수행하는 클래스입니다.
 * 정규 표현식을 사용하여 각 로그 라인에서 타임스탬프, 레벨, 메시지를 추출합니다.
 */
public class LogProcessor {

    // 로그 라인을 파싱하기 위한 정규 표현식 패턴 정의
    // 예시 로그 형식: [2026-05-22 10:00:00,123] [INFO] 메시지 내용
    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2},\d{3})\] \[(\w+)\] (.*)$"
    );

    // 타임스탬프 파싱을 위한 날짜/시간 포매터
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    /**
     * 지정된 로그 파일을 읽고 각 로그 라인을 파싱합니다.
     * @param logFilePath 분석할 로그 파일의 경로
     * @return 파싱된 LogEntry 객체들의 리스트
     */
    public List<LogEntry> parseLogFile(String logFilePath) {
        List<LogEntry> logEntries = new ArrayList<>();
        System.out.println("로그 파일 파싱을 시작합니다: " + logFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = LOG_PATTERN.matcher(line);
                if (matcher.matches()) {
                    // 정규 표현식 매칭 성공 시 LogEntry 객체 생성
                    LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER);
                    String level = matcher.group(2);
                    String message = matcher.group(3);
                    logEntries.add(new LogEntry(timestamp, level, message));
                } else {
                    // 파싱할 수 없는 라인은 건너뛰거나 경고 메시지 출력
                    System.err.println("경고: 파싱할 수 없는 로그 라인 발견 - " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("오류: 로그 파일을 읽는 중 문제가 발생했습니다: " + e.getMessage());
        }
        System.out.println("로그 파일 파싱이 완료되었습니다. 총 " + logEntries.size() + "개의 로그 항목을 파싱했습니다.");
        return logEntries;
    }

    /**
     * 파싱된 로그 항목 리스트를 분석하여 로그 레벨별 개수를 집계합니다.
     * @param logEntries 분석할 LogEntry 객체들의 리스트
     */
    public void analyzeLogs(List<LogEntry> logEntries) {
        System.out.println("
로그 분석 결과를 출력합니다:");
        Map<String, Integer> levelCounts = new HashMap<>();

        for (LogEntry entry : logEntries) {
            String level = entry.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);
        }

        System.out.println("--- 로그 레벨별 통계 ---");
        levelCounts.forEach((level, count) ->
                System.out.println(String.format("  [%s]: %d 건", level, count))
        );

        // 특히 ERROR 로그에 대한 추가 정보 제공
        int errorCount = levelCounts.getOrDefault("ERROR", 0);
        if (errorCount > 0) {
            System.out.println("
--- 오류 메시지 상세 ---");
            logEntries.stream()
                      .filter(entry -> "ERROR".equals(entry.getLevel()))
                      .forEach(entry -> System.out.println("  " + entry.getTimestamp().format(DATE_TIME_FORMATTER) + ": " + entry.getMessage()));
        } else {
            System.out.println("
오류(ERROR) 로그 메시지는 발견되지 않았습니다.");
        }
    }
}
