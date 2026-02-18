// Main.java
package com.example.sentiment;

import java.util.ArrayList;
import java.util.List;

/**
 * 뉴스 감성 분석 프로젝트의 메인 실행 클래스이다.
 * 샘플 뉴스 기사를 생성하고 감성 분석을 수행한 후 결과를 출력한다.
 */
public class Main {
    public static void main(String[] args) {
        // 1. 샘플 뉴스 기사 생성
        List<NewsArticle> articles = new ArrayList<>();
        articles.add(new NewsArticle(
            "새로운 기술 개발로 시장에 긍정적 영향 예상",
            "우리 회사는 혁신적인 신기술을 성공적으로 개발하여 시장에 큰 기쁨과 기대를 가져다주고 있다. 이는 기업의 미래 성장을 위한 강력한 강점이다."
        ));
        articles.add(new NewsArticle(
            "경제 악화로 인한 기업들의 실적 부진 우려",
            "최근 경제 상황의 악화는 많은 기업들에게 심각한 문제와 손실을 초래하고 있다. 이에 대한 시장의 걱정과 우려가 크다."
        ));
        articles.add(new NewsArticle(
            "신제품 출시, 시장 반응은 아직 중립적",
            "새로운 제품이 출시되었지만, 시장의 초기 반응은 아직 긍정적이지도 부정적이지도 않은 중립적인 상태이다. 추가적인 지켜봄이 필요하다."
        ));
        articles.add(new NewsArticle(
            "협력사와의 성공적인 파트너십으로 시너지 기대",
            "성공적인 협력으로 양사는 큰 발전을 기대하며, 이는 향후 더 좋은 성과를 가져올 것으로 예상된다."
        ));
        articles.add(new NewsArticle(
            "해고 소식에 직원들 사기 하락, 논란 증폭",
            "회사의 갑작스러운 해고 소식에 직원들의 사기가 크게 하락하였고, 내부적으로 큰 논란이 되고 있다. 불행한 소식이다."
        ));


        // 2. 감성 분석기 인스턴스 생성
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        // 3. 각 기사에 대해 감성 분석 수행 및 결과 출력
        System.out.println("--- 뉴스 기사 감성 분석 결과 ---");
        for (int i = 0; i < articles.size(); i++) {
            NewsArticle article = articles.get(i);
            SentimentAnalyzer.Sentiment sentiment = analyzer.analyze(article);

            System.out.println("
[" + (i + 1) + "번째 기사]");
            System.out.println(article.toString());
            System.out.println("분석된 감성: " + sentiment.getLabel());
            System.out.println("---------------------------------");
        }
    }
}