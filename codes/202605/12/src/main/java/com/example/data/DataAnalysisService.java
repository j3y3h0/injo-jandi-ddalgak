package com.example.data;

import com.example.data.model.DataRecord;

import java.util.List;
import java.util.OptionalDouble;

/**
 * DataRecord 리스트를 기반으로 데이터 분석을 수행하는 서비스 클래스입니다.
 * 현재는 점수의 평균을 계산하는 기능을 제공합니다.
 */
public class DataAnalysisService {

    /**
     * DataRecord 리스트에서 '점수' 필드의 평균을 계산합니다.
     *
     * @param records 분석할 DataRecord 리스트
     * @return 점수의 평균 (리스트가 비어있으면 OptionalDouble.empty())
     */
    public OptionalDouble calculateAverageScore(List<DataRecord> records) {
        if (records == null || records.isEmpty()) {
            return OptionalDouble.empty();
        }
        return records.stream()
                .mapToDouble(DataRecord::getScore)
                .average();
    }
}
