using System;
using System.Collections.Generic;
using System.Linq;

namespace DataAnalysisProject
{
    public static class StatisticsCalculator
    {
        /// <summary>
        /// 주어진 숫자 목록의 평균을 계산합니다.
        /// </summary>
        /// <param name="numbers">평균을 계산할 숫자 목록입니다.</param>
        /// <returns>숫자 목록의 평균입니다. 목록이 비어 있으면 0을 반환합니다.</returns>
        public static double CalculateMean(List<double> numbers)
        {
            if (numbers == null || !numbers.Any())
            {
                return 0;
            }
            return numbers.Average();
        }

        /// <summary>
        /// 주어진 숫자 목록의 중앙값을 계산합니다.
        /// </summary>
        /// <param name="numbers">중앙값을 계산할 숫자 목록입니다.</param>
        /// <returns>숫자 목록의 중앙값입니다. 목록이 비어 있으면 0을 반환합니다.</returns>
        public static double CalculateMedian(List<double> numbers)
        {
            if (numbers == null || !numbers.Any())
            {
                return 0;
            }

            numbers.Sort(); // 중앙값 계산을 위해 정렬

            int count = numbers.Count;
            if (count % 2 == 0)
            {
                // 짝수 개수인 경우, 중간 두 값의 평균
                return (numbers[count / 2 - 1] + numbers[count / 2]) / 2.0;
            }
            else
            {
                // 홀수 개수인 경우, 중간 값
                return numbers[count / 2];
            }
        }

        /// <summary>
        /// 주어진 숫자 목록의 표준 편차를 계산합니다.
        /// </summary>
        /// <param name="numbers">표준 편차를 계산할 숫자 목록입니다.</param>
        /// <returns>숫자 목록의 표준 편차입니다. 목록이 비어 있거나 요소가 하나뿐이면 0을 반환합니다.</returns>
        public static double CalculateStandardDeviation(List<double> numbers)
        {
            if (numbers == null || numbers.Count < 2) // 표준 편차는 최소 2개의 데이터 필요
            {
                return 0;
            }

            double mean = CalculateMean(numbers);
            double sumOfSquaresOfDifferences = numbers.Sum(number => Math.Pow(number - mean, 2));

            return Math.Sqrt(sumOfSquaresOfDifferences / (numbers.Count - 1)); // 표본 표준 편차
        }
    }
}
