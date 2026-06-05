# main.py
#
# 이 파일은 센서 데이터 이상 감지 프로젝트의 메인 실행 스크립트입니다.
# 데이터 생성, 이상 감지 모델 학습 및 예측, 결과 시각화의 전체 워크플로우를 담당합니다.

import pandas as pd
from data_generator import generate_sensor_data
from anomaly_detector import AnomalyDetector
from utils import plot_sensor_data_with_anomalies, save_dataframe_to_csv
import os

def main():
    """
    주요 프로그램 실행 로직을 포함하는 함수입니다.
    센서 데이터 생성부터 이상 감지 및 결과 시각화까지의 과정을 수행합니다.
    """
    print("--------------------------------------------------")
    print("       센서 데이터 이상 감지 프로젝트 시작        ")
    print("--------------------------------------------------")

    # 1. 센서 데이터 생성
    print("
[1단계] 시뮬레이션 센서 데이터 생성 중...")
    num_samples = 1500
    anomaly_ratio = 0.03 # 전체 데이터 중 3%를 이상치로 가정
    sensor_df = generate_sensor_data(num_samples=num_samples, anomaly_ratio=anomaly_ratio)
    
    # 생성된 데이터를 CSV 파일로 저장
    output_data_filename = "simulated_sensor_data.csv"
    save_dataframe_to_csv(sensor_df, output_data_filename)
    print(f"생성된 데이터가 '{output_data_filename}'에 저장되었습니다.")

    # 2. 이상 감지 모델 초기화 및 학습
    print("
[2단계] 이상 감지 모델(Isolation Forest) 학습 중...")
    # 'contamination' 매개변수는 예상되는 이상치 비율을 알려줍니다.
    # 여기서는 데이터 생성 시 사용한 anomaly_ratio와 유사하게 설정합니다.
    detector = AnomalyDetector(contamination=anomaly_ratio)
    
    # 모델 학습에 사용할 피처 선택. 여기서는 'value' 컬럼만 사용합니다.
    # Isolation Forest는 다변량 데이터도 처리 가능하지만, 예시를 위해 단변량으로 진행합니다.
    X = sensor_df[['value']]
    detector.fit(X)

    # 3. 이상 감지 예측
    print("
[3단계] 데이터에 대한 이상 감지 예측 수행 중...")
    # 모델 예측 결과는 -1(이상치) 또는 1(정상)으로 나옵니다.
    sensor_df['anomaly_prediction'] = detector.predict(X)
    
    # 이상 점수(anomaly score) 계산 및 추가 (점수가 낮을수록 이상치일 가능성 높음)
    sensor_df['anomaly_score'] = detector.decision_function(X)

    # 4. 결과 시각화
    print("
[4단계] 이상 감지 결과 시각화 중...")
    # 'anomaly_prediction' 컬럼을 사용하여 시각화 함수 호출
    plot_sensor_data_with_anomalies(
        sensor_df,
        value_col='value',
        anomaly_col='anomaly_prediction', # 모델의 예측 결과를 사용
        timestamp_col='timestamp',
        title=f"센서 데이터 이상 감지 결과 (총 {num_samples}개 샘플)"
    )

    # 5. 결과 요약
    print("
[5단계] 이상 감지 결과 요약:")
    detected_anomalies_count = sensor_df[sensor_df['anomaly_prediction'] == -1].shape[0]
    true_anomalies_count = sensor_df[sensor_df['is_anomaly'] == 1].shape[0]

    print(f"  총 데이터 샘플 수: {num_samples}")
    print(f"  생성된 실제 이상치 수: {true_anomalies_count}")
    print(f"  모델이 감지한 이상치 수: {detected_anomalies_count}")

    # 이상 감지된 데이터 프레임을 CSV로 저장 (옵션)
    output_result_filename = "anomaly_detection_results.csv"
    save_dataframe_to_csv(sensor_df, output_result_filename)
    print(f"모든 처리 결과가 '{output_result_filename}'에 저장되었습니다.")

    print("
--------------------------------------------------")
    print("       센서 데이터 이상 감지 프로젝트 완료        ")
    print("--------------------------------------------------")

if __name__ == "__main__":
    main()
