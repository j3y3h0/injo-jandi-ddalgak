package com.example.data;

import com.example.data.model.DataRecord;

import java.io.IOException;
import java.util.List;
import java.util.OptionalDouble;

/**
 * 데이터 분석 애플리케이션의 메인 클래스입니다.
 * CSV 파일을 읽고, 데이터를 분석한 후 결과를 출력합니다.
 */
public class Main {
    public static void main(String[] args) {
        String csvFilePath = "data.csv"; // 분석할 CSV 파일 경로

        CsvProcessor csvProcessor = new CsvProcessor();
        DataAnalysisService analysisService = new DataAnalysisService();

        try {
            // 1. CSV 파일 읽기
            System.out.println("CSV 파일을 읽는 중입니다: " + csvFilePath);
            List<DataRecord> records = csvProcessor.readCsvFile(csvFilePath);

            if (records.isEmpty()) {
                System.out.println("CSV 파일에서 읽을 데이터가 없습니다.");
                return;
            }
            System.out.println("총 " + records.size() + "개의 데이터 레코드를 읽었습니다.");

            // 2. 데이터 분석 (예: 점수 평균 계산)
            System.out.println("
데이터 분석을 수행하는 중입니다...");
            OptionalDouble averageScore = analysisService.calculateAverageScore(records);

            if (averageScore.isPresent()) {
                System.out.printf("점수의 평균: %.2f
", averageScore.getAsDouble());
            } else {
                System.out.println("점수 데이터를 찾을 수 없어 평균을 계산할 수 없습니다.");
            }

            // 추가적인 데이터 시각화를 위한 제안
            System.out.println("
--- 추가 제안 ---");
            System.out.println("이 데이터를 활용하여 다음과 같은 시각화를 고려할 수 있습니다:");
            System.out.println("- 나이대별 점수 분포를 막대 그래프로 표현");
            System.out.println("- 개인별 점수 변화를 선 그래프로 표현 (시간 데이터가 있다면)");
            System.out.println("이러한 시각화를 위해 Tableau, Power BI 또는 Java 기반 라이브러리(예: JFreeChart, XChart)를 사용할 수 있습니다.");


        } catch (IOException e) {
            System.err.println("CSV 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("애플리케이션 실행 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
