# data_generator.py
#
# 이 파일은 시뮬레이션된 센서 데이터를 생성합니다.
# 정상 데이터와 비정상 데이터를 포함하여 이상 감지 모델 테스트에 사용됩니다.

import numpy as np
import pandas as pd
import datetime

def generate_sensor_data(num_samples=1000, anomaly_ratio=0.03, random_seed=42):
    """
    시계열 센서 데이터를 생성합니다.
    일반적인 주기성과 노이즈를 가지며, 일정 비율의 이상치를 포함합니다.

    Args:
        num_samples (int): 생성할 데이터 샘플의 총 개수.
        anomaly_ratio (float): 전체 샘플 중 이상치의 비율.
        random_seed (int): 재현 가능한 데이터 생성을 위한 난수 시드.

    Returns:
        pandas.DataFrame: 'timestamp', 'value', 'is_anomaly' 컬럼을 포함하는 데이터프레임.
                          'is_anomaly'는 1이면 이상치, 0이면 정상치를 나타냅니다.
    """
    np.random.seed(random_seed)

    # 시간대 생성
    start_time = datetime.datetime(2023, 1, 1, 0, 0, 0)
    timestamps = [start_time + datetime.timedelta(minutes=i) for i in range(num_samples)]

    # 정상 데이터 생성 (주기성 + 노이즈)
    time_series = np.sin(np.linspace(0, 50, num_samples)) * 10 + np.random.randn(num_samples) * 2
    
    # 이상치 주입
    num_anomalies = int(num_samples * anomaly_ratio)
    anomaly_indices = np.random.choice(num_samples, num_anomalies, replace=False)
    
    # 이상치는 평균에서 크게 벗어난 값으로 설정
    for idx in anomaly_indices:
        anomaly_type = np.random.rand() # 이상치 유형 무작위 선택
        if anomaly_type < 0.5: # 갑작스런 큰 값
            time_series[idx] += np.random.uniform(20, 40)
        else: # 갑작스런 작은 값
            time_series[idx] -= np.random.uniform(20, 40)

    is_anomaly = np.zeros(num_samples)
    is_anomaly[anomaly_indices] = 1

    df = pd.DataFrame({
        'timestamp': timestamps,
        'value': time_series,
        'is_anomaly': is_anomaly
    })

    print(f"총 {num_samples}개의 샘플 중 {num_anomalies}개의 이상치 생성 완료.")
    return df

if __name__ == "__main__":
    # 데이터 생성 및 CSV로 저장 예시
    generated_df = generate_sensor_data(num_samples=2000, anomaly_ratio=0.02)
    generated_df.to_csv("sensor_data.csv", index=False)
    print("생성된 데이터를 'sensor_data.csv' 파일에 저장했습니다.")
