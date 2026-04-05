// DataGenerator.java
// 추천 시스템을 위한 샘플 사용자, 항목 및 평가 데이터를 생성하는 유틸리티 클래스이다.
// 실제 애플리케이션에서는 데이터베이스나 외부 API에서 데이터를 로드할 수 있다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGenerator {

    // 샘플 항목 리스트를 생성하여 반환하는 메서드이다.
    public static List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("i001", "책 A", "도서"));
        items.add(new Item("i002", "책 B", "도서"));
        items.add(new Item("i003", "영화 X", "영화"));
        items.add(new Item("i004", "영화 Y", "영화"));
        items.add(new Item("i005", "음악 앨범 P", "음악"));
        items.add(new Item("i006", "음악 앨범 Q", "음악"));
        items.add(new Item("i007", "책 C", "도서"));
        return items;
    }

    // 샘플 사용자 리스트와 그들의 평가 데이터를 생성하여 반환하는 메서드이다.
    public static List<User> generateUsers(List<Item> items) {
        List<User> users = new ArrayList<>();

        // 항목들을 쉽게 접근할 수 있도록 맵으로 변환한다.
        // 실제 데이터에서는 ID를 사용하여 항목을 조회할 수 있다.
        Item item001 = items.get(0); // 책 A
        Item item002 = items.get(1); // 책 B
        Item item003 = items.get(2); // 영화 X
        Item item004 = items.get(3); // 영화 Y
        Item item005 = items.get(4); // 음악 앨범 P
        Item item006 = items.get(5); // 음악 앨범 Q
        Item item007 = items.get(6); // 책 C


        // 사용자 U1: 책과 영화를 좋아함
        User user1 = new User("u001", "사용자 1");
        user1.addRating(item001, 5.0); // 책 A: 매우 좋음
        user1.addRating(item002, 4.0); // 책 B: 좋음
        user1.addRating(item003, 5.0); // 영화 X: 매우 좋음
        user1.addRating(item004, 3.0); // 영화 Y: 보통
        users.add(user1);

        // 사용자 U2: 영화와 음악을 좋아함
        User user2 = new User("u002", "사용자 2");
        user2.addRating(item003, 4.0); // 영화 X: 좋음
        user2.addRating(item004, 5.0); // 영화 Y: 매우 좋음
        user2.addRating(item005, 5.0); // 음악 앨범 P: 매우 좋음
        user2.addRating(item006, 4.0); // 음악 앨범 Q: 좋음
        users.add(user2);

        // 사용자 U3: 주로 책을 좋아함
        User user3 = new User("u003", "사용자 3");
        user3.addRating(item001, 4.0); // 책 A: 좋음
        user3.addRating(item002, 5.0); // 책 B: 매우 좋음
        user3.addRating(item007, 4.0); // 책 C: 좋음
        users.add(user3);

        // 사용자 U4: 음악을 좋아함
        User user4 = new User("u004", "사용자 4");
        user4.addRating(item005, 3.0); // 음악 앨범 P: 보통
        user4.addRating(item006, 5.0); // 음악 앨범 Q: 매우 좋음
        users.add(user4);

        // 사용자 U5: 새로운 사용자, 아직 평가가 거의 없음
        User user5 = new User("u005", "사용자 5");
        user5.addRating(item001, 3.0); // 책 A: 보통
        user5.addRating(item003, 2.0); // 영화 X: 나쁨 (향후 이 사용자에게는 영화 X와 유사한 항목은 추천되지 않을 수 있음)
        users.add(user5);
        
        return users;
    }
}
