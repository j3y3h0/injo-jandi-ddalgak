from data_loader import load_data
from visualizer import plot_data
import pandas as pd

def main():
    """
    메인 함수: 데이터를 로드하고 분석한 후 시각화합니다.
    """
    file_path = 'sample_data.csv'
    
    # 1. 데이터 로드
    df = load_data(file_path)

    if df is None:
        print("데이터 로드에 실패하여 프로그램을 종료합니다.")
        return

    print("
--- 데이터 로드 완료 ---")
    print(df.head())

    # 2. 간단한 데이터 분석
    print("
--- 데이터 통계 분석 ---")
    print(df['값'].describe())
    print(f"최대 값: {df['값'].max()}")
    print(f"최소 값: {df['값'].min()}")
    print(f"평균 값: {df['값'].mean():.2f}")

    # 3. 데이터 시각화
    output_image_name = "data_visualization.png"
    plot_data(df, title="시간에 따른 값 변화", output_filename=output_image_name)
    print(f"
시각화 결과가 '{output_image_name}' 파일로 저장되었습니다.")

if __name__ == "__main__":
    main()