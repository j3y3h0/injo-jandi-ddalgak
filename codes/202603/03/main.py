import pandas as pd
from data_handler import load_data
from analysis_functions import calculate_basic_statistics, group_and_sum

def main():
    """
    메인 함수: 데이터를 로드하고, 분석을 수행한 후 결과를 출력합니다.
    """
    file_path = "sample_data.csv"
    df = load_data(file_path)

    if df is not None:
        print("
--- 기본적인 통계 분석 (컬럼 '값') ---")
        statistics = calculate_basic_statistics(df, '값')
        if statistics:
            print(f"  평균: {statistics['평균']:.2f}")
            print(f"  중앙값: {statistics['중앙값']:.2f}")
        else:
            print("  통계 계산에 실패했습니다.")

        print("
--- 카테고리별 '수량' 합계 ---")
        category_sum = group_and_sum(df, '카테고리', '수량')
        if category_sum is not None:
            print(category_sum)
        else:
            print("  그룹별 합계 계산에 실패했습니다.")
    else:
        print("데이터 로드에 실패하여 분석을 수행할 수 없습니다.")

if __name__ == "__main__":
    main()

