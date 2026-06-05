# anomaly_detector.py
#
# 이 파일은 센서 데이터의 이상 감지를 위한 Isolation Forest 모델을 정의하고 사용합니다.
# Isolation Forest는 비지도 학습 알고리즘으로, 데이터셋 내의 이상치를 효율적으로 식별합니다.

from sklearn.ensemble import IsolationForest
import numpy as np
import pandas as pd

class AnomalyDetector:
    """
    Isolation Forest를 이용한 이상 감지기 클래스입니다.
    데이터를 학습하고, 새로운 데이터 포인트에 대한 이상 점수를 예측합니다.
    """
    def __init__(self, contamination='auto', random_state=42):
        """
        AnomalyDetector의 생성자입니다.

        Args:
            contamination (float or 'auto'): 데이터셋 내 이상치의 비율을 추정하는 매개변수입니다.
                                             'auto'로 설정하면 모델이 이상치 비율을 자체적으로 추정합니다.
                                             0과 0.5 사이의 float 값으로 지정할 수도 있습니다.
            random_state (int): Isolation Forest 모델의 난수 시드입니다. 결과 재현성을 위해 사용됩니다.
        """
        self.model = IsolationForest(contamination=contamination, random_state=random_state)
        print(f"Isolation Forest 모델 초기화 완료 (contamination: {contamination}).")

    def fit(self, X):
        """
        Isolation Forest 모델을 훈련 데이터에 맞춥니다.
        이 모델은 비지도 학습이므로, X는 피처 데이터만 포함합니다.

        Args:
            X (pandas.DataFrame or numpy.ndarray): 모델 학습에 사용될 피처 데이터입니다.
                                                  주로 센서 값 컬럼이 됩니다.
        """
        print("Isolation Forest 모델 학습 시작...")
        self.model.fit(X)
        print("Isolation Forest 모델 학습 완료.")

    def predict(self, X):
        """
        학습된 모델을 사용하여 새로운 데이터 포인트의 이상 여부를 예측합니다.

        Args:
            X (pandas.DataFrame or numpy.ndarray): 이상 여부를 예측할 피처 데이터입니다.

        Returns:
            numpy.ndarray: 각 데이터 포인트에 대한 이상 예측 결과입니다.
                           -1은 이상치, 1은 정상치를 나타냅니다.
        """
        print("이상 감지 예측 시작...")
        predictions = self.model.predict(X)
        print("이상 감지 예측 완료.")
        return predictions

    def decision_function(self, X):
        """
        학습된 모델을 사용하여 각 데이터 포인트의 이상 점수를 계산합니다.
        점수가 낮을수록(음수일수록) 더 이상치일 가능성이 높습니다.

        Args:
            X (pandas.DataFrame or numpy.ndarray): 이상 점수를 계산할 피처 데이터입니다.

        Returns:
            numpy.ndarray: 각 데이터 포인트에 대한 이상 점수입니다.
        """
        print("이상 점수 계산 시작...")
        scores = self.model.decision_function(X)
        print("이상 점수 계산 완료.")
        return scores

if __name__ == "__main__":
    # 간단한 테스트 예시
    print("AnomalyDetector 모듈 테스트 시작.")

    # 더미 데이터 생성
    np.random.seed(42)
    normal_data = np.random.randn(100, 1) * 2 + 5
    anomaly_data = np.random.randn(10, 1) * 10 + 30 # 크게 벗어난 값
    data = np.vstack((normal_data, anomaly_data))
    np.random.shuffle(data) # 데이터 섞기

    # 모델 초기화 및 학습
    detector = AnomalyDetector(contamination=0.1) # 10% 정도 이상치가 있다고 가정
    detector.fit(data)

    # 예측 및 이상 점수 계산
    predictions = detector.predict(data)
    scores = detector.decision_function(data)

    print("
일부 예측 결과 (처음 10개):")
    print(predictions[:10])
    print("
일부 이상 점수 (처음 10개):")
    print(scores[:10])

    print(f"
감지된 이상치 개수: {np.sum(predictions == -1)}")
    print("AnomalyDetector 모듈 테스트 완료.")
