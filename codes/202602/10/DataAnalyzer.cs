using System;
using System.Collections.Generic;
using System.Linq;

namespace DataAnalysisProject
{
    public static class DataAnalyzer
    {
        /// <summary>
        /// 주어진 숫자 리스트의 평균을 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트.</param>
        /// <returns>데이터 리스트의 평균값.</returns>
        public static double CalculateAverage(List<double> data)
        {
            if (!data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있어 평균을 계산할 수 없습니다.");
            }
            // Linq의 Average 메서드를 사용하여 평균을 계산합니다.
            return data.Average();
        }

        /// <summary>
        /// 주어진 숫자 리스트의 최소값을 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트.</param>
        /// <returns>데이터 리스트의 최소값.</returns>
        public static double CalculateMin(List<double> data)
        {
            if (!data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있어 최소값을 계산할 수 없습니다.");
            }
            // Linq의 Min 메서드를 사용하여 최소값을 계산합니다.
            return data.Min();
        }

        /// <summary>
        /// 주어진 숫자 리스트의 최대값을 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트.</param>
        /// <returns>데이터 리스트의 최대값.</returns>
        public static double CalculateMax(List<double> data)
        {
            if (!data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있어 최대값을 계산할 수 없습니다.");
            }
            // Linq의 Max 메서드를 사용하여 최대값을 계산합니다.
            return data.Max();
        }

        /// <summary>
        /// 주어진 숫자 리스트의 표준 편차를 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트.</param>
        /// <returns>데이터 리스트의 표준 편차.</returns>
        public static double CalculateStandardDeviation(List<double> data)
        {
            if (!data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있어 표준 편차를 계산할 수 없습니다.");
            }

            double average = data.Average();
            // 각 데이터 포인트와 평균 간의 차이의 제곱을 계산합니다.
            double sumOfSquaresOfDifferences = data.Sum(d => Math.Pow(d - average, 2));
            // 분산을 계산하고 제곱근을 취하여 표준 편차를 구합니다.
            return Math.Sqrt(sumOfSquaresOfDifferences / data.Count);
        }
    }
}
