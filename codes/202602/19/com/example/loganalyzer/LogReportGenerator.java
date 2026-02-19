// LogReportGenerator.java
package com.example.loganalyzer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 분석된 로그 엔트리 목록을 기반으로 보고서를 생성하는 유틸리티 클래스입니다.
 * 로그 레벨별 개수와 같은 통계를 보고서 파일로 작성합니다.
 */
public class LogReportGenerator {

    /**
     * 주어진 로그 엔트리 목록과 보고서 파일 경로를 사용하여 보고서를 생성합니다.
     *
     * @param logEntries 분석된 로그 엔트리 목록
     * @param outputPath 보고서를 저장할 파일 경로
     * @throws IOException 파일 작성 중 오류 발생 시
     */
    public static void generateReport(List<LogEntry> logEntries, Path outputPath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("로그 분석 보고서
");
            writer.write("===================

");
            writer.write("총 로그 엔트리 수: " + logEntries.size() + "

");

            // 로그 레벨별 개수 집계
            Map<String, Long> levelCounts = logEntries.stream()
                    .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));

            writer.write("로그 레벨별 개수:
");
            levelCounts.forEach((level, count) -> {
                try {
                    writer.write(String.format("  - %s: %d
", level, count));
                } catch (IOException e) {
                    // 내부 루프에서의 예외 처리
                    throw new RuntimeException("보고서 작성 중 오류 발생", e);
                }
            });
            writer.write("
");

            // 예시: 가장 최근 5개 오류 로그 메시지 (있다면)
            writer.write("최근 5개 ERROR/WARN 로그 메시지:
");
            logEntries.stream()
                    .filter(entry -> "ERROR".equals(entry.getLevel()) || "WARN".equals(entry.getLevel()))
                    .sorted((e1, e2) -> e2.getTimestamp().compareTo(e1.getTimestamp())) // 최신 순으로 정렬
                    .limit(5)
                    .forEach(entry -> {
                        try {
                            writer.write(String.format("  [%s] %s - %s
",
                                    entry.getTimestamp().format(LogParser.DATE_TIME_FORMATTER), // LogParser의 포맷터 사용
                                    entry.getLevel(),
                                    entry.getMessage()));
                        } catch (IOException e) {
                            throw new RuntimeException("보고서 작성 중 오류 발생", e);
                        }
                    });
            writer.write("
");

            writer.write("보고서 작성이 완료되었습니다.
");
        }
    }
}
