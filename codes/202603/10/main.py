# main.py
from sentiment_analyzer import analyze_sentiment

def main():
    """
    감성 분석 프로그램의 메인 함수입니다.
    사용자로부터 텍스트를 입력받아 감성을 분석하고 결과를 출력합니다.
    """
    print("감성 분석 프로그램입니다. 분석할 텍스트를 입력해주세요 (종료하려면 'exit' 입력):")
    while True:
        text = input("> ")
        if text.lower() == 'exit':
            break
        
        sentiment = analyze_sentiment(text)
        print(f"분석 결과: {sentiment}")

if __name__ == "__main__":
    main()
