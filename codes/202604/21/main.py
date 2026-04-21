# -*- coding: utf-8 -*-
import pandas as pd
from data_processor import load_data, preprocess_data
from analyzer import get_descriptive_statistics, analyze_by_category, find_top_n_values

def main():
    """
    메인 데이터 분석 워크플로우를 실행합니다.
    CSV 파일을 로드하고, 전처리한 다음, 다양한 분석을 수행합니다.
    """
    file_path = 'sample_data.csv'
    
    print("===== 데이터 분석 시작 =====")
    
    # 1. 데이터 로드
    df = load_data(file_path)
    if df is None:
        print("ERROR: 데이터 로드에 실패하여 분석을 중단합니다.")
        return
    
    print("
[원본 데이터 미리보기]")
    print(df.head())
    print("
" + "="*30 + "
")

    # 2. 데이터 전처리
    processed_df = preprocess_data(df.copy())
    if processed_df is None:
        print("ERROR: 데이터 전처리에 실패하여 분석을 중단합니다.")
        return

    print("
[전처리된 데이터 정보]")
    processed_df.info()
    print("
" + "="*30 + "
")
    
    # 3. 데이터 분석
    print("
[값(Value) 컬럼 기술 통계]")
    value_stats = get_descriptive_statistics(processed_df, 'Value')
    if value_stats is not None:
        print(value_stats)
    print("
" + "="*30 + "
")

    print("
[카테고리(Category)별 값(Value) 평균 분석]")
    category_avg = analyze_by_category(processed_df, 'Category', 'Value')
    if category_avg is not None:
        print(category_avg)
    print("
" + "="*30 + "
")
    
    print("
[값(Value) 상위 3개 데이터]")
    top_values = find_top_n_values(processed_df, 'Value', 3)
    if top_values is not None:
        print(top_values)
    print("
" + "="*30 + "
")

    print("
===== 데이터 분석 완료 =====")

if __name__ == '__main__':
    main()
