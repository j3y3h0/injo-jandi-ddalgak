# main.py

import pandas as pd
from text_preprocessing import tokenize_and_remove_stopwords
from model_trainer import generate_dummy_data, train_sentiment_model
import pickle # 모델 저장을 위해 pickle 모듈 사용

def main():
    """
    감성 분석 프로젝트의 메인 실행 함수입니다.
    더미 데이터를 생성하고, 모델을 학습하며, 새로운 텍스트에 대한 감성을 예측합니다.
    """
    print("1. 감성 분석을 위한 더미 데이터 생성 중...")
    dummy_data = generate_dummy_data()
    print("더미 데이터 생성 완료.")
    # print(dummy_data.head()) # 데이터 확인용

    print("
2. 감성 분석 모델 학습 중...")
    # 모델과 벡터라이저를 반환받습니다.
    model, tfidf_vectorizer = train_sentiment_model(dummy_data)
    print("모델 학습 완료.")

    # 학습된 모델과 TF-IDF 벡터라이저를 파일로 저장 (실제 서비스에서는 이렇게 저장하고 로드합니다)
    with open('sentiment_model.pkl', 'wb') as f:
        pickle.dump(model, f)
    with open('tfidf_vectorizer.pkl', 'wb') as f:
        pickle.dump(tfidf_vectorizer, f)
    print("모델과 벡터라이저 저장 완료: sentiment_model.pkl, tfidf_vectorizer.pkl")

    print("
3. 저장된 모델과 벡터라이저 로드 및 새로운 텍스트에 대한 감성 예측 수행 중...")
    # 저장된 모델과 벡터라이저를 로드합니다.
    with open('sentiment_model.pkl', 'rb') as f:
        loaded_model = pickle.load(f)
    with open('tfidf_vectorizer.pkl', 'rb') as f:
        loaded_vectorizer = pickle.load(f)
    print("모델과 벡터라이저 로드 완료.")

    # 예측할 새로운 텍스트 예시
    new_texts = [
        "이 제품 정말 훌륭합니다! 사용하기 편리하고 성능도 뛰어나요.",
        "너무 실망스러워요. 돈이 아깝습니다. 절대 구매하지 마세요.",
        "그냥 보통입니다. 특별히 좋지도 나쁘지도 않아요.",
        "다음에 또 이용하고 싶습니다. 만족스러웠습니다.",
        "기대했던 것보다 훨씬 못 미쳤습니다. 실망입니다.",
        "정말 재밌는 영화였어요. 배우들의 연기가 인상 깊었습니다."
    ]

    # 새로운 텍스트 전처리 및 예측
    for text in new_texts:
        processed_text = tokenize_and_remove_stopwords(text)
        vectorized_text = loaded_vectorizer.transform([processed_text])
        prediction = loaded_model.predict(vectorized_text)[0]
        sentiment = "긍정 (Positive)" if prediction == 1 else "부정 (Negative)"
        print(f"텍스트: '{text}' -> 예측 감성: {sentiment}")

    print("
모든 작업 완료.")

if __name__ == '__main__':
    main()
