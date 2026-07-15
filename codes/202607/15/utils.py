# utils.py
# 이 파일은 프로젝트의 유틸리티 함수들을 포함합니다.

import os
from PIL import Image
import random

def create_directory_if_not_exists(path):
    """
    지정된 경로에 디렉터리가 없으면 생성합니다.
    :param path: 생성할 디렉터리 경로
    """
    if not os.path.exists(path):
        os.makedirs(path)
        print(f"디렉터리 생성: {path}")
    else:
        print(f"디렉터리가 이미 존재합니다: {path}")

def generate_dummy_image(filepath, width=100, height=100):
    """
    더미 이미지 파일을 생성합니다. (흑백 또는 랜덤 색상)
    :param filepath: 저장할 이미지 파일 경로
    :param width: 이미지 너비
    :param height: 이미지 높이
    """
    try:
        # 랜덤 픽셀 값으로 이미지 생성 (간단한 더미 이미지)
        image = Image.new('RGB', (width, height), color = (random.randint(0,255), random.randint(0,255), random.randint(0,255)))
        # 이미지에 간단한 패턴 추가 (예: 무작위 사각형)
        for _ in range(random.randint(1, 5)):
            x1, y1 = random.randint(0, width - 10), random.randint(0, height - 10)
            x2, y2 = random.randint(x1 + 5, width), random.randint(y1 + 5, height)
            color = (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))
            for x in range(x1, x2):
                for y in range(y1, y2):
                    if 0 <= x < width and 0 <= y < height:
                        image.putpixel((x, y), color)
        
        image.save(filepath)
        print(f"더미 이미지 생성: {filepath}")
    except Exception as e:
        print(f"더미 이미지 생성 실패 ({filepath}): {e}")

def log_message(message, log_file='quality_inspection.log'):
    """
    메시지를 콘솔에 출력하고 로그 파일에 기록합니다.
    :param message: 기록할 메시지
    :param log_file: 로그 파일 경로
    """
    print(message)
    with open(log_file, 'a', encoding='utf-8') as f:
        f.write(f"{message}
")

if __name__ == '__main__':
    # 이 모듈이 직접 실행될 때의 테스트 코드
    output_dir = 'temp_images'
    create_directory_if_not_exists(output_dir)
    generate_dummy_image(os.path.join(output_dir, 'dummy_good_01.png'))
    generate_dummy_image(os.path.join(output_dir, 'dummy_bad_01.png'))
    log_message("유틸리티 테스트 완료.", log_file=os.path.join(output_dir, 'test.log'))
