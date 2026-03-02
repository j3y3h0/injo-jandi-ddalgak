# data_loader.py

import pandas as pd
# Konlpy는 설치가 필요하므로, 실제 환경에서는 주석 해제 후 사용합니다.
# from konlpy.tag import Okt

class DataLoader:
    """
    텍스트 데이터를 로드하고 전처리하는 클래스입니다.
    실제 환경에서는 CSV 파일 등에서 데이터를 읽어오고 Konlpy를 사용하여 한국어 텍스트를 전처리합니다.
    """
    def __init__(self):
        # self.okt = Okt() # Konlpy 형태소 분석기 초기화 (설치 후 사용)
        pass

    def load_data(self, file_path: str = None) -> list:
        """
        데이터를 로드하는 메서드입니다.
        현재는 예시 데이터를 반환하며, 실제로는 file_path를 통해 CSV 등을 로드합니다.

        Args:
            file_path (str, optional): 데이터를 로드할 파일 경로. 기본값은 None.

        Returns:
            list: 로드된 텍스트 데이터 리스트.
        """
        if file_path:
            # 실제 데이터 로드 로직 (예: CSV 파일)
            # df = pd.read_csv(file_path)
            # return df['text'].tolist()
            print(f"INFO: '{file_path}' 경로에서 데이터를 로드하는 것을 시뮬레이션합니다.")

        # 예시 데이터
        sample_texts = [
            "이 영화 정말 재미있었어요. 다시 보고 싶어요!",
            "서비스가 너무 불친절해요. 실망했습니다.",
            "상품 품질은 좋았지만 배송이 너무 늦었어요.",
            "새로운 기능들이 추가되어 더욱 편리해졌네요.",
            "그냥 그래요. 특별히 좋지도 나쁘지도 않아요."
        ]
        print("INFO: 예시 텍스트 데이터를 로드했습니다.")
        return sample_texts

    def preprocess_text(self, text: str) -> str:
        """
        텍스트를 전처리하는 메서드입니다.
        현재는 간단한 정규화만 수행하며, 실제로는 Konlpy를 사용한 형태소 분석 등을 포함합니다.

        Args:
            text (str): 전처리할 원본 텍스트.

        Returns:
            str: 전처리된 텍스트.
        """
        # 텍스트 정규화 (예: 공백 제거, 소문자 변환 등)
        processed_text = text.strip()

        # 실제 Konlpy를 이용한 형태소 분석 및 불용어 제거 예시 (설치 후 사용)
        # tokens = self.okt.morphs(processed_text) # 형태소 분석
        # tokens = [word for word in tokens if not word in ['이', '그', '저', '것', '수', '등']] # 불용어 제거 예시
        # processed_text = ' '.join(tokens)

        # 간단한 전처리만 수행함을 알림
        print(f"INFO: 텍스트 전처리 중 (원본: '{text[:20]}...', 전처리 후: '{processed_text[:20]}...')")
        return processed_text

if __name__ == "__main__":
    # 이 파일 단독 실행 시 테스트 코드
    data_loader = DataLoader()
    texts = data_loader.load_data()
    for text in texts:
        processed = data_loader.preprocess_text(text)
        print(f"원본: {text} -> 전처리: {processed}")
