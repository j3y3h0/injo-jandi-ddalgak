# utils.py
# 텍스트 전처리를 위한 유틸리티 함수들을 포함합니다.

def preprocess_text(text: str) -> str:
    """
    입력된 텍스트를 전처리합니다.
    현재는 불필요한 공백을 제거하고 소문자(알파벳의 경우)로 변환하는 간단한 전처리만 수행합니다.
    향후 필요에 따라 더 복잡한 정규화, 특수문자 제거 등의 기능을 추가할 수 있습니다.

    Args:
        text (str): 전처리할 원본 텍스트.

    Returns:
        str: 전처리된 텍스트.
    """
    if not isinstance(text, str):
        raise TypeError("입력은 문자열이어야 합니다.")
    
    # 양쪽 공백 제거 및 여러 개의 공백을 하나로 축소
    processed_text = ' '.join(text.split()).lower()
    
    return processed_text

if __name__ == "__main__":
    # 간단한 테스트 예시
    print("유틸리티 함수 테스트:")
    test_string_1 = "  안녕하세요,  반갑습니다!  "
    test_string_2 = "  PyThon  프로젝트 예제.  "
    test_string_3 = "   또 다른   문장   입니다.   "

    print(f"원본: '{test_string_1}' -> 전처리: '{preprocess_text(test_string_1)}'")
    print(f"원본: '{test_string_2}' -> 전처리: '{preprocess_text(test_string_2)}'")
    print(f"원본: '{test_string_3}' -> 전처리: '{preprocess_text(test_string_3)}'")
