// Product.java
// 재고 관리 시스템의 제품 정보를 나타내는 클래스입니다.

public class Product {
    private String id;        // 제품 고유 식별자
    private String name;      // 제품 이름
    private double price;     // 제품 가격
    private int quantity;     // 재고 수량

    // 생성자
    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter 메서드
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter 메서드 (재고 수량만 변경 가능하도록)
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("오류: 재고 수량은 음수가 될 수 없습니다.");
        }
    }

    // 제품 정보 문자열 반환
    @Override
    public String toString() {
        return "제품 ID: " + id + ", 이름: " + name + ", 가격: " + String.format("%.2f", price) + ", 수량: " + quantity;
    }
}
