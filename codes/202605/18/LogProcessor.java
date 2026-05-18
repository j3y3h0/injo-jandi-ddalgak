// LogProcessor.java
// 이 파일은 원시 로그 문자열을 파싱하여 LogEntry 객체로 변환하고 관리합니다.

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogProcessor {
    private List<LogEntry> logEntries; // 처리된 로그 항목들을 저장하는 리스트

    public LogProcessor() {
        this.logEntries = new ArrayList<>();
    }

    /**
     * 주어진 원시 로그 문자열 리스트를 파싱하여 LogEntry 객체 리스트를 생성합니다.
     * 로그 형식: [YYYY-MM-DDTHH:MM:SS] [LEVEL] MESSAGE
     *
     * @param rawLogs 파싱할 원시 로그 문자열 리스트
     */
    public void processLogs(List<String> rawLogs) {
        // 로그 파싱을 위한 정규 표현식
        // 예: [2026-05-18T10:00:00] [INFO] 사용자 로그인 성공
        String logPattern = "\[(\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2})\] \[([A-Z]+)\] (.+)";
        Pattern pattern = Pattern.compile(logPattern);

        for (String rawLog : rawLogs) {
            Matcher matcher = pattern.matcher(rawLog);
            if (matcher.matches()) {
                String timestampStr = matcher.group(1);
                String level = matcher.group(2);
                String message = matcher.group(3);
                logEntries.add(new LogEntry(timestampStr, level, message));
            } else {
                System.err.println("경고: 알 수 없는 형식의 로그입니다 - " + rawLog);
            }
        }
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }
}
