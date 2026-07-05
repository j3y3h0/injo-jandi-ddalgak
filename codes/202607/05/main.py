from data_loader import load_data
from analyzer import analyze_data
from visualizer import plot_data
import pandas as pd

def run_analysis_workflow():
    """
    데이터 로딩, 분석, 시각화 전체 워크플로우를 실행합니다.
    """
    data_file = "data.csv"
    
    print("--- 데이터 분석 워크플로우 시작 ---")
    
    # 1. 데이터 로드
    print(f"
1. '{data_file}' 파일로부터 데이터를 로드합니다.")
    df = load_data(data_file)
    
    if df.empty:
        print("데이터 로드에 실패하여 워크플로우를 종료합니다.")
        return
        
    print("
로드된 데이터 상위 5개 행:")
    print(df.head())
    
    # 2. 데이터 분석
    analysis_column = "Value"
    print(f"
2. '{analysis_column}' 컬럼에 대한 통계 분석을 수행합니다.")
    analysis_results = analyze_data(df, analysis_column)
    
    if analysis_results:
        print("
분석 결과:")
        for key, value in analysis_results.items():
            print(f"- {key}: {value:.2f}")
    else:
        print("데이터 분석에 실패했습니다.")

    # 3. 데이터 시각화
    output_plot_file = "output_plot.png"
    print(f"
3. 데이터를 시각화하고 '{output_plot_file}' 파일로 저장합니다.")
    plot_data(df, "Category", analysis_column, output_plot_file)
    
    print("
--- 데이터 분석 워크플로우 완료 ---")

if __name__ == "__main__":
    run_analysis_workflow()
