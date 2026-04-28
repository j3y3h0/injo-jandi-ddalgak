using System;
using System.Collections.Generic;
using System.Linq;

namespace SimpleDataProcessor
{
    /// <summary>
    /// 간단한 데이터 처리 및 분석 유틸리티의 메인 프로그램입니다.
    /// 이 프로그램은 DataProcessor 클래스를 사용하여 숫자 목록을 처리하고 결과를 출력합니다.
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            // 처리할 데이터 목록을 정의합니다.
            List<int> numbers = new List<int> { 10, 5, 20, 15, 25, 30, 5, 10 };

            Console.WriteLine("원래 데이터: " + string.Join(", ", numbers));

            // DataProcessor를 사용하여 데이터를 처리합니다.

            // 1. 중복 제거 및 정렬
            var distinctAndSortedNumbers = DataProcessor.GetDistinctAndSorted(numbers);
            Console.WriteLine("중복 제거 및 정렬된 데이터: " + string.Join(", ", distinctAndSortedNumbers));

            // 2. 15보다 큰 숫자 필터링
            var greaterThan15 = DataProcessor.FilterGreaterThan(numbers, 15);
            Console.WriteLine("15보다 큰 숫자: " + string.Join(", ", greaterThan15));

            // 3. 데이터 합계 계산
            int sum = DataProcessor.CalculateSum(numbers);
            Console.WriteLine("데이터 합계: " + sum);

            // 4. 데이터 평균 계산
            double average = DataProcessor.CalculateAverage(numbers);
            Console.WriteLine("데이터 평균: " + average);

            Console.WriteLine("
프로그램이 종료되었습니다. 아무 키나 눌러주세요.");
            Console.ReadKey();
        }
    }
}
