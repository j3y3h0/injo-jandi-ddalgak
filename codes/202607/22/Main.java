// Main.java
// 뉴스 추천 시스템의 메인 실행 클래스이다.

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. 샘플 뉴스 기사 데이터 생성
        List<NewsArticle> articles = new ArrayList<>();
        articles.add(new NewsArticle("001", "AI 기술의 최신 동향", "인공지능 분야의 새로운 발전...", Arrays.asList("AI", "기술", "혁신")));
        articles.add(new NewsArticle("002", "유럽 경제 회복 전망", "유럽 연합의 경제 지표 분석...", Arrays.asList("경제", "유럽", "정책")));
        articles.add(new NewsArticle("003", "머신러닝, 의료 분야에 적용", "진단 정확도 향상을 위한 머신러닝...", Arrays.asList("AI", "의료", "기술")));
        articles.add(new NewsArticle("004", "환경 보호를 위한 새로운 법안", "탄소 배출 감소를 목표로...", Arrays.asList("환경", "정책", "사회")));
        articles.add(new NewsArticle("005", "주식 시장 분석: 기술주 상승세", "글로벌 기술 기업들의 주가 동향...", Arrays.asList("경제", "주식", "기술")));
        articles.add(new NewsArticle("006", "데이터 과학의 미래", "빅데이터와 인공지능의 융합...", Arrays.asList("AI", "기술", "데이터")));

        // 2. 추천 서비스 초기화
        RecommendationService recommendationService = new RecommendationService(articles);

        // 3. 사용자 관심 주제 설정 및 뉴스 추천
        List<String> user1Interests = Arrays.asList("AI", "기술");
        System.out.println("사용자 1 (관심 주제: " + user1Interests + ")을 위한 추천 뉴스:");
        List<NewsArticle> user1Recommendations = recommendationService.recommendArticles(user1Interests, 3);
        user1Recommendations.forEach(System.out::println);
        System.out.println("----------------------------------------");

        List<String> user2Interests = Arrays.asList("경제", "정책");
        System.out.println("사용자 2 (관심 주제: " + user2Interests + ")을 위한 추천 뉴스:");
        List<NewsArticle> user2Recommendations = recommendationService.recommendArticles(user2Interests, 2);
        user2Recommendations.forEach(System.out::println);
        System.out.println("----------------------------------------");

        List<String> user3Interests = Arrays.asList("사회"); // 낮은 일치도를 가진 사용자
        System.out.println("사용자 3 (관심 주제: " + user3Interests + ")을 위한 추천 뉴스:");
        List<NewsArticle> user3Recommendations = recommendationService.recommendArticles(user3Interests, 2);
        user3Recommendations.forEach(System.out::println);
        System.out.println("----------------------------------------");

        List<String> user4Interests = new ArrayList<>(); // 관심 주제가 없는 사용자
        System.out.println("사용자 4 (관심 주제 없음)을 위한 추천 뉴스 (랜덤 2개):");
        List<NewsArticle> user4Recommendations = recommendationService.recommendArticles(user4Interests, 2);
        user4Recommendations.forEach(System.out::println);
        System.out.println("----------------------------------------");
    }
}
