package com.gemini.cli.project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 분석된 데이터를 기반으로 보고서를 생성하고 파일로 저장하는 클래스이다.
 */
public class ReportGenerator {

    /**
     * 분석 결과를 기반으로 문자열 형태의 보고서를 생성한다.
     *
     * @param averageAmount 'amount' 컬럼의 평균값
     * @param categoryCounts 카테고리별 개수 집계 맵
     * @return 생성된 보고서 문자열
     */
    public String generateReport(double averageAmount, Map<String, Long> categoryCounts) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("--- 데이터 분석 보고서 ---
");
        reportBuilder.append("생성 일시: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("
");
        reportBuilder.append("
");

        reportBuilder.append("1. 주요 통계:
");
        reportBuilder.append(String.format("   - 'amount' 컬럼 평균: %.2f
", averageAmount));
        reportBuilder.append("
");

        reportBuilder.append("2. 카테고리별 데이터 현황:
");
        if (categoryCounts.isEmpty()) {
            reportBuilder.append("   - 집계된 카테고리 데이터가 없다.
");
        } else {
            categoryCounts.forEach((category, count) ->
                    reportBuilder.append(String.format("   - %s: %d건
", category, count))
            );
        }
        reportBuilder.append("
");
        reportBuilder.append("--- 보고서 끝 ---
");

        return reportBuilder.toString();
    }

    /**
     * 생성된 보고서 문자열을 파일로 저장한다.
     *
     * @param reportContent 저장할 보고서 내용
     * @param filePath 저장할 파일 경로
     * @throws IOException 파일 쓰기 중 오류 발생 시
     */
    public void saveReportToFile(String reportContent, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(reportContent);
        }
    }
}
