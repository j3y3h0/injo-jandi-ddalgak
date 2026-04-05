// Item.java
// 추천 시스템에서 추천의 대상이 되는 항목을 정의하는 클래스이다.

public class Item {
    private String id;        // 항목의 고유 식별자이다.
    private String name;      // 항목의 이름이다.
    private String category;  // 항목의 카테고리이다.

    public Item(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // 항목의 ID를 반환하는 메서드이다.
    public String getId() {
        return id;
    }

    // 항목의 이름을 반환하는 메서드이다.
    public String getName() {
        return name;
    }

    // 항목의 카테고리를 반환하는 메서드이다.
    public String getCategory() {
        return category;
    }

    @Override
    // 두 Item 객체가 동일한지 비교하는 메서드이다. ID를 기준으로 비교한다.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    // Item 객체의 해시 코드를 반환하는 메서드이다. ID를 기반으로 생성한다.
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    // Item 객체를 문자열로 표현하는 메서드이다.
    public String toString() {
        return "Item{id='" + id + "', name='" + name + "', category='" + category + "'}";
    }
}
