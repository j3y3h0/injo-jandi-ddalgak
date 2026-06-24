// Main.java
package com.example.sentiment;

/**
 * 감성 분석 애플리케이션의 메인 클래스이다.
 * SentimentAnalyzer를 사용하여 텍스트의 감성을 분석한다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- 텍스트 감성 분석기 시작 ---");

        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        // 분석할 샘플 텍스트들
        String[] sampleTexts = {
            "이 영화는 정말 최고였다! 나는 매우 기쁘다.",
            "서비스가 너무 나쁘다. 정말 실망스럽다.",
            "오늘 날씨는 보통이다.",
            "훌륭한 경험이었고, 매우 만족한다.",
            "이 제품은 끔찍하고 사용하기 어렵다."
        };

        for (String text : sampleTexts) {
            SentimentResult result = analyzer.analyze(text);
            System.out.println("
텍스트: "" + text + """);
            System.out.println("분석 결과: " + result);
        }

        System.out.println("
--- 텍스트 감성 분석기 종료 ---");
    }
}