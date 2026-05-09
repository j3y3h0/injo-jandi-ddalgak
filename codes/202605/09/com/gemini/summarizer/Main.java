// Main.java
package com.gemini.summarizer;

/**
 * AI 기반 텍스트 요약기 애플리케이션의 메인 클래스입니다.
 * 사용자로부터 텍스트를 입력받아 요약 서비스를 통해 요약된 결과를 출력합니다.
 */
public class Main {
    public static void main(String[] args) {
        SummarizationService summarizer = new SimpleTextSummarizer();

        ConsoleUtil.printMessage("환영합니다! AI 기반 텍스트 요약기입니다.");
        ConsoleUtil.printMessage("요약하고 싶은 텍스트를 입력해주세요. (입력 종료는 빈 줄 입력 후 엔터)");

        StringBuilder inputTextBuilder = new StringBuilder();
        String line;
        while (true) {
            line = ConsoleUtil.readLine("> ");
            if (line.trim().isEmpty()) {
                break;
            }
            inputTextBuilder.append(line).append(" ");
        }

        String inputText = inputTextBuilder.toString().trim();

        if (!inputText.isEmpty()) {
            String summary = summarizer.summarize(inputText);

            ConsoleUtil.printMessage("
--- 원본 텍스트 ---");
            ConsoleUtil.printMessage(inputText);
            ConsoleUtil.printMessage("
--- 요약된 텍스트 ---");
            ConsoleUtil.printMessage(summary);
        } else {
            ConsoleUtil.printMessage("입력된 텍스트가 없어 요약을 진행하지 않습니다.");
        }

        ConsoleUtil.closeScanner();
        ConsoleUtil.printMessage("
애플리케이션을 종료합니다.");
    }
}
