package com.example.datanalytics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

public class DataProcessor {

    /**
     * 주어진 데이터 목록의 평균을 계산합니다.
     * @param data 데이터 목록
     * @return 데이터의 평균
     */
    public static double calculateMean(List<Double> data) {
        // Apache Commons Math 라이브러리를 사용하여 통계 계산을 수행합니다.
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Double value : data) {
            stats.addValue(value);
        }
        return stats.getMean();
    }

    /**
     * 주어진 데이터 목록의 표준편차를 계산합니다.
     * @param data 데이터 목록
     * @return 데이터의 표준편차
     */
    public static double calculateStandardDeviation(List<Double> data) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Double value : data) {
            stats.addValue(value);
        }
        return stats.getStandardDeviation();
    }
}
