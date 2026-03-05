# sentiment_analyzer.py
from textblob import TextBlob

class SentimentAnalyzer:
    """
    텍스트의 감성을 분석하는 클래스입니다.
    TextBlob 라이브러리를 사용합니다.
    """
    def analyze_sentiment(self, text: str) -> dict:
        """
        주어진 텍스트의 감성을 분석하여 결과와 점수를 반환합니다.

        Args:
            text (str): 분석할 텍스트 문자열입니다.

        Returns:
            dict: 감성 결과 (긍정, 부정, 중립) 및 극성(polarity) 점수를 포함하는 딕셔너리입니다.
                  예: {'sentiment': 'positive', 'polarity': 0.5}
        """
        analysis = TextBlob(text)
        polarity = analysis.sentiment.polarity # 극성: -1 (부정)에서 1 (긍정) 사이의 값

        sentiment_result = ""
        if polarity > 0:
            sentiment_result = "긍정"
        elif polarity < 0:
            sentiment_result = "부정"
        else:
            sentiment_result = "중립"

        return {
            "sentiment": sentiment_result,
            "polarity": polarity
        }

