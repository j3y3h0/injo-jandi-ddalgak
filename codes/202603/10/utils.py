# utils.py
import re

def preprocess_text(text):
    """
    텍스트를 전처리하여 토큰 목록을 반환합니다.
    - 소문자 변환
    - 불필요한 문자 제거 (한글, 영어 소문자, 공백만 허용)
    - 공백 기준 토큰화
    """
    text = text.lower()  # 소문자 변환
    text = re.sub(r'[^가-힣a-z\s]', '', text)  # 한글, 영어 소문자, 공백만 남기고 제거
    tokens = text.split()  # 공백 기준 토큰화
    return tokens
