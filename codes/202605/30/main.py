# main.py

from sentiment_analyzer import SentimentAnalyzer
from utils import preprocess_text

def run_sentiment_analysis():
    """
    샘플 텍스트에 대해 감성 분석을 실행하고 결과를 출력하는 메인 함수이다.
    """
    analyzer = SentimentAnalyzer()

    # 분석할 샘플 텍스트 목록 (영어 텍스트만 TextBlob으로 분석 가능)
    sample_texts = [
        "This is an excellent application. I love using it every day!",
        "I am very disappointed with the service. It was awful.",
        "The quick brown fox jumps over the lazy dog.",
        "Today's weather is neither good nor bad. It's just cloudy.",
        "What a fantastic experience! Highly recommended.",
        "I absolutely hate this product. It broke immediately.",
        "I like the design but the functionality needs improvement.",
    ]

    print("=== 감성 분석 프로젝트 시작 ===")
    print("분석할 텍스트:")
    for text in sample_texts:
        print(f"- '{text}'")
    print("-" * 30)

    for i, text in enumerate(sample_texts):
        # 텍스트 전처리
        processed_text = preprocess_text(text)

        # 감성 분석 수행
        result = analyzer.analyze_sentiment(processed_text)

        print(f"
[{i+1}] 텍스트: '{text}'")
        print(f"  극성 (Polarity): {result['polarity']:.2f}")
        print(f"  주관성 (Subjectivity): {result['subjectivity']:.2f}")
        print(f"  감성: {result['sentiment']}")
    
    print("
=== 감성 분석 프로젝트 완료 ===")

if __name__ == '__main__':
    run_sentiment_analysis()
