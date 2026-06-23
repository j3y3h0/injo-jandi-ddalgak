// LogParser.java
// 로그 문자열을 LogEntry 객체로 파싱하는 기능을 제공합니다.

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    // 예시 로그 형식: [2026-06-23 10:30:00] [INFO] [com.example.ServiceA] 사용자 로그인 성공
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})\] \[([A-Z]+)\] \[(.+?)\] (.+)"
    );

    /**
     * 주어진 로그 문자열을 파싱하여 LogEntry 객체로 변환합니다.
     * 파싱에 실패할 경우 null을 반환합니다.
     * @param logLine 파싱할 로그 문자열
     * @return 파싱된 LogEntry 객체 또는 파싱 실패 시 null
     */
    public static LogEntry parse(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.matches()) {
            try {
                LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1), java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String level = matcher.group(2);
                String source = matcher.group(3);
                String message = matcher.group(4);
                return new LogEntry(timestamp, level, source, message);
            } catch (DateTimeParseException e) {
                System.err.println("시간 파싱 오류: " + matcher.group(1) + " - " + e.getMessage());
                return null;
            } catch (Exception e) {
                System.err.println("로그 파싱 중 알 수 없는 오류 발생: " + logLine + " - " + e.getMessage());
                return null;
            }
        }
        return null; // 패턴이 일치하지 않음
    }
}
