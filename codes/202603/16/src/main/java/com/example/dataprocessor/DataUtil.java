package com.example.dataprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 이 클래스는 텍스트 데이터의 전처리 작업을 위한 유틸리티 메서드를 제공합니다.
 * 텍스트를 단어 단위로 분리하고 정규화하는 기능을 포함합니다.
 */
public class DataUtil {

    // 단어를 분리하고 불필요한 문자를 제거하기 위한 정규표현식 패턴입니다.
    private static final Pattern WORD_SPLIT_PATTERN = Pattern.compile("[\s.,;!?()]");

    /**
     * 주어진 텍스트를 단어 단위로 분리하고, 소문자로 변환하며, 불필요한 문자를 제거하여 정규화합니다.
     * 이는 실제 빅데이터 파이프라인에서 데이터 정제(Data Cleaning) 단계에 해당합니다.
     *
     * @param text 전처리할 원본 텍스트입니다.
     * @return 전처리되어 정규화된 단어 목록입니다.
     */
    public static List<String> tokenizeAndNormalize(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }

        // 텍스트를 소문자로 변환하고, 정규표현식을 사용하여 단어로 분리합니다.
        // 비어있는 문자열은 필터링합니다.
        return Arrays.stream(WORD_SPLIT_PATTERN.split(text.toLowerCase()))
                .map(String::trim)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }
}
