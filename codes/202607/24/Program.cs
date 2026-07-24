// Program.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace NumberCruncher
{
    class Program
    {
        static void Main(string[] args)
        {
            Logger.LogInfo("숫자 데이터 분석기 시작.");

            if (args.Length == 0)
            {
                Logger.LogError("사용법: dotnet run <파일경로> [--filter-min <숫자>] [--filter-max <숫자>]");
                return;
            }

            string filePath = args[0];
            double? filterMin = null;
            double? filterMax = null;

            // 명령줄 인수 파싱
            for (int i = 1; i < args.Length; i++)
            {
                switch (args[i].ToLower())
                {
                    case "--filter-min":
                        if (i + 1 < args.Length && double.TryParse(args[i + 1], out double minVal))
                        {
                            filterMin = minVal;
                            i++;
                        }
                        else
                        {
                            Logger.LogError("--filter-min 옵션에 유효한 숫자가 필요합니다.");
                            return;
                        }
                        break;
                    case "--filter-max":
                        if (i + 1 < args.Length && double.TryParse(args[i + 1], out double maxVal))
                        {
                            filterMax = maxVal;
                            i++;
                        }
                        else
                        {
                            Logger.LogError("--filter-max 옵션에 유효한 숫자가 필요합니다.");
                            return;
                        }
                        break;
                    default:
                        Logger.LogError($"알 수 없는 옵션: {args[i]}");
                        return;
                }
            }

            DataProcessor processor = new DataProcessor();
            StatisticsCalculator calculator = new StatisticsCalculator();

            // 1. 파일에서 숫자 로드
            IEnumerable<double> numbers = processor.LoadNumbersFromFile(filePath);
            if (!numbers.Any())
            {
                Logger.LogError("처리할 숫자가 파일에 없습니다. 애플리케이션을 종료합니다.");
                return;
            }

            // 2. 숫자 필터링
            IEnumerable<double> filteredNumbers = processor.FilterNumbers(numbers, filterMin, filterMax);
            if (!filteredNumbers.Any())
            {
                Logger.LogError("필터링 조건에 맞는 숫자가 없습니다. 애플리케이션을 종료합니다.");
                return;
            }

            // 3. 통계 계산
            StatisticsResult result = calculator.CalculateStatistics(filteredNumbers);

            // 4. 결과 출력
            Console.WriteLine("
--- 분석 결과 ---");
            Console.WriteLine($"원본 숫자 개수: {numbers.Count()}");
            Console.WriteLine($"필터링된 숫자 개수: {result.Count}");
            Console.WriteLine($"합계: {result.Sum}");
            Console.WriteLine($"평균: {result.Average:F2}"); // 소수점 둘째 자리까지
            Console.WriteLine($"최소값: {result.Min}");
            Console.WriteLine($"최대값: {result.Max}");
            Console.WriteLine("-----------------
");

            Logger.LogInfo("숫자 데이터 분석기 완료.");
        }
    }
}
