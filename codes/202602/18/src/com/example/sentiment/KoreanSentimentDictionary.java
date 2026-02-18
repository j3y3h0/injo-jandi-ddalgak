// KoreanSentimentDictionary.java
package com.example.sentiment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 한국어 감성 분석을 위한 긍정/부정 단어 사전을 제공하는 유틸리티 클래스이다.
 * 실제 서비스에서는 훨씬 더 방대한 사전과 복잡한 가중치 시스템을 사용한다.
 */
public class KoreanSentimentDictionary {

    private static final Set<String> POSITIVE_WORDS = new HashSet<>(Arrays.asList(
        "좋은", "훌륭한", "긍정적", "발전", "성공", "기쁨", "행복", "최고", "이득", "개선", "성장", "희망", "기대", "성과", "강점", "유리", "환영"
    ));

    private static final Set<String> NEGATIVE_WORDS = new HashSet<>(Arrays.asList(
        "나쁜", "문제", "부정적", "하락", "실패", "슬픔", "불행", "최악", "손실", "악화", "위험", "걱정", "우려", "논란", "약점", "불리", "반대", "압박"
    ));

    /**
     * 주어진 단어가 긍정적인 단어인지 확인한다.
     *
     * @param word 확인할 단어
     * @return 긍정적인 단어이면 true, 아니면 false
     */
    public static boolean isPositive(String word) {
        return POSITIVE_WORDS.contains(word);
    }

    /**
     * 주어진 단어가 부정적인 단어인지 확인한다.
     *
     * @param word 확인할 단어
     * @return 부정적인 단어이면 true, 아니면 false
     */
    public static boolean isNegative(String word) {
        return NEGATIVE_WORDS.contains(word);
    }
}