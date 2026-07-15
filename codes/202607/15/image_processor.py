# image_processor.py
# 이 파일은 이미지 전처리 및 특징 추출 기능을 담당합니다.

from PIL import Image
import numpy as np
import os
from utils import log_message

class ImageProcessor:
    """
    이미지 파일을 처리하고 특징을 추출하는 클래스입니다.
    """
    def __init__(self, target_size=(64, 64)):
        """
        ImageProcessor를 초기화합니다.
        :param target_size: 모든 이미지를 리사이징할 크기 (너비, 높이)
        """
        self.target_size = target_size
        log_message(f"ImageProcessor 초기화. 타겟 이미지 크기: {self.target_size}")

    def load_and_preprocess_image(self, image_path):
        """
        지정된 경로에서 이미지를 로드하고 전처리합니다.
        - 이미지를 RGB 포맷으로 변환
        - 지정된 타겟 크기로 리사이징
        :param image_path: 로드할 이미지 파일의 경로
        :return: 전처리된 PIL Image 객체 또는 None (오류 발생 시)
        """
        try:
            with Image.open(image_path) as img:
                img = img.convert('RGB')  # 모든 이미지를 RGB로 변환하여 일관성 유지
                img = img.resize(self.target_size) # 지정된 크기로 리사이징
                log_message(f"이미지 전처리 완료: {os.path.basename(image_path)}")
                return img
        except FileNotFoundError:
            log_message(f"오류: 파일을 찾을 수 없습니다 - {image_path}")
            return None
        except Exception as e:
            log_message(f"오류: 이미지 처리 중 문제가 발생했습니다 ({image_path}): {e}")
            return None

    def extract_features(self, preprocessed_image):
        """
        전처리된 이미지에서 간단한 특징을 추출합니다.
        여기서는 이미지의 평균 픽셀 값과 표준 편차를 특징으로 사용합니다.
        (실제 AI 모델에서는 더 복잡한 특징 추출 기법이 사용됩니다.)
        :param preprocessed_image: 전처리된 PIL Image 객체
        :return: 특징 벡터 (numpy 배열) 또는 None
        """
        if preprocessed_image is None:
            return None
        
        try:
            # 이미지를 numpy 배열로 변환
            img_array = np.array(preprocessed_image)
            
            # 각 채널(R, G, B)별 평균 픽셀 값과 전체 픽셀 값의 표준 편차 추출
            mean_r = np.mean(img_array[:, :, 0])
            mean_g = np.mean(img_array[:, :, 1])
            mean_b = np.mean(img_array[:, :, 2])
            std_dev = np.std(img_array) # 전체 픽셀 값의 표준 편차
            
            features = np.array([mean_r, mean_g, mean_b, std_dev])
            log_message(f"특징 추출 완료: {features}")
            return features
        except Exception as e:
            log_message(f"오류: 특징 추출 중 문제가 발생했습니다: {e}")
            return None

if __name__ == '__main__':
    # 이 모듈이 직접 실행될 때의 테스트 코드
    from utils import generate_dummy_image, create_directory_if_not_exists
    
    test_dir = 'test_images_proc'
    create_directory_if_not_exists(test_dir)
    dummy_img_path = os.path.join(test_dir, 'dummy_test_image.png')
    generate_dummy_image(dummy_img_path)

    processor = ImageProcessor()
    processed_img = processor.load_and_preprocess_image(dummy_img_path)
    if processed_img:
        features = processor.extract_features(processed_img)
        if features is not None:
            log_message(f"테스트 이미지의 추출된 특징: {features}")
        else:
            log_message("특징 추출 테스트 실패.")
    else:
        log_message("이미지 전처리 테스트 실패.")
