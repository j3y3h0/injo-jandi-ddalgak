# sentiment_analyzer.py
from utils import preprocess_text

# 간단한 규칙 기반 감성 사전 (예시)
# 실제 산업 프로젝트에서는 더 복잡한 사전, 기계 학습 모델 또는 딥러닝 모델을 사용합니다.
positive_keywords = ['좋은', '최고', '행복', '만족', '훌륭', '사랑', '긍정적', '기쁘다', '성공']
negative_keywords = ['나쁜', '최악', '슬픔', '불만', '실망', '혐오', '부정적', '화나다', '실패']

def analyze_sentiment(text):
    """
    주어진 텍스트의 감성을 분석합니다.
    여기서는 간단한 키워드 매칭 방식을 사용합니다.
    """
    tokens = preprocess_text(text)
    
    positive_count = sum(1 for token in tokens if token in positive_keywords)
    negative_count = sum(1 for token in tokens if token in negative_keywords)
    
    if positive_count > negative_count:
        return "긍정적"
    elif negative_count > positive_count:
        return "부정적"
    else:
        return "중립"
