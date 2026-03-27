using System;
using System.Collections.Generic;
using System.Linq;

namespace StockAnomalyDetection
{
    /// <summary>
    /// 주식 데이터에서 이상 값을 감지하는 로직을 포함하는 클래스.
    /// 이동 평균과 표준 편차를 활용하여 가격 이상을 감지한다.
    /// </summary>
    public class AnomalyDetector
    {
        private readonly int _windowSize; // 이동 평균 및 표준 편차 계산에 사용할 윈도우 크기
        private readonly decimal _thresholdMultiplier; // 이상 감지 임계값을 위한 표준 편차 승수

        /// <summary>
        /// 이상 감지에 사용되는 윈도우 크기를 가져온다.
        /// </summary>
        public int WindowSize => _windowSize; // 추가된 public 속성

        /// <summary>
        /// AnomalyDetector 클래스의 새 인스턴스를 초기화한다.
        /// </summary>
        /// <param name="windowSize">이동 평균 및 표준 편차를 계산할 데이터 포인트의 수. 일반적으로 10에서 60 정도의 값을 사용한다.</param>
        /// <param name="thresholdMultiplier">현재 가격이 이동 평균에서 표준 편차의 몇 배 이상 벗어날 때 이상으로 간주할지 결정하는 값. 일반적으로 2 또는 3을 사용한다.</param>
        public AnomalyDetector(int windowSize = 20, decimal thresholdMultiplier = 2.5m)
        {
            if (windowSize <= 0)
                throw new ArgumentOutOfRangeException(nameof(windowSize), "윈도우 크기는 0보다 커야 합니다.");
            if (thresholdMultiplier <= 0)
                throw new ArgumentOutOfRangeException(nameof(thresholdMultiplier), "임계값 승수는 0보다 커야 합니다.");

            _windowSize = windowSize;
            _thresholdMultiplier = thresholdMultiplier;
        }

        /// <summary>
        /// 특정 주식 틱에 대해 이상 여부를 감지한다.
        /// </summary>
        /// <param name="currentTick">현재 처리 중인 StockTick 데이터</param>
        /// <param name="recentPrices">이상 감지에 사용할 최근 가격 데이터 목록</param>
        /// <returns>이상 감지 결과 메시지. 이상이 감지되지 않으면 null을 반환한다.</returns>
        public string Detect(StockTick currentTick, IEnumerable<decimal> recentPrices)
        {
            // 윈도우 크기만큼 데이터가 충분하지 않으면 이상 감지를 수행할 수 없다.
            var pricesList = recentPrices.ToList();
            if (pricesList.Count < _windowSize)
            {
                return null; // 데이터 부족, 감지 보류
            }

            // 이동 평균 계산
            decimal movingAverage = pricesList.Average();

            // 표준 편차 계산
            decimal sumOfSquaresOfDifferences = pricesList.Sum(price => (price - movingAverage) * (price - movingAverage));
            decimal standardDeviation = (decimal)Math.Sqrt((double)(sumOfSquaresOfDifferences / pricesList.Count));

            // 상한 및 하한 임계값 계산
            decimal upperThreshold = movingAverage + (standardDeviation * _thresholdMultiplier);
            decimal lowerThreshold = movingAverage - (standardDeviation * _thresholdMultiplier);

            // 현재 가격이 임계값을 벗어나는지 확인
            if (currentTick.Price > upperThreshold)
            {
                return $"[이상 감지]: {currentTick.Symbol} 가격 {currentTick.Price:F2}가 이동 평균 {movingAverage:F2}와 표준 편차({standardDeviation:F2} * {_thresholdMultiplier})를 초과하는 급등 현상 감지!";
            }
            else if (currentTick.Price < lowerThreshold)
            {
                return $"[이상 감지]: {currentTick.Symbol} 가격 {currentTick.Price:F2}가 이동 평균 {movingAverage:F2}와 표준 편차({standardDeviation:F2} * {_thresholdMultiplier}) 이하로 급락 현상 감지!";
            }

            return null; // 이상 감지 안됨
        }
    }
}
