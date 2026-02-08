// TextAnalyzer.java
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 텍스트를 분석하여 단어의 빈도를 계산하는 클래스입니다.
 * 텍스트 정제, 단어 추출 및 빈도 계산 기능을 제공합니다.
 */
public class TextAnalyzer {

    /**
     * 주어진 텍스트에서 단어 빈도를 분석하여 맵 형태로 반환합니다.
     * 텍스트는 소문자로 변환되고, 비알파벳 문자는 공백으로 처리됩니다.
     *
     * @param text 분석할 원본 텍스트
     * @return 단어와 그 단어의 빈도를 담고 있는 맵
     */
    public Map<String, Integer> analyze(String text) {
        // 텍스트를 소문자로 변환하여 대소문자 구분을 없앱니다.
        String lowerCaseText = text.toLowerCase();

        // 정규식을 사용하여 알파벳이 아닌 모든 문자를 공백으로 대체합니다.
        // 예를 들어, "Hello, world!"는 "hello world"가 됩니다.
        String cleanedText = lowerCaseText.replaceAll("[^a-z가-힣\s]", " ");

        // 공백을 기준으로 텍스트를 단어로 분리합니다.
        // trim()을 사용하여 앞뒤 공백을 제거하고, " "는 하나 이상의 공백을 의미합니다.
        String[] words = cleanedText.trim().split("\s+");

        // 단어 빈도를 저장할 맵을 생성합니다.
        Map<String, Integer> wordFrequencies = new HashMap<>();

        // 각 단어의 빈도를 계산합니다.
        for (String word : words) {
            // 비어있는 문자열은 단어로 처리하지 않습니다.
            if (!word.isEmpty()) {
                wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequencies;
    }

    /**
     * 단어 빈도 맵에서 가장 빈번하게 등장하는 단어들을 지정된 개수만큼 반환합니다.
     * 결과는 빈도수를 기준으로 내림차순 정렬됩니다.
     *
     * @param wordFrequencies 단어와 빈도가 담긴 맵
     * @param limit 반환할 상위 단어의 개수
     * @return 상위 N개의 단어와 빈도를 담고 있는 정렬된 맵
     */
    public Map<String, Integer> getTopNWords(Map<String, Integer> wordFrequencies, int limit) {
        // 맵 엔트리를 스트림으로 변환하여 빈도수를 기준으로 내림차순 정렬합니다.
        // 그리고 지정된 개수(limit)만큼 잘라냅니다.
        return wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                // 결과를 LinkedHashMap에 수집하여 정렬 순서를 유지합니다.
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // 병합 함수 (동일 키 발생 시 처리, 여기서는 발생하지 않음)
                        LinkedHashMap::new // 순서 유지를 위한 LinkedHashMap 사용
                ));
    }
}
