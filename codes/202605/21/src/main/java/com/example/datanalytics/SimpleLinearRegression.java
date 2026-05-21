package com.example.datanalytics;

import java.util.List;

public class SimpleLinearRegression {
    private double intercept; // 절편
    private double slope;     // 기울기

    /**
     * 선형 회귀 모델을 훈련합니다.
     * 주어진 독립 변수(X)와 종속 변수(Y)를 사용하여 절편과 기울기를 계산합니다.
     *
     * @param x 독립 변수 목록
     * @param y 종속 변수 목록
     */
    public void train(List<Double> x, List<Double> y) {
        if (x.size() != y.size() || x.isEmpty()) {
            throw new IllegalArgumentException("독립 변수와 종속 변수의 크기가 같아야 하며 비어 있지 않아야 합니다.");
        }

        int n = x.size();

        double sumX = 0.0;
        double sumY = 0.0;
        double sumXY = 0.0;
        double sumX2 = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * y.get(i);
            sumX2 += x.get(i) * x.get(i);
        }

        // 기울기(slope) 계산: beta1 = (n * sum(xy) - sum(x) * sum(y)) / (n * sum(x^2) - sum(x)^2)
        slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);

        // 절편(intercept) 계산: beta0 = mean(y) - beta1 * mean(x)
        intercept = (sumY / n) - slope * (sumX / n);
    }

    /**
     * 훈련된 모델을 사용하여 새로운 독립 변수에 대한 종속 변수를 예측합니다.
     *
     * @param newX 예측할 새로운 독립 변수 값
     * @return 예측된 종속 변수 값
     */
    public double predict(double newX) {
        return intercept + slope * newX;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }
}
