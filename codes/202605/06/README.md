# 주식 시장 동향 분석 프로젝트

이 프로젝트는 `yfinance` 라이브러리를 사용하여 특정 주식의 과거 데이터를 가져오고, `pandas` 라이브러리를 통해 간단한 기술적 분석(예: 이동 평균 계산)을 수행하는 소규모 파이썬 애플리케이션입니다.

## 프로젝트 구조

- `README.md`: 프로젝트에 대한 설명 문서입니다.
- `main.py`: 프로그램의 진입점입니다. 데이터 가져오기 및 분석 로직을 호출합니다.
- `data_fetcher.py`: `yfinance`를 사용하여 주식 데이터를 웹에서 가져오는 기능을 담당합니다.
- `analyzer.py`: 가져온 주식 데이터에 대한 기술적 분석을 수행하는 기능을 담당합니다.

## 실행 방법

1.  **필요 라이브러리 설치**:
    이 프로젝트를 실행하기 위해 다음 파이썬 라이브러리가 필요합니다: `yfinance`, `pandas`.
    다음 명령어를 사용하여 설치할 수 있습니다:
    ```bash
    pip install yfinance pandas
    ```

2.  **프로젝트 실행**:
    `main.py` 파일을 실행하면 프로젝트가 시작됩니다.
    ```bash
    python main.py
    ```

## 코드 설명

### `main.py`
이 파일은 `data_fetcher.py`와 `analyzer.py` 모듈에서 함수를 임포트하여 사용합니다. `main` 함수에서는 분석하고자 하는 주식 티커(예: 'AAPL' for Apple)와 기간을 설정하고, 데이터를 가져와 분석을 수행한 후 결과를 출력합니다.

### `data_fetcher.py`
`fetch_stock_data(ticker, start_date, end_date)` 함수를 제공합니다. 이 함수는 주어진 주식 티커와 시작/종료 날짜를 기반으로 `yfinance`를 통해 주식의 일별 데이터를 DataFrame 형태로 반환합니다.

### `analyzer.py`
`calculate_moving_average(data, window)` 함수를 제공합니다. 이 함수는 주식 데이터 DataFrame과 이동 평균을 계산할 기간(`window`)을 인수로 받아, 지정된 기간의 종가 이동 평균을 계산하여 DataFrame에 추가한 후 반환합니다.

## 개발 환경
- Python 3.8 이상
- pip (패키지 관리자)