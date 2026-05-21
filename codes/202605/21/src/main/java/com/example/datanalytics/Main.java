package com.example.datanalytics;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 데이터 분석 프로젝트의 메인 애플리케이션입니다.
        // 여기서는 데이터 로드, 처리 및 모델 적용 과정을 보여줍니다.

        System.out.println("데이터 분석 프로젝트를 시작합니다.");

        // 1. 샘플 데이터 생성
        List<Double> data = Arrays.asList(10.0, 12.0, 15.0, 13.0, 18.0, 20.0, 22.0, 25.0, 23.0, 28.0);
        List<Double> independentVariables = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);

        System.out.println("원본 데이터: " + data);

        // 2. DataProcessor를 이용한 데이터 처리 (평균 및 표준편차 계산)
        double mean = DataProcessor.calculateMean(data);
        double standardDeviation = DataProcessor.calculateStandardDeviation(data);

        System.out.printf("데이터 평균: %.2f%n", mean);
        System.out.printf("데이터 표준편차: %.2f%n", standardDeviation);

        // 3. SimpleLinearRegression을 이용한 모델 적용
        SimpleLinearRegression regression = new SimpleLinearRegression();
        regression.train(independentVariables, data);

        System.out.printf("선형 회귀 모델 훈련 완료. 절편 (Intercept): %.2f, 기울기 (Slope): %.2f%n",
                          regression.getIntercept(), regression.getSlope());

        // 4. 새로운 값에 대한 예측
        double newValue = 11.0;
        double predictedValue = regression.predict(newValue);
        System.out.printf("새로운 값 (%.2f)에 대한 예측: %.2f%n", newValue, predictedValue);

        System.out.println("데이터 분석 프로젝트를 종료합니다.");
    }
}
