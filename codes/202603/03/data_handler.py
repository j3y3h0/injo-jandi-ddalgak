import pandas as pd

def load_data(file_path):
    """
    지정된 경로의 CSV 파일을 읽어 Pandas DataFrame으로 반환합니다.

    Args:
        file_path (str): CSV 파일의 경로.

    Returns:
        pandas.DataFrame: 로드된 데이터가 담긴 DataFrame.
    """
    try:
        df = pd.read_csv(file_path)
        print(f"'{file_path}' 파일이 성공적으로 로드되었습니다.")
        return df
    except FileNotFoundError:
        print(f"오류: '{file_path}' 파일을 찾을 수 없습니다.")
        return None
    except Exception as e:
        print(f"데이터 로드 중 오류 발생: {e}")
        return None

