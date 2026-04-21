# -*- coding: utf-8 -*-
import pandas as pd

def get_descriptive_statistics(df, column):
    """
    지정된 컬럼의 기술 통계를 계산합니다.
    Args:
        df (pd.DataFrame): 분석할 데이터프레임.
        column (str): 기술 통계를 계산할 컬럼 이름.
    Returns:
        pd.Series: 기술 통계 결과.
    """
    if df is None or column not in df.columns:
        print(f"ERROR: 데이터프레임이 유효하지 않거나 컬럼 '{column}'이 존재하지 않습니다.")
        return None
    
    print(f"INFO: 컬럼 '{column}'의 기술 통계를 계산합니다.")
    return df[column].describe()

def analyze_by_category(df, category_column, value_column):
    """
    지정된 범주형 컬럼별로 값 컬럼의 평균을 분석합니다.
    Args:
        df (pd.DataFrame): 분석할 데이터프레임.
        category_column (str): 범주형 컬럼 이름.
        value_column (str): 분석할 값 컬럼 이름.
    Returns:
        pd.Series: 범주별 평균.
    """
    if df is None or category_column not in df.columns or value_column not in df.columns:
        print(f"ERROR: 데이터프레임이 유효하지 않거나 컬럼 '{category_column}' 또는 '{value_column}'이 존재하지 않습니다.")
        return None
    
    print(f"INFO: '{category_column}'별 '{value_column}'의 평균을 분석합니다.")
    return df.groupby(category_column)[value_column].mean()

def find_top_n_values(df, value_column, n=3):
    """
    지정된 값 컬럼에서 상위 N개의 값을 찾습니다.
    Args:
        df (pd.DataFrame): 분석할 데이터프레임.
        value_column (str): 상위 값을 찾을 컬럼 이름.
        n (int): 반환할 상위 값의 개수.
    Returns:
        pd.DataFrame: 상위 N개 값의 데이터프레임.
    """
    if df is None or value_column not in df.columns:
        print(f"ERROR: 데이터프레임이 유효하지 않거나 컬럼 '{value_column}'이 존재하지 않습니다.")
        return None
    
    print(f"INFO: 컬럼 '{value_column}'에서 상위 {n}개 값을 찾습니다.")
    return df.nlargest(n, value_column)

if __name__ == '__main__':
    # 모듈 테스트를 위한 예시 데이터프레임 생성
    data = {
        'Category': ['A', 'B', 'A', 'C', 'B', 'A', 'C', 'B', 'A', 'C'],
        'Value': [100, 150, 120, 200, 180, 110, 220, 160, 130, 210],
        'Date': pd.to_datetime(['2023-01-01', '2023-01-02', '2023-01-03', '2023-01-04', '2023-01-05',
                                '2023-01-06', '2023-01-07', '2023-01-08', '2023-01-09', '2023-01-10'])
    }
    sample_df = pd.DataFrame(data)

    print("원본 데이터:")
    print(sample_df)

    print("
'Value' 컬럼 기술 통계:")
    print(get_descriptive_statistics(sample_df, 'Value'))

    print("
'Category'별 'Value' 평균:")
    print(analyze_by_category(sample_df, 'Category', 'Value'))
    
    print("
'Value' 상위 3개:")
    print(find_top_n_values(sample_df, 'Value', 3))
