import pandas as pd
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
import numpy as np

def preprocess_data(df: pd.DataFrame) -> tuple[pd.DataFrame, ColumnTransformer]:
    """
    판매 데이터를 전처리하여 모델 학습에 적합한 형태로 만듭니다.
    날짜 관련 피처 생성, 범주형 변수 원-핫 인코딩 등을 포함합니다.

    Args:
        df (pd.DataFrame): 전처리할 원본 데이터프레임. 'date' 컬럼이 포함되어야 합니다.

    Returns:
        tuple[pd.DataFrame, ColumnTransformer]: 전처리된 데이터프레임과 피처 변환을 위한 ColumnTransformer 객체.
    """
    df_processed = df.copy()

    # 'date' 컬럼을 datetime 객체로 변환
    if 'date' in df_processed.columns and not pd.api.types.is_datetime64_any_dtype(df_processed['date']):
        df_processed['date'] = pd.to_datetime(df_processed['date'])

    # 날짜 관련 피처 생성
    df_processed['year'] = df_processed['date'].dt.year
    df_processed['month'] = df_processed['date'].dt.month
    df_processed['day'] = df_processed['date'].dt.day
    df_processed['day_of_week'] = df_processed['date'].dt.dayofweek # 월=0, 일=6
    df_processed['day_of_year'] = df_processed['date'].dt.dayofyear
    df_processed['week_of_year'] = df_processed['date'].dt.isocalendar().week.astype(int)
    df_processed['quarter'] = df_processed['date'].dt.quarter

    # 시간 지연(Lag) 피처 생성 (예: 이전 7일간의 평균 판매량)
    # 데이터가 날짜별로 정렬되어 있다고 가정
    df_processed = df_processed.sort_values(by='date')
    df_processed['sales_lag_7'] = df_processed.groupby('product_id')['sales'].transform(lambda x: x.shift(7))
    df_processed['sales_lag_30'] = df_processed.groupby('product_id')['sales'].transform(lambda x: x.shift(30))
    df_processed['sales_rolling_mean_7'] = df_processed.groupby('product_id')['sales'].transform(lambda x: x.rolling(window=7, min_periods=1).mean().shift(1))

    # 결측값 처리 (여기서는 시프트로 인해 생긴 NaN을 0으로 채움, 실제로는 더 정교한 방법 사용 가능)
    df_processed.fillna(0, inplace=True)

    # 범주형 변수와 수치형 변수 정의
    categorical_features = ['product_id', 'region']
    numerical_features = [
        'year', 'month', 'day', 'day_of_week', 'day_of_year', 'week_of_year', 'quarter',
        'promotion', 'sales_lag_7', 'sales_lag_30', 'sales_rolling_mean_7'
    ]

    # ColumnTransformer를 사용하여 범주형 변수 원-핫 인코딩
    preprocessor = ColumnTransformer(
        transformers=[
            ('cat', OneHotEncoder(handle_unknown='ignore'), categorical_features)],
        remainder='passthrough' # 나머지 컬럼 (수치형)은 그대로 유지
    )
    
    # 파이프라인 생성 (데이터프레임 형태를 유지하기 위해, 실제 변환은 나중에 수행)
    # 여기서는 preprocessor를 반환하여 나중에 fit_transform을 호출하도록 합니다.
    # 실제로 변환된 데이터프레임을 반환하려면, 다음처럼 할 수 있습니다.
    # transformed_data = preprocessor.fit_transform(df_processed)
    # column_names = preprocessor.get_feature_names_out()
    # df_transformed = pd.DataFrame(transformed_data, columns=column_names)
    # 하지만 여기서는 모델 훈련 시점에 fit_transform을 호출하는 것이 일반적이므로,
    # preprocessor 객체 자체를 반환하고 수치형 피처 리스트를 포함합니다.
    
    # 모델 훈련에 사용될 최종 피처 리스트 (date, sales 제외)
    features_for_model = [col for col in numerical_features if col in df_processed.columns] + categorical_features
    
    return df_processed, preprocessor, features_for_model

if __name__ == '__main__':
    # 테스트를 위한 더미 데이터 로드
    from data_loader import load_sales_data
    dummy_df = load_sales_data()
    
    print("원본 더미 데이터 헤드:")
    print(dummy_df.head())
    print("
원본 더미 데이터 정보:")
    dummy_df.info()

    processed_df_raw, preprocessor_obj, features_list = preprocess_data(dummy_df)
    
    print("
전처리된 데이터 (날짜 피처 및 lag 피처 추가):")
    print(processed_df_raw.head())
    print(f"
모델 학습에 사용될 피처 목록: {features_list}")

    # 실제 변환 예시 (훈련 시점에 수행될 작업)
    # X = processed_df_raw[features_list]
    # y = processed_df_raw['sales']
    # X_transformed = preprocessor_obj.fit_transform(X)
    # print(f"
변환된 피처 데이터 형태: {X_transformed.shape}")
    # print(f"변환된 피처 데이터 미리보기 (일부): 
{X_transformed[:5, :5]}")
