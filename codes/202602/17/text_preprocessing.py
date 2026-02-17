# text_preprocessing.py

from konlpy.tag import Okt
import re

# Okt 형태소 분석기 초기화
okt = Okt()

# 한국어 불용어 목록 정의 (예시)
# 실제 프로젝트에서는 더 상세하고 확장된 불용어 목록을 사용해야 합니다.
korean_stopwords = ['이', '그', '저', '것', '수', '등', '들', '으로', '와', '과', '하다', '있다', '이다', '되다', '이다', '아니다', '이다', '않다']

def clean_text(text):
    """
    텍스트에서 한글, 숫자, 영어를 제외한 모든 문자를 제거합니다.
    """
    text = re.sub(r'[^가-힣a-zA-Z0-9\s]', '', text)
    return text

def tokenize_and_remove_stopwords(text):
    """
    주어진 한국어 텍스트를 형태소 단위로 토큰화하고 불용어를 제거합니다.
    """
    # 텍스트 정제
    text = clean_text(text)
    # Okt 형태소 분석기를 사용하여 토큰화 (명사, 동사, 형용사 등)
    # stem=True 옵션으로 어간 추출을 시도합니다.
    tokens = okt.morphs(text, stem=True)
    # 불용어 제거 및 한 글자 단어 제거
    filtered_tokens = [
        word for word in tokens 
        if word not in korean_stopwords and len(word) > 1
    ]
    return ' '.join(filtered_tokens)

if __name__ == '__main__':
    # 테스트 코드
    sample_text = "이것은 정말 좋은 영화였다고 생각합니다. 하지만 어떤 사람들은 별로라고 하더군요."
    processed_text = tokenize_and_remove_stopwords(sample_text)
    print(f"원본 텍스트: {sample_text}")
    print(f"전처리된 텍스트: {processed_text}")

    sample_text_2 = "나는 오늘 매우 행복하다. 기분이 최고다."
    processed_text_2 = tokenize_and_remove_stopwords(sample_text_2)
    print(f"원본 텍스트: {sample_text_2}")
    print(f"전처리된 텍스트: {processed_text_2}")
