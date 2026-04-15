// Main.java
// 감성 분석기 예제를 실행하는 메인 클래스

package com.gemini.sentiment;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("감성 분석기 예제를 시작합니다.");

        // 감성 분석기 인스턴스 생성
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        // 분석할 문장 리스트 생성
        List<Sentence> sentencesToAnalyze = new ArrayList<>();
        sentencesToAnalyze.add(new Sentence("이 영화는 정말 최고다. 매우 좋다."));
        sentencesToAnalyze.add(new Sentence("서비스가 너무 나쁘다. 불만족스럽다."));
        sentencesToAnalyze.add(new Sentence("날씨가 맑고 기분이 좋다."));
        sentencesToAnalyze.add(new Sentence("문제가 발생하여 상황이 좋지 않다."));
        sentencesToAnalyze.add(new Sentence("점심 메뉴는 짜장면이었다."));
        sentencesToAnalyze.add(new Sentence("정말 끔찍한 경험이었다."));
        sentencesToAnalyze.add(new Sentence("새로운 제품 출시가 기대된다."));
        sentencesToAnalyze.add(new Sentence("실패는 성공의 어머니다."));


        System.out.println("
--- 감성 분석 결과 ---");
        for (Sentence sentence : sentencesToAnalyze) {
            Sentence analyzedSentence = analyzer.analyze(sentence);
            System.out.println(analyzedSentence);
        }

        System.out.println("
감성 분석기 예제를 마칩니다.");
    }
}
