# main.py
from data_fetcher import fetch_stock_data
from analyzer import calculate_moving_average
import pandas as pd

def main():
    """
    주식 데이터를 가져오고 이동 평균을 계산하여 출력하는 메인 함수입니다.
    """
    # 분석할 주식 티커와 기간 설정
    ticker = 'AAPL'  # 예시: Apple 주식
    start_date = '2023-01-01'
    end_date = '2023-12-31'
    ma_window = 20 # 이동 평균 기간 (예: 20일 이동 평균)

    print(f"[{ticker}] 주식 데이터 ({start_date} ~ {end_date}) 가져오는 중...")
    stock_data = fetch_stock_data(ticker, start_date, end_date)

    if not stock_data.empty:
        print(f"
가져온 [{ticker}] 데이터의 처음 5개 행:")
        print(stock_data.head())

        print(f"
[{ticker}] 데이터에 {ma_window}일 이동 평균 계산 중...")
        data_with_ma = calculate_moving_average(stock_data.copy(), ma_window) # 복사본 사용

        if not data_with_ma.empty:
            print(f"
[{ticker}] 데이터의 마지막 5개 행과 {ma_window}일 이동 평균:")
            # 'Close'와 계산된 이동 평균 컬럼만 선택하여 출력
            print(data_with_ma[['Close', f'MA_{ma_window}']].tail())
        else:
            print("이동 평균 계산에 실패했습니다.")
    else:
        print("주식 데이터를 가져오는 데 실패했습니다. 프로그램을 종료합니다.")

if __name__ == '__main__':
    main()
