package com.example.csvprocessor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CSV 데이터를 분석하는 비즈니스 로직을 포함하는 클래스입니다.
 * 예를 들어, 카테고리별 평균 값을 계산하는 기능을 제공합니다.
 */
public class DataAnalyzer {

    /**
     * 제공된 Record 리스트에서 카테고리별 평균 'Value'를 계산합니다.
     *
     * @param records 분석할 Record 객체 리스트
     * @return 각 카테고리의 이름과 해당 카테고리의 평균 값을 매핑하는 맵
     */
    public Map<String, Double> calculateAverageValueByCategory(List<Record> records) {
        if (records == null || records.isEmpty()) {
            return Map.of(); // 빈 맵 반환
        }

        // Stream API를 사용하여 카테고리별 평균 계산
        return records.stream()
                .collect(Collectors.groupingBy(
                        Record::getCategory,
                        Collectors.averagingDouble(Record::getValue)
                ));
    }

    /**
     * 특정 값 이상의 레코드를 필터링합니다.
     *
     * @param records 필터링할 Record 객체 리스트
     * @param threshold 최소 기준 값
     * @return 기준 값 이상의 Record 객체 리스트
     */
    public List<Record> filterRecordsAboveValue(List<Record> records, double threshold) {
        if (records == null || records.isEmpty()) {
            return List.of();
        }

        return records.stream()
                .filter(record -> record.getValue() >= threshold)
                .collect(Collectors.toList());
    }
}
