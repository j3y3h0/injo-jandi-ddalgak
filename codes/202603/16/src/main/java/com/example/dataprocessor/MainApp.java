package com.example.dataprocessor;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * 이 클래스는 빅데이터 텍스트 분석기 애플리케이션의 메인 진입점입니다.
 * 샘플 텍스트 데이터를 처리하여 단어 빈도를 계산하고, 결과를 출력합니다.
 */
public class MainApp {

    public static void main(String[] args) {
        // 분석할 샘플 텍스트 데이터입니다.
        String sampleText = "빅데이터는 현대 사회의 핵심입니다. 빅데이터 분석은 새로운 통찰력을 제공합니다. " +
                            "데이터는 새로운 기회를 창출하며, 데이터 분석은 미래를 만듭니다.";

        System.out.println("--- 텍스트 분석 시작 ---");
        System.out.println("원본 텍스트: " + sampleText);

        // 1. 텍스트 전처리: 단어 추출 및 정규화
        List<String> words = DataUtil.tokenizeAndNormalize(sampleText);
        System.out.println("
전처리된 단어 목록 (" + words.size() + "개): " + words.subList(0, Math.min(10, words.size())) + (words.size() > 10 ? "..." : "")); // 처음 10개만 출력

        // 2. 단어 빈도 계산
        WordCounter wordCounter = new WordCounter();
        Map<String, Integer> wordFrequencies = wordCounter.countWords(words);
        System.out.println("
단어별 빈도수 (일부): " + wordFrequencies.entrySet().stream()
                                            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                            .limit(5)
                                            .collect(java.util.stream.Collectors.toList())); // 상위 5개만 출력

        // 3. 가장 빈번한 단어 찾기 (선택 사항)
        List<Map.Entry<String, Integer>> topWords = wordCounter.getTopNWords(wordFrequencies, 3);
        System.out.println("
가장 빈번한 상위 " + topWords.size() + "개 단어:");
        for (int i = 0; i < topWords.size(); i++) {
            Map.Entry<String, Integer> entry = topWords.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + ": " + entry.getValue() + "회");
        }

        System.out.println("
--- 텍스트 분석 완료 ---");
    }
}
