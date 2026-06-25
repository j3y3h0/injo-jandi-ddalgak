# -*- coding: utf-8 -*-
import pandas as pd
import numpy as np

def load_sales_data():
    """
    가상의 판매 데이터를 생성하고 DataFrame으로 반환한다.
    """
    np.random.seed(42)
    dates = pd.date_range(start='2023-01-01', periods=100, freq='D')
    products = ['노트북', '스마트폰', '태블릿', '모니터']
    regions = ['서울', '경기', '부산', '대구']

    data = {
        '날짜': np.random.choice(dates, 1000),
        '제품': np.random.choice(products, 1000, p=[0.4, 0.3, 0.2, 0.1]),
        '지역': np.random.choice(regions, 1000, p=[0.4, 0.3, 0.2, 0.1]),
        '판매량': np.random.randint(1, 50, 1000),
        '가격': np.random.randint(100000, 1000000, 1000)
    }
    df = pd.DataFrame(data)
    df['총판매액'] = df['판매량'] * df['가격']
    return df

if __name__ == '__main__':
    df = load_sales_data()
    print("가상 판매 데이터의 상위 5개 행:")
    print(df.head())
    print("
데이터 정보:")
    print(df.info())
