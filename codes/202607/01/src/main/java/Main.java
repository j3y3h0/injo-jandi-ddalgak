// Main.java
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 입력 및 출력 파일 경로 설정
        String inputFilePath = "input.csv"; // 분석할 CSV 파일 경로
        String outputFilePath = "output.csv"; // 분석 결과를 저장할 CSV 파일 경로

        System.out.println("CSV 데이터 분석을 시작합니다.");

        try {
            // DataAnalyzer 인스턴스 생성
            DataAnalyzer analyzer = new DataAnalyzer(inputFilePath, outputFilePath);
            // 데이터 분석 및 결과 저장 실행
            analyzer.analyzeAndSave();
            System.out.println("CSV 데이터 분석이 성공적으로 완료되었습니다. 결과는 " + outputFilePath + " 에 저장되었습니다.");
        } catch (IOException e) {
            // 파일 입출력 오류 발생 시
            System.err.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // 그 외 다른 예외 발생 시
            System.err.println("데이터 분석 중 예상치 못한 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}