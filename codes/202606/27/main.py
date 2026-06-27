# main.py
# 프로그램의 진입점이며, 사용자로부터 텍스트를 입력받아 감성 분석을 수행합니다.

from utils import preprocess_text
from sentiment_analyzer import SentimentAnalyzer

def main():
    """
    메인 함수: 사용자로부터 텍스트를 입력받고 감성 분석을 수행한 후 결과를 출력합니다.
    """
    print("=" * 40)
    print("     간단한 한국어 감성 분석기")
    print("     종료하려면 'exit' 또는 'quit'을 입력하세요.")
    print("=" * 40)

    analyzer = SentimentAnalyzer()

    while True:
        try:
            user_input = input("
분석할 텍스트를 입력하세요: ")
            
            if user_input.lower() in ["exit", "quit"]:
                print("프로그램을 종료합니다.")
                break
            
            if not user_input.strip():
                print("텍스트를 입력해주세요.")
                continue

            # 텍스트 전처리
            processed_text = preprocess_text(user_input)
            
            # 감성 분석 수행
            sentiment = analyzer.analyze(processed_text)
            
            # 결과 출력
            print(f"입력된 텍스트: '{user_input}'")
            print(f"분석된 감성: {sentiment}")

        except TypeError as e:
            print(f"오류 발생: {e}")
            print("올바른 형식의 텍스트를 입력해주세요.")
        except Exception as e:
            print(f"예상치 못한 오류가 발생했습니다: {e}")

if __name__ == "__main__":
    main()
