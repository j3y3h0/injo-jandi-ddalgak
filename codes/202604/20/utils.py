# utils.py
# 이 파일은 프로젝트 전반에 걸쳐 사용될 수 있는 유틸리티 함수들을 포함합니다.

def preprocess_text(text: str) -> str:
    """
    입력 텍스트를 전처리합니다.
    (예: 공백 제거, 특수 문자 처리 등. 현재는 간단한 예시)
    """
    # 현재는 간단한 전처리만 수행합니다. 필요에 따라 확장 가능합니다.
    processed_text = text.strip()
    return processed_text

def get_model_config(model_name: str) -> dict:
    """
    주어진 모델 이름에 대한 설정 정보를 반환합니다.
    (예: 최대 입력 길이, 토크나이저 설정 등)
    """
    if model_name == "skt/kogpt2-base-v2":
        return {
            "max_input_length": 1024, # 예시 값
            "language": "korean"
        }
    elif model_name == "facebook/bart-large-cnn":
        return {
            "max_input_length": 1024, # 예시 값
            "language": "english"
        }
    else:
        return {
            "max_input_length": 512, # 기본 값
            "language": "unknown"
        }

# 필요에 따라 다른 유틸리티 함수들을 여기에 추가할 수 있습니다.
