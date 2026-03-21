from data_preprocessing import preprocess_text
from model_training import load_data, train_model, save_model
from sentiment_analyzer import load_model, predict_sentiment

def run_sentiment_analysis():
    """
    감성 분석 프로젝트의 전체 워크플로우를 실행하는 함수이다.
    """
    print("--- 감성 분석 프로젝트 시작 ---")

    # 1. 데이터 로드 및 전처리 (훈련 데이터)
    print("
1. 훈련 데이터 로드...")
    raw_texts, labels = load_data()
    # 훈련 데이터를 전처리하는 단계는 모델 파이프라인 내부에서 TfidfVectorizer가 처리하므로,
    # 여기서는 개별 전처리는 생략하고 raw_texts를 직접 사용한다.
    # 만약 TfidfVectorizer 외부에서 전처리된 텍스트를 받도록 한다면, 여기서 preprocess_text를 호출해야 한다.
    # 현재 scikit-learn 파이프라인은 문자열 리스트를 직접 받아서 내부적으로 처리한다.
    
    # 2. 모델 훈련 및 저장
    print("2. 감성 분석 모델 훈련 및 저장...")
    model = train_model(raw_texts, labels)
    save_model(model)
    
    # 3. 모델 로드 및 감성 예측 (새로운 텍스트)
    print("
3. 새로운 텍스트 감성 예측...")
    loaded_model = load_model()
    
    if loaded_model:
        sample_news_articles = [
            "최근 AI 기술 발전은 인류에게 큰 도움이 될 것이다.",
            "새로운 AI 정책은 논란의 여지가 많으며, 우려가 크다.",
            "이 영화는 정말 최고였다. 다시 보고 싶다.",
            "서비스 장애로 인해 많은 사용자들이 불편을 겪었다."
        ]
        
        for i, article in enumerate(sample_news_articles):
            sentiment = predict_sentiment(article, loaded_model)
            print(f"  기사 {i+1}: '{article}' -> 감성: {sentiment}")
    else:
        print("모델을 로드할 수 없어 예측을 수행할 수 없습니다.")

    print("
--- 감성 분석 프로젝트 완료 ---")

if __name__ == "__main__":
    run_sentiment_analysis()
