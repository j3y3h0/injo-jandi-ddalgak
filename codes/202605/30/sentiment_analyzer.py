# sentiment_analyzer.py

from textblob import TextBlob

class SentimentAnalyzer:
    """
    TextBlob 라이브러리를 사용하여 텍스트 감성을 분석하는 클래스이다.
    """
    def analyze_sentiment(self, text: str) -> dict:
        """
        주어진 텍스트의 감성을 분석하고 극성(polarity)과 주관성(subjectivity)을 반환한다.
        극성은 -1.0(부정)부터 +1.0(긍정) 사이의 값이다.
        주관성은 0.0(객관적)부터 +1.0(주관적) 사이의 값이다.
        """
        if not text:
            return {"polarity": 0.0, "subjectivity": 0.0, "sentiment": "중립"}

        analysis = TextBlob(text)
        polarity = analysis.sentiment.polarity
        subjectivity = analysis.sentiment.subjectivity

        # 극성 값을 기반으로 감성 분류
        if polarity > 0:
            sentiment = "긍정"
        elif polarity < 0:
            sentiment = "부정"
        else:
            sentiment = "중립"

        return {
            "polarity": polarity,
            "subjectivity": subjectivity,
            "sentiment": sentiment
        }

if __name__ == '__main__':
    # 간단한 테스트 예시
    analyzer = SentimentAnalyzer()

    texts_to_analyze = [
        "I love this product! It's amazing.",
        "This is a terrible experience, I hate it.",
        "The weather is neutral today.",
        "Python is a programming language.",
        "This movie was fantastic and exciting."
    ]

    print("--- 감성 분석 테스트 시작 ---")
    for text in texts_to_analyze:
        result = analyzer.analyze_sentiment(text)
        print(f"
텍스트: '{text}'")
        print(f"  극성 (Polarity): {result['polarity']:.2f}")
        print(f"  주관성 (Subjectivity): {result['subjectivity']:.2f}")
        print(f"  감성: {result['sentiment']}")
    print("--- 감성 분석 테스트 종료 ---")
