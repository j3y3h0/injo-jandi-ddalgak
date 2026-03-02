# main.py

from data_loader import DataLoader
from sentiment_analyzer import SentimentAnalyzer

def run_sentiment_analysis():
    """
    텍스트 감성 분석 프로젝트의 메인 실행 함수입니다.
    데이터 로딩, 전처리, 감성 분석 모델 초기화 및 예측 과정을 수행합니다.
    """
    print("--- 텍스트 감성 분석 프로젝트 시작 ---")

    # 1. 데이터 로더 초기화
    data_loader = DataLoader()
    print("
[단계 1] 데이터 로더 초기화 완료.")

    # 2. 샘플 텍스트 데이터 로드 (실제 환경에서는 파일 경로를 전달)
    # 예를 들어, data_loader.load_data('reviews.csv') 와 같이 사용할 수 있습니다.
    texts_to_analyze = data_loader.load_data()
    print(f"[단계 2] 분석할 텍스트 {len(texts_to_analyze)}개를 로드했습니다.")

    # 3. 감성 분석기 초기화
    sentiment_analyzer = SentimentAnalyzer()
    print("
[단계 3] 감성 분석기 초기화 완료.")

    # 4. 모델 학습 (예시이므로 가상의 학습 데이터 사용)
    # 실제로는 대규모의 라벨링된 데이터를 사용하여 모델을 학습해야 합니다.
    # train_texts = data_loader.load_data('train_data.csv') # 학습 데이터 로드 예시
    # train_labels = [...] # 해당 학습 데이터의 라벨
    # sentiment_analyzer.train(train_texts, train_labels)
    print("
[단계 4] 모델 학습 시뮬레이션: 이 단계에서 실제 모델이 학습되거나 로드됩니다.")
    # 간단한 가상 학습 데이터로 train 메서드 호출 (실제 학습은 아님)
    dummy_train_texts = ["긍정적인 문장", "부정적인 문장"]
    dummy_train_labels = ["긍정", "부정"]
    sentiment_analyzer.train(dummy_train_texts, dummy_train_labels)


    # 5. 각 텍스트에 대해 감성 분석 수행 및 결과 출력
    print("
[단계 5] 텍스트별 감성 분석 결과:")
    for i, text in enumerate(texts_to_analyze):
        # 텍스트 전처리 (data_loader의 preprocess_text 사용)
        preprocessed_text = data_loader.preprocess_text(text)
        
        # 감성 예측
        sentiment = sentiment_analyzer.predict_sentiment(preprocessed_text)
        
        print(f"  [{i+1}] 원본: '{text}'")
        print(f"      전처리: '{preprocessed_text}'")
        print(f"      예측 감성: {sentiment}")
        print("-" * 30)

    print("
--- 텍스트 감성 분석 프로젝트 완료 ---")

if __name__ == "__main__":
    run_sentiment_analysis()
