// SentimentResult.java
package com.example.sentiment;

/**
 * 감성 분석 결과를 나타내는 클래스이다.
 * 감성 레이블과 점수를 포함한다.
 */
public class SentimentResult {
    private final String sentiment; // 감성 레이블 (예: "긍정", "부정", "중립")
    private final double score;     // 감성 점수 (예: 0.0 ~ 1.0)

    /**
     * 새로운 SentimentResult 객체를 생성한다.
     * @param sentiment 감성 레이블
     * @param score 감성 점수
     */
    public SentimentResult(String sentiment, double score) {
        this.sentiment = sentiment;
        this.score = score;
    }

    /**
     * 감성 레이블을 반환한다.
     * @return 감성 레이블
     */
    public String getSentiment() {
        return sentiment;
    }

    /**
     * 감성 점수를 반환한다.
     * @return 감성 점수
     */
    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "감성: " + sentiment + ", 점수: " + String.format("%.2f", score);
    }
}