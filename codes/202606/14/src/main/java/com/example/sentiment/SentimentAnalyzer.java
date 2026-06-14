package com.example.sentiment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 텍스트의 감성을 분석하는 유틸리티 클래스입니다.
 * 간단한 키워드 매칭을 통해 긍정, 부정 또는 중립 감성을 판단합니다.
 */
public class SentimentAnalyzer {

    // 긍정적인 감성을 나타내는 키워드 집합
    private static final Set<String> POSITIVE_KEYWORDS = new HashSet<>(Arrays.asList(
            "좋다", "최고", "훌륭하다", "만족하다", "행복하다", "기쁘다", "감사하다", "사랑하다", "성공하다", "완벽하다"
    ));

    // 부정적인 감성을 나타내는 키워드 집합
    private static final Set<String> NEGATIVE_KEYWORDS = new HashSet<>(Arrays.asList(
            "나쁘다", "최악", "끔찍하다", "불만족하다", "슬프다", "화나다", "짜증나다", "실망하다", "실패하다", "문제"
    ));

    /**
     * 주어진 텍스트의 감성을 분석합니다.
     * 텍스트 내에서 긍정 및 부정 키워드의 출현 빈도를 기반으로 감성을 결정합니다.
     *
     * @param text 분석할 텍스트 문자열
     * @return 텍스트의 감성 분석 결과 (POSITIVE, NEGATIVE, NEUTRAL)
     */
    public SentimentResult analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            return SentimentResult.NEUTRAL;
        }

        String lowerCaseText = text.toLowerCase();
        int positiveScore = 0;
        int negativeScore = 0;

        // 긍정 키워드 탐색
        for (String keyword : POSITIVE_KEYWORDS) {
            if (lowerCaseText.contains(keyword)) {
                positiveScore++;
            }
        }

        // 부정 키워드 탐색
        for (String keyword : NEGATIVE_KEYWORDS) {
            if (lowerCaseText.contains(keyword)) {
                negativeScore++;
            }
        }

        if (positiveScore > negativeScore) {
            return SentimentResult.POSITIVE;
        } else if (negativeScore > positiveScore) {
            return SentimentResult.NEGATIVE;
        } else {
            // 점수가 같거나 키워드가 발견되지 않은 경우 중립으로 판단
            return SentimentResult.NEUTRAL;
        }
    }
}