// SummarizationService.java
package com.gemini.summarizer;

/**
 * 텍스트 요약 기능을 정의하는 인터페이스입니다.
 * 다양한 요약 구현체를 위한 계약을 제공합니다.
 */
public interface SummarizationService {
    /**
     * 주어진 텍스트를 요약합니다.
     * @param text 원본 텍스트
     * @return 요약된 텍스트
     */
    String summarize(String text);
}
