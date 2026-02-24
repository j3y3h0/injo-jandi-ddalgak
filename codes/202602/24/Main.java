// Main.java
import java.util.List;
import java.util.Map;
import java.io.IOException;

/**
 * 엑셀 데이터 처리 및 분석 애플리케이션의 진입점입니다.
 * Apache POI를 사용하여 엑셀 파일을 읽고, 데이터를 분석한 후, 결과를 보고서로 생성합니다.
 */
public class Main {
    private static final String INPUT_FILE = "input.xlsx";
    private static final String OUTPUT_FILE = "output.xlsx";
    private static final String SHEET_NAME = "Sheet1"; // 가정: 첫 번째 시트 이름

    public static void main(String[] args) {
        System.out.println("--- 엑셀 데이터 처리 및 분석 애플리케이션 시작 ---");

        ExcelProcessor excelProcessor = new ExcelProcessor();
        DataAnalyzer dataAnalyzer = new DataAnalyzer();
        ReportGenerator reportGenerator = new ReportGenerator();

        try {
            // 1. 엑셀 파일에서 데이터 읽기 (예: 첫 번째 시트의 모든 데이터를 문자열 리스트의 리스트로 가정)
            // 실제 구현에서는 각 열의 타입에 맞게 파싱하는 로직이 필요합니다.
            List<List<String>> rawData = excelProcessor.readExcel(INPUT_FILE, SHEET_NAME);

            if (rawData.isEmpty()) {
                System.out.println("입력 엑셀 파일에서 데이터를 찾을 수 없습니다: " + INPUT_FILE);
                return;
            }

            System.out.println(INPUT_FILE + " 파일에서 " + rawData.size() + "개의 행을 성공적으로 읽었습니다.");

            // 예시: 첫 번째 열(인덱스 0)의 숫자 데이터를 추출하여 분석 (실제 데이터 타입에 따라 파싱 로직 변경 필요)
            List<Double> numericData = dataAnalyzer.extractNumericColumn(rawData, 0);

            if (numericData.isEmpty()) {
                System.out.println("분석할 숫자 데이터를 찾을 수 없습니다. 첫 번째 열에 숫자 데이터가 있는지 확인하십시오.");
                return;
            }

            // 2. 데이터 분석
            double sum = dataAnalyzer.calculateSum(numericData);
            double average = dataAnalyzer.calculateAverage(numericData);
            double max = dataAnalyzer.findMaximum(numericData);
            double min = dataAnalyzer.findMinimum(numericData);

            System.out.println("데이터 분석 완료.");

            // 3. 분석 결과 콘솔에 보고서 출력
            reportGenerator.generateConsoleReport(sum, average, max, min);

            // 4. 분석 결과를 새 엑셀 파일로 저장 (예시: 간단한 요약 정보)
            List<List<String>> outputData = List.of(
                List.of("지표", "값"),
                List.of("합계", String.valueOf(sum)),
                List.of("평균", String.valueOf(average)),
                List.of("최대값", String.valueOf(max)),
                List.of("최소값", String.valueOf(min))
            );
            excelProcessor.writeExcel(OUTPUT_FILE, SHEET_NAME, outputData);
            System.out.println("분석 결과가 " + OUTPUT_FILE + " 파일에 성공적으로 저장되었습니다.");

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("애플리케이션 실행 중 예외가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--- 엑셀 데이터 처리 및 분석 애플리케이션 종료 ---");
    }
}