# -*- coding: utf-8 -*-
from data_loader import load_sales_data
from data_processor import process_sales_data
from visualizer import plot_sales_data

def main():
    """
    주요 분석 및 시각화 프로세스를 실행한다.
    1. 판매 데이터를 로드한다.
    2. 로드된 데이터를 처리하여 제품별/지역별 판매액을 계산한다.
    3. 계산된 데이터를 기반으로 시각화 차트를 생성하고 파일로 저장한다.
    """
    print("1. 판매 데이터 로드 중...")
    sales_df = load_sales_data()
    print(f"총 {len(sales_df)} 건의 데이터가 로드되었습니다.")

    print("
2. 판매 데이터 처리 중 (제품별, 지역별 판매액 계산)...")
    product_sales, region_sales = process_sales_data(sales_df)
    print("데이터 처리가 완료되었습니다.")

    print("
3. 판매 데이터 시각화 및 파일 저장 중...")
    plot_sales_data(product_sales, region_sales)
    print("시각화 차트 저장이 완료되었습니다.")
    print("
분석 프로세스가 성공적으로 완료되었습니다.")

if __name__ == '__main__':
    main()
