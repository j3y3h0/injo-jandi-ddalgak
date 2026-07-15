# main.py
# 이 파일은 AI 기반 품질 검사 자동화 솔루션의 메인 실행 스크립트입니다.

import os
from image_processor import ImageProcessor
from quality_model import QualityModel
from utils import create_directory_if_not_exists, generate_dummy_image, log_message

def run_quality_inspection(input_dir='input_images', output_dir='output_results'):
    """
    품질 검사 워크플로우를 실행합니다.
    :param input_dir: 검사할 이미지가 있는 디렉터리
    :param output_dir: 결과가 저장될 디렉터리
    """
    log_message("--- AI 기반 품질 검사 솔루션 시작 ---")

    # 입력 및 출력 디렉터리 생성 확인
    create_directory_if_not_exists(input_dir)
    create_directory_if_not_exists(output_dir)

    # 더미 이미지 생성 (테스트를 위해, 실제 실행 시에는 이미지를 input_dir에 넣어두어야 합니다.)
    if not os.listdir(input_dir): # input_dir이 비어있으면 더미 이미지 생성
        log_message(f"'{input_dir}' 디렉터리가 비어있어 더미 이미지를 생성합니다.")
        for i in range(1, 4):
            generate_dummy_image(os.path.join(input_dir, f'product_good_{i}.png'))
        for i in range(1, 3):
            generate_dummy_image(os.path.join(input_dir, f'product_bad_{i}.png'))

    processor = ImageProcessor()
    model = QualityModel()
    
    results = []

    # 입력 디렉터리 내의 모든 이미지 파일 처리
    for filename in os.listdir(input_dir):
        if filename.lower().endswith(('.png', '.jpg', '.jpeg', '.gif', '.bmp')):
            image_path = os.path.join(input_dir, filename)
            log_message(f"
이미지 검사 시작: {filename}")

            # 1. 이미지 로드 및 전처리
            preprocessed_image = processor.load_and_preprocess_image(image_path)
            if preprocessed_image is None:
                results.append({'filename': filename, 'status': '처리 실패', 'prediction': '판단 불가'})
                continue

            # 2. 특징 추출
            features = processor.extract_features(preprocessed_image)
            if features is None:
                results.append({'filename': filename, 'status': '특징 추출 실패', 'prediction': '판단 불가'})
                continue

            # 3. 품질 예측
            prediction = model.predict(features)
            
            # 결과 저장
            results.append({'filename': filename, 'status': '처리 완료', 'prediction': prediction})
            log_message(f"'{filename}' 품질 예측 결과: {prediction}")

    # 최종 결과 보고
    log_message("
--- 품질 검사 최종 결과 ---")
    for res in results:
        log_message(f"파일: {res['filename']}, 상태: {res['status']}, 예측: {res['prediction']}")
    log_message("--- 품질 검사 솔루션 종료 ---")

    # 결과를 파일로 저장 (예시)
    output_filepath = os.path.join(output_dir, 'inspection_report.txt')
    with open(output_filepath, 'w', encoding='utf-8') as f:
        f.write("AI 기반 품질 검사 보고서
")
        f.write("------------------------
")
        for res in results:
            f.write(f"파일: {res['filename']}, 예측: {res['prediction']}
")
    log_message(f"검사 보고서가 '{output_filepath}'에 저장되었습니다.")


if __name__ == '__main__':
    run_quality_inspection()
