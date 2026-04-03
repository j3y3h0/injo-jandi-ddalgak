import pandas as pd
import numpy as np

def load_sales_data(file_path: str = None) -> pd.DataFrame:
    """
    판매 데이터를 로드하고 초기 정제를 수행합니다.
    파일 경로가 제공되지 않으면 더미 데이터를 생성합니다.

    Args:
        file_path (str, optional): CSV 파일의 경로. Defaults to None.

    Returns:
        pd.DataFrame: 로드되거나 생성된 판매 데이터.
    """
    if file_path:
        try:
            df = pd.read_csv(file_path)
            print(f"'{file_path}'에서 데이터를 성공적으로 로드했습니다.")
        except FileNotFoundError:
            print(f"경고: '{file_path}' 파일을 찾을 수 없습니다. 더미 데이터를 생성합니다.")
            df = _generate_dummy_data()
    else:
        print("파일 경로가 제공되지 않았습니다. 더미 데이터를 생성합니다.")
        df = _generate_dummy_data()

    # 데이터프레임 컬럼명 정제 (예: 공백 제거, 소문자 변환)
    df.columns = df.columns.str.strip().str.lower().str.replace(' ', '_')

    # 필요한 경우 추가적인 초기 정제 로직을 여기에 추가할 수 있습니다.
    # 예: 결측치 처리, 날짜 컬럼 형식 변환 등

    return df

def _generate_dummy_data() -> pd.DataFrame:
    """
    판매 예측을 위한 더미 데이터를 생성합니다.
    날짜, 제품 ID, 지역, 프로모션 여부, 판매량 컬럼을 포함합니다.
    """
    num_samples = 1000
    start_date = pd.to_datetime('2023-01-01')
    dates = pd.date_range(start=start_date, periods=num_samples, freq='D')
    
    data = {
        'date': dates,
        'product_id': np.random.choice([f'P{i:03d}' for i in range(1, 11)], num_samples),
        'region': np.random.choice(['Seoul', 'Busan', 'Daegu', 'Gwangju'], num_samples),
        'promotion': np.random.choice([0, 1], num_samples, p=[0.7, 0.3]),
        'sales': np.random.randint(100, 1000, num_samples) + np.random.randint(0, 200, num_samples) * (dates.month % 12 + 1) # 월별 트렌드 추가
    }
    df = pd.DataFrame(data)
    return df

if __name__ == '__main__':
    # 예시 사용법
    # 1. 파일 없이 더미 데이터 로드
    df_dummy = load_sales_data()
    print("
더미 데이터 미리보기:")
    print(df_dummy.head())
    print(f"더미 데이터 크기: {df_dummy.shape}")

    # 2. 존재하지 않는 파일 경로로 시도 (더미 데이터 생성)
    df_non_existent = load_sales_data("non_existent_file.csv")
    print("
존재하지 않는 파일 경로 시도 (더미 데이터 생성):")
    print(df_non_existent.head())

    # 3. 실제 파일이 있다면 다음과 같이 사용
    # 예: fake_sales_data.csv 파일을 미리 생성했다고 가정
    # df_real = load_sales_data("fake_sales_data.csv")
    # print("
실제 파일 데이터 미리보기:")
    # print(df_real.head())
