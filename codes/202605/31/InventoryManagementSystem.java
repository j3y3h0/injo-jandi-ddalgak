// InventoryManagementSystem.java
// 재고 관리 시스템의 메인 실행 클래스입니다.
// Inventory 및 Product 클래스를 사용하여 재고 관리 기능을 시연합니다.

public class InventoryManagementSystem {
    public static void main(String[] args) {
        // 재고 관리 시스템 객체 생성
        Inventory inventory = new Inventory();

        // 1. 새로운 제품 추가
        System.out.println("--- 제품 추가 ---");
        Product product1 = new Product("P001", "노트북", 1200.00, 10);
        inventory.addProduct(product1);

        Product product2 = new Product("P002", "무선 마우스", 25.50, 50);
        inventory.addProduct(product2);

        Product product3 = new Product("P003", "기계식 키보드", 75.00, 20);
        inventory.addProduct(product3);

        // 이미 존재하는 제품 ID로 추가 시 수량 업데이트
        Product product1_update = new Product("P001", "노트북", 1250.00, 5); // 가격은 무시되고 수량만 추가됨
        inventory.addProduct(product1_update);
        System.out.println();

        // 2. 모든 제품 목록 출력
        inventory.listAllProducts();

        // 3. 특정 제품 정보 조회
        System.out.println("--- 특정 제품 조회 ---");
        Product foundProduct = inventory.getProduct("P002");
        if (foundProduct != null) {
            System.out.println("조회된 제품: " + foundProduct);
        } else {
            System.out.println("제품 ID 'P002'을(를) 찾을 수 없습니다.");
        }

        Product notFoundProduct = inventory.getProduct("P004");
        if (notFoundProduct == null) {
            System.out.println("제품 ID 'P004'을(를) 찾을 수 없습니다.");
        }
        System.out.println();

        // 4. 제품 재고 수량 업데이트
        System.out.println("--- 제품 수량 업데이트 ---");
        inventory.updateProductQuantity("P001", 12);
        inventory.updateProductQuantity("P005", 5); // 없는 제품 업데이트 시도
        System.out.println();

        // 업데이트 후 모든 제품 목록 출력
        inventory.listAllProducts();

        // 5. 제품 제거
        System.out.println("--- 제품 제거 ---");
        inventory.removeProduct("P002");
        inventory.removeProduct("P006"); // 없는 제품 제거 시도
        System.out.println();

        // 제거 후 모든 제품 목록 출력
        inventory.listAllProducts();
    }
}
