// Main.java
// 로그 파일을 읽고, 파싱하며, 분석 결과를 출력하는 메인 클래스입니다.

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String logFilePath = "sample.log"; // 분석할 로그 파일 경로

        try (Stream<String> lines = Files.lines(Paths.get(logFilePath))) {
            // 1. 로그 파일 읽기 및 파싱
            List<LogEntry> logEntries = lines
                .map(LogParser::parse) // 각 라인을 LogEntry 객체로 파싱
                .filter(java.util.Objects::nonNull) // 파싱에 실패한 라인 (null) 제외
                .collect(Collectors.toList());

            if (logEntries.isEmpty()) {
                System.out.println("분석할 로그 엔트리가 없습니다.");
                return;
            }

            // 2. 로그 분석 수행
            LogAnalyzer analyzer = new LogAnalyzer(logEntries);

            System.out.println("--- 로그 레벨별 통계 ---");
            analyzer.analyzeLogLevels().forEach((level, count) ->
                System.out.println(String.format("  [%s]: %d 건", level, count))
            );

            System.out.println("
--- 고유 소스별 통계 ---");
            analyzer.analyzeUniqueSources().forEach((source, count) ->
                System.out.println(String.format("  [%s]: %d 건", source, count))
            );

            System.out.println("
--- ERROR 레벨 로그 목록 ---");
            List<LogEntry> errorLogs = analyzer.filterLogsByLevel("ERROR");
            if (errorLogs.isEmpty()) {
                System.out.println("  ERROR 레벨 로그가 없습니다.");
            } else {
                errorLogs.forEach(log -> System.out.println("  " + log));
            }

        } catch (IOException e) {
            System.err.println("로그 파일을 읽는 중 오류 발생: " + e.getMessage());
        }
    }
}
