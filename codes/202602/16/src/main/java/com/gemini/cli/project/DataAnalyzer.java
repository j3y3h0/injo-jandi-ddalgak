package com.gemini.cli.project;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CSV 데이터에 대한 기본적인 분석을 수행하는 클래스이다.
 * 특정 컬럼의 평균 계산, 카테고리별 개수 집계 등을 수행할 수 있다.
 */
public class DataAnalyzer {

    /**
     * 특정 컬럼의 숫자 값들에 대한 평균을 계산한다.
     * 컬럼 값이 숫자로 변환될 수 없는 경우 무시한다.
     *
     * @param records 분석할 CSV 레코드 리스트
     * @param columnName 평균을 계산할 컬럼의 이름
     * @return 지정된 컬럼의 숫자 값들에 대한 평균. 숫자가 없는 경우 0.0을 반환한다.
     */
    public double calculateAverage(List<Map<String, String>> records, String columnName) {
        return records.stream()
                .map(record -> record.get(columnName)) // 컬럼 값 추출
                .filter(value -> value != null && !value.trim().isEmpty()) // null 또는 빈 값 필터링
                .mapToDouble(value -> {
                    try {
                        return Double.parseDouble(value); // Double로 변환 시도
                    } catch (NumberFormatException e) {
                        return Double.NaN; // 숫자가 아닌 경우 NaN 반환
                    }
                })
                .filter(value -> !Double.isNaN(value)) // NaN 값 필터링
                .average() // 평균 계산
                .orElse(0.0); // 값이 없는 경우 0.0 반환
    }

    /**
     * 특정 카테고리 컬럼의 값들을 기준으로 각 카테고리별 레코드 개수를 집계한다.
     *
     * @param records 분석할 CSV 레코드 리스트
     * @param categoryColumnName 카테고리 분류 기준으로 사용할 컬럼의 이름
     * @return 각 카테고리 이름과 해당 카테고리에 속하는 레코드 개수를 담은 Map
     */
    public Map<String, Long> countByCategory(List<Map<String, String>> records, String categoryColumnName) {
        return records.stream()
                .map(record -> record.get(categoryColumnName)) // 카테고리 컬럼 값 추출
                .filter(value -> value != null && !value.trim().isEmpty()) // null 또는 빈 값 필터링
                .collect(Collectors.groupingBy(
                        category -> category, // 카테고리 값으로 그룹화
                        Collectors.counting() // 각 그룹의 개수 세기
                ));
    }
}
