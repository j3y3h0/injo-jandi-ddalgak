package com.gemini.cli.project;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 애플리케이션의 메인 클래스이다.
 * CSV 데이터를 처리하고 분석하며 보고서를 생성하는 과정을 조율한다.
 */
public class Main {

    private static final String INPUT_FILE_PATH = "input.csv";
    private static final String OUTPUT_FILE_PATH = "output_summary.txt";

    public static void main(String[] args) {
        System.out.println("CSV 데이터 처리 애플리케이션을 시작한다.");

        CsvProcessor csvProcessor = new CsvProcessor();
        DataAnalyzer dataAnalyzer = new DataAnalyzer();
        ReportGenerator reportGenerator = new ReportGenerator();

        try {
            // 1. CSV 파일 읽기
            System.out.println("CSV 파일 (" + INPUT_FILE_PATH + ")을 읽는 중이다...");
            List<Map<String, String>> records = csvProcessor.readCsv(INPUT_FILE_PATH);
            System.out.println(records.size() + "개의 레코드를 성공적으로 읽었다.");

            if (records.isEmpty()) {
                System.out.println("읽을 데이터가 없어 처리를 종료한다.");
                return;
            }

            // 2. 데이터 분석
            System.out.println("데이터를 분석하는 중이다...");
            // 예시: "amount" 컬럼의 평균값을 계산
            double averageAmount = dataAnalyzer.calculateAverage(records, "amount");
            System.out.println("분석 결과 - 'amount' 컬럼의 평균: " + String.format("%.2f", averageAmount));

            // 예시: "category" 컬럼별 개수 집계
            Map<String, Long> categoryCounts = dataAnalyzer.countByCategory(records, "category");
            System.out.println("분석 결과 - 'category'별 개수: " + categoryCounts);


            // 3. 보고서 생성 및 출력
            System.out.println("보고서를 생성하는 중이다...");
            String report = reportGenerator.generateReport(averageAmount, categoryCounts);
            System.out.println("
--- 생성된 보고서 ---");
            System.out.println(report);
            System.out.println("--------------------
");

            // 4. 보고서 파일로 저장
            reportGenerator.saveReportToFile(report, OUTPUT_FILE_PATH);
            System.out.println("보고서가 " + OUTPUT_FILE_PATH + "에 저장되었다.");

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류가 발생했다: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했다: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("CSV 데이터 처리 애플리케이션을 종료한다.");
    }
}
