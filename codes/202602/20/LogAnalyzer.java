// LogAnalyzer.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 로그 파일을 읽고 특정 키워드의 발생 횟수를 분석하는 메인 애플리케이션 클래스이다.
 */
public class LogAnalyzer {

    // 분석할 로그 파일의 경로이다.
    private static final String LOG_FILE_PATH = "sample.log";

    public static void main(String[] args) {
        // 검색할 키워드 목록을 정의한다.
        List<String> keywordsToSearch = Arrays.asList("ERROR", "INFO", "WARN", "DEBUG", "실패", "성공");

        try {
            // 로그 파일을 읽어 모든 라인을 리스트로 가져온다.
            List<String> logLines = Files.readAllLines(Paths.get(LOG_FILE_PATH));

            // KeywordCounter 유틸리티를 사용하여 키워드 발생 횟수를 계산한다.
            Map<String, Integer> counts = KeywordCounter.countKeywords(logLines, keywordsToSearch);

            // 분석 결과를 출력한다.
            System.out.println("로그 파일 [" + LOG_FILE_PATH + "] 분석 결과이다.");
            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                System.out.println("키워드 '" + entry.getKey() + "': " + entry.getValue() + "회 발생하였다.");
            }

        } catch (IOException e) {
            // 파일 읽기 중 오류가 발생하면 예외를 처리한다.
            System.err.println("로그 파일을 읽는 중 오류가 발생하였다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
