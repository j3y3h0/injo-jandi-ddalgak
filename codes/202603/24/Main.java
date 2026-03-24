import java.util.List;

/**
 * Main 클래스는 애플리케이션의 진입점입니다.
 * CsvParser를 사용하여 data.csv 파일을 읽고, StatisticsCalculator를 사용하여 데이터를 분석한 후 결과를 콘솔에 출력합니다.
 */
public class Main {
    private static final String CSV_FILE_PATH = "data.csv";

    public static void main(String[] args) {
        System.out.println("데이터 분석을 시작합니다...");

        // 1. CSV 파일에서 데이터 읽기
        List<Double> data = CsvParser.parseCsv(CSV_FILE_PATH);

        if (data.isEmpty()) {
            System.out.println("분석할 데이터가 없습니다. '" + CSV_FILE_PATH + "' 파일이 비어있거나 읽을 수 없습니다.");
            return;
        }

        System.out.println("
--- 원본 데이터 ---");
        System.out.println(data);

        // 2. 데이터 분석 (평균, 최솟값, 최댓값 계산)
        double average = StatisticsCalculator.calculateAverage(data);
        double min = StatisticsCalculator.findMin(data);
        double max = StatisticsCalculator.findMax(data);

        // 3. 분석 결과 출력
        System.out.println("
--- 분석 결과 ---");
        System.out.printf("데이터 개수: %d%n", data.size());
        System.out.printf("평균: %.2f%n", average);
        System.out.printf("최솟값: %.2f%n", min);
        System.out.printf("최댓값: %.2f%n", max);

        System.out.println("
데이터 분석이 완료되었습니다.");
    }
}
