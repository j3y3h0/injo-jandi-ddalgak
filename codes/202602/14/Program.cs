// Program.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvAnalyzer
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 분석 프로그램 시작.");

            // 1. 샘플 CSV 파일 생성 (실행을 위해 필요)
            string sampleCsvPath = "sample.csv";
            CreateSampleCsvFile(sampleCsvPath);

            // 2. CSV 파일 파싱
            CsvParser parser = new CsvParser();
            List<DataRecord> records = parser.Parse(sampleCsvPath);

            if (records.Any())
            {
                Console.WriteLine("
CSV 파일 파싱 완료. 다음은 첫 3개 레코드입니다:");
                foreach (var record in records.Take(3))
                {
                    Console.WriteLine($"  {string.Join(", ", record.Fields.Select(f => $"{f.Key}: {f.Value}"))}");
                }

                // 3. 통계 계산 (예: "Age" 또는 "Score" 열에 대한 통계)
                Console.WriteLine("
--- 데이터 통계 분석 (예: 'Score' 열) ---");
                List<double> scores = new List<double>();
                foreach (var record in records)
                {
                    string scoreString = record.GetValue("Score");
                    if (double.TryParse(scoreString, out double score))
                    {
                        scores.Add(score);
                    }
                }

                StatisticsCalculator stats = new StatisticsCalculator();
                Console.WriteLine($"  Score 평균: {stats.CalculateMean(scores):F2}");
                Console.WriteLine($"  Score 중앙값: {stats.CalculateMedian(scores):F2}");
                Console.WriteLine($"  Score 표준 편차: {stats.CalculateStandardDeviation(scores):F2}");
            }
            else
            {
                Console.WriteLine("
파싱된 데이터가 없습니다. 분석을 진행할 수 없습니다.");
            }

            Console.WriteLine("
CSV 데이터 분석 프로그램 종료.");
        }

        /// <summary>
        /// 샘플 CSV 파일을 생성합니다.
        /// </summary>
        /// <param name="filePath">생성할 파일의 경로입니다.</param>
        static void CreateSampleCsvFile(string filePath)
        {
            Console.WriteLine($"샘플 CSV 파일 '{filePath}' 생성 중...");
            string[] lines = {
                "Name,Age,Score,City",
                "김철수,30,85.5,서울",
                "이영희,24,92.0,부산",
                "박민수,35,78.2,대구",
                "최수진,28,88.9,서울",
                "정준호,42,75.1,인천",
                "강지혜,22,95.3,부산",
                "한동현,31,81.7,광주",
                "유은서,29,89.5,대전",
                "송현우,26,70.0,서울",
                "윤서영,33,91.2,부산"
            };
            File.WriteAllLines(filePath, lines);
            Console.WriteLine("샘플 CSV 파일 생성 완료.");
        }
    }
}
