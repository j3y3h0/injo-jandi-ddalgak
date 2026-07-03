import pandas as pd

def load_data(file_path):
    """
    지정된 CSV 파일로부터 데이터를 로드합니다.
    Args:
        file_path (str): 로드할 CSV 파일의 경로입니다.
    Returns:
        pandas.DataFrame: 로드된 데이터프레임입니다.
    """
    try:
        # '날짜' 컬럼을 datetime 형식으로 파싱하여 인덱스로 설정합니다.
        # 인코딩 문제 발생 시 'encoding' 파라미터 추가 고려 (예: encoding='cp949' 또는 'euc-kr')
        df = pd.read_csv(file_path, encoding='utf-8')
        df['날짜'] = pd.to_datetime(df['날짜'])
        df = df.set_index('날짜')
        print(f"데이터 로드 성공: {file_path}")
        return df
    except FileNotFoundError:
        print(f"오류: 파일을 찾을 수 없습니다 - {file_path}")
        return None
    except Exception as e:
        print(f"데이터 로드 중 오류 발생: {e}")
        return None

if __name__ == "__main__":
    # 이 모듈이 단독으로 실행될 때 테스트를 위한 코드입니다.
    # 실제 프로젝트에서는 main.py에서 호출됩니다.
    sample_df = load_data('sample_data.csv')
    if sample_df is not None:
        print("
로드된 데이터 미리보기:")
        print(sample_df.head())
        print("
데이터 정보:")
        sample_df.info()