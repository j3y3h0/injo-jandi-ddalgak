# model_trainer.py
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error, r2_score
import pandas as pd
import joblib

def train_model(X_train: pd.DataFrame, y_train: pd.Series, X_test: pd.DataFrame, y_test: pd.Series):
    """
    RandomForestRegressor 모델을 학습시키고 성능을 평가합니다.

    Args:
        X_train (pd.DataFrame): 훈련용 특성 데이터.
        y_train (pd.Series): 훈련용 타겟 데이터.
        X_test (pd.DataFrame): 테스트용 특성 데이터.
        y_test (pd.Series): 테스트용 타겟 데이터.

    Returns:
        RandomForestRegressor: 학습된 모델.
    """
    if X_train.empty or y_train.empty:
        print("훈련 데이터가 비어 있어 모델을 학습할 수 없습니다.")
        return None

    # RandomForestRegressor 모델 초기화 및 학습
    # n_estimators: 결정 트리의 개수, random_state: 재현성을 위한 시드
    model = RandomForestRegressor(n_estimators=100, random_state=42, n_jobs=-1)
    print("RandomForestRegressor 모델 학습 시작...")
    model.fit(X_train, y_train)
    print("모델 학습 완료.")

    # 테스트 데이터로 모델 성능 평가
    if not X_test.empty and not y_test.empty:
        y_pred = model.predict(X_test)
        mse = mean_squared_error(y_test, y_pred)
        r2 = r2_score(y_test, y_pred)
        print(f"
모델 성능 평가:")
        print(f"  평균 제곱 오차 (MSE): {mse:.2f}")
        print(f"  결정 계수 (R-squared): {r2:.2f}")
    else:
        print("
테스트 데이터가 없어 모델 성능 평가를 건너뜀.")

    # 학습된 모델 저장 (선택 사항)
    # joblib.dump(model, 'stock_prediction_model.joblib')
    # print("학습된 모델이 'stock_prediction_model.joblib'으로 저장되었습니다.")

    return model

if __name__ == '__main__':
    # 테스트를 위한 예제 코드 (preprocessor.py에서 가져온 가상 데이터 사용)
    from preprocessor import preprocess_data
    
    # 가상의 주식 데이터 생성
    data = {
        'Date': pd.to_datetime(pd.date_range(start='2022-01-01', periods=100, freq='D')),
        'Open': [100 + i for i in range(100)],
        'High': [102 + i for i in range(100)],
        'Low': [98 + i for i in range(100)],
        'Close': [101 + i + (i%5) for i in range(100)],
        'Volume': [100000 - i*100 for i in range(100)]
    }
    sample_df = pd.DataFrame(data).set_index('Date')
    
    X_train, X_test, y_train, y_test = preprocess_data(sample_df)
    
    if not X_train.empty:
        trained_model = train_model(X_train, y_train, X_test, y_test)
        if trained_model:
            print("
모델 학습 및 평가 테스트 완료.")
