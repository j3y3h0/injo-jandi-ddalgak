# sentiment_analyzer.py
# 텍스트 감성 분석 로직을 구현합니다.

from typing import List

class SentimentAnalyzer:
    """
    규칙 기반의 한국어 텍스트 감성 분석기 클래스입니다.
    미리 정의된 긍정 및 부정 키워드 목록을 사용하여 텍스트의 감성을 분석합니다.
    """
    def __init__(self):
        """
        감성 분석을 위한 긍정 및 부정 키워드 목록을 초기화합니다.
        이 목록은 필요에 따라 확장하거나 외부 파일에서 로드하도록 변경할 수 있습니다.
        """
        # 긍정 키워드 목록
        self.positive_keywords: List[str] = [
            "좋다", "최고", "아름답다", "행복", "기쁘다", "훌륭하다", "만족", "사랑", "감사", "성공",
            "재밌다", "웃기다", "즐겁다", "긍정", "완벽", "편안", "신난다", "감동", "예쁘다", "멋지다"
        ]
        # 부정 키워드 목록
        self.negative_keywords: List[str] = [
            "나쁘다", "최악", "슬프다", "화나다", "힘들다", "짜증", "불만", "싫다", "실패", "문제",
            "어렵다", "지루하다", "피곤하다", "부정", "끔찍", "불편", "짜증나다", "안 좋다", "무섭다", "우울"
        ]

    def analyze(self, text: str) -> str:
        """
        입력된 텍스트의 감성을 분석하여 '긍정', '부정', '중립' 중 하나를 반환합니다.
        텍스트 내에 포함된 긍정 및 부정 키워드의 수를 세어 감성을 결정합니다.

        Args:
            text (str): 전처리된 텍스트.

        Returns:
            str: 분석된 감성 ('긍정', '부정', '중립').
        """
        if not isinstance(text, str):
            raise TypeError("입력은 문자열이어야 합니다.")

        positive_count = 0
        negative_count = 0

        # 긍정 키워드 검사
        for keyword in self.positive_keywords:
            positive_count += text.count(keyword)

        # 부정 키워드 검사
        for keyword in self.negative_keywords:
            negative_count += text.count(keyword)

        # 감성 결정
        if positive_count > negative_count:
            return "긍정"
        elif negative_count > positive_count:
            return "부정"
        else:
            # 긍정/부정 키워드가 없거나 같은 수일 경우
            return "중립"

if __name__ == "__main__":
    # 간단한 테스트 예시
    print("감성 분석기 클래스 테스트:")
    analyzer = SentimentAnalyzer()

    texts_to_analyze = [
        "이 영화 정말 최고예요. 너무 재밌고 행복했어요!", # 긍정
        "서비스가 너무 나빴고, 정말 짜증나고 불만스러웠습니다.", # 부정
        "오늘은 날씨가 좋네요. 기분이 상쾌합니다.", # 긍정 (좋다, 기쁘다)
        "일이 너무 힘들고 어렵습니다.", # 부정
        "평범한 하루였어요.", # 중립
        "정말 훌륭한 아이디어예요. 완벽해요!", # 긍정
        "최악의 경험이었고, 다시는 방문하고 싶지 않습니다.", # 부정
        "책을 읽는 것은 즐거운 일입니다.", # 긍정
        "나는 아무 생각도 없습니다.", # 중립
        "기쁘고 즐거운 시간이었지만, 동시에 슬프기도 했습니다." # 중립 (각 키워드 2개씩)
    ]

    for i, text in enumerate(texts_to_analyze):
        # 실제 main.py에서는 전처리된 텍스트를 입력으로 받습니다. 여기서는 간단히 lower()만 적용
        analysis_result = analyzer.analyze(text.lower())
        print(f"{i+1}. '{text}' -> 감성: {analysis_result}")
