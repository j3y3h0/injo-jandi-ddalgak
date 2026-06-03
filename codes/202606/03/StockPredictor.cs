using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.ML;
using Microsoft.ML.Data;
using Microsoft.ML.Transforms;

namespace StockPredictorApp
{
    public class StockPredictor
    {
        private readonly MLContext _mlContext;
        private ITransformer _trainedModel;

        public StockPredictor()
        {
            _mlContext = new MLContext();
        }

        // 모델을 훈련합니다.
        public void Train(List<StockFeature> trainingData)
        {
            // 훈련 데이터를 IDataView로 변환합니다.
            IDataView dataView = _mlContext.Data.LoadFromEnumerable(trainingData);

            // 데이터 처리 파이프라인을 정의합니다.
            // "Close" 컬럼을 예측 대상(Label)으로 설정하고,
            // 나머지 수치형 컬럼("Open", "High", "Low", "Volume")을 특징(Features)으로 결합합니다.
            var dataProcessPipeline = _mlContext.Transforms.CopyColumns(outputColumnName: "Label", inputColumnName: "Close")
                .Append(_mlContext.Transforms.Concatenate("Features", "Open", "High", "Low", "Volume"));

            // 모델 훈련 알고리즘을 선택합니다. 여기서는 SDCA (Stochastic Dual Coordinate Ascent) 회귀를 사용합니다.
            var trainer = _mlContext.Regression.Trainers.Sdca(labelColumnName: "Label", featureColumnName: "Features");
            
            // 데이터 처리 파이프라인과 트레이너를 결합합니다.
            var trainingPipeline = dataProcessPipeline.Append(trainer);

            Console.WriteLine("모델 훈련 시작...");
            _trainedModel = trainingPipeline.Fit(dataView); // 모델 훈련
            Console.WriteLine("모델 훈련 완료.");
        }

        // 훈련된 모델을 사용하여 새로운 데이터에 대한 예측을 수행합니다.
        public StockPrediction Predict(StockFeature stockToPredict)
        {
            if (_trainedModel == null)
            {
                throw new InvalidOperationException("모델이 훈련되지 않았습니다. 먼저 Train() 메서드를 호출하세요.");
            }

            // 예측 엔진을 생성합니다.
            var predictionEngine = _mlContext.Model.CreatePredictionEngine<StockFeature, StockPrediction>(_trainedModel);

            // 예측 수행
            var prediction = predictionEngine.Predict(stockToPredict);

            return prediction;
        }
    }
}
