import pandas as pd

def calculate_basic_statistics(df, column_name):
    """
    주어진 DataFrame의 특정 컬럼에 대한 기본적인 통계량(평균, 중앙값)을 계산합니다.

    Args:
        df (pandas.DataFrame): 분석할 DataFrame.
        column_name (str): 통계량을 계산할 컬럼의 이름.

    Returns:
        dict: 평균(mean)과 중앙값(median)을 포함하는 딕셔너리.
              컬럼이 없거나 데이터가 유효하지 않으면 None을 반환.
    """
    if df is None or df.empty:
        print("분석할 데이터프레임이 비어있거나 유효하지 않습니다.")
        return None

    if column_name not in df.columns:
        print(f"오류: '{column_name}' 컬럼을 데이터프레임에서 찾을 수 없습니다.")
        return None

    # 숫자형 데이터만 선택하여 통계 계산
    numeric_data = pd.to_numeric(df[column_name], errors='coerce').dropna()

    if numeric_data.empty:
        print(f"'{column_name}' 컬럼에 유효한 숫자 데이터가 없습니다.")
        return None

    mean_value = numeric_data.mean()
    median_value = numeric_data.median()

    return {
        "평균": mean_value,
        "중앙값": median_value
    }

def group_and_sum(df, group_column, sum_column):
    """
    주어진 DataFrame을 특정 컬럼으로 그룹화하고, 다른 컬럼의 합계를 계산합니다.

    Args:
        df (pandas.DataFrame): 분석할 DataFrame.
        group_column (str): 그룹화할 컬럼의 이름.
        sum_column (str): 합계를 계산할 컬럼의 이름.

    Returns:
        pandas.Series: 그룹별 합계가 담긴 Series.
                       컬럼이 없거나 데이터가 유효하지 않으면 None을 반환.
    """
    if df is None or df.empty:
        print("그룹화할 데이터프레임이 비어있거나 유효하지 않습니다.")
        return None

    if group_column not in df.columns or sum_column not in df.columns:
        print(f"오류: '{group_column}' 또는 '{sum_column}' 컬럼을 데이터프레임에서 찾을 수 없습니다.")
        return None

    # 숫자형 데이터만 선택하여 합계 계산
    df[sum_column] = pd.to_numeric(df[sum_column], errors='coerce')
    grouped_data = df.groupby(group_column)[sum_column].sum().dropna()

    if grouped_data.empty:
        print(f"'{group_column}' 기준으로 그룹화된 '{sum_column}'의 유효한 합계 데이터가 없습니다.")
        return None

    return grouped_data
