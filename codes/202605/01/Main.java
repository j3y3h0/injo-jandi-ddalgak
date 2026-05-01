// Main.java
package com.gemini.health_anomaly_detector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 건강 이상 징후 탐지 프로그램의 메인 클래스입니다.
 * 샘플 환자 데이터를 생성하고 AnomalyDetector를 사용하여 이상 징후를 탐지합니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("환자 건강 데이터 이상 징후 탐지 프로그램 시작.");

        // 1. 샘플 환자 데이터 생성
        List<PatientData> patientDataList = generateSamplePatientData(20);

        System.out.println("
--- 전체 환자 데이터 ---");
        patientDataList.forEach(System.out::println);

        // 2. AnomalyDetector 초기화 (임계값 설정)
        // Z-score 임계값 2.0은 데이터의 95%가 이 범위 내에 있다는 것을 의미합니다.
        // 이 값을 조정하여 이상 징후 탐지의 민감도를 변경할 수 있습니다.
        AnomalyDetector detector = new AnomalyDetector(2.0);

        // 3. 이상 징후 탐지
        List<PatientData> anomalies = detector.detectAnomalies(patientDataList);

        // 4. 결과 출력
        System.out.println("
--- 탐지된 이상 징후 환자 데이터 ---");
        if (anomalies.isEmpty()) {
            System.out.println("이상 징후가 탐지된 환자가 없습니다.");
        } else {
            anomalies.forEach(System.out::println);
        }

        System.out.println("
환자 건강 데이터 이상 징후 탐지 프로그램 종료.");
    }

    /**
     * 무작위 샘플 환자 데이터를 생성합니다.
     * @param count 생성할 환자 데이터의 수
     * @return 생성된 환자 데이터 리스트
     */
    private static List<PatientData> generateSamplePatientData(int count) {
        List<PatientData> data = new ArrayList<>();
        Random random = new Random();

        // 정상 범위의 평균 및 표준편차
        double normalHeartRateMean = 75.0;
        double normalHeartRateStdDev = 5.0;
        double normalTemperatureMean = 36.8;
        double normalTemperatureStdDev = 0.4;

        for (int i = 0; i < count; i++) {
            String patientId = "PAT" + String.format("%03d", i + 1);
            double heartRate;
            double temperature;

            // 약 10% 확률로 이상치 데이터 생성
            if (random.nextDouble() < 0.1) {
                // 이상치: 심박수 또는 체온이 정상 범위를 크게 벗어남
                heartRate = normalHeartRateMean + (random.nextBoolean() ? 1 : -1) * (normalHeartRateStdDev * 3 + random.nextDouble() * 10);
                temperature = normalTemperatureMean + (random.nextBoolean() ? 1 : -1) * (normalTemperatureStdDev * 3 + random.nextDouble() * 2);
            } else {
                // 정상치: 평균 근처에서 정규 분포에 따라 생성
                heartRate = normalHeartRateMean + random.nextGaussian() * normalHeartRateStdDev;
                temperature = normalTemperatureMean + random.nextGaussian() * normalTemperatureStdDev;
            }

            // 너무 비현실적인 값 방지 (예: 음수 심박수)
            heartRate = Math.max(30, heartRate);
            temperature = Math.max(30.0, temperature);

            data.add(new PatientData(patientId, heartRate, temperature));
        }
        return data;
    }
}
