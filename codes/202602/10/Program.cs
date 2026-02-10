using System;
using System.Collections.Generic;
using DataAnalysisProject; // 네임스페이스 참조

namespace DataAnalysisProject
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8; // 한글 출력을 위해 설정

            Console.WriteLine("데이터 분석 프로젝트 시작.");

            // 1. 원시 데이터 입력 (예시)
            List<double> rawData = new List<double> { 10.5, 20.0, -5.0, 30.2, 15.8, double.NaN, 25.1, 0.0, 12.3, -8.1, 18.7 };
            Console.WriteLine($"
원시 데이터: {string.Join(", ", rawData)}");

            // 2. 데이터 처리 (정제)
            Console.WriteLine("
데이터를 처리(정제)합니다.");
            List<double> processedData = DataProcessor.CleanData(rawData);
            Console.WriteLine($"정제된 데이터: {string.Join(", ", processedData)}");

            // 3. 데이터 분석
            if (processedData.Count > 0)
            {
                Console.WriteLine("
데이터를 분석합니다.");
                double average = DataAnalyzer.CalculateAverage(processedData);
                double min = DataAnalyzer.CalculateMin(processedData);
                double max = DataAnalyzer.CalculateMax(processedData);
                double standardDeviation = DataAnalyzer.CalculateStandardDeviation(processedData);

                Console.WriteLine($"평균: {average:F2}");
                Console.WriteLine($"최소값: {min:F2}");
                Console.WriteLine($"최대값: {max:F2}");
                Console.WriteLine($"표준 편차: {standardDeviation:F2}");
            }
            else
            {
                Console.WriteLine("정제된 데이터가 없어 분석을 수행할 수 없습니다.");
            }

            Console.WriteLine("
데이터 분석 프로젝트 종료.");
        }
    }
}
