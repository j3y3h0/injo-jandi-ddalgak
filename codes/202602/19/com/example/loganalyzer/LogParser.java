// LogParser.java
package com.example.loganalyzer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 로그 문자열을 파싱하여 LogEntry 객체로 변환하는 유틸리티 클래스입니다.
 * 특정 로그 형식에 맞춰 파싱을 수행합니다.
 *
 * 예시 로그 형식: [2026-02-19 10:00:01] INFO - Application started.
 */
public class LogParser {
    // 로그 라인 패턴 (날짜, 시간, 레벨, 메시지)
    private static final Pattern LOG_PATTERN = Pattern.compile("^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})\]\s+(\w+)\s+-\s+(.*)$");
    // 날짜/시간 형식 지정자
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 주어진 로그 라인 문자열을 파싱하여 LogEntry 객체를 생성합니다.
     * 파싱에 실패하면 Optional.empty()를 반환합니다.
     *
     * @param logLine 파싱할 로그 라인 문자열
     * @return 파싱된 LogEntry 객체를 포함하는 Optional, 또는 파싱 실패 시 Optional.empty()
     */
    public static Optional<LogEntry> parseLogLine(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.matches()) {
            try {
                LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER);
                String level = matcher.group(2);
                String message = matcher.group(3);
                return Optional.of(new LogEntry(timestamp, level, message));
            } catch (DateTimeParseException e) {
                System.err.println("날짜/시간 파싱 오류: " + logLine + " - " + e.getMessage());
                return Optional.empty();
            }
        }
        return Optional.empty(); // 패턴과 일치하지 않음
    }
}
