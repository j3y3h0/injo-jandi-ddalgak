// StatisticsCalculator.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace CsvAnalyzer
{
    /// <summary>
    /// 수치 데이터에 대한 통계 계산을 제공하는 클래스입니다.
    /// </summary>
    public class StatisticsCalculator
    {
        /// <summary>
        /// 주어진 수치 데이터의 평균을 계산합니다.
        /// </summary>
        /// <param name="data">수치 데이터 목록입니다.</param>
        /// <returns>데이터의 평균입니다. 데이터가 없을 경우 NaN을 반환합니다.</returns>
        public double CalculateMean(List<double> data)
        {
            if (data == null || !data.Any())
            {
                return double.NaN;
            }
            return data.Average();
        }

        /// <summary>
        /// 주어진 수치 데이터의 중앙값을 계산합니다.
        /// </summary>
        /// <param name="data">수치 데이터 목록입니다.</param>
        /// <returns>데이터의 중앙값입니다. 데이터가 없을 경우 NaN을 반환합니다.</returns>
        public double CalculateMedian(List<double> data)
        {
            if (data == null || !data.Any())
            {
                return double.NaN;
            }

            var sortedData = data.OrderBy(d => d).ToList();
            int count = sortedData.Count;

            if (count % 2 == 0)
            {
                // 짝수 개일 경우, 중간 두 값의 평균
                return (sortedData[count / 2 - 1] + sortedData[count / 2]) / 2.0;
            }
            else
            {
                // 홀수 개일 경우, 중간 값
                return sortedData[count / 2];
            }
        }

        /// <summary>
        /// 주어진 수치 데이터의 표준 편차를 계산합니다. (표본 표준 편차)
        /// </summary>
        /// <param name="data">수치 데이터 목록입니다.</param>
        /// <returns>데이터의 표준 편차입니다. 데이터가 2개 미만일 경우 NaN을 반환합니다.</returns>
        public double CalculateStandardDeviation(List<double> data)
        {
            if (data == null || data.Count < 2)
            {
                return double.NaN;
            }

            double mean = CalculateMean(data);
            double sumOfSquaresOfDifferences = data.Sum(d => Math.Pow(d - mean, 2));

            // 표본 표준 편차 (n-1)
            return Math.Sqrt(sumOfSquaresOfDifferences / (data.Count - 1));
        }
    }
}
