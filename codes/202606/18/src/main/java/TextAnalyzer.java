import java.io.IOException;
import java.util.Map;

/**
 * TextAnalyzer는 텍스트 파일의 내용을 분석하여 단어 빈도를 계산하고 보고서를 생성하는
 * 메인 애플리케이션 클래스입니다.
 * FileProcessor, WordCounter, AnalysisReportGenerator를 사용하여 작업을 수행합니다.
 */
public class TextAnalyzer {

    public static void main(String[] args) {
        // 분석할 파일의 경로를 인자로 받거나, 기본값을 사용합니다.
        // 현재 작업 디렉토리에 'input.txt' 파일이 있다고 가정합니다.
        String filePath = "input.txt";
        if (args.length > 0) {
            filePath = args[0];
        }

        System.out.println("--- 텍스트 분석 시작 ---");
        System.out.println("분석 파일: " + filePath);

        FileProcessor fileProcessor = new FileProcessor();
        WordCounter wordCounter = new WordCounter();
        AnalysisReportGenerator reportGenerator = new AnalysisReportGenerator();

        try {
            // 1. 파일 내용 읽기
            String fileContent = fileProcessor.readFile(filePath);
            System.out.println("파일 내용 읽기 완료.");

            // 2. 단어 빈도 계산
            Map<String, Integer> wordCounts = wordCounter.countWords(fileContent);
            System.out.println("단어 빈도 계산 완료.");

            // 3. 분석 보고서 생성 및 출력
            // 상위 10개 단어를 포함하는 보고서를 생성합니다.
            String report = reportGenerator.generateReport(wordCounts, 10);
            System.out.println("분석 보고서 생성 완료.");
            System.out.println("
" + report);

        } catch (IOException e) {
            System.err.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--- 텍스트 분석 종료 ---");
    }
}
