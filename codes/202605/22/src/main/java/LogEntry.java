package com.gemini.loganalyzer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 로그 항목을 나타내는 데이터 클래스입니다.
 * 각 로그 라인에서 파싱된 정보를 저장합니다.
 */
public class LogEntry {
    private LocalDateTime timestamp; // 로그 발생 시각
    private String level;            // 로그 레벨 (예: INFO, WARN, ERROR)
    private String message;          // 로그 메시지 내용

    public LogEntry(LocalDateTime timestamp, String level, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
    }

    // Getter 메서드들
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    // Setter 메서드들 (필요한 경우)
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        // 로그 항목을 문자열로 표현하는 메서드
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        return String.format("[%s] [%s] %s", timestamp.format(formatter), level, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry logEntry = (LogEntry) o;
        return Objects.equals(timestamp, logEntry.timestamp) &&
               Objects.equals(level, logEntry.level) &&
               Objects.equals(message, logEntry.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, level, message);
    }
}
