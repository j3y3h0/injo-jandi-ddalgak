// Program.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace DataAnalysisApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 분석 프로그램 시작.");

            string filePath = "data.csv"; // 분석할 CSV 파일 경로

            // CsvProcessor를 사용하여 CSV 파일 읽기
            CsvProcessor csvProcessor = new CsvProcessor();
            List<List<string>> rawData = csvProcessor.ReadCsv(filePath);

            if (rawData == null || rawData.Count == 0)
            {
                Console.WriteLine("오류: CSV 파일에서 데이터를 읽는 데 실패했거나 파일이 비어 있습니다.");
                return;
            }

            // 첫 번째 열 (숫자 데이터라고 가정) 파싱
            List<double> numericalData = new List<double>();
            // 헤더 행을 건너뛰고 두 번째 행부터 데이터를 처리합니다. (인덱스 1부터 시작)
            foreach (var row in rawData.Skip(1)) 
            {
                if (row.Count > 0 && double.TryParse(row[0], out double value)) // 첫 번째 열만 분석
                {
                    numericalData.Add(value);
                }
            }

            if (numericalData.Count == 0)
            {
                Console.WriteLine("오류: 분석할 유효한 숫자 데이터가 없습니다.");
                return;
            }

            // DataAnalyzer를 사용하여 데이터 분석
            DataAnalyzer analyzer = new DataAnalyzer();
            double min = analyzer.CalculateMinimum(numericalData);
            double max = analyzer.CalculateMaximum(numericalData);
            double average = analyzer.CalculateAverage(numericalData);

            // 결과 출력
            Console.WriteLine("
--- 분석 결과 ---");
            Console.WriteLine($"총 데이터 수: {numericalData.Count}");
            Console.WriteLine($"최소값: {min:F2}");
            Console.WriteLine($"최대값: {max:F2}");
            Console.WriteLine($"평균: {average:F2}");
            Console.WriteLine("CSV 데이터 분석 프로그램 종료.");
        }
    }
}
