// Main.java
// 프로그램의 진입점 역할을 하는 클래스입니다.
// 샘플 데이터를 정의하고, DataProcessor와 StatisticsUtil을 사용하여 통계 분석을 수행합니다.
public class Main {

    public static void main(String[] args) {
        // 분석할 샘플 데이터 배열을 정의합니다.
        double[] data = {10.5, 20.3, 15.7, 25.0, 12.1, 18.9, 30.2, 5.8, 22.4, 17.6};

        System.out.println("--- 데이터 분석 결과 ---");
        System.out.print("원본 데이터: ");
        for (double d : data) {
            System.out.print(d + " ");
        }
        System.out.println("
");

        // DataProcessor를 사용하여 데이터의 합계를 계산합니다.
        double sum = DataProcessor.calculateSum(data);
        System.out.println("데이터 합계: " + sum);

        // StatisticsUtil을 사용하여 다양한 통계 값을 계산합니다.
        double mean = StatisticsUtil.calculateMean(data);
        System.out.println("평균: " + String.format("%.2f", mean));

        double median = StatisticsUtil.calculateMedian(data);
        System.out.println("중앙값: " + String.format("%.2f", median));

        double stdDev = StatisticsUtil.calculateStandardDeviation(data);
        System.out.println("표준 편차: " + String.format("%.2f", stdDev));

        System.out.println("
--- 분석 완료 ---");
    }
}
