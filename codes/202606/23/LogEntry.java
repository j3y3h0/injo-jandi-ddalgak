// LogEntry.java
// 로그 파일의 각 항목을 나타내는 데이터 클래스입니다.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private LocalDateTime timestamp;
    private String level; // 예: INFO, WARN, ERROR
    private String source; // 예: com.example.MyService
    private String message;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry(LocalDateTime timestamp, String level, String source, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.source = source;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
               "timestamp=" + timestamp.format(FORMATTER) +
               ", level='" + level + ''' +
               ", source='" + source + ''' +
               ", message='" + message + ''' +
               '}';
    }
}
