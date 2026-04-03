import pandas as pd
from data_loader import load_sales_data
from data_processor import preprocess_data
from model_trainer import train_model
from predictor import load_model, make_predictions
import os

def run_sales_forecasting(
    data_file: str = None,
    model_name: str = 'sales_forecast_model.pkl',
    predict_days: int = 7
):
    """
    판매 예측 워크플로우를 실행합니다.
    데이터 로드, 전처리, 모델 훈련 및 예측을 포함합니다.

    Args:
        data_file (str, optional): 판매 데이터 CSV 파일 경로. 제공되지 않으면 더미 데이터를 사용합니다.
        model_name (str): 훈련된 모델을 저장하고 로드할 파일 이름.
        predict_days (int): 미래를 예측할 일수.
    """
    print("--- 판매 예측 시스템 시작 ---")

    # 1. 데이터 로드
    print("
[단계 1/4] 데이터 로드 중...")
    df = load_sales_data(data_file)
    if df.empty:
        print("데이터 로드에 실패했습니다. 시스템을 종료합니다.")
        return

    # 2. 데이터 전처리
    print("
[단계 2/4] 데이터 전처리 중...")
    processed_df_raw, preprocessor_obj, features_list = preprocess_data(df.copy())
    
    # 예측을 위한 마지막 알려진 데이터 준비 (date, product_id, region, promotion, sales 포함)
    # predictor 모듈에서 lag 피처 생성을 위해 필요
    last_known_data_for_prediction = processed_df_raw.copy()
    
    # 3. 모델 훈련 및 저장
    print("
[단계 3/4] 모델 훈련 및 저장 중...")
    trained_model_pipeline = train_model(
        processed_df_raw,
        preprocessor_obj,
        features_list,
        target='sales',
        model_path=model_name
    )

    if trained_model_pipeline is None:
        print("모델 훈련에 실패했습니다. 시스템을 종료합니다.")
        return

    # 4. 미래 판매량 예측
    print(f"
[단계 4/4] 다음 {predict_days}일 판매량 예측 중...")
    loaded_model = load_model(model_name) # 훈련된 모델을 다시 로드하여 일관성 유지
    if loaded_model:
        predictions_df = make_predictions(
            loaded_model,
            last_known_data_for_prediction,
            predict_days,
            features_list # 모델 훈련에 사용된 피처 리스트 전달
        )
        if not predictions_df.empty:
            print(f"
다음 {predict_days}일 동안의 판매 예측 결과:")
            print(predictions_df.head(10))
            print(f"...총 {len(predictions_df)}개의 예측이 생성되었습니다.")
        else:
            print("예측을 생성하지 못했습니다.")
    else:
        print("예측을 위해 모델을 로드할 수 없습니다.")
        
    print("
--- 판매 예측 시스템 완료 ---")

if __name__ == '__main__':
    # 프로젝트 실행 예시:
    # 1. 더미 데이터로 실행
    print("----- 더미 데이터로 시스템 실행 -----")
    run_sales_forecasting(predict_days=14)

    # 2. 실제 데이터 파일이 있다면 다음과 같이 사용할 수 있습니다.
    # 예: 'path/to/your/sales_data.csv'
    # print("
----- 실제 데이터 파일로 시스템 실행 -----")
    # if os.path.exists('sample_sales_data.csv'): # 예시 파일 존재 여부 확인
    #     run_sales_forecasting(data_file='sample_sales_data.csv', predict_days=7)
    # else:
    #     print("경고: 'sample_sales_data.csv' 파일을 찾을 수 없습니다. 실제 데이터 파일로 실행하려면 이 파일을 생성해주세요.")
