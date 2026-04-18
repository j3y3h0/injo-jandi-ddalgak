from typing import Dict, List

def _load_keywords() -> Dict[str, int]:
    """
    감성 분석을 위한 키워드 사전을 로드합니다.
    여기서는 간단한 예시 키워드를 정의합니다.
    실제 애플리케이션에서는 더 방대한 사전을 사용하거나,
    머신러닝 모델을 통해 감성을 분류합니다.

    Returns:
        Dict[str, int]: 키워드와 해당 감성 점수를 매핑한 사전.
                       양수: 긍정, 음수: 부정
    """
    # 간단한 한국어 긍정/부정 키워드 예시
    keywords = {
        "좋다": 2, "최고": 2, "만족": 2, "행복": 2, "감사": 1, "훌륭": 1, "기쁘다": 1,
        "나쁘다": -2, "최악": -2, "불만": -2, "슬프다": -2, "화나다": -1, "실망": -1, "문제": -1,
        "즐겁다": 1, "신난다": 1, "아름답다": 1, "예쁘다": 1, "멋지다": 1,
        "어렵다": -1, "복잡하다": -1, "짜증": -1, "피곤": -1, "지루하다": -1
    }
    return keywords

def analyze_sentiment(text: str) -> str:
    """
    전처리된 텍스트의 감성을 분석합니다.
    미리 정의된 키워드 사전을 기반으로 텍스트에 포함된 키워드의 점수를 합산하여 감성을 판단합니다.

    Args:
        text (str): 전처리된 텍스트.

    Returns:
        str: "긍정", "부정", "중립" 중 하나.
    """
    keyword_scores = _load_keywords()
    total_score = 0

    # 텍스트를 단어 단위로 분리 (간단한 공백 분리)
    # 실제 한국어 처리에서는 형태소 분석기(konlpy 등) 사용이 권장됩니다.
    words = text.split()

    for word in words:
        # 키워드가 단어에 포함되는 경우를 고려
        for keyword, score in keyword_scores.items():
            if keyword in word: # '너무 좋다' -> '좋다' 탐지
                total_score += score
                # 한 단어에 여러 키워드가 있을 수 있지만, 여기서는 한 번만 점수 부여
                break # 현재 단어에 대한 키워드 탐색 중단

    if total_score > 0:
        return "긍정"
    elif total_score < 0:
        return "부정"
    else:
        return "중립"

