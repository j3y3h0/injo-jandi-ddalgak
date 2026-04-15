// Sentence.java
// 분석할 문장과 그 감성 분석 결과를 저장하는 데이터 클래스

package com.gemini.sentiment;

public class Sentence {
    private String text;        // 원본 문장 텍스트
    private Sentiment sentiment; // 분석된 감성

    public Sentence(String text) {
        this.text = text;
        this.sentiment = Sentiment.NEUTRAL; // 초기값은 중립으로 설정
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
    }

    @Override
    public String toString() {
        return "문장: "" + text + "" -> 감성: " + sentiment;
    }
}
