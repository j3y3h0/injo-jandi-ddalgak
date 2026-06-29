// Main.java
import java.io.IOException;
import java.util.List;

/**
 * 애플리케이션의 메인 클래스입니다.
 * DataProcessor를 사용하여 CSV 파일을 읽고 분석 결과를 출력합니다.
 */
public class Main {
    private static final String CSV_FILE_PATH = "sample.csv";

    public static void main(String[] args) {
        System.out.println("CSV 파일 데이터 분석을 시작합니다.");

        DataProcessor processor = new DataProcessor();

        try {
            // CSV 파일 읽기
            List<DataModel> data = processor.readCsvFile(CSV_FILE_PATH);

            if (data.isEmpty()) {
                System.out.println("처리할 데이터가 없습니다. '" + CSV_FILE_PATH + "' 파일이 비어있거나 존재하지 않을 수 있습니다.");
                return;
            }

            System.out.println("
--- 원본 데이터 ---");
            data.forEach(System.out::println);

            // 데이터 분석
            double averageScore = processor.calculateAverageScore(data);
            double maxScore = processor.findMaxScore(data);
            double minScore = processor.findMinScore(data);

            System.out.println("
--- 분석 결과 ---");
            System.out.printf("총 %d개의 데이터 레코드가 처리되었습니다.
", data.size());
            System.out.printf("평균 점수: %.2f
", averageScore);
            System.out.printf("최고 점수: %.2f
", maxScore);
            System.out.printf("최저 점수: %.2f
", minScore);

        } catch (IOException e) {
            System.err.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
            System.err.println("'" + CSV_FILE_PATH + "' 파일이 현재 디렉터리에 있는지 확인하십시오.");
        } catch (Exception e) {
            System.err.println("예기치 않은 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("
CSV 파일 데이터 분석이 완료되었습니다.");
    }
}
