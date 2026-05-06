# data_fetcher.py
import yfinance as yf
import pandas as pd

def fetch_stock_data(ticker: str, start_date: str, end_date: str) -> pd.DataFrame:
    """
    지정된 티커, 시작일, 종료일로 주식 데이터를 가져옵니다.

    Args:
        ticker (str): 주식 티커 심볼 (예: 'AAPL').
        start_date (str): 데이터 조회 시작일 (예: '2023-01-01').
        end_date (str): 데이터 조회 종료일 (예: '2023-12-31').

    Returns:
        pd.DataFrame: 가져온 주식 데이터. 오류 발생 시 빈 DataFrame을 반환합니다.
    """
    try:
        data = yf.download(ticker, start=start_date, end=end_date)
        if data.empty:
            print(f"[{ticker}]에 대한 데이터를 찾을 수 없습니다. 날짜 범위를 확인하세요.")
        return data
    except Exception as e:
        print(f"데이터를 가져오는 중 오류 발생: {e}")
        return pd.DataFrame()

if __name__ == '__main__':
    # 예시 사용법
    sample_ticker = 'AAPL'
    sample_start = '2023-01-01'
    sample_end = '2023-12-31'
    aapl_data = fetch_stock_data(sample_ticker, sample_start, sample_end)
    if not aapl_data.empty:
        print(f"{sample_ticker} 데이터 미리보기:")
        print(aapl_data.head())
