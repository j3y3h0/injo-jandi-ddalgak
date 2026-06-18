import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AnalysisReportGenerator 클래스는 단어 빈도 분석 결과를 기반으로 보고서를 생성합니다.
 * 주로 가장 빈번하게 나타나는 단어들을 정렬하여 출력하는 기능을 제공합니다.
 */
public class AnalysisReportGenerator {

    /**
     * 단어 빈도 맵에서 상위 N개의 단어를 찾아 포맷된 문자열로 반환합니다.
     *
     * @param wordCounts 각 단어와 해당 단어의 출현 횟수를 담은 맵
     * @param topN       보고서에 포함할 상위 단어의 개수
     * @return 상위 N개 단어와 그 빈도를 포함하는 보고서 문자열
     */
    public String generateReport(Map<String, Integer> wordCounts, int topN) {
        if (wordCounts == null || wordCounts.isEmpty()) {
            return "분석할 단어가 없습니다.";
        }

        // 단어 빈도 맵을 스트림으로 변환하여 정렬하고 상위 N개의 항목을 선택합니다.
        List<Map.Entry<String, Integer>> sortedWords = wordCounts.entrySet().stream()
                // 빈도수(값)를 기준으로 내림차순 정렬합니다. 빈도수가 같으면 단어(키)를 기준으로 오름차순 정렬합니다.
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                // 상위 N개만 선택합니다.
                .limit(topN)
                // 리스트로 수집합니다.
                .collect(Collectors.toList());

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("--- 텍스트 분석 보고서 ---
");
        reportBuilder.append("가장 자주 나타나는 단어 (상위 ").append(topN).append("개):
");
        reportBuilder.append("------------------------
");

        if (sortedWords.isEmpty()) {
            reportBuilder.append("상위 단어를 찾을 수 없습니다.
");
        } else {
            for (int i = 0; i < sortedWords.size(); i++) {
                Map.Entry<String, Integer> entry = sortedWords.get(i);
                reportBuilder.append(String.format("%d. '%s': %d회
", (i + 1), entry.getKey(), entry.getValue()));
            }
        }
        reportBuilder.append("------------------------
");

        // 총 단어 수와 고유 단어 수를 추가할 수도 있습니다.
        reportBuilder.append(String.format("총 단어 수: %d개
", wordCounts.values().stream().mapToInt(Integer::intValue).sum()));
        reportBuilder.append(String.format("고유 단어 수: %d개
", wordCounts.size()));

        return reportBuilder.toString();
    }
}
