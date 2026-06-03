using System;
using System.Collections.Generic;
using System.Linq;

namespace StockPredictorApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("주식 가격 예측 애플리케이션 시작...");

            // 1. 샘플 데이터 로드 (실제 환경에서는 파일/DB/API에서 로드)
            List<StockFeature> trainingData = DataProcessor.GetSampleData();
            Console.WriteLine($"훈련 데이터 {trainingData.Count}개 로드 완료.");

            // 2. 모델 훈련
            StockPredictor predictor = new StockPredictor();
            predictor.Train(trainingData);

            // 3. 예측할 데이터 준비
            // 예시: 가장 최근 데이터의 다음 날을 예측한다고 가정
            StockFeature lastDayData = trainingData.Last();
            StockFeature stockToPredict = new StockFeature
            {
                Open = lastDayData.Close + 1, // 다음 날 시가는 마지막 종가보다 조금 높다고 가정
                High = lastDayData.Close + 3,
                Low = lastDayData.Close - 1,
                Volume = lastDayData.Volume + 5000 // 거래량도 약간 증가했다고 가정
                // Close는 예측 대상이므로 여기에 값을 넣지 않음
            };

            Console.WriteLine("
새로운 데이터에 대한 예측을 수행합니다:");
            Console.WriteLine($"  예측 입력 (시가: {stockToPredict.Open}, 고가: {stockToPredict.High}, 저가: {stockToPredict.Low}, 거래량: {stockToPredict.Volume})");

            // 4. 예측 수행
            StockPrediction prediction = predictor.Predict(stockToPredict);

            // 5. 예측 결과 출력
            Console.WriteLine($"예측된 종가: {prediction.PredictedClose:F2}");
            Console.WriteLine("
주식 가격 예측 애플리케이션 종료.");

            // 사용자가 결과를 볼 수 있도록 잠시 대기
            // Console.ReadKey();
        }
    }
}