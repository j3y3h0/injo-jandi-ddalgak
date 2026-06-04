package com.example.dataprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 데이터 처리 및 분석 애플리케이션의 메인 클래스입니다.
 * 이 애플리케이션은 샘플 데이터를 생성하고,
 * 데이터의 평균을 계산하는 간단한 분석을 수행합니다.
 */
public class DataProcessorApp {

    public static void main(String[] args) {
        System.out.println("데이터 처리 애플리케이션을 시작합니다.");

        // 1. 샘플 데이터 생성
        List<DataModel> sampleData = generateSampleData(10);
        System.out.println("
생성된 샘플 데이터:");
        for (DataModel data : sampleData) {
            System.out.println(data);
        }

        // 2. 데이터 분석: 값들의 평균 계산
        double averageValue = calculateAverage(sampleData);
        System.out.println("
데이터 값의 평균: " + String.format("%.2f", averageValue));

        System.out.println("
데이터 처리 애플리케이션을 종료합니다.");
    }

    /**
     * 지정된 개수만큼의 샘플 DataModel 객체를 생성합니다.
     * 각 객체는 임의의 이름과 0.0에서 100.0 사이의 임의의 값을 가집니다.
     *
     * @param count 생성할 데이터 모델의 개수
     * @return 생성된 DataModel 객체 리스트
     */
    private static List<DataModel> generateSampleData(int count) {
        List<DataModel> dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String name = "Item-" + (i + 1);
            double value = 0.0 + (100.0 - 0.0) * random.nextDouble(); // 0.0 이상 100.0 미만
            dataList.add(new DataModel(name, value));
        }
        return dataList;
    }

    /**
     * DataModel 리스트의 값(value) 필드에 대한 평균을 계산합니다.
     * 리스트가 비어있으면 0.0을 반환합니다.
     *
     * @param dataList 평균을 계산할 DataModel 객체 리스트
     * @return 값 필드의 평균
     */
    private static double calculateAverage(List<DataModel> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (DataModel data : dataList) {
            sum += data.getValue();
        }
        return sum / dataList.size();
    }
}
