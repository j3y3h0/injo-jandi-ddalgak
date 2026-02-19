// LogAnalyzer.java
package com.example.loganalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 로그 파일을 읽고 분석하여 보고서를 생성하는 메인 애플리케이션 클래스입니다.
 *
 * 사용법: java com.example.loganalyzer.LogAnalyzer <로그_파일_경로> <보고서_저장_경로>
 */
public class LogAnalyzer {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("사용법: java com.example.loganalyzer.LogAnalyzer <로그_파일_경로> <보고서_저장_경로>");
            System.exit(1);
        }

        Path logFilePath = Paths.get(args[0]);
        Path reportOutputPath = Paths.get(args[1]);

        if (!Files.exists(logFilePath)) {
            System.err.println("오류: 로그 파일이 존재하지 않습니다. 경로: " + logFilePath);
            System.exit(1);
        }

        System.out.println("로그 파일 분석 시작: " + logFilePath);

        List<LogEntry> logEntries;
        try (Stream<String> lines = Files.lines(logFilePath)) {
            logEntries = lines.map(LogParser::parseLogLine)
                              .filter(java.util.Optional::isPresent)
                              .map(java.util.Optional::get)
                              .collect(Collectors.toList());
            System.out.println("총 " + logEntries.size() + "개의 로그 엔트리 파싱 완료.");
        } catch (IOException e) {
            System.err.println("로그 파일 읽기 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
            return; // 추가
        }

        try {
            LogReportGenerator.generateReport(logEntries, reportOutputPath);
            System.out.println("로그 분석 보고서가 성공적으로 생성되었습니다: " + reportOutputPath);
        } catch (IOException e) {
            System.err.println("보고서 생성 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
