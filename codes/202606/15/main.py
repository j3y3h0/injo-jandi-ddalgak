# main.py
import pandas as pd
from datetime import datetime, timedelta
from data_fetcher import fetch_stock_data
from preprocessor import preprocess_data
from model_trainer import train_model

def run_stock_prediction(ticker: str, start_date: str, end_date: str):
    """
    주식 예측 모델의 전체 워크플로우를 실행합니다.

    Args:
        ticker (str): 주식 심볼.
        start_date (str): 데이터 수집 시작 날짜 (YYYY-MM-DD).
        end_date (str): 데이터 수집 종료 날짜 (YYYY-MM-DD).
    """
    print(f"--- 주식 예측 모델 시작: {ticker} ({start_date} ~ {end_date}) ---")

    # 1. 데이터 가져오기
    print("1. 주식 데이터 가져오기...")
    df = fetch_stock_data(ticker, start_date, end_date)
    if df.empty:
        print("데이터 가져오기에 실패하여 프로그램을 종료합니다.")
        return

    # 2. 데이터 전처리
    print("
2. 데이터 전처리...")
    X_train, X_test, y_train, y_test = preprocess_data(df)
    if X_train.empty:
        print("데이터 전처리에 실패하여 프로그램을 종료합니다.")
        return

    # 3. 모델 학습
    print("
3. 모델 학습...")
    model = train_model(X_train, y_train, X_test, y_test)
    if model is None:
        print("모델 학습에 실패하여 프로그램을 종료합니다.")
        return

    # 4. 예측 수행 (간단한 예시: 테스트 데이터의 첫 번째 샘플로 예측)
    print("
4. 예측 수행 (테스트 데이터의 첫 번째 샘플로 다음 종가 예측)...")
    if not X_test.empty:
        # 예측할 데이터는 테스트 세트의 첫 번째 행
        # 실제 환경에서는 새로운, 아직 모르는 데이터를 사용해야 합니다.
        sample_to_predict = X_test.iloc[[0]]
        
        # 실제 값은 y_test의 첫 번째 값
        actual_value = y_test.iloc[0]

        predicted_value = model.predict(sample_to_predict)[0]
        
        print(f"  예측된 다음 날 종가: {predicted_value:.2f}")
        print(f"  실제 다음 날 종가: {actual_value:.2f}")
        print(f"  예측 오차: {(predicted_value - actual_value):.2f}")
    else:
        print("테스트 데이터가 충분하지 않아 예측을 수행할 수 없습니다.")
    
    print("
--- 주식 예측 모델 완료 ---")

if __name__ == '__main__':
    # 예측하고자 하는 주식 티커와 기간 설정
    TICKER = 'AAPL'  # 예: Apple Inc.
    END_DATE = datetime.now().strftime('%Y-%m-%d')
    START_DATE = (datetime.now() - timedelta(days=365*3)).strftime('%Y-%m-%d') # 지난 3년치 데이터

    run_stock_prediction(TICKER, START_DATE, END_DATE)

    # 다른 주식에 대해 실행하고 싶다면 아래 주석을 해제하고 사용하세요.
    # print("
" + "="*50 + "
")
    # TICKER_MSFT = 'MSFT'
    # run_stock_prediction(TICKER_MSFT, START_DATE, END_DATE)
