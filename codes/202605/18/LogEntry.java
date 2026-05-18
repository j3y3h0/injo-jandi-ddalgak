// LogEntry.java
// 이 파일은 로그 항목을 나타내는 데이터 구조를 정의합니다.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private LocalDateTime timestamp; // 로그 발생 시각
    private String level;            // 로그 레벨 (예: INFO, WARN, ERROR)
    private String message;          // 로그 메시지

    public LogEntry(String timestampStr, String level, String message) {
        // ISO_LOCAL_DATE_TIME 형식의 날짜 및 시간 파싱
        this.timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.level = level;
        this.message = message;
    }

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
        return String.format("[%s] [%s] %s",
                             timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                             level,
                             message);
    }
}
