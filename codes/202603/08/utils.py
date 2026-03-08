# -*- coding: utf-8 -*-

import re

def clean_text(text: str) -> str:
    """
    텍스트에서 불필요한 공백, 특수문자 등을 제거하여 정제합니다.
    Args:
        text (str): 정제할 원본 텍스트.
    Returns:
        str: 정제된 텍스트.
    """
    # HTML 태그 제거
    text = re.sub(r'<[^>]+>', '', text)
    # 특수 문자, 숫자 제거 (한글, 영어, 기본 구두점 제외)
    text = re.sub(r'[^가-힣a-zA-Z0-9\s.,?!]', '', text)
    # 여러 공백을 하나의 공백으로 치환
    text = re.sub(r'\s+', ' ', text).strip()
    return text

def save_to_file(filename: str, content: str):
    """
    주어진 내용을 파일로 저장합니다.
    Args:
        filename (str): 저장할 파일 이름.
        content (str): 파일에 저장할 내용.
    """
    try:
        with open(filename, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"'{filename}' 파일에 내용이 성공적으로 저장되었습니다.")
    except IOError as e:
        print(f"파일 저장 중 오류 발생: {e}")

if __name__ == '__main__':
    # 테스트 코드
    sample_text = "  안녕하세요! <p>이것은</p> 테스트 텍스트입니다. 12345, 특수문자!@#$%. "
    cleaned = clean_text(sample_text)
    print(f"원본: {sample_text}")
    print(f"정제 후: {cleaned}")

    save_to_file("test_output.txt", "이것은 유틸리티 파일 테스트 내용입니다.")
