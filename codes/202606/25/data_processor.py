# -*- coding: utf-8 -*-
import pandas as pd

def process_sales_data(df: pd.DataFrame):
    """
    판매 데이터를 처리하여 제품별, 지역별 총 판매액을 계산한다.

    Args:
        df (pd.DataFrame): 원본 판매 데이터.

    Returns:
        tuple: 제품별 총 판매액 DataFrame과 지역별 총 판매액 DataFrame.
    """
    # 제품별 총 판매액 계산
    product_sales = df.groupby('제품')['총판매액'].sum().reset_index()
    product_sales = product_sales.sort_values(by='총판매액', ascending=False)

    # 지역별 총 판매액 계산
    region_sales = df.groupby('지역')['총판매액'].sum().reset_index()
    region_sales = region_sales.sort_values(by='총판매액', ascending=False)

    return product_sales, region_sales

if __name__ == '__main__':
    from data_loader import load_sales_data
    df = load_sales_data()
    product_sales_df, region_sales_df = process_sales_data(df)
    print("
제품별 총 판매액:")
    print(product_sales_df)
    print("
지역별 총 판매액:")
    print(region_sales_df)
