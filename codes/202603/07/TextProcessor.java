// TextProcessor.java
// 텍스트에서 단어 빈도수를 계산하는 핵심 로직을 포함하는 클래스이다.

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class TextProcessor {

    /**
     * 주어진 텍스트에서 각 단어의 출현 빈도수를 계산한다.
     * 텍스트는 먼저 TextUtility를 통해 정규화되고, 공백을 기준으로 단어를 분리한다.
     *
     * @param text 단어 빈도수를 계산할 원본 텍스트이다.
     * @return 각 단어와 그 빈도수를 매핑하는 Map 객체를 반환한다.
     */
    public Map<String, Integer> getWordFrequency(String text) {
        Map<String, Integer> wordFrequencies = new HashMap<>();

        if (text == null || text.isEmpty()) {
            return wordFrequencies;
        }

        // 텍스트를 정규화하고 공백을 정리한다.
        String normalizedText = TextUtility.normalizeText(text);
        String cleanedText = TextUtility.consolidateSpaces(normalizedText);

        // 공백을 기준으로 텍스트를 단어로 분리한다.
        // 빈 문자열이 생성되지 않도록 필터링한다.
        Arrays.stream(cleanedText.split(" "))
              .filter(word -> !word.isEmpty())
              .forEach(word -> wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1));

        return wordFrequencies;
    }
}
