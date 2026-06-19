using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace DataAnalysisProject
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 통계 분석 프로그램입니다.");
            Console.WriteLine("프로젝트 실행 디렉토리에 'data.csv' 파일을 준비해주세요.");

            string filePath = "data.csv"; // 실행 파일과 같은 경로에 data.csv가 있다고 가정

            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: '{filePath}' 파일을 찾을 수 없습니다. 파일을 생성하고 다시 실행해주세요.");
                return;
            }

            try
            {
                // CSV 파일 파싱
                List<List<double>> parsedData = CsvParser.Parse(filePath);

                if (parsedData == null || parsedData.Count == 0)
                {
                    Console.WriteLine("오류: 파싱할 데이터가 없거나 CSV 파일 형식이 올바르지 않습니다.");
                    return;
                }

                // 모든 데이터를 단일 리스트로 합치기 (통계 분석을 위해)
                List<double> allNumbers = parsedData.SelectMany(row => row).ToList();

                if (allNumbers.Count == 0)
                {
                    Console.WriteLine("오류: CSV 파일에서 유효한 숫자 데이터를 찾을 수 없습니다.");
                    return;
                }

                // 통계 계산
                double mean = StatisticsCalculator.CalculateMean(allNumbers);
                double median = StatisticsCalculator.CalculateMedian(allNumbers);
                double standardDeviation = StatisticsCalculator.CalculateStandardDeviation(allNumbers);

                // 결과 출력
                Console.WriteLine("
--- 통계 분석 결과 ---");
                Console.WriteLine($"총 데이터 개수: {allNumbers.Count}");
                Console.WriteLine($"평균: {mean:F2}");
                Console.WriteLine($"중앙값: {median:F2}");
                Console.WriteLine($"표준 편차: {standardDeviation:F2}");
                Console.WriteLine("---------------------");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"처리 중 오류가 발생했습니다: {ex.Message}");
            }

            Console.WriteLine("
프로그램을 종료합니다. 아무 키나 누르십시오.");
            Console.ReadKey();
        }
    }
}
