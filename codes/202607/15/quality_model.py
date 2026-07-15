# quality_model.py
# 이 파일은 가상의 품질 검사 AI 모델을 시뮬레이션합니다.

import numpy as np
from sklearn.ensemble import RandomForestClassifier # scikit-learn은 개념적 사용
from utils import log_message

class QualityModel:
    """
    이미지 특징을 기반으로 제품의 품질(정상/불량)을 분류하는 가상의 AI 모델입니다.
    실제 AI 모델 학습 대신 간단한 규칙 또는 가상의 분류기로 동작합니다.
    """
    def __init__(self):
        """
        QualityModel을 초기화합니다.
        여기서는 간단한 예시를 위해 가상의 분류 규칙을 정의합니다.
        실제 상황에서는 훈련된 모델(예: RandomForestClassifier)이 로드될 수 있습니다.
        """
        self.threshold = 100.0 # 품질 판단을 위한 임계값 (예시)
        log_message("QualityModel 초기화. (가상 모델)")
        
        # 실제 모델처럼 보이기 위한 가상의 scikit-learn 모델 (여기서는 학습되지 않음)
        # self.model = RandomForestClassifier(random_state=42)

    def predict(self, features):
        """
        추출된 특징 벡터를 사용하여 제품의 품질을 예측합니다.
        :param features: 이미지에서 추출된 특징 벡터 (numpy 배열)
        :return: '정상' 또는 '불량' 문자열
        """
        if features is None or len(features) < 4:
            log_message("오류: 유효하지 않은 특징 벡터입니다. 품질 판단 불가.")
            return "판단 불가"

        # 예시: 네 번째 특징(표준 편차)을 기반으로 품질 판단
        # 표준 편차가 특정 임계값보다 높으면 불량으로 간주하는 단순 로직
        # 실제 AI 모델은 학습된 패턴에 따라 복잡한 판단을 수행합니다.
        
        # 가상의 scikit-learn 모델을 사용한다면:
        # prediction = self.model.predict([features])
        # if prediction[0] == 0: return '정상' else '불량'

        if features[3] > self.threshold: # 표준 편차가 높으면 '불량'
            log_message(f"품질 예측: 불량 (특징: {features[3]:.2f} > 임계값: {self.threshold})")
            return "불량"
        else: # 표준 편차가 낮으면 '정상'
            log_message(f"품질 예측: 정상 (특징: {features[3]:.2f} <= 임계값: {self.threshold})")
            return "정상"

if __name__ == '__main__':
    # 이 모듈이 직접 실행될 때의 테스트 코드
    model = QualityModel()
    
    # 정상으로 예측될 특징 (낮은 표준 편차)
    good_features = np.array([120.0, 130.0, 140.0, 50.0]) # 표준 편차 50
    print(f"특징 {good_features} -> 품질: {model.predict(good_features)}")
    
    # 불량으로 예측될 특징 (높은 표준 편차)
    bad_features = np.array([80.0, 90.0, 100.0, 150.0]) # 표준 편차 150
    print(f"특징 {bad_features} -> 품질: {model.predict(bad_features)}")

    # 유효하지 않은 특징
    invalid_features = np.array([10.0, 20.0])
    print(f"특징 {invalid_features} -> 품질: {model.predict(invalid_features)}")
