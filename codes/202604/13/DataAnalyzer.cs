// DataAnalyzer.cs
// 센서 데이터에 대한 분석 기능을 제공합니다.

using System;
using System.Collections.Generic;
using System.Linq;

namespace SensorAnalyzer
{
    /// <summary>
    /// 센서 데이터 목록에 대한 통계 분석 및 간단한 이상치 탐지를 수행하는 클래스입니다.
    /// </summary>
    public class DataAnalyzer
    {
        private readonly List<SensorData> _sensorDataList;

        public DataAnalyzer(List<SensorData> sensorDataList)
        {
            _sensorDataList = sensorDataList ?? throw new ArgumentNullException(nameof(sensorDataList));
        }

        /// <summary>
        /// 센서 데이터의 평균값을 계산합니다.
        /// </summary>
        /// <returns>센서 값의 평균입니다.</returns>
        public double CalculateAverage()
        {
            if (!_sensorDataList.Any())
            {
                return 0.0;
            }
            return _sensorDataList.Average(data => data.Value);
        }

        /// <summary>
        /// 센서 데이터의 최소값을 찾습니다.
        /// </summary>
        /// <returns>센서 값의 최소값입니다.</returns>
        public double FindMinimum()
        {
            if (!_sensorDataList.Any())
            {
                throw new InvalidOperationException("데이터가 비어 있어 최소값을 찾을 수 없습니다.");
            }
            return _sensorDataList.Min(data => data.Value);
        }

        /// <summary>
        /// 센서 데이터의 최대값을 찾습니다.
        /// </summary>
        /// <returns>센서 값의 최대값입니다.</returns>
        public double FindMaximum()
        {
            if (!_sensorDataList.Any())
            {
                throw new InvalidOperationException("데이터가 비어 있어 최대값을 찾을 수 없습니다.");
            }
            return _sensorDataList.Max(data => data.Value);
        }

        /// <summary>
        /// 간단한 방식으로 이상치를 탐지합니다.
        /// 여기서는 평균에서 특정 임계값(예: 10% 이상 차이) 이상 벗어나는 값을 이상치로 간주합니다.
        /// </summary>
        /// <param name="thresholdPercentage">평균값 대비 이상치로 간주할 최소 퍼센트 차이 (예: 0.10 for 10%)</param>
        /// <returns>탐지된 이상치 SensorData 목록입니다.</returns>
        public List<SensorData> DetectAnomalies(double thresholdPercentage = 0.10)
        {
            List<SensorData> anomalies = new List<SensorData>();
            if (!_sensorDataList.Any())
            {
                return anomalies;
            }

            double average = CalculateAverage();
            double lowerBound = average * (1 - thresholdPercentage);
            double upperBound = average * (1 + thresholdPercentage);

            foreach (var data in _sensorDataList)
            {
                if (data.Value < lowerBound || data.Value > upperBound)
                {
                    anomalies.Add(data);
                }
            }
            return anomalies;
        }

        // TODO: 표준 편차 기반의 이상치 탐지 등 더 고급 분석 기능을 추가할 수 있습니다.
    }
}
