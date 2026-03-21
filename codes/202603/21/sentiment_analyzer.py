import joblib
from data_preprocessing import preprocess_text # 전처리 함수 임포트

def load_model(filename="sentiment_model.pkl"):
    """
    저장된 감성 분석 모델을 로드하는 함수이다.
    """
    try:
        model = joblib.load(filename)
        return model
    except FileNotFoundError:
        print(f"오류: 모델 파일 '{filename}'을 찾을 수 없습니다. 모델 훈련을 먼저 수행해야 합니다.")
        return None

def predict_sentiment(text, model):
    """
    주어진 텍스트의 감성을 예측하는 함수이다.
    """
    if model is None:
        return "모델 로드 실패"

    preprocessed_text = preprocess_text(text) # 텍스트 전처리
    # 모델은 TF-IDF 벡터라이저를 포함한 파이프라인이므로, 전처리된 텍스트를 직접 입력한다.
    prediction = model.predict([preprocessed_text])
    
    if prediction[0] == 1:
        return "긍정"
    else:
        return "부정"

if __name__ == "__main__":
    # 이 부분은 main.py에서 호출되므로, 여기서는 간단한 테스트만 수행한다.
    # 실제 실행 시에는 main.py를 통해 모델이 훈련되고 저장된 후 사용된다.
    pass # main.py에서 실제 로직을 테스트할 것이다.
