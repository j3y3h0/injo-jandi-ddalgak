from utils import preprocess_text
from sentiment_analyzer import analyze_sentiment

def main():
    """
    메인 함수입니다. 사용자로부터 텍스트를 입력받아 감성을 분석하고 결과를 출력합니다.
    """
    print("텍스트 감성 분석 프로그램입니다. 종료하려면 'exit'을 입력하세요.")
    while True:
        user_input = input("분석할 텍스트를 입력하세요: ")
        if user_input.lower() == 'exit':
            break

        # 텍스트 전처리
        processed_text = preprocess_text(user_input)
        print(f"전처리된 텍스트: {processed_text}")

        # 감성 분석
        sentiment = analyze_sentiment(processed_text)
        print(f"감성 분석 결과: {sentiment}
")

if __name__ == "__main__":
    main()

