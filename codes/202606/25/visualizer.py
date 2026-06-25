# -*- coding: utf-8 -*-
import matplotlib.pyplot as plt
import pandas as pd
import os

def plot_sales_data(product_sales: pd.DataFrame, region_sales: pd.DataFrame):
    """
    제품별 및 지역별 판매 데이터를 막대 그래프로 시각화한다.

    Args:
        product_sales (pd.DataFrame): 제품별 총 판매액 DataFrame.
        region_sales (pd.DataFrame): 지역별 총 판매액 DataFrame.
    """
    plt.rcParams['font.family'] = 'Malgun Gothic' # Windows 한글 폰트 설정
    plt.rcParams['axes.unicode_minus'] = False # 마이너스 폰트 깨짐 방지

    # 제품별 판매액 그래프
    plt.figure(figsize=(10, 6))
    plt.bar(product_sales['제품'], product_sales['총판매액'], color='skyblue')
    plt.title('제품별 총 판매액')
    plt.xlabel('제품')
    plt.ylabel('총 판매액 (원)')
    plt.xticks(rotation=45)
    plt.tight_layout()
    plt.savefig('product_sales_bar_chart.png')
    plt.close() # 그래프 창 닫기

    # 지역별 판매액 그래프
    plt.figure(figsize=(10, 6))
    plt.bar(region_sales['지역'], region_sales['총판매액'], color='lightcoral')
    plt.title('지역별 총 판매액')
    plt.xlabel('지역')
    plt.ylabel('총 판매액 (원)')
    plt.xticks(rotation=45)
    plt.tight_layout()
    plt.savefig('region_sales_bar_chart.png')
    plt.close() # 그래프 창 닫기

    print("차트가 'product_sales_bar_chart.png' 및 'region_sales_bar_chart.png' 파일로 저장되었습니다.")

if __name__ == '__main__':
    # 테스트를 위한 가상 데이터 생성
    data_product = {'제품': ['노트북', '스마트폰', '태블릿'], '총판매액': [150000000, 120000000, 80000000]}
    product_df = pd.DataFrame(data_product)

    data_region = {'지역': ['서울', '경기', '부산'], '총판매액': [200000000, 100000000, 50000000]}
    region_df = pd.DataFrame(data_region)

    plot_sales_data(product_df, region_df)
