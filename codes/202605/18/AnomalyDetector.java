// AnomalyDetector.java
// 이 파일은 로그 데이터에서 이상 징후를 감지하는 로직을 포함합니다.

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnomalyDetector {

    private final int errorThreshold; // 오류 로그 감지 임계값

    public AnomalyDetector(int errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    /**
     * 주어진 로그 엔트리 리스트에서 이상 징후를 감지합니다.
     * 현재는 일정 수 이상의 'ERROR' 레벨 로그 발생을 이상 징후로 판단합니다.
     *
     * @param logEntries 분석할 로그 엔트리 리스트
     * @return 이상 징후 감지 여부
     */
    public boolean detect(List<LogEntry> logEntries) {
        // 'ERROR' 레벨 로그의 수를 계산합니다.
        long errorLogCount = logEntries.stream()
                .filter(entry -> "ERROR".equals(entry.getLevel()))
                .count();

        System.out.printf("분석된 총 로그 수: %d, ERROR 로그 수: %d%n", logEntries.size(), errorLogCount);

        // 오류 로그 수가 임계값을 초과하는지 확인합니다.
        if (errorLogCount >= errorThreshold) {
            System.out.println("이상 징후 감지: ERROR 로그 수가 임계값(" + errorThreshold + ")을 초과했습니다.");
            return true;
        } else {
            System.out.println("정상: ERROR 로그 수가 임계값(" + errorThreshold + ") 이내입니다.");
            return false;
        }
    }

    /**
     * 로그 레벨별 분포를 출력합니다. (이상 징후 감지는 아님, 보조 분석 기능)
     * @param logEntries 분석할 로그 엔트리 리스트
     */
    public void analyzeLogLevelDistribution(List<LogEntry> logEntries) {
        Map<String, Long> levelCounts = logEntries.stream()
                .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));

        System.out.println("
로그 레벨별 분포:");
        levelCounts.forEach((level, count) ->
                System.out.printf("  - %s: %d%n", level, count));
    }
}
