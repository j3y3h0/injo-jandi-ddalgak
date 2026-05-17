// Main.java
// 추천 시스템 프로젝트의 메인 실행 파일입니다.
// 사용자, 아이템 데이터를 설정하고 추천 엔진을 초기화하여 추천을 수행합니다.

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. 샘플 사용자 데이터 생성
        List<User> users = new ArrayList<>();

        User userA = new User("userA");
        userA.addRating("item1", 5.0);
        userA.addRating("item2", 4.0);
        userA.addRating("item3", 3.0);
        userA.addRating("item5", 2.0); // userA만 평점 매긴 아이템
        users.add(userA);

        User userB = new User("userB");
        userB.addRating("item1", 4.0);
        userB.addRating("item2", 5.0);
        userB.addRating("item3", 2.0);
        users.add(userB);

        User userC = new User("userC");
        userC.addRating("item1", 3.0);
        userC.addRating("item2", 4.0);
        userC.addRating("item4", 5.0); // userC만 평점 매긴 아이템
        users.add(userC);

        User userD = new User("userD");
        userD.addRating("item2", 4.5);
        userD.addRating("item3", 4.0);
        userD.addRating("item4", 3.5);
        users.add(userD);

        // 2. 샘플 아이템 데이터 생성
        List<Item> items = new ArrayList<>();
        items.add(new Item("item1", "영화 A"));
        items.add(new Item("item2", "영화 B"));
        items.add(new Item("item3", "영화 C"));
        items.add(new Item("item4", "영화 D"));
        items.add(new Item("item5", "영화 E"));

        // 3. 추천 엔진 초기화
        RecommendationEngine engine = new RecommendationEngine(users, items);

        // 4. 특정 사용자에게 추천 수행
        System.out.println("--- userA에게 추천할 아이템 ---");
        List<RecommendationEngine.RecommendedItem> recommendationsForA = engine.getRecommendations(userA);
        if (recommendationsForA.isEmpty()) {
            System.out.println("추천할 아이템이 없습니다.");
        } else {
            for (RecommendationEngine.RecommendedItem rec : recommendationsForA) {
                System.out.println(rec);
            }
        }
        System.out.println();

        System.out.println("--- userC에게 추천할 아이템 ---");
        List<RecommendationEngine.RecommendedItem> recommendationsForC = engine.getRecommendations(userC);
        if (recommendationsForC.isEmpty()) {
            System.out.println("추천할 아이템이 없습니다.");
        } else {
            for (RecommendationEngine.RecommendedItem rec : recommendationsForC) {
                System.out.println(rec);
            }
        }
        System.out.println();
    }
}
