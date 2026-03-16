package com.example.dataprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

/**
 * 이 클래스는 주어진 단어 목록에서 각 단어의 출현 빈도를 계산하는 기능을 제공합니다.
 * 이는 맵리듀스(MapReduce) 패턴의 'Map' 및 'Reduce' 단계를 단순화한 형태입니다.
 */
public class WordCounter {

    /**
     * 단어 목록을 받아 각 단어의 빈도를 계산하여 맵(Map) 형태로 반환합니다.
     *
     * @param words 처리할 단어 목록입니다.
     * @return 각 단어와 그 단어의 출현 빈도를 매핑한 맵(Map)입니다.
     */
    public Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        // 각 단어의 빈도를 계산합니다.
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        return wordFrequencies;
    }

    /**
     * 단어 빈도 맵에서 가장 빈번하게 나타나는 상위 N개의 단어를 찾습니다.
     *
     * @param wordFrequencies 단어 빈도 맵입니다.
     * @param n               가져올 상위 단어의 개수입니다.
     * @return 빈도수가 높은 순서대로 정렬된 상위 N개의 단어 엔트리 목록입니다.
     */
    public List<Map.Entry<String, Integer>> getTopNWords(Map<String, Integer> wordFrequencies, int n) {
        // 빈도수를 기준으로 내림차순 정렬하여 상위 N개의 단어를 추출합니다.
        return wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .collect(Collectors.toList());
    }
}
