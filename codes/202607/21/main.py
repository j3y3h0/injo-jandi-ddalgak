# main.py
from sentiment_analyzer import analyze_sentiment

def main():
    print("텍스트 감성 분석 프로그램입니다.")
    while True:
        text = input("분석할 텍스트를 입력하세요 (종료하려면 'exit' 입력): ")
        if text.lower() == 'exit':
            break
        
        sentiment, score = analyze_sentiment(text)
        print(f"입력 텍스트: '{text}'")
        print(f"분석 결과: {sentiment} (스코어: {score})")
        print("-" * 30)

if __name__ == "__main__":
    main()
