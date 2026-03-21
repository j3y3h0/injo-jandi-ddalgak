import joblib
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline

def load_data():
    """
    훈련에 사용될 샘플 데이터를 로드하는 함수이다.
    실제 프로젝트에서는 외부 파일에서 데이터를 읽어온다.
    """
    # 긍정 및 부정 샘플 데이터 (매우 간략화)
    texts = [
        "이 제품은 정말 훌륭합니다. 매우 만족합니다.", # 긍정
        "AI 기술의 발전은 매우 기대됩니다. 미래가 밝습니다.", # 긍정
        "뉴스 내용이 흥미롭고 유익합니다.", # 긍정
        "이 서비스는 최악입니다. 다시는 사용하지 않을 것입니다.", # 부정
        "성능이 너무 나쁩니다. 실망스럽습니다.", # 부정
        "해당 기사는 사실과 다릅니다. 잘못된 정보입니다.", # 부정
        "긍정적인 평가입니다.", # 긍정
        "부정적인 평가입니다.", # 부정
        "개선이 필요합니다.", # 부정
        "아주 좋습니다." # 긍정
    ]
    labels = [1, 1, 1, 0, 0, 0, 1, 0, 0, 1] # 1: 긍정, 0: 부정
    return texts, labels

def train_model(texts, labels):
    """
    텍스트 데이터를 사용하여 감성 분석 모델을 훈련하는 함수이다.
    TF-IDF 벡터화와 로지스틱 회귀 모델을 사용한다.
    """
    # 전처리 단계는 외부에서 수행된다고 가정하거나, 파이프라인에 포함 가능.
    # 여기서는 간단히 텍스트 자체를 입력으로 받는다.
    pipeline = Pipeline([
        ('tfidf', TfidfVectorizer()),
        ('classifier', LogisticRegression())
    ])
    pipeline.fit(texts, labels)
    return pipeline

def save_model(model, filename="sentiment_model.pkl"):
    """
    훈련된 모델을 파일로 저장하는 함수이다.
    """
    joblib.dump(model, filename)
    print(f"모델이 '{filename}'으로 저장되었습니다.")

if __name__ == "__main__":
    texts, labels = load_data()
    model = train_model(texts, labels)
    save_model(model)
