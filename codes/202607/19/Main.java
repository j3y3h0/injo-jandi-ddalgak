// Main.java
package com.example.csvproject;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

/**
 * CSV 데이터 분석 애플리케이션의 메인 클래스이다.
 * CsvProcessor를 사용하여 CSV 파일을 읽고, DataAnalyzer를 사용하여 데이터를 분석한 후 결과를 출력한다.
 */
public class Main {
    public static void main(String[] args) {
        // 분석할 CSV 파일 경로
        // 이 파일은 'C:\server\gpt-code-diary\codes\202607\19\sample.csv'에 위치해야 한다.
        String csvFilePath = "sample.csv";
        // 분석할 데이터가 있는 컬럼 이름
        String targetColumn = "Value";

        CsvProcessor csvProcessor = new CsvProcessor();
        DataAnalyzer dataAnalyzer = new DataAnalyzer();

        try {
            // 1. CSV 파일 읽기
            System.out.println("CSV 파일 '" + csvFilePath + "'을(를) 읽는 중...");
            List<CSVRecord> records = csvProcessor.readCsvFile(csvFilePath);
            System.out.println("총 " + records.size() + "개의 레코드를 성공적으로 읽었다.");

            if (records.isEmpty()) {
                System.out.println("읽어들인 데이터가 없다. 분석을 수행할 수 없다.");
                return;
            }

            // 2. 데이터 분석
            System.out.println("데이터 분석을 수행하는 중...");
            double sum = dataAnalyzer.calculateSum(records, targetColumn);
            double average = dataAnalyzer.calculateAverage(records, targetColumn);

            // 3. 결과 출력
            System.out.println("
--- 분석 결과 ---");
            System.out.println("컬럼 '" + targetColumn + "'의 합계: " + sum);
            System.out.println("컬럼 '" + targetColumn + "'의 평균: " + average);

        } catch (IOException e) {
            System.err.println("파일을 읽는 중 오류가 발생했다: " + e.getMessage());
            System.err.println("'" + csvFilePath + "' 파일이 존재하고 읽기 권한이 있는지 확인해라.");
        } catch (Exception e) {
            System.err.println("예기치 않은 오류가 발생했다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
