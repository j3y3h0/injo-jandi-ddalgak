import pandas as pd
import matplotlib.pyplot as plt
import os

def plot_data(df: pd.DataFrame, x_column: str, y_column: str, output_filename: str = "output_plot.png"):
    """
    주어진 DataFrame의 데이터를 사용하여 그래프를 생성하고 파일로 저장합니다.
    이 함수는 'Category'와 'Value' 컬럼을 사용하여 막대 그래프를 생성합니다.
    
    Args:
        df (pd.DataFrame): 시각화할 DataFrame입니다.
        x_column (str): x축으로 사용할 컬럼 이름입니다.
        y_column (str): y축으로 사용할 컬럼 이름입니다.
        output_filename (str): 생성될 이미지 파일의 이름입니다.
    """
    if df.empty:
        print("오류: 시각화할 데이터가 비어 있습니다.")
        return
    
    if x_column not in df.columns or y_column not in df.columns:
        print(f"오류: DataFrame에 '{x_column}' 또는 '{y_column}' 컬럼이 존재하지 않습니다.")
        return

    # 카테고리별 값의 평균을 계산하여 막대 그래프 생성
    plot_data = df.groupby(x_column)[y_column].mean().reset_index()

    plt.figure(figsize=(10, 6))
    plt.bar(plot_data[x_column], plot_data[y_column], color='skyblue')
    plt.xlabel(x_column)
    plt.ylabel(y_column)
    plt.title(f'{x_column}별 {y_column} 평균')
    plt.grid(axis='y', linestyle='--', alpha=0.7)
    
    # 그래프 저장
    try:
        plt.savefig(output_filename)
        print(f"그래프가 '{os.path.abspath(output_filename)}' 파일로 성공적으로 저장되었습니다.")
    except Exception as e:
        print(f"그래프 저장 중 오류가 발생했습니다: {e}")
    finally:
        plt.close() # 메모리 해제

if __name__ == "__main__":
    # 예시 사용법
    from data_loader import load_data
    
    sample_df = load_data("data.csv")
    if not sample_df.empty:
        plot_data(sample_df, "Category", "Value")
