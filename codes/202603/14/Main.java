// Main.java
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "data.csv"; // 처리할 CSV 파일 경로

        try {
            // 1. CSV 파일 읽기 및 데이터 파싱
            List<Map<String, String>> records = CsvProcessor.readCsv(filePath);
            System.out.println("--- 원본 데이터 ---");
            for (Map<String, String> record : records) {
                System.out.println(record);
            }

            // 2. 데이터 집계 (예: 제품별 총 판매량)
            Map<String, Double> productSales = DataAggregator.aggregateByProduct(records);
            System.out.println("
--- 제품별 총 판매량 ---");
            ReportGenerator.printProductSales(productSales);

            // 3. 지역별 평균 판매량 집계
            Map<String, Double> regionAverageSales = DataAggregator.calculateAverageSalesByRegion(records);
            System.out.println("
--- 지역별 평균 판매량 ---");
            ReportGenerator.printRegionAverageSales(regionAverageSales);

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
