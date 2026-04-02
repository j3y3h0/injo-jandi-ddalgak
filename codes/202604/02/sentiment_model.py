# sentiment_model.py

class SentimentAnalyzer:
    """
    텍스트 감성 분석을 위한 클래스.
    간단한 키워드 기반으로 긍정, 부정, 중립 감성을 분류합니다.
    """
    def __init__(self):
        # 긍정적인 감성을 나타내는 키워드 리스트
        self.positive_keywords = {
            "좋", "감동", "재미", "행복", "기쁨", "성공", "발전", "희망", "긍정", "만족", "최고", "훌륭", "개선"
        }
        # 부정적인 감성을 나타내는 키워드 리스트
        self.negative_keywords = {
            "나쁘", "슬픔", "걱정", "불안", "문제", "실패", "하락", "위험", "부정", "불만", "최악", "어렵", "악화"
        }

    def analyze_sentiment(self, tokens):
        """
        전처리된 토큰 리스트를 기반으로 감성을 분석합니다.
        긍정 키워드 수와 부정 키워드 수를 비교하여 감성을 결정합니다.
        """
        positive_count = 0
        negative_count = 0

        for token in tokens:
            if token in self.positive_keywords:
                positive_count += 1
            elif token in self.negative_keywords:
                negative_count += 1
        
        if positive_count > negative_count:
            return "긍정적"
        elif negative_count > positive_count:
            return "부정적"
        else:
            return "중립적"

# 테스트 코드 (실제로 실행되지는 않지만 예시를 위해 포함)
if __name__ == "__main__":
    analyzer = SentimentAnalyzer()
    
    # 예시 토큰 리스트
    tokens1 = ["영화", "재미", "감동"]
    sentiment1 = analyzer.analyze_sentiment(tokens1)
    print(f"토큰: {tokens1}, 감성: {sentiment1}") # 예상 출력: 긍정적

    tokens2 = ["경제", "상황", "걱정", "악화"]
    sentiment2 = analyzer.analyze_sentiment(tokens2)
    print(f"토큰: {tokens2}, 감성: {sentiment2}") # 예상 출력: 부정적

    tokens3 = ["오늘", "날씨", "좋"] # '좋'은 긍정 키워드에 포함됨
    sentiment3 = analyzer.analyze_sentiment(tokens3)
    print(f"토큰: {tokens3}, 감성: {sentiment3}") # 예상 출력: 긍정적

    tokens4 = ["아침", "식사"]
    sentiment4 = analyzer.analyze_sentiment(tokens4)
    print(f"토큰: {tokens4}, 감성: {sentiment4}") # 예상 출력: 중립적
