// User.java
// 사용자 정보를 나타내는 클래스입니다.
// 각 사용자는 고유한 ID와 아이템에 대한 평점 목록을 가집니다.

import java.util.Map;
import java.util.HashMap;

public class User {
    private String userId;
    private Map<String, Double> ratings; // 아이템 ID와 해당 아이템에 대한 평점

    public User(String userId) {
        this.userId = userId;
        this.ratings = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Double> getRatings() {
        return ratings;
    }

    // 아이템에 대한 평점을 추가합니다.
    public void addRating(String itemId, Double rating) {
        this.ratings.put(itemId, rating);
    }

    @Override
    public String toString() {
        return "User{" +
               "userId='" + userId + ''' +
               ", ratings=" + ratings +
               '}';
    }
}
