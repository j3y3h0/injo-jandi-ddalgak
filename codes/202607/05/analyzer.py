import pandas as pd

def analyze_data(df: pd.DataFrame, column: str) -> dict:
    """
    주어진 DataFrame의 특정 컬럼에 대해 기본적인 통계 분석을 수행합니다.
    
    Args:
        df (pd.DataFrame): 분석할 DataFrame입니다.
        column (str): 분석을 수행할 컬럼의 이름입니다.
        
    Returns:
        dict: 평균, 중앙값 등 통계 결과를 담고 있는 딕셔너리입니다.
    """
    if df.empty:
        print("오류: 분석할 데이터가 비어 있습니다.")
        return {}
    
    if column not in df.columns:
        print(f"오류: DataFrame에 '{column}' 컬럼이 존재하지 않습니다.")
        return {}
        
    if not pd.api.types.is_numeric_dtype(df[column]):
        print(f"경고: '{column}' 컬럼은 숫자형 데이터가 아니므로 통계 분석이 제한될 수 있습니다.")
        # 숫자형이 아닌 경우를 위해 카테고리별 개수를 분석에 추가할 수 있습니다.
        # return {"counts": df[column].value_counts().to_dict()}
    
    print(f"'{column}' 컬럼에 대한 데이터 분석을 수행합니다.")
    analysis_results = {
        "평균": df[column].mean(),
        "중앙값": df[column].median(),
        "최소값": df[column].min(),
        "최대값": df[column].max(),
        "표준편차": df[column].std()
    }
    return analysis_results

if __name__ == "__main__":
    # 예시 사용법
    from data_loader import load_data
    
    sample_df = load_data("data.csv")
    if not sample_df.empty:
        analysis = analyze_data(sample_df, "Value")
        print("
분석 결과:")
        for key, value in analysis.items():
            print(f"{key}: {value:.2f}")

        category_analysis = analyze_data(sample_df, "Category") # 비숫자형 컬럼 분석 예시
        if category_analysis:
            print("
카테고리 컬럼 분석 결과 (값의 분포):")
            print(sample_df["Category"].value_counts())
