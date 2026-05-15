import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// SentimentAnalyzer.java
public class SentimentAnalyzer {

    // 긍정적인 단어들의 집합이다.
    private static final Set<String> POSITIVE_WORDS = new HashSet<>(Arrays.asList(
            "좋다", "최고", "훌륭하다", "만족하다", "추천하다", "기쁘다", "행복하다", "대단하다", "멋지다", "감사하다"
    ));

    // 부정적인 단어들의 집합이다.
    private static final Set<String> NEGATIVE_WORDS = new HashSet<>(Arrays.asList(
            "나쁘다", "최악", "실망하다", "불만족", "문제", "슬프다", "화나다", "끔찍하다", "아쉽다", "불쾌하다"
    ));

    /**
     * 주어진 텍스트의 감성을 분석한다.
     * 긍정적인 단어와 부정적인 단어의 출현 빈도를 기반으로 감성을 결정한다.
     *
     * @param text 분석할 텍스트이다.
     * @return 텍스트의 감성 (POSITIVE, NEGATIVE, NEUTRAL)이다.
     */
    public Sentiment analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            return Sentiment.NEUTRAL;
        }

        // 텍스트를 소문자로 변환하고 단어로 분리한다.
        String lowerCaseText = text.toLowerCase();
        // 간단한 토큰화를 위해 공백을 기준으로 분리한다.
        // 더 정교한 분석을 위해서는 Nouns, Adjectives 등을 추출하는 NLP 라이브러리가 필요하다.
        String[] words = lowerCaseText.split("\s+|[.,!?;'"()]");

        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : words) {
            String cleanWord = word.trim();
            if (cleanWord.isEmpty()) {
                continue;
            }
            if (POSITIVE_WORDS.contains(cleanWord)) {
                positiveCount++;
            } else if (NEGATIVE_WORDS.contains(cleanWord)) {
                negativeCount++;
            }
        }

        if (positiveCount > negativeCount) {
            return Sentiment.POSITIVE;
        } else if (negativeCount > positiveCount) {
            return Sentiment.NEGATIVE;
        } else {
            return Sentiment.NEUTRAL;
        }
    }
}
