package com.example.csvprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * CSV 데이터 처리 및 분석 애플리케이션의 메인 클래스입니다.
 * 이 애플리케이션은 Apache Commons CSV 라이브러리를 사용하여 CSV 파일을 읽고,
 * 데이터를 분석한 후, 결과를 다시 CSV 파일로 저장하는 과정을 시연합니다.
 */
public class Main {

    private static final String INPUT_CSV_PATH = "input.csv";
    private static final String OUTPUT_CSV_PATH = "filtered_output.csv";

    public static void main(String[] args) {
        System.out.println("CSV 데이터 처리 및 분석 애플리케이션을 시작합니다.");

        // 1. 샘플 CSV 파일 생성 (입력 데이터)
        createSampleCsvFile();

        CsvProcessor csvProcessor = new CsvProcessor();
        DataAnalyzer dataAnalyzer = new DataAnalyzer();

        try {
            // 2. CSV 파일 읽기
            System.out.println(INPUT_CSV_PATH + " 파일에서 데이터를 읽습니다...");
            List<Record> records = csvProcessor.readCsv(INPUT_CSV_PATH);
            System.out.println("읽어온 레코드 수: " + records.size());
            records.forEach(System.out::println);

            // 3. 데이터 분석: 카테고리별 평균 값 계산
            System.out.println("
카테고리별 평균 값을 분석합니다...");
            Map<String, Double> averageValues = dataAnalyzer.calculateAverageValueByCategory(records);
            averageValues.forEach((category, avgValue) ->
                    System.out.println("카테고리: " + category + ", 평균 값: " + String.format("%.2f", avgValue))
            );

            // 4. 데이터 필터링: 특정 값 이상인 레코드 필터링
            double threshold = 50.0;
            System.out.println("
값 " + threshold + " 이상인 레코드를 필터링합니다...");
            List<Record> filteredRecords = dataAnalyzer.filterRecordsAboveValue(records, threshold);
            System.out.println("필터링된 레코드 수: " + filteredRecords.size());
            filteredRecords.forEach(System.out::println);

            // 5. 필터링된 데이터를 새 CSV 파일로 작성
            System.out.println("
필터링된 데이터를 " + OUTPUT_CSV_PATH + " 파일로 저장합니다...");
            csvProcessor.writeCsv(OUTPUT_CSV_PATH, filteredRecords);
            System.out.println("데이터 저장이 완료되었습니다.");

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("
CSV 데이터 처리 및 분석 애플리케이션을 종료합니다.");
    }

    /**
     * 애플리케이션 실행을 위한 샘플 input.csv 파일을 생성합니다.
     * 이 파일은 프로그램이 읽을 초기 데이터를 제공합니다.
     */
    private static void createSampleCsvFile() {
        System.out.println("샘플 " + INPUT_CSV_PATH + " 파일을 생성합니다...");
        List<String> lines = Arrays.asList(
                "Name,Category,Value",
                "ItemA,Electronics,100.5",
                "ItemB,Books,25.0",
                "ItemC,Electronics,75.2",
                "ItemD,Home,120.0",
                "ItemE,Books,40.0",
                "ItemF,Electronics,150.75"
        );
        try {
            Files.write(Path.of(INPUT_CSV_PATH), lines);
            System.out.println(INPUT_CSV_PATH + " 파일이 성공적으로 생성되었습니다.");
        } catch (IOException e) {
            System.err.println("샘플 CSV 파일 생성 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
