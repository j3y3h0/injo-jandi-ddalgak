import matplotlib.pyplot as plt
import pandas as pd

def plot_data(df, title="데이터 시각화", x_label="날짜", y_label="값", output_filename="data_visualization.png"):
    """
    데이터프레임을 기반으로 라인 플롯을 생성하고 이미지 파일로 저장합니다.
    Args:
        df (pandas.DataFrame): 시각화할 데이터프레임입니다. '값' 컬럼을 포함해야 합니다.
        title (str): 그래프의 제목입니다.
        x_label (str): X축 레이블입니다.
        y_label (str): Y축 레이블입니다.
        output_filename (str): 저장될 이미지 파일의 이름입니다.
    """
    if '값' not in df.columns:
        print("오류: 데이터프레임에 '값' 컬럼이 없습니다. 시각화를 건너뜁니다.")
        return

    plt.figure(figsize=(10, 6)) # 그래프 크기 설정
    plt.plot(df.index, df['값'], marker='o', linestyle='-') # 라인 플롯 생성
    plt.title(title, fontsize=16) # 제목 설정
    plt.xlabel(x_label, fontsize=12) # X축 레이블 설정
    plt.ylabel(y_label, fontsize=12) # Y축 레이블 설정
    plt.grid(True) # 그리드 표시
    plt.tight_layout() # 레이아웃 자동 조정

    # 한글 폰트 설정 (Mac, Windows, Linux 환경에 따라 다를 수 있음)
    try:
        plt.rcParams['font.family'] = 'Malgun Gothic' # Windows
        plt.rcParams['axes.unicode_minus'] = False # 마이너스 폰트 깨짐 방지
    except:
        try:
            plt.rcParams['font.family'] = 'AppleGothic' # Mac
            plt.rcParams['axes.unicode_minus'] = False
        except:
            print("경고: 한글 폰트 설정에 실패했습니다. 기본 폰트로 표시됩니다.")


    try:
        plt.savefig(output_filename) # 그래프를 이미지 파일로 저장
        print(f"시각화 결과가 '{output_filename}'으로 저장되었습니다.")
    except Exception as e:
        print(f"시각화 결과를 저장하는 중 오류 발생: {e}")
    plt.close() # 그래프 창 닫기 (메모리 해제)

if __name__ == "__main__":
    # 이 모듈이 단독으로 실행될 때 테스트를 위한 코드입니다.
    # 실제 프로젝트에서는 main.py에서 호출됩니다.
    # 가상의 데이터프레임 생성
    dates = pd.to_datetime(['2023-01-01', '2023-01-02', '2023-01-03'])
    values = [10, 15, 12]
    test_df = pd.DataFrame({'값': values}, index=dates)
    test_df.index.name = '날짜'

    print("테스트 데이터프레임:")
    print(test_df)
    plot_data(test_df, title="테스트 시각화", output_filename="test_visualization.png")