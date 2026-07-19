// DataAnalyzer.java
package com.example.csvproject;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CSVRecord 리스트에서 데이터를 분석하는 클래스이다.
 * 주로 숫자 데이터를 대상으로 합계와 평균을 계산하는 기능을 제공한다.
 */
public class DataAnalyzer {

    /**
     * 지정된 컬럼 이름에 해당하는 모든 숫자 값들의 합계를 계산한다.
     * 유효하지 않은 숫자 형식의 값은 무시한다.
     *
     * @param records 분석할 CSVRecord 리스트이다.
     * @param columnName 합계를 계산할 컬럼의 이름이다.
     * @return 지정된 컬럼 값들의 합계이다.
     */
    public double calculateSum(List<CSVRecord> records, String columnName) {
        return records.stream()
                .mapToDouble(record -> {
                    try {
                        // 컬럼 이름으로 값을 가져와 double 형태로 변환한다.
                        return Double.parseDouble(record.get(columnName));
                    } catch (NumberFormatException e) {
                        // 숫자 형식이 아닌 값은 0으로 처리하거나 무시할 수 있다. 여기서는 무시한다.
                        System.err.println("경고: '" + record.get(columnName) + "'는 유효한 숫자가 아니다. 이 값은 합계 계산에서 제외된다.");
                        return 0.0; // 또는 예외 처리 로직에 따라 다른 동작 수행
                    } catch (IllegalArgumentException e) {
                        System.err.println("경고: 컬럼 '" + columnName + "'을(를) 찾을 수 없다. 이 값은 합계 계산에서 제외된다.");
                        return 0.0;
                    }
                })
                .sum();
    }

    /**
     * 지정된 컬럼 이름에 해당하는 모든 숫자 값들의 평균을 계산한다.
     * 유효하지 않은 숫자 형식의 값은 무시한다.
     *
     * @param records 분석할 CSVRecord 리스트이다.
     * @param columnName 평균을 계산할 컬럼의 이름이다.
     * @return 지정된 컬럼 값들의 평균이다. 데이터가 없으면 0.0을 반환한다.
     */
    public double calculateAverage(List<CSVRecord> records, String columnName) {
        List<Double> validNumbers = records.stream()
                .map(record -> {
                    try {
                        return Double.parseDouble(record.get(columnName));
                    } catch (NumberFormatException | IllegalArgumentException e) {
                        return null; // 유효하지 않은 값은 null로 표시
                    }
                })
                .filter(num -> num != null) // 유효한 숫자만 필터링
                .collect(Collectors.toList());

        if (validNumbers.isEmpty()) {
            return 0.0; // 유효한 숫자가 없으면 평균은 0이다.
        }

        double sum = validNumbers.stream().mapToDouble(Double::doubleValue).sum();
        return sum / validNumbers.size();
    }
}
