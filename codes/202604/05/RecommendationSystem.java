// RecommendationSystem.java
// 추천 시스템의 메인 클래스이다.
// DataGenerator를 이용하여 데이터를 준비하고, CollaborativeFiltering을 통해 추천을 수행한다.

import java.util.List;

public class RecommendationSystem {

    public static void main(String[] args) {
        System.out.println("개인화 추천 시스템을 시작합니다.
");

        // 1. 샘플 항목 데이터 생성
        System.out.println("1. 샘플 항목 데이터를 생성합니다.");
        List<Item> items = DataGenerator.generateItems();
        System.out.println("생성된 항목 수: " + items.size() + "개
");

        // 2. 샘플 사용자 및 평가 데이터 생성
        System.out.println("2. 샘플 사용자 및 평가 데이터를 생성합니다.");
        List<User> users = DataGenerator.generateUsers(items);
        System.out.println("생성된 사용자 수: " + users.size() + "명
");

        // 3. 추천 시스템 초기화
        CollaborativeFiltering recommender = new CollaborativeFiltering();

        // 4. 특정 사용자에게 추천 수행
        // 여기서는 "사용자 5"에게 항목을 추천한다.
        User targetUser = users.stream()
                               .filter(u -> u.getId().equals("u005"))
                               .findFirst()
                               .orElse(null);

        if (targetUser != null) {
            System.out.println("3. " + targetUser.getName() + "님을 위한 개인화 추천을 시작합니다.");
            System.out.println(targetUser.getName() + "님이 이미 평가한 항목:");
            targetUser.getRatings().forEach((item, rating) ->
                System.out.println("  - " + item.getName() + ": " + rating + "점")
            );
            System.out.println();

            int numRecommendations = 3; // 추천할 항목의 개수
            List<Item> recommendations = recommender.recommendItems(targetUser, users, items, numRecommendations);

            System.out.println("4. " + targetUser.getName() + "님께 " + numRecommendations + "개의 항목을 추천합니다:");
            if (recommendations.isEmpty()) {
                System.out.println("  추천할 항목이 없습니다. 더 많은 사용자 데이터가 필요할 수 있습니다.");
            } else {
                for (int i = 0; i < recommendations.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + recommendations.get(i).getName() + " (" + recommendations.get(i).getCategory() + ")");
                }
            }
            System.out.println("
개인화 추천 시스템을 종료합니다.");
        } else {
            System.out.println("대상 사용자를 찾을 수 없습니다.");
        }
    }
}
