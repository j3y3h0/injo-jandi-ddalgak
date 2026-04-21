# -*- coding: utf-8 -*-
import pandas as pd

def load_data(file_path):
    """
    지정된 경로에서 CSV 파일을 로드합니다.
    Args:
        file_path (str): CSV 파일의 경로.
    Returns:
        pd.DataFrame: 로드된 데이터프레임.
    """
    try:
        df = pd.read_csv(file_path)
        print(f"INFO: '{file_path}' 파일 로드 성공.")
        return df
    except FileNotFoundError:
        print(f"ERROR: '{file_path}' 파일을 찾을 수 없습니다.")
        return None
    except Exception as e:
        print(f"ERROR: 파일 로드 중 오류 발생 - {e}")
        return None

def preprocess_data(df):
    """
    데이터프레임을 전처리합니다.
    'Date' 컬럼을 datetime 형식으로 변환하고, 필요한 경우 다른 전처리 작업을 수행합니다.
    Args:
        df (pd.DataFrame): 전처리할 데이터프레임.
    Returns:
        pd.DataFrame: 전처리된 데이터프레임.
    """
    if df is None:
        return None
    
    # 'Date' 컬럼을 datetime 형식으로 변환
    if 'Date' in df.columns:
        df['Date'] = pd.to_datetime(df['Date'])
        print("INFO: 'Date' 컬럼을 datetime 형식으로 변환했습니다.")
    else:
        print("WARNING: 'Date' 컬럼이 존재하지 않습니다.")
    
    # 예시: 'Value' 컬럼의 결측치 처리 (여기서는 결측치가 없다고 가정)
    # df['Value'].fillna(df['Value'].mean(), inplace=True)
    
    return df

if __name__ == '__main__':
    # 모듈 테스트를 위한 예시
    sample_df = load_data('sample_data.csv')
    if sample_df is not None:
        print("
원본 데이터:")
        print(sample_df.head())
        
        processed_df = preprocess_data(sample_df.copy())
        if processed_df is not None:
            print("
전처리된 데이터:")
            print(processed_df.info())
            print(processed_df.head())
