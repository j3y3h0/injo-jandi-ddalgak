// KeywordCounter.java
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 로그 라인에서 특정 키워드의 발생 횟수를 계산하는 유틸리티 클래스이다.
 */
public class KeywordCounter {

    /**
     * 주어진 로그 라인 목록에서 각 키워드의 발생 횟수를 계산한다.
     *
     * @param logLines 분석할 로그 라인 목록이다.
     * @param keywords 검색할 키워드 목록이다.
     * @return 각 키워드와 해당 발생 횟수를 매핑하는 맵이다.
     */
    public static Map<String, Integer> countKeywords(List<String> logLines, List<String> keywords) {
        // 키워드별 발생 횟수를 저장할 맵을 생성한다.
        Map<String, Integer> keywordCounts = new HashMap<>();

        // 모든 키워드를 맵에 초기화한다 (초기 값은 0).
        for (String keyword : keywords) {
            keywordCounts.put(keyword, 0);
        }

        // 각 로그 라인을 순회하며 키워드를 검색한다.
        for (String line : logLines) {
            // 라인을 소문자로 변환하여 대소문자 구분 없이 검색한다.
            String lowerCaseLine = line.toLowerCase();
            for (String keyword : keywords) {
                // 특정 키워드가 라인에 포함되어 있는지 확인한다.
                if (lowerCaseLine.contains(keyword.toLowerCase())) {
                    // 키워드가 발견되면 해당 키워드의 카운트를 1 증가시킨다.
                    keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);
                }
            }
        }
        return keywordCounts;
    }
}
