package com.example.csvprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * CSV 데이터 처리 애플리케이션의 메인 클래스이다.
 * 샘플 CSV 파일을 읽고, 데이터를 처리하며, 결과를 출력한다.
 */
public class CsvProcessor {

    private static final String SAMPLE_CSV_FILE = "data.csv"; // 샘플 CSV 파일 이름

    public static void main(String[] args) {
        // 샘플 CSV 파일이 없으면 생성한다.
        if (!Files.exists(Paths.get(SAMPLE_CSV_FILE))) {
            createSampleCsvFile();
        }

        try {
            // CSV 파일 읽기
            List<DataModel> records = CsvUtil.readCsv(SAMPLE_CSV_FILE);
            System.out.println("CSV 파일에서 읽어온 데이터:");
            records.forEach(System.out::println);
            System.out.println("
----------------------------------------
");

            // 특정 과목의 평균 점수 계산 (예: "수학")
            String targetSubject = "수학";
            double averageMathScore = CsvUtil.calculateAverageScore(records, targetSubject);
            System.out.printf("%s 과목의 평균 점수: %.2f
", targetSubject, averageMathScore);

            // 다른 과목의 평균 점수 계산 (예: "영어")
            targetSubject = "영어";
            double averageEnglishScore = CsvUtil.calculateAverageScore(records, targetSubject);
            System.out.printf("%s 과목의 평균 점수: %.2f
", targetSubject, averageEnglishScore);

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류가 발생했다: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("CSV 파일의 점수 형식이 올바르지 않다: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 프로그램 실행을 위한 샘플 CSV 파일을 생성한다.
     */
    private static void createSampleCsvFile() {
        String csvContent = "이름,과목,점수
" +
                            "김철수,수학,90
" +
                            "김철수,영어,85
" +
                            "이영희,수학,78
" +
                            "이영희,영어,92
" +
                            "박찬호,수학,88
" +
                            "박찬호,영어,75
";
        try {
            Files.write(Paths.get(SAMPLE_CSV_FILE), csvContent.getBytes());
            System.out.println("샘플 CSV 파일이 생성되었다: " + SAMPLE_CSV_FILE);
        } catch (IOException e) {
            System.err.println("샘플 CSV 파일 생성 중 오류가 발생했다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
