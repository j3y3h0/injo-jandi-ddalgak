# preprocessor.py
from konlpy.tag import Okt

class TextPreprocessor:
    """
    텍스트 전처리를 위한 클래스.
    한국어 텍스트를 형태소 분석하고 불용어를 제거합니다.
    """
    def __init__(self):
        self.okt = Okt()
        # 간단한 한국어 불용어 리스트. 필요에 따라 확장 가능.
        self.stopwords = {'은', '는', '이', '가', '을', '를', '다', '고', '하다', '의', '에', '와', '과', '도', '만', '으로', '에서', '에게', '하여', '할', '수', '때문', '것', '게', '듯', '면', '지만', '거나', '처럼', '까지', '부터'}

    def preprocess_text(self, text):
        """
        주어진 텍스트를 전처리합니다.
        1. 형태소 분석 (명사, 동사, 형용사만 추출)
        2. 불용어 제거
        """
        # 형태소 분석 및 특정 품사(명사, 동사, 형용사) 추출
        # 여기서는 좀 더 단순하게 명사만 추출하도록 하겠습니다.
        # 더 복잡한 분석을 원하면 pos 인자를 수정하거나 다른 품사도 포함할 수 있습니다.
        tokens = self.okt.nouns(text)
        
        # 불용어 제거
        filtered_tokens = [
            token for token in tokens if token not in self.stopwords and len(token) > 1
        ]
        
        return filtered_tokens

# 테스트 코드 (실제로 실행되지는 않지만 예시를 위해 포함)
if __name__ == "__main__":
    preprocessor = TextPreprocessor()
    test_text = "이 영화는 정말 재미있고 감동적입니다. 경제 상황이 좋지 않아 걱정이 됩니다."
    processed_tokens = preprocessor.preprocess_text(test_text)
    print(f"원본 텍스트: {test_text}")
    print(f"전처리된 토큰: {processed_tokens}")
    # 예상 출력: 원본 텍스트: 이 영화는 정말 재미있고 감동적입니다. 경제 상황이 좋지 않아 걱정이 됩니다.
    # 예상 출력: 전처리된 토큰: ['영화', '재미', '감동', '경제', '상황', '걱정']
