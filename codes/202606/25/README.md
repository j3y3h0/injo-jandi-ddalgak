# 판매 데이터 분석 및 시각화 프로젝트

## 개요

이 프로젝트는 가상의 판매 데이터를 생성하고, 이를 분석하여 제품별 및 지역별 총 판매액을 계산한 뒤, 결과를 시각화하는 파이썬 애플리케이션입니다. `pandas` 라이브러리를 사용하여 데이터를 효율적으로 처리하고, `matplotlib` 라이브러리를 사용하여 분석 결과를 그래프로 표현합니다.

## 프로젝트 구조

```
.
├── main.py
├── data_loader.py
├── data_processor.py
├── visualizer.py
└── README.md
```

*   `main.py`: 애플리케이션의 진입점입니다. 데이터 로드, 처리, 시각화 과정을 총괄합니다.
*   `data_loader.py`: 가상의 판매 데이터를 생성하고 `pandas` DataFrame 형태로 반환하는 역할을 합니다.
*   `data_processor.py`: `data_loader.py`에서 로드된 데이터를 받아 제품별, 지역별 총 판매액을 계산하는 로직을 포함합니다.
*   `visualizer.py`: `data_processor.py`에서 처리된 데이터를 기반으로 막대 그래프를 생성하고 `.png` 이미지 파일로 저장하는 기능을 제공합니다.

## 실행 방법

### 1. 필수 라이브러리 설치

이 프로젝트를 실행하려면 다음 파이썬 라이브러리가 필요합니다:

*   `pandas`
*   `matplotlib`
*   `numpy`

아래 명령어를 사용하여 설치할 수 있습니다:

```bash
pip install pandas matplotlib numpy
```

### 2. 프로젝트 실행

모든 라이브러리가 설치되었다면, `main.py` 파일을 실행하여 프로젝트를 시작할 수 있습니다.

```bash
python main.py
```

프로그램이 성공적으로 실행되면, `product_sales_bar_chart.png` (제품별 판매액 그래프)와 `region_sales_bar_chart.png` (지역별 판매액 그래프) 두 개의 이미지 파일이 현재 디렉터리에 생성됩니다. 콘솔에는 데이터 로드, 처리, 시각화 과정에 대한 메시지가 출력될 것입니다.

## 코드 설명

*   **`data_loader.py`**:
    *   `load_sales_data()` 함수는 `numpy`와 `pandas`를 활용하여 무작위 날짜, 제품, 지역, 판매량, 가격을 포함하는 1000개의 가상 판매 기록을 생성합니다.
    *   `총판매액` 컬럼은 `판매량 * 가격`으로 계산됩니다.

*   **`data_processor.py`**:
    *   `process_sales_data(df)` 함수는 입력된 DataFrame `df`를 `제품`과 `지역`별로 그룹화하여 각 그룹의 `총판매액`을 집계합니다.
    *   집계된 결과는 각각 `제품별 총 판매액` DataFrame과 `지역별 총 판매액` DataFrame으로 반환됩니다.

*   **`visualizer.py`**:
    *   `plot_sales_data(product_sales, region_sales)` 함수는 `matplotlib.pyplot`을 사용하여 두 개의 막대 그래프를 생성합니다.
    *   하나는 제품별 총 판매액을, 다른 하나는 지역별 총 판매액을 보여줍니다.
    *   생성된 그래프는 각각 `product_sales_bar_chart.png`와 `region_sales_bar_chart.png` 파일로 저장됩니다.
    *   한글 폰트(`Malgun Gothic`) 설정을 통해 그래프 내 한글 깨짐 현상을 방지합니다.

이 프로젝트를 통해 간단한 데이터 분석 및 시각화 파이프라인을 이해할 수 있습니다.
