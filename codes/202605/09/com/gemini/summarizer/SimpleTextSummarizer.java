// SimpleTextSummarizer.java
package com.gemini.summarizer;

/**
 * SummarizationService 인터페이스의 간단한 구현체입니다.
 * 실제 AI 모델과의 연동 대신, 텍스트의 일정 비율을 추출하는 방식으로 요약을 시뮬레이션합니다.
 * 실제 AI 서비스에서는 이 부분이 외부 AI API 호출 또는 내장된 AI 모델 추론 로직으로 대체될 수 있습니다.
 */
public class SimpleTextSummarizer implements SummarizationService {

    private static final double SUMMARY_RATIO = 0.3; // 원본 텍스트의 30%를 요약으로 사용

    @Override
    public String summarize(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "요약할 텍스트가 없습니다.";
        }

        String[] sentences = text.split("[.!?]\s*"); // 문장 단위로 분리
        int numSentencesToKeep = (int) Math.ceil(sentences.length * SUMMARY_RATIO);

        if (numSentencesToKeep == 0 && sentences.length > 0) {
            numSentencesToKeep = 1; // 최소한 한 문장은 포함
        }

        StringBuilder summaryBuilder = new StringBuilder();
        for (int i = 0; i < numSentencesToKeep; i++) {
            if (i < sentences.length) {
                summaryBuilder.append(sentences[i].trim());
                if (!sentences[i].trim().endsWith(".")) { // 마침표가 없으면 추가
                    summaryBuilder.append(".");
                }
                summaryBuilder.append(" ");
            }
        }

        String summary = summaryBuilder.toString().trim();
        if (summary.isEmpty() && !text.isEmpty()) {
            // 문장 분리가 실패했거나, 너무 짧아 요약이 안 된 경우 처음 일부를 반환
            return text.substring(0, Math.min(text.length(), 100)) + "...";
        }
        return summary;
    }
}
