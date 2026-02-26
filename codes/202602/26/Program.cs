// Program.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvProcessor
{
    public class Program
    {
        public static void Main(string[] args)
        {
            // CSV 파일 경로를 지정합니다.
            string filePath = "sample_data.csv";
            
            // 샘플 CSV 파일이 없으면 생성합니다.
            if (!File.Exists(filePath))
            {
                Console.WriteLine("샘플 CSV 파일이 존재하지 않아 생성합니다.");
                CreateSampleCsv(filePath);
            }

            Console.WriteLine($"CSV 파일 '{filePath}'을(를) 처리합니다.");

            try
            {
                // CsvReader를 사용하여 CSV 파일을 읽습니다.
                List<Dictionary<string, string>> records = CsvReader.ReadCsv(filePath);

                // 읽은 데이터를 콘솔에 출력합니다.
                Console.WriteLine("
--- 원본 데이터 ---");
                foreach (var record in records)
                {
                    Console.WriteLine(string.Join(", ", record.Select(kv => $"{kv.Key}: {kv.Value}")));
                }

                // DataProcessor를 사용하여 데이터 평균을 계산합니다.
                // 'Value'라는 컬럼의 평균을 가정합니다.
                double averageValue = DataProcessor.CalculateAverage(records, "Value");
                Console.WriteLine($"
'Value' 컬럼의 평균: {averageValue:F2}");

                // DataProcessor를 사용하여 특정 조건으로 데이터를 필터링합니다.
                // 'Category'가 'A'인 데이터를 필터링합니다.
                List<Dictionary<string, string>> filteredRecords = DataProcessor.FilterData(records, "Category", "A");
                Console.WriteLine("
--- 'Category'가 'A'인 데이터 ---");
                foreach (var record in filteredRecords)
                {
                    Console.WriteLine(string.Join(", ", record.Select(kv => $"{kv.Key}: {kv.Value}")));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"오류 발생: {ex.Message}");
            }

            Console.WriteLine("
처리 완료.");
        }

        /// <summary>
        /// 샘플 CSV 파일을 생성합니다.
        /// </summary>
        /// <param name="filePath">생성할 파일 경로입니다.</param>
        private static void CreateSampleCsv(string filePath)
        {
            string[] lines = new string[]
            {
                "ID,Name,Category,Value",
                "1,ItemA,A,100",
                "2,ItemB,B,150",
                "3,ItemC,A,200",
                "4,ItemD,C,120",
                "5,ItemE,B,180"
            };
            File.WriteAllLines(filePath, lines);
        }
    }
}