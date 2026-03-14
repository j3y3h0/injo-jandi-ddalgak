// DataAggregator.java
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DataAggregator {

    /**
     * 제품(Product)별 총 판매량을 집계한다.
     *
     * @param records 처리할 데이터 레코드 리스트
     * @return 제품 이름을 키로, 총 판매량을 값으로 하는 맵
     */
    public static Map<String, Double> aggregateByProduct(List<Map<String, String>> records) {
        Map<String, Double> productSales = new HashMap<>();
        for (Map<String, String> record : records) {
            String product = record.get("Product");
            double sales = Double.parseDouble(record.get("Sales"));
            productSales.put(product, productSales.getOrDefault(product, 0.0) + sales);
        }
        return productSales;
    }

    /**
     * 지역(Region)별 평균 판매량을 계산한다.
     *
     * @param records 처리할 데이터 레코드 리스트
     * @return 지역 이름을 키로, 평균 판매량을 값으로 하는 맵
     */
    public static Map<String, Double> calculateAverageSalesByRegion(List<Map<String, String>> records) {
        Map<String, Double> regionTotalSales = new HashMap<>();
        Map<String, Integer> regionCounts = new HashMap<>();

        for (Map<String, String> record : records) {
            String region = record.get("Region");
            double sales = Double.parseDouble(record.get("Sales"));

            regionTotalSales.put(region, regionTotalSales.getOrDefault(region, 0.0) + sales);
            regionCounts.put(region, regionCounts.getOrDefault(region, 0) + 1);
        }

        Map<String, Double> regionAverageSales = new HashMap<>();
        for (Map.Entry<String, Double> entry : regionTotalSales.entrySet()) {
            String region = entry.getKey();
            double totalSales = entry.getValue();
            int count = regionCounts.get(region);
            regionAverageSales.put(region, totalSales / count);
        }
        return regionAverageSales;
    }
}
