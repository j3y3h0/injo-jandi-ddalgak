// Inventory.java
// 재고 관리 기능을 제공하는 클래스입니다.
// 제품 목록을 저장하고, 추가, 제거, 업데이트, 검색 기능을 제공합니다.

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // 제품 ID를 키로 하여 Product 객체를 저장하는 맵입니다.
    private Map<String, Product> products;

    // 생성자
    public Inventory() {
        this.products = new HashMap<>();
    }

    /**
     * 새로운 제품을 재고에 추가합니다.
     * 이미 존재하는 제품 ID인 경우, 해당 제품의 수량을 업데이트합니다.
     * @param product 추가할 제품 객체
     */
    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            Product existingProduct = products.get(product.getId());
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            System.out.println("제품 '" + product.getName() + "'의 재고를 업데이트했습니다. 새 수량: " + existingProduct.getQuantity());
        } else {
            products.put(product.getId(), product);
            System.out.println("새 제품 '" + product.getName() + "'을 재고에 추가했습니다.");
        }
    }

    /**
     * 제품 ID를 사용하여 재고에서 제품을 제거합니다.
     * @param productId 제거할 제품의 ID
     * @return 제거 성공 여부
     */
    public boolean removeProduct(String productId) {
        if (products.containsKey(productId)) {
            Product removedProduct = products.remove(productId);
            System.out.println("제품 ID '" + productId + "' (" + removedProduct.getName() + ")을 재고에서 제거했습니다.");
            return true;
        } else {
            System.out.println("오류: 제품 ID '" + productId + "'을(를) 찾을 수 없어 제거할 수 없습니다.");
            return false;
        }
    }

    /**
     * 제품 ID를 사용하여 재고에서 제품을 검색합니다.
     * @param productId 검색할 제품의 ID
     * @return 찾은 Product 객체, 없으면 null
     */
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    /**
     * 제품의 재고 수량을 업데이트합니다.
     * @param productId 수량을 업데이트할 제품의 ID
     * @param newQuantity 새로운 재고 수량
     * @return 업데이트 성공 여부
     */
    public boolean updateProductQuantity(String productId, int newQuantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            System.out.println("제품 ID '" + productId + "'의 재고 수량을 " + newQuantity + "(으)로 업데이트했습니다.");
            return true;
        } else {
            System.out.println("오류: 제품 ID '" + productId + "'을(를) 찾을 수 없어 수량을 업데이트할 수 없습니다.");
            return false;
        }
    }

    /**
     * 현재 재고에 있는 모든 제품의 목록을 출력합니다.
     */
    public void listAllProducts() {
        if (products.isEmpty()) {
            System.out.println("현재 재고에 제품이 없습니다.");
            return;
        }
        System.out.println("
--- 현재 재고 목록 ---");
        for (Product product : products.values()) {
            System.out.println(product);
        }
        System.out.println("--------------------
");
    }
}
