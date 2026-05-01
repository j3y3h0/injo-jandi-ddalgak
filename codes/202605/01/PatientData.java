// PatientData.java
package com.gemini.health_anomaly_detector;

/**
 * 환자 건강 데이터를 나타내는 클래스입니다.
 * 심박수, 체온 등 기본적인 건강 지표를 포함합니다.
 */
public class PatientData {
    private String patientId;
    private double heartRate; // 심박수
    private double temperature; // 체온

    public PatientData(String patientId, double heartRate, double temperature) {
        this.patientId = patientId;
        this.heartRate = heartRate;
        this.temperature = temperature;
    }

    public String getPatientId() {
        return patientId;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "PatientData{" +
               "patientId='" + patientId + ''' +
               ", heartRate=" + heartRate +
               ", temperature=" + temperature +
               '}';
    }
}
