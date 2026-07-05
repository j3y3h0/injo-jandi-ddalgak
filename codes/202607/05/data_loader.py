import pandas as pd

def load_data(file_path: str) -> pd.DataFrame:
    """
    CSV 파일로부터 데이터를 로드합니다.
    
    Args:
        file_path (str): 로드할 CSV 파일의 경로입니다.
        
    Returns:
        pd.DataFrame: 로드된 데이터를 담고 있는 pandas DataFrame 객체입니다.
    """
    try:
        df = pd.read_csv(file_path)
        print(f"'{file_path}' 파일로부터 데이터를 성공적으로 로드하였습니다.")
        return df
    except FileNotFoundError:
        print(f"오류: '{file_path}' 파일을 찾을 수 없습니다.")
        return pd.DataFrame()
    except Exception as e:
        print(f"데이터 로드 중 오류가 발생했습니다: {e}")
        return pd.DataFrame()

if __name__ == "__main__":
    # 예시 사용법
    sample_df = load_data("data.csv")
    if not sample_df.empty:
        print("
로드된 데이터 미리보기:")
        print(sample_df.head())
