// ReportGenerator.java
import java.util.Map;

public class ReportGenerator {

    /**
     * 제품별 총 판매량 보고서를 출력한다.
     *
     * @param productSales 제품별 총 판매량 맵
     */
    public static void printProductSales(Map<String, Double> productSales) {
        for (Map.Entry<String, Double> entry : productSales.entrySet()) {
            System.out.printf("  제품 %s: 총 판매량 %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    /**
     * 지역별 평균 판매량 보고서를 출력한다.
     *
     * @param regionAverageSales 지역별 평균 판매량 맵
     */
    public static void printRegionAverageSales(Map<String, Double> regionAverageSales) {
        for (Map.Entry<String, Double> entry : regionAverageSales.entrySet()) {
            System.out.printf("  지역 %s: 평균 판매량 %.2f%n", entry.getKey(), entry.getValue());
        }
    }
}
