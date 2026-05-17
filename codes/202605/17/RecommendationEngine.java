// RecommendationEngine.java
// 협업 필터링 기반의 추천 엔진 로직을 구현한 클래스입니다.
// 사용자 간의 유사도를 계산하고, 이를 기반으로 아이템을 추천합니다.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class RecommendationEngine {

    private List<User> users;
    private List<Item> items;

    public RecommendationEngine(List<User> users, List<Item> items) {
        this.users = users;
        this.items = items;
    }

    // 두 사용자 간의 피어슨 상관계수를 계산합니다.
    // 이는 두 사용자의 평점 패턴이 얼마나 유사한지를 나타냅니다.
    public double calculatePearsonCorrelation(User user1, User user2) {
        Set<String> commonItems = new HashSet<>(user1.getRatings().keySet());
        commonItems.retainAll(user2.getRatings().keySet()); // 두 사용자 모두가 평점을 매긴 아이템

        if (commonItems.isEmpty()) {
            return 0.0; // 공통 아이템이 없으면 유사도 0
        }

        double sum1 = 0.0, sum2 = 0.0;
        double sum1Sq = 0.0, sum2Sq = 0.0;
        double pSum = 0.0;
        int n = commonItems.size();

        for (String item : commonItems) {
            double rating1 = user1.getRatings().get(item);
            double rating2 = user2.getRatings().get(item);

            sum1 += rating1;
            sum2 += rating2;
            sum1Sq += rating1 * rating1;
            sum2Sq += rating2 * rating2;
            pSum += rating1 * rating2;
        }

        // 피어슨 상관계수 공식 적용
        double numerator = pSum - (sum1 * sum2 / n);
        double denominator = Math.sqrt((sum1Sq - (sum1 * sum1 / n)) * (sum2Sq - (sum2 * sum2 / n)));

        if (denominator == 0) {
            return 0.0; // 분모가 0이면 유사도 0
        }

        return numerator / denominator;
    }

    // 특정 사용자에게 아이템을 추천합니다.
    // 다른 사용자들과의 유사도를 기반으로 가중 평균 평점을 계산하여 추천합니다.
    public List<RecommendedItem> getRecommendations(User targetUser) {
        Map<String, Double> totals = new HashMap<>(); // 아이템별 가중 평점의 합
        Map<String, Double> simSums = new HashMap<>(); // 아이템별 유사도 합

        for (User otherUser : users) {
            if (otherUser.getUserId().equals(targetUser.getUserId())) {
                continue; // 자기 자신과는 비교하지 않습니다.
            }

            double similarity = calculatePearsonCorrelation(targetUser, otherUser);

            if (similarity <= 0) { // 유사도가 0 이하면 의미 없는 것으로 간주합니다.
                continue;
            }

            for (Map.Entry<String, Double> entry : otherUser.getRatings().entrySet()) {
                String itemId = entry.getKey();
                Double rating = entry.getValue();

                // 타겟 사용자가 아직 평점을 매기지 않은 아이템만 고려합니다.
                if (!targetUser.getRatings().containsKey(itemId)) {
                    totals.put(itemId, totals.getOrDefault(itemId, 0.0) + rating * similarity);
                    simSums.put(itemId, simSums.getOrDefault(itemId, 0.0) + similarity);
                }
            }
        }

        List<RecommendedItem> recommendations = new ArrayList<>();
        for (Map.Entry<String, Double> entry : totals.entrySet()) {
            String itemId = entry.getKey();
            Double total = entry.getValue();
            Double simSum = simSums.get(itemId);

            if (simSum == 0) {
                continue;
            }

            double weightedAverage = total / simSum;
            // itemId를 실제 Item 객체로 변환합니다. (간단하게 itemId를 이름으로 사용)
            recommendations.add(new RecommendedItem(new Item(itemId, "Item " + itemId), weightedAverage));
        }

        // 추천 점수가 높은 순서로 정렬합니다.
        Collections.sort(recommendations, Comparator.comparingDouble(RecommendedItem::getScore).reversed());

        return recommendations;
    }

    // 추천된 아이템과 그 점수를 담는 헬퍼 클래스
    public static class RecommendedItem {
        private Item item;
        private double score;

        public RecommendedItem(Item item, double score) {
            this.item = item;
            this.score = score;
        }

        public Item getItem() {
            return item;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "RecommendedItem{" +
                   "item=" + item.getName() +
                   ", score=" + String.format("%.2f", score) +
                   '}';
        }
    }
}
