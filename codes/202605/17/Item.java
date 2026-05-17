// Item.java
// 아이템 정보를 나타내는 클래스입니다.
// 각 아이템은 고유한 ID와 이름을 가집니다.

public class Item {
    private String itemId;
    private String name;

    public Item(String itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
               "itemId='" + itemId + ''' +
               ", name='" + name + ''' +
               '}';
    }
}
