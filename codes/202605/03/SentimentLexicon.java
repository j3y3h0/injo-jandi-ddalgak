// SentimentLexicon.java
// 감성 분석을 위한 긍정/부정 단어 사전을 관리하는 클래스입니다.

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SentimentLexicon {
    // 긍정적인 단어들의 집합입니다.
    private final Set<String> positiveWords;
    // 부정적인 단어들의 집합입니다.
    private final Set<String> negativeWords;

    public SentimentLexicon() {
        // 실제 애플리케이션에서는 데이터베이스나 파일에서 로드할 수 있습니다.
        // 여기서는 예시를 위해 하드코딩된 단어들을 사용합니다.
        positiveWords = new HashSet<>(Arrays.asList(
            "좋다", "최고", "행복", "기쁘다", "훌륭하다", "만족하다", "감사하다", "사랑", "멋지다", "성공"
        ));
        negativeWords = new HashSet<>(Arrays.asList(
            "나쁘다", "싫다", "슬프다", "불행", "실망", "화나다", "짜증", "문제", "어렵다", "실패"
        ));
    }

    // 주어진 단어가 긍정적인 단어인지 확인합니다.
    public boolean isPositive(String word) {
        return positiveWords.contains(word);
    }

    // 주어진 단어가 부정적인 단어인지 확인합니다.
    public boolean isNegative(String word) {
        return negativeWords.contains(word);
    }
}
