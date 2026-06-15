# preprocessor.py
import pandas as pd
from sklearn.model_selection import train_test_split

def preprocess_data(df: pd.DataFrame, target_column: str = 'Close', n_steps: int = 1) -> tuple:
    """
    주식 데이터를 머신러닝 모델 학습에 적합한 형태로 전처리합니다.
    이동 평균과 같은 기술적 지표를 계산하고, 특성(X)과 타겟(y) 데이터를 생성합니다.

    Args:
        df (pd.DataFrame): Yahoo Finance에서 가져온 원본 주식 데이터.
        target_column (str): 예측하고자 하는 타겟 컬럼명 (기본값: 'Close').
        n_steps (int): 예측할 미래 단계 수 (기본값: 1, 즉 다음 날).

    Returns:
        tuple: (X_train, X_test, y_train, y_test) 훈련 및 테스트 데이터셋.
    """
    if df.empty:
        print("전처리할 데이터프레임이 비어 있습니다.")
        return pd.DataFrame(), pd.DataFrame(), pd.Series(), pd.Series()

    # 필요한 컬럼만 선택
    df = df[[target_column, 'Open', 'High', 'Low', 'Volume']].copy()

    # 결측치 제거
    df.dropna(inplace=True)
    if df.empty:
        print("결측치 제거 후 데이터프레임이 비어 있습니다.")
        return pd.DataFrame(), pd.DataFrame(), pd.Series(), pd.Series()

    # 특성 생성: 이동 평균 (SMA - Simple Moving Average)
    df['SMA_5'] = df[target_column].rolling(window=5).mean()
    df['SMA_10'] = df[target_column].rolling(window=10).mean()
    df['SMA_20'] = df[target_column].rolling(window=20).mean()

    # 특성 생성: 일일 수익률
    df['Daily_Return'] = df[target_column].pct_change() * 100

    # 타겟 변수 생성: n_steps 미래의 종가
    # 다음 날의 종가를 예측하기 위해 target_column을 n_steps만큼 쉬프트
    df['Target'] = df[target_column].shift(-n_steps)

    # 특성 및 타겟 변수 설정
    features = ['Open', 'High', 'Low', 'Volume', target_column, 'SMA_5', 'SMA_10', 'SMA_20', 'Daily_Return']
    
    # NaN 값 제거 (이동 평균 및 타겟 변수 생성으로 인해 발생)
    df.dropna(inplace=True)

    if df.empty:
        print("특성 및 타겟 생성 후 데이터프레임이 비어 있습니다.")
        return pd.DataFrame(), pd.DataFrame(), pd.Series(), pd.Series()

    X = df[features]
    y = df['Target']

    # 훈련 세트와 테스트 세트 분리
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42, shuffle=False)
    
    print(f"데이터 전처리 완료. 훈련 데이터 {len(X_train)}개, 테스트 데이터 {len(X_test)}개.")
    return X_train, X_test, y_train, y_test

if __name__ == '__main__':
    # 테스트를 위한 예제 코드
    # 가상의 주식 데이터 생성
    data = {
        'Date': pd.to_datetime(pd.date_range(start='2022-01-01', periods=100, freq='D')),
        'Open': [100 + i for i in range(100)],
        'High': [102 + i for i in range(100)],
        'Low': [98 + i for i in range(100)],
        'Close': [101 + i + (i%5) for i in range(100)],
        'Volume': [100000 - i*100 for i in range(100)]
    }
    sample_df = pd.DataFrame(data).set_index('Date')
    
    print("원본 데이터의 처음 5행:")
    print(sample_df.head())

    X_train, X_test, y_train, y_test = preprocess_data(sample_df)

    if not X_train.empty:
        print("
X_train의 처음 5행:")
        print(X_train.head())
        print("
y_train의 처음 5행:")
        print(y_train.head())
        print(f"
X_train shape: {X_train.shape}, y_train shape: {y_train.shape}")
        print(f"X_test shape: {X_test.shape}, y_test shape: {y_test.shape}")
