# AI 기반 판매 예측 시스템

이 프로젝트는 AI를 활용하여 미래 판매량을 예측하는 소규모 시스템이다. `pandas`, `scikit-learn`, `joblib` 등의 파이썬 라이브러리를 사용하여 데이터 로드, 전처리, 모델 훈련 및 예측 과정을 수행한다.

## 1. 프로젝트 구조

프로젝트는 다음 파일들로 구성되어 있다:

-   `main.py`: 프로젝트의 진입점이다. 전체 판매 예측 워크플로우를 조율한다.
-   `data_loader.py`: 판매 데이터를 로드하고 초기 정제를 수행한다. 파일 경로가 주어지지 않으면 더미 데이터를 생성한다.
-   `data_processor.py`: 로드된 데이터를 전처리한다. 날짜 관련 피처 생성, 시간 지연(Lag) 피처 생성, 범주형 변수 원-핫 인코딩 등의 작업을 포함한다.
-   `model_trainer.py`: 전처리된 데이터를 사용하여 머신러닝 모델(RandomForestRegressor)을 훈련하고, 훈련된 모델을 파일로 저장한다.
-   `predictor.py`: 저장된 모델을 로드하여 미래 판매량을 예측한다.
-   `README.md`: 이 문서이다.

## 2. 사용 언어 및 라이브러리

-   **언어**: Python 3.8+
-   **주요 라이브러리**:
    -   `pandas`: 데이터 처리 및 분석
    -   `numpy`: 수치 계산
    -   `scikit-learn`: 머신러닝 모델 (RandomForestRegressor, Pipeline, ColumnTransformer, OneHotEncoder 등)
    -   `joblib`: 훈련된 모델 저장 및 로드

## 3. 설치 방법

프로젝트 실행에 필요한 라이브러리들을 설치해야 한다. `pip`를 사용하여 다음 명령어로 설치할 수 있다:

```bash
pip install pandas numpy scikit-learn joblib
```

## 4. 실행 방법

`main.py` 파일을 실행하여 판매 예측 시스템을 구동한다.

```bash
python main.py
```

-   **더미 데이터 사용**: 별도의 `data_file` 인수를 제공하지 않으면 `data_loader.py`에서 생성된 더미 데이터를 사용하여 시스템이 실행된다.
-   **실제 데이터 사용**: `main.py`의 `run_sales_forecasting` 함수 호출 시 `data_file` 인수에 실제 판매 데이터가 담긴 CSV 파일의 경로를 전달할 수 있다. CSV 파일은 'date', 'product\_id', 'region', 'promotion', 'sales'와 같은 컬럼을 포함해야 한다.

    ```python
    # main.py 파일 내에서 run_sales_forecasting 호출 시
    # run_sales_forecasting(data_file='your_sales_data.csv', predict_days=7)
    ```

## 5. 예측 결과

시스템이 성공적으로 실행되면, 훈련된 모델을 기반으로 한 미래 판매량 예측 결과가 콘솔에 출력될 것이다. 훈련된 모델은 `sales_forecast_model.pkl` 파일로 저장된다.

## 6. 코드 주석 및 문서화

모든 코드 파일 내 주석과 이 `README.md` 문서는 한글로 작성되어 있다.
