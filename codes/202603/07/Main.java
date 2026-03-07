// Main.java
// 텍스트 분석 프로그램의 메인 진입점이다.
// TextUtility와 TextProcessor 클래스를 사용하여 텍스트의 단어 빈도수를 계산하고 출력하는 예제를 제공한다.

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 분석할 예제 텍스트이다.
        String sampleText = "Hello world, this is a sample text. Hello again, world. This text is for testing purposes.";

        System.out.println("--- 원본 텍스트 ---");
        System.out.println(sampleText);
        System.out.println();

        // TextProcessor 인스턴스를 생성한다.
        TextProcessor processor = new TextProcessor();

        // 단어 빈도수를 계산한다.
        Map<String, Integer> wordFrequencies = processor.getWordFrequency(sampleText);

        System.out.println("--- 단어 빈도수 결과 ---");
        // 계산된 단어 빈도수를 출력한다.
        wordFrequencies.entrySet().stream()
                       .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // 빈도수가 높은 순서로 정렬
                       .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + "회"));
    }
}
