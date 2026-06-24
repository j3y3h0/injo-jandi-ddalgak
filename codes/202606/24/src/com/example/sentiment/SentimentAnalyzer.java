// SentimentAnalyzer.java
package com.example.sentiment;

import java.util.HashMap;
import java.util.Map;

/**
 * 텍스트의 감성을 분석하는 클래스이다.
 * 간단한 키워드 기반 방식을 사용한다.
 */
public class SentimentAnalyzer {

    private final Map<String, Integer> sentimentLexicon; // 감성 사전

    /**
     * 새로운 SentimentAnalyzer 객체를 생성하고 감성 사전을 초기화한다.
     */
    public SentimentAnalyzer() {
        sentimentLexicon = new HashMap<>();
        // 긍정적인 단어들 (예시)
        sentimentLexicon.put("좋다", 1);
        sentimentLexicon.put("최고", 1);
        sentimentLexicon.put("기쁘다", 1);
        sentimentLexicon.put("행복하다", 1);
        sentimentLexicon.put("만족하다", 1);
        sentimentLexicon.put("훌륭하다", 1);
        sentimentLexicon.put("아름답다", 1);
        sentimentLexicon.put("성공", 1);
        sentimentLexicon.put("긍정적", 1);

        // 부정적인 단어들 (예시)
        sentimentLexicon.put("나쁘다", -1);
        sentimentLexicon.put("최악", -1);
        sentimentLexicon.put("슬프다", -1);
        sentimentLexicon.put("불행", -1);
        sentimentLexicon.put("실망", -1);
        sentimentLexicon.put("끔찍하다", -1);
        sentimentLexicon.put("실패", -1);
        sentimentLexicon.put("부정적", -1);
        sentimentLexicon.put("어렵다", -1);
    }

    /**
     * 주어진 텍스트의 감성을 분석한다.
     * @param text 분석할 텍스트
     * @return 감성 분석 결과 (SentimentResult 객체)
     */
    public SentimentResult analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new SentimentResult("중립", 0.0);
        }

        int totalScore = 0;
        int wordCount = 0;

        // 텍스트를 소문자로 변환하고 단어로 분리한다.
        // 여기서는 간단하게 공백으로 분리하지만, 실제로는 형태소 분석이 필요하다.
        String[] words = text.toLowerCase().split("\s+");

        for (String word : words) {
            // 구두점 제거 (간단화)
            String cleanWord = word.replaceAll("[^가-힣a-zA-Z]", "");
            if (sentimentLexicon.containsKey(cleanWord)) {
                totalScore += sentimentLexicon.get(cleanWord);
                wordCount++;
            }
        }

        if (wordCount == 0) {
            return new SentimentResult("중립", 0.0);
        }

        // 점수 정규화 (예: -1 ~ 1 범위를 0 ~ 1로)
        double normalizedScore = (double) totalScore / wordCount;
        String sentimentLabel;

        if (normalizedScore > 0.1) { // 긍정 임계값
            sentimentLabel = "긍정";
        } else if (normalizedScore < -0.1) { // 부정 임계값
            sentimentLabel = "부정";
        } else {
            sentimentLabel = "중립";
        }

        // 점수를 0-1 범위로 매핑 (예시)
        double finalScore = (normalizedScore + 1) / 2; // -1 -> 0, 0 -> 0.5, 1 -> 1

        return new SentimentResult(sentimentLabel, finalScore);
    }
}