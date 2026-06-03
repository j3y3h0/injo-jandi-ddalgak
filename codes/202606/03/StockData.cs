using Microsoft.ML.Data;

namespace StockPredictorApp
{
    // 훈련 데이터 및 예측 입력으로 사용될 주식 데이터 모델
    public class StockFeature
    {
        // [LoadColumn(0)] 등을 사용하여 CSV 파일 컬럼과 매핑할 수 있으나,
        // 여기서는 코드에서 직접 데이터를 생성하므로 필요 없음.
        
        [ColumnName("Open")] // 시가
        public float Open { get; set; }

        [ColumnName("High")] // 고가
        public float High { get; set; }

        [ColumnName("Low")] // 저가
        public float Low { get; set; }

        [ColumnName("Volume")] // 거래량
        public float Volume { get; set; }

        [ColumnName("Close")] // 종가 (예측 대상)
        public float Close { get; set; }
    }

    // 예측 결과 모델
    public class StockPrediction
    {
        [ColumnName("Score")] // ML.NET 모델의 예측 결과는 기본적으로 'Score' 컬럼에 저장됨
        public float PredictedClose { get; set; }
    }
}