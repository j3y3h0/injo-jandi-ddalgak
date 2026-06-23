// LogAnalyzer.java
// 파싱된 LogEntry 객체들을 분석하여 통계를 생성합니다.

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private final List<LogEntry> logEntries;

    public LogAnalyzer(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    /**
     * 로그 레벨별 발생 횟수를 계산합니다.
     * 예: INFO: 100, WARN: 50, ERROR: 10
     * @return 로그 레벨을 키로, 발생 횟수를 값으로 하는 Map
     */
    public Map<String, Long> analyzeLogLevels() {
        return logEntries.stream()
                         .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));
    }

    /**
     * 고유한 로그 소스(예: 클래스명 또는 서비스명)의 발생 횟수를 계산합니다.
     * @return 로그 소스를 키로, 발생 횟수를 값으로 하는 Map
     */
    public Map<String, Long> analyzeUniqueSources() {
        return logEntries.stream()
                         .collect(Collectors.groupingBy(LogEntry::getSource, Collectors.counting()));
    }

    /**
     * 특정 레벨의 로그 메시지 목록을 필터링합니다.
     * @param level 필터링할 로그 레벨
     * @return 해당 레벨의 LogEntry 목록
     */
    public List<LogEntry> filterLogsByLevel(String level) {
        return logEntries.stream()
                         .filter(entry -> entry.getLevel().equalsIgnoreCase(level))
                         .collect(Collectors.toList());
    }
}
