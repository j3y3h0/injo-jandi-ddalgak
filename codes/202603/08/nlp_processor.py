# -*- coding: utf-8 -*-

from konlpy.tag import Okt
from typing import Tuple

class SentimentAnalyzer:
    """
    한국어 텍스트에 대한 감성 분석을 수행하는 클래스입니다.
    Konlpy의 Okt 형태소 분석기를 사용하여 텍스트를 분석하고,
    미리 정의된 긍정/부정 키워드 리스트를 기반으로 감성 점수를 계산합니다.
    """
    def __init__(self):
        self.okt = Okt()
        # 간단한 감성 분석을 위한 긍정/부정 키워드 리스트
        # 실제 환경에서는 더 정교한 사전이나 머신러닝 모델을 사용합니다.
        self.positive_keywords = set([
            "좋다", "긍정", "강점", "성공", "발전", "개선", "기대", "희망",
            "최고", "훌륭하다", "뛰어나다", "상승", "증가", "이점", "혜택",
            "안정", "회복", "지원", "협력", "번영", "성장", "강화", "확대"
        ])
        self.negative_keywords = set([
            "나쁘다", "부정", "약점", "실패", "하락", "감소", "우려", "문제",
            "최악", "형편없다", "논란", "비판", "반대", "피해", "손실",
            "불안", "위험", "갈등", "분열", "침체", "악화", "축소", "제한"
        ])

    def analyze_sentiment(self, text: str) -> Tuple[float, str]:
        """
        주어진 텍스트의 감성을 분석하여 점수와 긍정/부정/중립 여부를 반환합니다.
        Args:
            text (str): 분석할 한국어 텍스트.
        Returns:
            Tuple[float, str]: 감성 점수 (양수: 긍정, 음수: 부정, 0: 중립)와
                               "긍정", "부정", "중립" 중 하나의 문자열.
        """
        if not text:
            return 0.0, "중립"

        # 형태소 분석 (명사, 형용사, 동사 등 주요 품사 추출)
        # nouns(), morphs(), phrases(), pos() 등 다양한 함수가 있습니다.
        # 여기서는 pos()를 사용하여 품사 정보를 함께 활용합니다.
        tokens = self.okt.pos(text, norm=True, stem=True) # 정규화 및 어간 추출

        score = 0
        positive_count = 0
        negative_count = 0

        for word, tag in tokens:
            # 명사, 동사, 형용사 위주로 감성 분석에 사용
            if tag in ['Noun', 'Verb', 'Adjective']:
                if word in self.positive_keywords:
                    score += 1
                    positive_count += 1
                elif word in self.negative_keywords:
                    score -= 1
                    negative_count += 1
        
        sentiment_label = "중립"
        if score > 0:
            sentiment_label = "긍정"
        elif score < 0:
            sentiment_label = "부정"
        
        # 감성 점수 정규화 (전체 키워드 개수에 비례하여)
        total_keywords = positive_count + negative_count
        if total_keywords > 0:
            normalized_score = score / total_keywords
        else:
            normalized_score = 0.0

        return normalized_score, sentiment_label

if __name__ == '__main__':
    # 테스트 코드
    analyzer = SentimentAnalyzer()

    texts_to_analyze = [
        "이번 경제 정책은 시장에 긍정적인 영향을 미칠 것으로 기대된다.",
        "정부의 새로운 방안은 많은 비판과 우려를 낳고 있다.",
        "날씨가 너무 좋아서 기분이 최고로 행복하다.",
        "주식 시장이 급락하여 투자자들의 불안이 커지고 있다.",
        "그는 뛰어난 실력으로 프로젝트를 성공적으로 이끌었다.",
        "별다른 특이사항 없이 무난하게 진행되었다."
    ]

    for i, text in enumerate(texts_to_analyze):
        sentiment_score, sentiment_label = analyzer.analyze_sentiment(text)
        print(f"
--- 텍스트 {i+1} ---")
        print(f"원본 텍스트: {text}")
        print(f"감성 점수: {sentiment_score:.2f}")
        print(f"감성 라벨: {sentiment_label}")

