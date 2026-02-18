// SentimentAnalyzer.java
package com.example.sentiment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 뉴스 기사의 감성을 분석하는 클래스이다.
 * 간단한 규칙 기반의 감성 분석을 수행하며, 긍정/부정 단어 사전을 활용한다.
 */
public class SentimentAnalyzer {

    // 한글 단어를 추출하기 위한 정규 표현식이다.
    // 여기서는 공백이나 구두점으로 구분된 한글 단어를 간단히 추출한다.
    private static final Pattern KOREAN_WORD_PATTERN = Pattern.compile("[가-힣]+");

    /**
     * 주어진 뉴스 기사의 감성을 분석한다.
     *
     * @param article 분석할 뉴스 기사 객체
     * @return 분석된 감성 (긍정, 부정, 중립)
     */
    public Sentiment analyze(NewsArticle article) {
        if (article == null || article.getContent() == null || article.getContent().trim().isEmpty()) {
            return Sentiment.NEUTRAL;
        }

        String content = article.getContent().toLowerCase(); // 감성 사전과 일관성을 위해 소문자로 변환 (필요시)
        int positiveScore = 0;
        int negativeScore = 0;

        Matcher matcher = KOREAN_WORD_PATTERN.matcher(content);
        while (matcher.find()) {
            String word = matcher.group();
            if (KoreanSentimentDictionary.isPositive(word)) {
                positiveScore++;
            } else if (KoreanSentimentDictionary.isNegative(word)) {
                negativeScore++;
            }
        }

        if (positiveScore > negativeScore) {
            return Sentiment.POSITIVE;
        } else if (negativeScore > positiveScore) {
            return Sentiment.NEGATIVE;
        } else {
            return Sentiment.NEUTRAL;
        }
    }

    /**
     * 감성 분석 결과를 나타내는 열거형이다.
     */
    public enum Sentiment {
        POSITIVE("긍정적"),
        NEGATIVE("부정적"),
        NEUTRAL("중립적");

        private final String label;

        Sentiment(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}