# main.py
from sentiment_analyzer import SentimentAnalyzer

def main():
    """
    샘플 뉴스 헤드라인의 감성을 분석하고 결과를 출력하는 메인 함수입니다.
    """
    # 분석할 샘플 뉴스 헤드라인 목록입니다.
    # TextBlob은 주로 영어 텍스트에 최적화되어 있어, 예제는 영어로 작성되었습니다.
    news_headlines = [
        "Government announces new economic growth plan, boosting investor confidence.",
        "Major company reports significant quarterly losses, stocks plummet.",
        "Local charity event successfully raises record-breaking funds for community.",
        "Weather forecast predicts stable conditions for the weekend.",
        "Controversial policy decision sparks public outrage and protests."
    ]

    analyzer = SentimentAnalyzer()

    print("--- 뉴스 헤드라인 감성 분석 결과 ---")
    print("-" * 40)

    for i, headline in enumerate(news_headlines):
        print(f"[{i+1}] 헤드라인: {headline}")
        result = analyzer.analyze_sentiment(headline)
        print(f"    감성: {result['sentiment']} (극성 점수: {result['polarity']:.2f})")
        print("-" * 40)

if __name__ == "__main__":
    main()

