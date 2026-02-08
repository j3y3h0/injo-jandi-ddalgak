// Main.java
import java.io.IOException;
import java.util.Map;

/**
 * 텍스트 키워드 빈도 분석 프로그램의 메인 클래스입니다.
 * 프로그램 실행 시 인자로 텍스트 파일 경로를 받아 해당 파일의 단어 빈도를 분석하고 상위 단어를 출력합니다.
 */
public class Main {

    public static void main(String[] args) {
        // 프로그램 실행 시 인자가 하나(파일 경로) 제공되었는지 확인합니다.
        if (args.length != 1) {
            System.out.println("사용법: java Main <텍스트_파일_경로>");
            System.out.println("예시: java Main input.txt");
            return; // 인자 오류 시 프로그램 종료
        }

        String filePath = args[0]; // 첫 번째 인자를 파일 경로로 사용합니다.
        String fileContent;

        try {
            // FileUtil을 사용하여 파일 내용을 읽어옵니다.
            fileContent = FileUtil.readFileToString(filePath);
        } catch (IOException e) {
            // 파일 읽기 중 오류 발생 시 오류 메시지를 출력하고 프로그램을 종료합니다.
            System.err.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
            return;
        }

        // TextAnalyzer 객체를 생성합니다.
        TextAnalyzer analyzer = new TextAnalyzer();

        // 파일 내용을 분석하여 단어 빈도를 계산합니다.
        Map<String, Integer> wordFrequencies = analyzer.analyze(fileContent);

        // 상위 10개 단어를 가져옵니다. (이 숫자는 필요에 따라 변경 가능합니다.)
        int topN = 10;
        Map<String, Integer> topWords = analyzer.getTopNWords(wordFrequencies, topN);

        // 분석 결과를 출력합니다.
        System.out.println("--- 텍스트 분석 결과 (상위 " + topN + " 단어) ---");
        if (topWords.isEmpty()) {
            System.out.println("분석할 단어가 없거나 결과가 없습니다.");
        } else {
            for (Map.Entry<String, Integer> entry : topWords.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "회");
            }
        }
    }
}
