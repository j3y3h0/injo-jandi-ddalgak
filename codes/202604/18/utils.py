import re

def preprocess_text(text: str) -> str:
    """
    입력된 텍스트를 전처리합니다.
    현재는 한글, 영어 알파벳을 제외한 특수문자 및 숫자를 제거하고, 텍스트를 공백 기준으로 정리합니다.

    Args:
        text (str): 전처리할 원본 텍스트.

    Returns:
        str: 전처리된 텍스트.
    """
    # 한글, 영어 대소문자만 남기고 모든 특수문자 및 숫자 제거
    text = re.sub(r'[^가-힣a-zA-Z\s]', '', text)
    # 여러 공백을 하나의 공백으로 치환
    text = re.sub(r'\s+', ' ', text).strip()
    return text

