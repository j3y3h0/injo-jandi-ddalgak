// User.java
// 추천 시스템의 사용자를 정의하는 클래스이다. 사용자의 고유 정보와 평가한 항목들을 관리한다.

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;                       // 사용자의 고유 식별자이다.
    private String name;                     // 사용자의 이름이다.
    private Map<Item, Double> ratings;       // 사용자가 각 항목에 부여한 평가 (항목 -> 점수) 맵이다.

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.ratings = new HashMap<>();
    }

    // 사용자의 ID를 반환하는 메서드이다.
    public String getId() {
        return id;
    }

    // 사용자의 이름을 반환하는 메서드이다.
    public String getName() {
        return name;
    }

    // 사용자에게 항목 평가를 추가하는 메서드이다.
    public void addRating(Item item, Double rating) {
        this.ratings.put(item, rating);
    }

    // 특정 항목에 대한 사용자의 평가를 반환하는 메서드이다. 평가하지 않았다면 null을 반환한다.
    public Double getRating(Item item) {
        return this.ratings.get(item);
    }

    // 사용자가 평가한 모든 항목과 평가 점수 맵을 반환하는 메서드이다.
    public Map<Item, Double> getRatings() {
        return ratings;
    }

    @Override
    // 두 User 객체가 동일한지 비교하는 메서드이다. ID를 기준으로 비교한다.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    // User 객체의 해시 코드를 반환하는 메서드이다. ID를 기반으로 생성한다.
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    // User 객체를 문자열로 표현하는 메서드이다.
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', ratings=" + ratings.size() + " items}";
    }
}
