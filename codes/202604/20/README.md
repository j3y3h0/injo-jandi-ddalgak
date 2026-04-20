# AI 기반 텍스트 요약 프로젝트

이 프로젝트는 Hugging Face의 `transformers` 라이브러리를 활용하여 텍스트 요약을 수행하는 파이썬 기반의 소규모 애플리케이션입니다. 사용자가 입력한 긴 텍스트를 요약하여 핵심 내용을 추출하는 기능을 제공합니다.

## 프로젝트 구조

```
.
├── README.md
├── main.py
├── summarizer.py
└── utils.py
```

*   `README.md`: 프로젝트에 대한 설명, 설정 방법, 실행 방법 등을 담고 있습니다.
*   `main.py`: 프로그램의 진입점입니다. 사용자로부터 텍스트를 입력받고, `summarizer.py`의 요약 함수를 호출하여 결과를 출력합니다.
*   `summarizer.py`: 텍스트 요약의 핵심 로직을 포함합니다. Hugging Face `transformers` 라이브러리를 사용하여 사전 학습된 요약 모델을 로드하고 텍스트를 요약합니다.
*   `utils.py`: 유틸리티 함수들을 모아 놓은 파일입니다. 현재 프로젝트에서는 간단한 모델 로드나 텍스트 전처리 기능을 포함할 수 있습니다.

## 실행 방법

### 1. 가상 환경 설정 (권장)

```bash
python -m venv venv
./venv/Scripts/activate  # Windows
# source venv/bin/activate # Linux/macOS
```

### 2. 필요한 라이브러리 설치

이 프로젝트는 다음 라이브러리들을 사용합니다:

*   `transformers`: Hugging Face 모델 사용
*   `torch` 또는 `tensorflow`: `transformers`의 백엔드 프레임워크 (선택 사항, transformers 설치 시 종속성으로 설치될 수 있음)
*   `sentencepiece`: `transformers` 토크나이저에 필요

```bash
pip install transformers torch sentencepiece
# 또는
# pip install transformers tensorflow sentencepiece
```

### 3. 프로젝트 실행

`main.py` 파일을 실행하여 텍스트 요약 기능을 사용합니다.

```bash
python main.py
```

프로그램이 시작되면 요약할 텍스트를 입력하라는 메시지가 나타날 것입니다. 텍스트를 입력하고 Enter를 누르면 요약된 결과가 출력됩니다.

## 사용된 라이브러리 및 알고리즘

*   **Hugging Face Transformers**: 최신 NLP 모델들을 쉽게 사용할 수 있도록 하는 파이썬 라이브러리입니다. 여기서는 BART, T5, 혹은 GPT-2와 같은 사전 학습된 요약 모델을 활용합니다.
*   **PyTorch / TensorFlow**: 딥러닝 모델의 백엔드 프레임워크입니다. `transformers` 라이브러리는 이들 중 하나를 필요로 합니다.
*   **텍스트 요약 알고리즘**: 주로 Abstractive Summarization (추상적 요약) 방식을 사용하며, 모델이 원본 텍스트의 내용을 이해하고 새로운 문장으로 요약문을 생성합니다.

---
**작성자:** Gemini CLI
**작성일:** 2026년 4월 20일
