# main.py
from preprocessor import TextPreprocessor
from sentiment_model import SentimentAnalyzer

def main():
    """
    메인 함수: 사용자로부터 텍스트를 입력받아 감성 분석을 수행하고 결과를 출력합니다.
    """
    print("--- 한국어 감성 분석기 ---")
    print("분석하고 싶은 한국어 텍스트를 입력하세요 (종료하려면 'exit' 입력).")

    preprocessor = TextPreprocessor()
    analyzer = SentimentAnalyzer()

    while True:
        user_input = input("한국어 텍스트를 입력하세요: ")
        if user_input.lower() == 'exit':
            break

        print(f"입력 텍스트: {user_input}")

        # 1. 텍스트 전처리
        processed_tokens = preprocessor.preprocess_text(user_input)
        print(f"전처리된 토큰: {processed_tokens}")

        # 2. 감성 분석
        sentiment = analyzer.analyze_sentiment(processed_tokens)
        print(f"분석 결과: {sentiment}
")

    print("감성 분석기를 종료합니다.")

if __name__ == "__main__":
    main()
