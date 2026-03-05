# sentiment_analyzer.py
# 이 파일은 텍스트 감성 분석을 수행하는 클래스를 정의합니다.
# TextBlob 라이브러리를 활용하여 텍스트의 감성을 긍정, 부정, 중립으로 분류합니다.

from textblob import TextBlob

class SentimentAnalyzer:
    """
    텍스트 감성 분석을 위한 클래스입니다.
    TextBlob을 사용하여 텍스트의 감성 극성(polarity)을 분석하고,
    이를 기반으로 긍정, 부정, 중립으로 분류합니다.
    """

    def analyze_sentiment(self, text):
        """
        주어진 텍스트의 감성을 분석합니다.

        Args:
            text (str): 분석할 텍스트 문자열입니다.

        Returns:
            tuple: (감성 극성 점수, 분류된 감성 문자열)을 반환합니다.
                   감성 극성 점수는 -1.0(부정)에서 1.0(긍정) 사이의 값입니다.
                   분류된 감성 문자열은 '긍정', '부정', '중립' 중 하나입니다.
        """
        analysis = TextBlob(text)
        polarity = analysis.sentiment.polarity # 감성 극성 점수 (-1.0: 부정, 1.0: 긍정)

        if polarity > 0:
            sentiment_label = "긍정"
        elif polarity < 0:
            sentiment_label = "부정"
        else:
            sentiment_label = "중립"

        return polarity, sentiment_label

if __name__ == "__main__":
    # 이 파일이 직접 실행될 경우 감성 분석을 테스트하는 예시
    analyzer = SentimentAnalyzer()
    test_sentences = [
        "I love this product!",
        "This is a terrible idea.",
        "It is what it is.",
        "Today is a beautiful day.",
        "I am very disappointed with the service."
    ]

    print("감성 분석 테스트 결과:")
    for sentence in test_sentences:
        polarity, label = analyzer.analyze_sentiment(sentence)
        print(f"문장: '{sentence}' -> 극성: {polarity:.2f}, 감성: {label}")
