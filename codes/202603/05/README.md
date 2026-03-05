# 텍스트 감성 분석 프로젝트

이 프로젝트는 Python을 활용하여 간단한 텍스트 감성 분석을 수행하는 소규모 애플리케이션입니다. 사용자에게 긍정, 부정, 중립 감성을 판별하는 기능을 제공합니다.

## 프로젝트 구조

*   `README.md`: 프로젝트에 대한 설명서입니다.
*   `main.py`: 프로그램의 진입점이며, 데이터 로딩, 감성 분석 실행, 결과 출력을 담당합니다.
*   `sentiment_analyzer.py`: `TextBlob` 라이브러리를 사용하여 텍스트 감성 분석을 수행하는 핵심 로직을 포함하고 있습니다.
*   `data_loader.py`: 분석에 사용될 예시 텍스트 데이터를 로드하는 기능을 제공합니다.

## 실행 방법

1.  **필요 라이브러리 설치**: 이 프로젝트는 `TextBlob` 라이브러리를 사용합니다. 아래 명령어를 통해 설치할 수 있습니다.
    ```bash
    pip install textblob
    python -m textblob.download_corpora
    ```
    *`python -m textblob.download_corpora` 명령은 `TextBlob`가 사용하는 NLTK 말뭉치 데이터를 다운로드하는 데 필요합니다.*

2.  **프로그램 실행**: 프로젝트의 루트 디렉터리에서 다음 명령어를 실행합니다.
    ```bash
    python main.py
    ```

## 코드 설명

### `main.py`

이 파일은 `data_loader.py`로부터 텍스트 데이터를 로드하고, `sentiment_analyzer.py`의 `SentimentAnalyzer` 클래스를 사용하여 감성을 분석합니다. 분석된 각 문장의 감성 점수(polarity)와 분류된 감성(긍정/부정/중립)을 출력합니다.

### `sentiment_analyzer.py`

`SentimentAnalyzer` 클래스를 정의하고 있습니다. `analyze_sentiment` 메서드는 입력된 텍스트에 대해 `TextBlob`을 이용하여 감성 점수를 계산하고, 이 점수에 따라 '긍정', '부정', '중립'으로 분류합니다.

### `data_loader.py`

`load_data` 함수는 감성 분석을 위한 예시 문장들을 리스트 형태로 반환합니다. 실제 시나리오에서는 이 함수를 데이터베이스나 파일에서 데이터를 읽어오는 로직으로 대체할 수 있습니다.
