// StatisticsCalculator.cs
using System.Collections.Generic;
using System.Linq;
using System;

namespace NumberCruncher
{
    /// <summary>
    /// 통계 계산 결과를 담는 구조체입니다.
    /// </summary>
    public struct StatisticsResult
    {
        public double Sum { get; set; }
        public double Average { get; set; }
        public double Min { get; set; }
        public double Max { get; set; }
        public int Count { get; set; }
    }

    /// <summary>
    /// 숫자 목록에 대한 통계를 계산하는 기능을 제공하는 클래스입니다.
    /// </summary>
    public class StatisticsCalculator
    {
        /// <summary>
        /// 숫자 목록의 합계, 평균, 최소값, 최대값, 개수를 계산합니다.
        /// </summary>
        /// <param name="numbers">통계를 계산할 숫자 목록입니다.</param>
        /// <returns>계산된 통계 결과를 담은 StatisticsResult 구조체입니다.</returns>
        public StatisticsResult CalculateStatistics(IEnumerable<double> numbers)
        {
            var result = new StatisticsResult();
            var numsList = numbers.ToList();

            if (!numsList.Any())
            {
                Logger.LogInfo("통계를 계산할 숫자가 없습니다. 기본값으로 결과를 반환합니다.");
                return result; // 기본값 (0)으로 초기화된 구조체 반환
            }

            result.Count = numsList.Count;
            result.Sum = numsList.Sum();
            result.Average = numsList.Average();
            result.Min = numsList.Min();
            result.Max = numsList.Max();

            Logger.LogInfo($"통계 계산 완료: 합계={result.Sum}, 평균={result.Average}, 최소값={result.Min}, 최대값={result.Max}, 개수={result.Count}");
            return result;
        }
    }
}
