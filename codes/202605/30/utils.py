# utils.py

# 이 파일은 텍스트 전처리 유틸리티 함수를 포함한다.

def preprocess_text(text: str) -> str:
    """
    주어진 텍스트를 전처리한다.
    현재는 특별한 전처리 없이 텍스트를 그대로 반환한다.
    향후 소문자 변환, 구두점 제거 등의 로직이 추가될 수 있다.
    """
    return text.strip()

if __name__ == '__main__':
    # 간단한 테스트 예시
    sample_text = "   안녕하세요,    이것은 테스트입니다!   "
    processed_text = preprocess_text(sample_text)
    print(f"원본 텍스트: '{sample_text}'")
    print(f"전처리된 텍스트: '{processed_text}'")
