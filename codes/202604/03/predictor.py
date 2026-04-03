import pandas as pd
import joblib # 모델 로드를 위함
import numpy as np
from datetime import timedelta

def load_model(model_path: str = 'sales_forecast_model.pkl'):
    """
    저장된 판매 예측 모델을 로드합니다.

    Args:
        model_path (str): 로드할 모델 파일의 경로.

    Returns:
        Pipeline: 로드된 scikit-learn Pipeline 모델.
    """
    try:
        model = joblib.load(model_path)
        print(f"모델이 '{model_path}'에서 성공적으로 로드되었습니다.")
        return model
    except FileNotFoundError:
        print(f"오류: '{model_path}'에 모델 파일을 찾을 수 없습니다. 먼저 모델을 훈련시키고 저장하십시오.")
        return None
    except Exception as e:
        print(f"모델 로드 중 오류 발생: {e}")
        return None

def make_predictions(
    model,
    last_known_data: pd.DataFrame,
    num_prediction_days: int,
    features: list
) -> pd.DataFrame:
    """
    로드된 모델을 사용하여 미래 판매량을 예측합니다.
    예측을 위해 필요한 미래 피처를 생성해야 합니다.

    Args:
        model (Pipeline): 훈련된 scikit-learn Pipeline 모델.
        last_known_data (pd.DataFrame): 예측 시작을 위한 가장 최근 데이터.
                                       'date', 'product_id', 'region', 'promotion', 'sales' 컬럼 포함.
        num_prediction_days (int): 예측할 미래 일수.
        features (list): 모델 훈련에 사용된 피처 목록.

    Returns:
        pd.DataFrame: 예측된 판매량을 포함하는 데이터프레임.
    """
    if model is None:
        return pd.DataFrame()

    print(f"{num_prediction_days}일 동안의 미래 판매량을 예측합니다...")

    # 예측을 위한 새로운 날짜 생성
    last_date = last_known_data['date'].max()
    future_dates = [last_date + timedelta(days=i) for i in range(1, num_prediction_days + 1)]

    # 각 제품 ID와 지역 조합에 대해 미래 데이터프레임 생성
    unique_products = last_known_data['product_id'].unique()
    unique_regions = last_known_data['region'].unique()
    
    future_data_rows = []
    for product_id in unique_products:
        for region in unique_regions:
            for future_date in future_dates:
                # 더미 프로모션 값 (실제로는 미래 프로모션 계획 필요)
                promotion = np.random.choice([0, 1], p=[0.7, 0.3]) 
                future_data_rows.append({
                    'date': future_date,
                    'product_id': product_id,
                    'region': region,
                    'promotion': promotion,
                    'sales': 0 # 예측 대상이므로 0으로 설정
                })
    
    future_df = pd.DataFrame(future_data_rows)
    
    # 마지막 알려진 데이터와 미래 데이터를 결합하여 피처 생성을 용이하게 합니다.
    # 이 과정에서 sales_lag_X 등의 피처가 생성될 수 있도록 합니다.
    combined_df = pd.concat([last_known_data, future_df], ignore_index=True)
    
    # 'date' 컬럼을 datetime 객체로 변환
    combined_df['date'] = pd.to_datetime(combined_df['date'])

    # 데이터 전처리 로직을 재사용하여 미래 피처 생성
    # model_trainer에서 Pipeline을 사용하므로, 전처리기는 모델 내부에 포함되어 있습니다.
    # 여기서는 predict 메서드가 자동으로 전처리를 수행할 것으로 예상합니다.
    
    # 예측 대상 데이터 준비 (미래 데이터만)
    X_future = combined_df[combined_df['date'] > last_date]
    X_future = X_future[features] # 모델 훈련에 사용된 피처만 선택
    
    # 예측 수행
    future_predictions = model.predict(X_future)

    # 예측 결과를 미래 데이터프레임에 추가
    future_df['predicted_sales'] = future_predictions
    
    # 원본 데이터프레임의 'sales' 컬럼을 유지하기 위해 'sales' 컬럼은 드롭하고 'predicted_sales'로 대체합니다.
    # 필요한 경우, `future_df`의 'sales' 컬럼을 제거하거나 `predicted_sales`를 `sales`로 이름을 변경할 수 있습니다.
    # 여기서는 `predicted_sales` 컬럼을 명시적으로 추가합니다.

    print("예측이 완료되었습니다.")
    return future_df[['date', 'product_id', 'region', 'promotion', 'predicted_sales']]

if __name__ == '__main__':
    # 테스트를 위한 더미 데이터 로드 및 전처리
    from data_loader import load_sales_data
    from data_processor import preprocess_data
    from model_trainer import train_model

    # 1. 모델이 없다고 가정하고 로드 시도
    print("--- 모델 로드 실패 테스트 ---")
    model_not_found = load_model("non_existent_model.pkl")

    # 2. 모델 훈련 및 저장 (테스트용)
    print("
--- 모델 훈련 및 저장 테스트 ---")
    dummy_df_train = load_sales_data()
    processed_df_train, preprocessor_obj_train, features_list_train = preprocess_data(dummy_df_train)
    # 훈련 시 sales_lag_X 등이 생성되므로, 이 데이터를 사용하여 모델 훈련
    trained_pipeline = train_model(processed_df_train, preprocessor_obj_train, features_list_train, target='sales')

    # 3. 훈련된 모델 로드
    print("
--- 훈련된 모델 로드 테스트 ---")
    loaded_model = load_model('sales_forecast_model.pkl')
    
    if loaded_model:
        # 예측을 위한 마지막 알려진 데이터 준비 (더미 데이터의 마지막 N일)
        last_known_data_for_pred = dummy_df_train.tail(20).copy() # 마지막 20일 데이터를 사용
        
        # 날짜 관련 피처를 다시 생성합니다. predictor는 Pipeline에 의해 전처리기가 자동으로 적용됩니다.
        # 그러나 lag features와 같은 것은 원본 데이터가 필요하므로,
        # predict 함수 내에서 last_known_data와 future_df를 결합하여 처리하는 것이 중요합니다.
        # 'sales' 컬럼은 미래 예측 시 사용되지 않으므로, 이 부분을 고려해야 합니다.
        # make_predictions 함수가 combined_df를 사용하여 전처리 로직을 따르도록 설계되어 있습니다.

        num_days_to_predict = 7
        predictions_df = make_predictions(
            loaded_model,
            last_known_data_for_pred,
            num_days_to_predict,
            features_list_train # 모델 훈련에 사용된 피처 리스트 전달
        )
        print("
예측 결과 미리보기:")
        print(predictions_df.head())
        print(f"예측 결과 크기: {predictions_df.shape}")
    else:
        print("모델 로드 실패로 예측을 수행할 수 없습니다.")
