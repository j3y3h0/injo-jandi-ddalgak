# analyzer.py
import pandas as pd

def calculate_moving_average(data: pd.DataFrame, window: int = 20) -> pd.DataFrame:
    """
    주어진 주식 데이터의 종가에 대한 이동 평균을 계산합니다.

    Args:
        data (pd.DataFrame): 주식 데이터 (반드시 'Close' 컬럼을 포함해야 합니다).
        window (int): 이동 평균을 계산할 기간 (기본값: 20일).

    Returns:
        pd.DataFrame: 이동 평균 컬럼이 추가된 주식 데이터 DataFrame.
                      입력 데이터에 'Close' 컬럼이 없으면 빈 DataFrame을 반환합니다.
    """
    if 'Close' not in data.columns:
        print("데이터에 'Close' 컬럼이 없어 이동 평균을 계산할 수 없습니다.")
        return pd.DataFrame()

    try:
        # 'Close' 컬럼을 사용하여 이동 평균 계산
        data[f'MA_{window}'] = data['Close'].rolling(window=window).mean()
        return data
    except Exception as e:
        print(f"이동 평균 계산 중 오류 발생: {e}")
        return pd.DataFrame()

if __name__ == '__main__':
    # 예시 사용법 (가상 데이터 생성)
    sample_data = {
        'Date': pd.to_datetime(['2023-01-01', '2023-01-02', '2023-01-03', '2023-01-04', '2023-01-05',
                                '2023-01-06', '2023-01-07', '2023-01-08', '2023-01-09', '2023-01-10']),
        'Close': [100, 102, 101, 105, 103, 106, 108, 107, 110, 109]
    }
    sample_df = pd.DataFrame(sample_data).set_index('Date')

    print("원본 데이터:")
    print(sample_df)

    # 3일 이동 평균 계산
    df_with_ma = calculate_moving_average(sample_df, window=3)
    if not df_with_ma.empty:
        print("
3일 이동 평균이 추가된 데이터:")
        print(df_with_ma)
