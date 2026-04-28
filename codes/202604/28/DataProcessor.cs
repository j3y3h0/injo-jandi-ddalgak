using System;
using System.Collections.Generic;
using System.Linq;

namespace SimpleDataProcessor
{
    /// <summary>
    /// 데이터를 처리하는 정적 메서드를 제공하는 유틸리티 클래스입니다.
    /// LINQ를 활용하여 데이터 필터링, 정렬, 집계 기능을 구현합니다.
    /// </summary>
    public static class DataProcessor
    {
        /// <summary>
        /// 주어진 숫자 목록에서 중복을 제거하고 오름차순으로 정렬된 새 목록을 반환합니다.
        /// </summary>
        /// <param name="numbers">처리할 숫자 목록입니다.</param>
        /// <returns>중복이 제거되고 정렬된 숫자 목록입니다.</returns>
        public static List<int> GetDistinctAndSorted(List<int> numbers)
        {
            // LINQ의 Distinct()와 OrderBy() 메서드를 사용합니다.
            return numbers.Distinct().OrderBy(n => n).ToList();
        }

        /// <summary>
        /// 주어진 숫자 목록에서 특정 값보다 큰 숫자들만 필터링하여 새 목록을 반환합니다.
        /// </summary>
        /// <param name="numbers">처리할 숫자 목록입니다.</param>
        /// <param name="threshold">필터링 기준이 되는 값입니다.</param>
        /// <returns>기준 값보다 큰 숫자들의 목록입니다.</returns>
        public static List<int> FilterGreaterThan(List<int> numbers, int threshold)
        {
            // LINQ의 Where() 메서드를 사용하여 필터링합니다.
            return numbers.Where(n => n > threshold).ToList();
        }

        /// <summary>
        /// 주어진 숫자 목록의 모든 숫자의 합계를 계산합니다.
        /// </summary>
        /// <param name="numbers">처리할 숫자 목록입니다.</param>
        /// <returns>숫자 목록의 합계입니다.</returns>
        public static int CalculateSum(List<int> numbers)
        {
            // LINQ의 Sum() 메서드를 사용합니다.
            return numbers.Sum();
        }

        /// <summary>
        /// 주어진 숫자 목록의 평균을 계산합니다.
        /// 목록이 비어있는 경우 0을 반환합니다.
        /// </summary>
        /// <param name="numbers">처리할 숫자 목록입니다.</param>
        /// <returns>숫자 목록의 평균입니다.</returns>
        public static double CalculateAverage(List<int> numbers)
        {
            // LINQ의 Any()로 목록이 비어있는지 확인하고 Average() 메서드를 사용합니다.
            return numbers.Any() ? numbers.Average() : 0.0;
        }
    }
}
