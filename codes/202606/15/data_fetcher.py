# data_fetcher.py
import yfinance as yf
import pandas as pd
from datetime import datetime

def fetch_stock_data(ticker: str, start_date: str, end_date: str) -> pd.DataFrame:
    """
    지정된 주식 티커와 기간에 대한 과거 주식 데이터를 Yahoo Finance에서 가져옵니다.

    Args:
        ticker (str): 주식 심볼 (예: 'AAPL', 'MSFT').
        start_date (str): 데이터 시작 날짜 (YYYY-MM-DD 형식).
        end_date (str): 데이터 종료 날짜 (YYYY-MM-DD 형식).

    Returns:
        pd.DataFrame: 가져온 주식 데이터 (날짜, 시가, 고가, 저가, 종가, 거래량).
    """
    try:
        data = yf.download(ticker, start=start_date, end=end_date)
        if data.empty:
            print(f"[{ticker}]에 대한 데이터를 가져오지 못했습니다. 티커 또는 날짜를 확인해주세요.")
            return pd.DataFrame()
        print(f"[{ticker}]에 대한 {start_date}부터 {end_date}까지의 주식 데이터를 성공적으로 가져왔습니다.")
        return data
    except Exception as e:
        print(f"데이터를 가져오는 중 오류 발생: {e}")
        return pd.DataFrame()

if __name__ == '__main__':
    # 테스트를 위한 예제 코드
    sample_ticker = 'AAPL'
    sample_start = '2023-01-01'
    sample_end = '2023-12-31'
    
    df = fetch_stock_data(sample_ticker, sample_start, sample_end)
    if not df.empty:
        print("
가져온 데이터의 처음 5행:")
        print(df.head())
        print("
가져온 데이터의 마지막 5행:")
        print(df.tail())
        print(f"
총 데이터 행 수: {len(df)}")
