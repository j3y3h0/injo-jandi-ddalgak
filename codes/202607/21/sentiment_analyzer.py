# sentiment_analyzer.py
from utils import load_sentiment_dictionary

def tokenize_korean_text(text):
    """
    간단한 한국어 텍스트 토큰화 함수.
    실제 NLP에서는 KoNLPy와 같은 라이브러리를 사용합니다.
    """
    # 여기서는 공백을 기준으로 간단하게 분리합니다.
    # 더 정교한 분석을 위해서는 형태소 분석기가 필요합니다.
    return text.split()

def analyze_sentiment(text):
    """
    주어진 텍스트의 감성을 분석합니다.
    긍정/부정 단어 사전을 기반으로 점수를 계산합니다.
    """
    positive_words, negative_words = load_sentiment_dictionary()
    
    tokens = tokenize_korean_text(text)
    
    sentiment_score = 0
    for token in tokens:
        if token in positive_words:
            sentiment_score += 1
        elif token in negative_words:
            sentiment_score -= 1
    
    if sentiment_score > 0:
        sentiment = "긍정"
    elif sentiment_score < 0:
        sentiment = "부정"
    else:
        sentiment = "중립"
        
    return sentiment, sentiment_score
