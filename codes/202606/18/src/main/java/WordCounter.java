import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WordCounter 클래스는 주어진 텍스트에서 단어 빈도를 계산하는 기능을 제공합니다.
 * 텍스트를 정제하고 단어를 추출하여 각 단어의 출현 횟수를 맵 형태로 반환합니다.
 */
public class WordCounter {

    // 단어를 추출하기 위한 정규 표현식. 한글, 영문자, 숫자 조합을 단어로 간주합니다.
    private static final Pattern WORD_PATTERN = Pattern.compile("[\p{IsHangul}a-zA-Z0-9]+");

    /**
     * 주어진 텍스트에서 단어 빈도를 계산합니다.
     * 텍스트는 소문자로 변환되고 구두점 및 특수 문자는 제거됩니다.
     *
     * @param text 분석할 원본 텍스트
     * @return 각 단어와 해당 단어의 출현 횟수를 담은 맵
     */
    public Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        if (text == null || text.trim().isEmpty()) {
            return wordCounts;
        }

        // 텍스트를 소문자로 변환하여 대소문자 구분 없이 단어를 세도록 합니다.
        String lowerCaseText = text.toLowerCase();

        // 정규 표현식을 사용하여 텍스트에서 단어를 찾습니다.
        Matcher matcher = WORD_PATTERN.matcher(lowerCaseText);

        while (matcher.find()) {
            String word = matcher.group();
            // 맵에 단어가 이미 존재하면 카운트를 1 증가시키고, 없으면 새로 추가하며 카운트를 1로 초기화합니다.
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        return wordCounts;
    }
}
