# model_trainer.py

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
import numpy as np

# text_preprocessing.py 파일에서 필요한 함수를 임포트합니다.
from text_preprocessing import tokenize_and_remove_stopwords

def generate_dummy_data():
    """
    감성 분석 모델 학습을 위한 더미 데이터를 생성합니다.
    실제 사용 시에는 실제 레이블링된 데이터셋을 사용해야 합니다.
    """
    texts = [
        "이 영화는 정말 재미있고 감동적이다.", "최고의 영화! 꼭 보세요.", "너무 좋았다. 다시 보고 싶다.",
        "정말 실망스러운 경험이었다.", "시간 낭비. 비추천합니다.", "별로였다. 기대 이하였다."
    ]
    labels = [1, 1, 1, 0, 0, 0] # 1: 긍정, 0: 부정

    # 더미 데이터를 늘리기 위해 반복
    extended_texts = []
    extended_labels = []
    for _ in range(50): # 데이터를 50배로 늘립니다.
        extended_texts.extend(texts)
        extended_labels.extend(labels)

    return pd.DataFrame({'text': extended_texts, 'label': extended_labels})

def train_sentiment_model(data_frame):
    """
    주어진 데이터프레임으로 감성 분석 모델을 학습시키고 평가합니다.
    데이터프레임은 'text'와 'label' 컬럼을 포함해야 합니다.
    """
    # 텍스트 전처리
    data_frame['processed_text'] = data_frame['text'].apply(tokenize_and_remove_stopwords)

    # 데이터 분할
    X_train, X_test, y_train, y_test = train_test_split(
        data_frame['processed_text'], data_frame['label'], test_size=0.2, random_state=42
    )

    # TF-IDF 벡터화
    tfidf_vectorizer = TfidfVectorizer(max_features=1000) # 최대 1000개의 특성 사용
    X_train_tfidf = tfidf_vectorizer.fit_transform(X_train)
    X_test_tfidf = tfidf_vectorizer.transform(X_test)

    # 로지스틱 회귀 모델 학습
    model = LogisticRegression(random_state=42, solver='liblinear')
    model.fit(X_train_tfidf, y_train)

    # 모델 평가
    y_pred = model.predict(X_test_tfidf)
    print("
모델 평가 결과:")
    print(classification_report(y_test, y_pred))

    return model, tfidf_vectorizer

if __name__ == '__main__':
    # 더미 데이터 생성
    dummy_data = generate_dummy_data()
    print("더미 데이터 샘플:")
    print(dummy_data.head())

    # 모델 학습 및 벡터라이저 반환
    trained_model, vectorizer = train_sentiment_model(dummy_data)

    # 학습된 모델과 벡터라이저를 사용하여 예측 테스트 (간단한 예시)
    sample_texts_for_prediction = [
        "정말 최고의 서비스였어요. 강력 추천합니다!",
        "너무나 실망스럽고 별로였습니다.",
        "그냥 그랬어요. 평범했습니다.",
        "다음에 또 방문하고 싶어요."
    ]
    processed_samples = [tokenize_and_remove_stopwords(text) for text in sample_texts_for_prediction]
    vectorized_samples = vectorizer.transform(processed_samples)
    predictions = trained_model.predict(vectorized_samples)

    print("
새로운 텍스트에 대한 감성 예측:")
    for i, text in enumerate(sample_texts_for_prediction):
        sentiment = "긍정" if predictions[i] == 1 else "부정"
        print(f"'{text}' -> 감성: {sentiment}")
