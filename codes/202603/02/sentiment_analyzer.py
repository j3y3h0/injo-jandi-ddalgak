# sentiment_analyzer.py

# 실제 환경에서는 scikit-learn 라이브러리를 사용하여 모델을 구축하고 로드합니다.
# from sklearn.feature_extraction.text import TfidfVectorizer
# from sklearn.linear_model import LogisticRegression
# import joblib # 모델 저장을 위함

class SentimentAnalyzer:
    """
    텍스트 감성 분석 모델을 담당하는 클래스입니다.
    실제 환경에서는 scikit-learn의 TfidfVectorizer와 같은 피처 추출기와
    LogisticRegression과 같은 분류 모델을 사용합니다.
    """
    def __init__(self):
        # 실제 모델 로딩 로직 (미리 학습된 모델을 불러올 경우)
        # try:
        #     self.vectorizer = joblib.load('tfidf_vectorizer.pkl')
        #     self.model = joblib.load('sentiment_model.pkl')
        #     print("INFO: 사전 학습된 감성 분석 모델을 로드했습니다.")
        # except FileNotFoundError:
        #     print("WARNING: 사전 학습된 모델 파일을 찾을 수 없습니다. 새로운 모델을 학습해야 합니다.")
        #     self.vectorizer = TfidfVectorizer(max_features=5000) # 예시 설정
        #     self.model = LogisticRegression(max_iter=1000) # 예시 설정
        self.model_status = "초기화됨 (학습 필요 또는 가상 모델 사용)"
        print(f"INFO: 감성 분석기 초기화 완료. 상태: {self.model_status}")

    def train(self, texts: list, labels: list):
        """
        감성 분석 모델을 학습하는 메서드입니다.
        실제 환경에서는 텍스트 데이터를 벡터화하고 분류 모델을 학습시킵니다.

        Args:
            texts (list): 학습에 사용할 텍스트 데이터 리스트.
            labels (list): 각 텍스트에 해당하는 감성 라벨 (예: "긍정", "부정").
        """
        print("INFO: 감성 분석 모델 학습을 시뮬레이션합니다.")
        # 실제 학습 로직 (예: TfidfVectorizer로 텍스트 벡터화 후 LogisticRegression 학습)
        # X = self.vectorizer.fit_transform(texts)
        # self.model.fit(X, labels)
        # joblib.dump(self.vectorizer, 'tfidf_vectorizer.pkl')
        # joblib.dump(self.model, 'sentiment_model.pkl')
        self.model_status = "학습 완료 (가상 모델)"
        print("INFO: 감성 분석 모델 학습 시뮬레이션 완료.")

    def predict_sentiment(self, text: str) -> str:
        """
        주어진 텍스트의 감성을 예측하는 메서드입니다.
        현재는 간단한 키워드 기반 로직으로 감성을 반환하며, 실제로는 학습된 모델을 사용합니다.

        Args:
            text (str): 감성을 분석할 텍스트.

        Returns:
            str: 예측된 감성 ("긍정", "부정", "중립").
        """
        print(f"INFO: 텍스트 '{text[:20]}...'에 대한 감성 예측을 시뮬레이션합니다.")
        # 간단한 키워드 기반 감성 예측 (모델이 없을 경우의 폴백 또는 예시)
        if "좋" in text or "재미있" in text or "최고" in text or "만족" in text or "편리" in text:
            return "긍정"
        elif "나쁘" in text or "불친절" in text or "실망" in text or "늦" in text or "문제" in text:
            return "부정"
        else:
            return "중립"

        # 실제 예측 로직 (학습된 모델이 있을 경우)
        # if hasattr(self, 'model') and hasattr(self, 'vectorizer'):
        #     text_vector = self.vectorizer.transform([text])
        #     prediction = self.model.predict(text_vector)
        #     return prediction[0]
        # else:
        #     # 모델이 로드되지 않았거나 학습되지 않았을 경우, 키워드 기반 예측으로 대체
        #     if "좋" in text or "긍정" in text:
        #         return "긍정"
        #     elif "나쁘" in text or "부정" in text:
        #         return "부정"
        #     else:
        #         return "중립"

if __name__ == "__main__":
    # 이 파일 단독 실행 시 테스트 코드
    analyzer = SentimentAnalyzer()

    # 가상의 학습 데이터 (실제 데이터 대신)
    train_texts = [
        "정말 좋은 경험이었어요.", "최악의 서비스였습니다.", "괜찮았어요.",
        "매우 만족합니다.", "다시는 이용하고 싶지 않아요."
    ]
    train_labels = ["긍정", "부정", "중립", "긍정", "부정"]

    analyzer.train(train_texts, train_labels)

    test_texts = [
        "이 제품 정말 최고네요!",
        "배송이 너무 느려서 화가 납니다.",
        "그냥 보통이었어요.",
        "새로운 기능이 매우 편리해요."
    ]

    for text in test_texts:
        sentiment = analyzer.predict_sentiment(text)
        print(f"텍스트: '{text}' -> 감성: {sentiment}")
