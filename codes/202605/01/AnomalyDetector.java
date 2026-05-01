// AnomalyDetector.java
package com.gemini.health_anomaly_detector;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 환자 데이터에서 이상 징후를 탐지하는 클래스입니다.
 * Z-score 방법을 사용하여 데이터를 분석합니다.
 * (참고: 실제 산업 환경에서는 Apache Commons Math와 같은 라이브러리를 사용하여
 * 평균 및 표준편차 계산을 더 견고하게 수행할 수 있습니다.)
 */
public class AnomalyDetector {

    private double threshold; // 이상 징후 판단 기준 (Z-score 임계값)

    public AnomalyDetector(double threshold) {
        this.threshold = threshold;
    }

    /**
     * 주어진 건강 지표 리스트에서 평균을 계산합니다.
     * @param values 건강 지표 값 리스트
     * @return 평균 값
     */
    private double calculateMean(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    /**
     * 주어진 건강 지표 리스트에서 표준편차를 계산합니다.
     * @param values 건강 지표 값 리스트
     * @param mean 평균 값
     * @return 표준편차 값
     */
    private double calculateStandardDeviation(List<Double> values, double mean) {
        double sumOfSquares = values.stream()
                .mapToDouble(value -> Math.pow(value - mean, 2))
                .sum();
        return Math.sqrt(sumOfSquares / values.size());
    }

    /**
     * Z-score를 계산하여 이상 징후를 탐지합니다.
     * Z-score는 (개별 값 - 평균) / 표준편차 입니다.
     * @param patientDataList 분석할 환자 데이터 리스트
     * @return 이상 징후가 탐지된 환자 데이터 리스트
     */
    public List<PatientData> detectAnomalies(List<PatientData> patientDataList) {
        if (patientDataList == null || patientDataList.isEmpty()) {
            return new ArrayList<>();
        }

        // 심박수와 체온 데이터를 추출합니다.
        List<Double> heartRates = patientDataList.stream()
                .map(PatientData::getHeartRate)
                .collect(Collectors.toList());
        List<Double> temperatures = patientDataList.stream()
                .map(PatientData::getTemperature)
                .collect(Collectors.toList());

        // 심박수 및 체온의 평균과 표준편차를 계산합니다.
        double meanHeartRate = calculateMean(heartRates);
        double stdDevHeartRate = calculateStandardDeviation(heartRates, meanHeartRate);
        double meanTemperature = calculateMean(temperatures);
        double stdDevTemperature = calculateStandardDeviation(temperatures, meanTemperature);

        List<PatientData> anomalousPatients = new ArrayList<>();

        // 각 환자 데이터에 대해 Z-score를 계산하고 임계값을 초과하는지 확인합니다.
        for (PatientData data : patientDataList) {
            double zScoreHeartRate = (stdDevHeartRate == 0) ? 0 : Math.abs((data.getHeartRate() - meanHeartRate) / stdDevHeartRate);
            double zScoreTemperature = (stdDevTemperature == 0) ? 0 : Math.abs((data.getTemperature() - meanTemperature) / stdDevTemperature);

            if (zScoreHeartRate > threshold || zScoreTemperature > threshold) {
                anomalousPatients.add(data);
            }
        }
        return anomalousPatients;
    }
}
