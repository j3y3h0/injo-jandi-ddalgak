// LogEntry.java
package com.example.loganalyzer;

import java.time.LocalDateTime;

/**
 * 로그 파일의 각 엔트리를 나타내는 데이터 모델 클래스입니다.
 * 날짜, 시간, 로그 레벨, 메시지 내용을 포함합니다.
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

    // Getter 메서드
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
               "timestamp=" + timestamp +
               ", level='" + level + ''' +
               ", message='" + message + ''' +
               '}';
    }
}
