import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error, mean_squared_error, r2_score
import joblib # 모델 저장을 위함
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer

def train_model(
    df: pd.DataFrame,
    preprocessor: ColumnTransformer,
    features: list,
    target: str = 'sales',
    model_path: str = 'sales_forecast_model.pkl'
) -> RandomForestRegressor:
    """
    제공된 데이터를 사용하여 판매 예측 모델을 훈련하고 저장합니다.

    Args:
        df (pd.DataFrame): 전처리된 데이터프레임.
        preprocessor (ColumnTransformer): 데이터 전처리에 사용될 ColumnTransformer 객체.
        features (list): 모델 훈련에 사용될 피처 컬럼 이름 리스트.
        target (str): 예측 대상 컬럼 이름 (기본값: 'sales').
        model_path (str): 훈련된 모델을 저장할 경로 및 파일 이름.

    Returns:
        RandomForestRegressor: 훈련된 RandomForestRegressor 모델.
    """
    print("모델 훈련을 시작합니다...")

    # 피처와 타겟 분리
    X = df[features]
    y = df[target]

    # 훈련 세트와 테스트 세트 분리
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    print(f"훈련 데이터 크기: {X_train.shape}, 테스트 데이터 크기: {X_test.shape}")

    # 모델 파이프라인 정의
    # 전처리 단계와 모델 학습 단계를 결합합니다.
    model_pipeline = Pipeline(steps=[
        ('preprocessor', preprocessor),
        ('regressor', RandomForestRegressor(n_estimators=100, random_state=42, n_jobs=-1))
    ])

    # 모델 훈련
    model_pipeline.fit(X_train, y_train)
    print("모델 훈련이 완료되었습니다.")

    # 모델 평가
    y_pred = model_pipeline.predict(X_test)
    mae = mean_absolute_error(y_test, y_pred)
    rmse = np.sqrt(mean_squared_error(y_test, y_pred))
    r2 = r2_score(y_test, y_pred)

    print(f"
모델 평가 결과:")
    print(f"  평균 절대 오차 (MAE): {mae:.2f}")
    print(f"  평균 제곱근 오차 (RMSE): {rmse:.2f}")
    print(f"  R-제곱 (R2 Score): {r2:.2f}")

    # 모델 저장
    joblib.dump(model_pipeline, model_path)
    print(f"훈련된 모델이 '{model_path}'에 저장되었습니다.")

    return model_pipeline

if __name__ == '__main__':
    # 테스트를 위한 데이터 로드 및 전처리
    from data_loader import load_sales_data
    from data_processor import preprocess_data

    # 더미 데이터 로드
    dummy_df = load_sales_data()

    # 데이터 전처리
    processed_df_raw, preprocessor_obj, features_list = preprocess_data(dummy_df)
    
    # 모델 훈련 및 저장
    trained_model = train_model(processed_df_raw, preprocessor_obj, features_list, target='sales')
    print("
모델 훈련 테스트 완료.")
