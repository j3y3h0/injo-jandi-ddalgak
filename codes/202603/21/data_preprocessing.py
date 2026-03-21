import re

def preprocess_text(text):
    """
    텍스트를 전처리하는 함수이다.
    불필요한 문자 제거 및 소문자 변환을 수행한다.
    """
    text = text.lower() # 소문자 변환
    text = re.sub(r'[^가-힣a-z\s]', '', text) # 한글, 영어 소문자, 공백만 남김
    return text

def tokenize_text(text):
    """
    전처리된 텍스트를 토큰(단어)으로 분리하는 함수이다.
    """
    return text.split()

if __name__ == "__main__":
    sample_text = "안녕하세요! 이 프로젝트는 정말 유용합니다. 하지만 몇몇 단점도 있습니다."
    preprocessed = preprocess_text(sample_text)
    tokens = tokenize_text(preprocessed)
    print(f"원문: {sample_text}")
    print(f"전처리 후: {preprocessed}")
    print(f"토큰화 후: {tokens}")
