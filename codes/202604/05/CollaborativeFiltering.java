// CollaborativeFiltering.java
// 협업 필터링 알고리즘을 구현하여 항목을 추천하는 클래스이다.
// 이 예시에서는 간단한 항목 기반(Item-Based) 협업 필터링을 사용한다.

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollaborativeFiltering {

    // 대상 사용자에게 항목을 추천하는 메서드이다.
    // numRecommendations: 추천할 항목의 개수
    public List<Item> recommendItems(User targetUser, List<User> allUsers, List<Item> allItems, int numRecommendations) {
        // 이미 대상 사용자가 평가한 항목들은 추천에서 제외한다.
        Set<Item> ratedItemsByUser = targetUser.getRatings().keySet();

        // 각 항목과 다른 모든 항목 간의 유사도를 미리 계산한다.
        // 실제 시스템에서는 이 유사도 계산이 오프라인에서 배치로 수행되기도 한다.
        Map<Item, Map<Item, Double>> itemSimilarities = calculateAllItemSimilarities(allUsers, allItems);

        // 예측 평가를 저장할 맵이다.
        Map<Item, Double> predictedRatings = new HashMap<>();

        // 대상 사용자가 아직 평가하지 않은 각 항목에 대해 예측 평가를 계산한다.
        for (Item itemToPredict : allItems) {
            if (!ratedItemsByUser.contains(itemToPredict)) {
                double predictedRating = predictRating(targetUser, itemToPredict, itemSimilarities, ratedItemsByUser);
                if (predictedRating > 0) { // 유효한 예측 평가만 포함한다.
                    predictedRatings.put(itemToPredict, predictedRating);
                }
            }
        }

        // 예측 평가가 높은 순서대로 항목을 정렬하여 상위 N개를 반환한다.
        return predictedRatings.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numRecommendations)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // 대상 사용자가 특정 항목에 대해 매길 예측 평가를 계산하는 메서드이다.
    private double predictRating(User targetUser, Item itemToPredict,
                                 Map<Item, Map<Item, Double>> itemSimilarities,
                                 Set<Item> ratedItemsByUser) {
        double numerator = 0.0;   // 분자 (유사도 * 평가 점수의 합)
        double denominator = 0.0; // 분모 (유사도의 합)

        // 대상 사용자가 평가한 각 항목에 대해 반복한다.
        for (Item ratedItem : ratedItemsByUser) {
            // 현재 예측하려는 항목(itemToPredict)과 사용자가 이미 평가한 항목(ratedItem) 간의 유사도를 가져온다.
            Double similarity = itemSimilarities.getOrDefault(itemToPredict, Collections.emptyMap()).get(ratedItem);

            // 유사도가 0보다 크고, 사용자가 평가한 항목의 점수가 존재한다면 예측에 활용한다.
            if (similarity != null && similarity > 0) {
                Double rating = targetUser.getRating(ratedItem);
                if (rating != null) {
                    numerator += similarity * rating;
                    denominator += similarity;
                }
            }
        }

        // 분모가 0이 아니면 예측 평가를 반환한다. 0이면 예측 불가이므로 0을 반환한다.
        return denominator == 0 ? 0 : numerator / denominator;
    }


    // 모든 항목 쌍 간의 코사인 유사도를 계산하는 메서드이다.
    private Map<Item, Map<Item, Double>> calculateAllItemSimilarities(List<User> allUsers, List<Item> allItems) {
        Map<Item, Map<Item, Double>> itemSimilarities = new HashMap<>();

        for (int i = 0; i < allItems.size(); i++) {
            for (int j = i + 1; j < allItems.size(); j++) { // 각 항목 쌍에 대해 한 번만 계산한다.
                Item item1 = allItems.get(i);
                Item item2 = allItems.get(j);

                double similarity = calculateCosineSimilarity(item1, item2, allUsers);

                itemSimilarities.computeIfAbsent(item1, k -> new HashMap<>()).put(item2, similarity);
                itemSimilarities.computeIfAbsent(item2, k -> new HashMap<>()).put(item1, similarity); // 대칭성을 활용
            }
        }
        return itemSimilarities;
    }

    // 두 항목 간의 코사인 유사도를 계산하는 메서드이다.
    // 코사인 유사도는 두 벡터(여기서는 항목에 대한 사용자 평가 벡터) 간의 각도의 코사인 값이다.
    // 값이 1에 가까울수록 유사하다.
    private double calculateCosineSimilarity(Item item1, Item item2, List<User> allUsers) {
        double dotProduct = 0.0;      // 두 항목의 평가 벡터 내적
        double normA = 0.0;           // 첫 번째 항목 평가 벡터의 크기 제곱
        double normB = 0.0;           // 두 번째 항목 평가 벡터의 크기 제곱

        for (User user : allUsers) {
            Double rating1 = user.getRating(item1);
            Double rating2 = user.getRating(item2);

            // 두 항목 모두에 평가를 내린 사용자만 고려한다.
            if (rating1 != null && rating2 != null) {
                dotProduct += rating1 * rating2;
                normA += rating1 * rating1;
                normB += rating2 * rating2;
            }
        }

        // 분모가 0이 아니면 코사인 유사도를 계산한다. 0이면 유사도 없음(0)을 반환한다.
        if (normA == 0 || normB == 0) {
            return 0.0;
        } else {
            return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        }
    }
}
