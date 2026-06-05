# utils.py
#
# 이 파일은 데이터 시각화 및 파일 저장과 같은 유틸리티 함수를 제공합니다.
# 주로 이상 감지 결과를 그래프로 표현하는 데 사용됩니다.

import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import os

def plot_sensor_data_with_anomalies(df, value_col='value', anomaly_col='is_anomaly', timestamp_col='timestamp', title="센서 데이터 및 이상치 감지 결과"):
    """
    센서 데이터와 감지된 이상치를 시각화하는 함수입니다.

    Args:
        df (pandas.DataFrame): 센서 데이터가 포함된 데이터프레임.
        value_col (str): 센서 값이 있는 컬럼의 이름.
        anomaly_col (str): 이상치 여부를 나타내는 컬럼의 이름 (1: 이상치, 0: 정상).
        timestamp_col (str): 타임스탬프 컬럼의 이름.
        title (str): 그래프의 제목.
    """
    plt.figure(figsize=(15, 7))

    # 전체 센서 값 플로팅
    plt.plot(df[timestamp_col], df[value_col], label='센서 값', color='blue', alpha=0.7)

    # 이상치로 감지된 포인트들을 표시
    anomalies = df[df[anomaly_col] == -1] # Isolation Forest는 이상치를 -1로 예측합니다.
    if not anomalies.empty:
        plt.scatter(anomalies[timestamp_col], anomalies[value_col], color='red', s=50, label='감지된 이상치', zorder=5)

    # 실제 이상치 (데이터 생성 시 is_anomaly=1로 마킹된 부분)도 표시 (선택 사항)
    # 실제 이상치와 감지된 이상치를 구분하기 위해 다른 마커 사용
    true_anomalies = df[df['is_anomaly'] == 1]
    if not true_anomalies.empty:
        plt.scatter(true_anomalies[timestamp_col], true_anomalies[value_col], color='green', marker='x', s=100, label='실제 이상치', zorder=6)


    plt.title(title, fontsize=16)
    plt.xlabel('시간', fontsize=12)
    plt.ylabel('센서 값', fontsize=12)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.show()
    print("그래프 시각화 완료.")

def save_dataframe_to_csv(df, filename="output_data.csv", index=False):
    """
    데이터프레임을 CSV 파일로 저장합니다.

    Args:
        df (pandas.DataFrame): 저장할 데이터프레임.
        filename (str): 저장될 파일 이름.
        index (bool): 데이터프레임의 인덱스를 CSV에 쓸지 여부.
    """
    try:
        df.to_csv(filename, index=index)
        print(f"데이터프레임을 '{filename}' 파일에 성공적으로 저장했습니다.")
    except Exception as e:
        print(f"데이터프레임 저장 중 오류 발생: {e}")

if __name__ == "__main__":
    # 간단한 테스트 예시
    print("utils 모듈 테스트 시작.")

    # 더미 데이터 생성
    test_df = pd.DataFrame({
        'timestamp': pd.to_datetime(pd.date_range(start='2023-01-01', periods=100)),
        'value': np.sin(np.linspace(0, 10, 100)) * 10 + np.random.randn(100),
        'is_anomaly': 0 # 실제 이상치는 없다고 가정
    })
    # 인위적인 이상치 주입 (감지된 이상치처럼 -1로 설정)
    test_df.loc[10, 'value'] = 30
    test_df.loc[10, 'is_anomaly'] = -1
    test_df.loc[50, 'value'] = -20
    test_df.loc[50, 'is_anomaly'] = -1

    # plot 함수 테스트
    plot_sensor_data_with_anomalies(test_df, anomaly_col='is_anomaly', title="테스트 센서 데이터")

    # save_dataframe_to_csv 함수 테스트
    save_dataframe_to_csv(test_df, "test_output.csv")
    if os.path.exists("test_output.csv"):
        print("'test_output.csv' 파일이 성공적으로 생성되었습니다.")
        os.remove("test_output.csv")
        print("'test_output.csv' 파일을 삭제했습니다.")

    print("utils 모듈 테스트 완료.")
