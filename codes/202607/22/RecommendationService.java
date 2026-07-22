// RecommendationService.java
// 사용자 관심사에 기반한 뉴스 기사 추천 로직을 제공하는 서비스이다.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RecommendationService {

    private final List<NewsArticle> articles; // 시스템에 등록된 전체 뉴스 기사 목록이다.

    public RecommendationService(List<NewsArticle> articles) {
        this.articles = new ArrayList<>(articles);
    }

    /**
     * 사용자의 관심 주제 목록을 기반으로 뉴스 기사를 추천한다.
     * 주제 일치도가 높은 기사를 우선적으로 추천하며, 최대 `limit`개까지 반환한다.
     *
     * @param userInterests 사용자가 관심을 가진 주제 목록이다.
     * @param limit         추천할 기사의 최대 개수이다.
     * @return 추천 뉴스 기사 목록이다.
     */
    public List<NewsArticle> recommendArticles(List<String> userInterests, int limit) {
        if (userInterests == null || userInterests.isEmpty()) {
            // 관심 주제가 없으면 최신 기사 또는 인기 기사 등을 추천할 수 있으나,
            // 여기서는 단순히 모든 기사를 반환한다.
            Collections.shuffle(articles); // 무작위로 섞어서 반환
            return articles.stream().limit(limit).collect(Collectors.toList());
        }

        // 각 기사별로 사용자 관심 주제와의 일치도를 계산한다.
        Map<NewsArticle, Long> articleScores = new HashMap<>();
        Set<String> userInterestSet = userInterests.stream()
                                                    .map(String::toLowerCase)
                                                    .collect(Collectors.toSet());

        for (NewsArticle article : articles) {
            long matchingTopics = article.getTopics().stream()
                                         .map(String::toLowerCase)
                                         .filter(userInterestSet::contains)
                                         .count();
            articleScores.put(article, matchingTopics);
        }

        // 일치도 점수가 높은 순서대로 기사를 정렬한다.
        // 점수가 같으면 기사 ID로 정렬한다. (안정성을 위해)
        List<NewsArticle> recommended = articleScores.entrySet().stream()
                .sorted(Map.Entry.<NewsArticle, Long>comparingByValue().reversed()
                          .thenComparing(entry -> entry.getKey().getId()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toList());

        return recommended;
    }
}
