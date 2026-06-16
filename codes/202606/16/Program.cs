// Program.cs
// CsvDataProcessor 애플리케이션의 메인 진입점입니다.

using System;
using System.Collections.Generic;
using System.IO;

namespace CsvDataProcessor
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 처리 및 분석 프로그램 시작");

            // 1. CSV 파일 생성 (예제 데이터)
            string csvFilePath = "sample.csv";
            CreateSampleCsv(csvFilePath);

            // 2. CsvProcessor 초기화 및 CSV 파일 읽기
            CsvProcessor processor = new CsvProcessor();
            List<DataModel> data = processor.ReadCsv(csvFilePath);

            if (data.Count == 0)
            {
                Console.WriteLine("처리할 데이터가 없습니다. 프로그램을 종료합니다.");
                return;
            }

            Console.WriteLine("
--- 원본 데이터 ---");
            foreach (var item in data)
            {
                Console.WriteLine(item);
            }

            // 3. 데이터 분석 (카테고리별 합계)
            Console.WriteLine("
--- 카테고리별 값 합계 분석 ---");
            Dictionary<string, int> analysisResults = processor.AnalyzeByCategory(data);

            foreach (var entry in analysisResults)
            {
                Console.WriteLine($"카테고리: {entry.Key}, 총합: {entry.Value}");
            }

            Console.WriteLine("
CSV 데이터 처리 및 분석 프로그램 종료");
        }

        /// <summary>
        /// 예제 CSV 파일을 생성합니다.
        /// </summary>
        /// <param name="filePath">생성할 CSV 파일의 경로입니다.</param>
        static void CreateSampleCsv(string filePath)
        {
            // 헤더와 예제 데이터를 포함하는 CSV 내용입니다.
            string csvContent = "Name,Category,Value
" +
                                "상품A,전자제품,100
" +
                                "상품B,의류,200
" +
                                "상품C,전자제품,150
" +
                                "상품D,식품,50
" +
                                "상품E,의류,100";

            try
            {
                // CSV 파일을 쓰고, 파일이 이미 존재하면 덮어씁니다.
                File.WriteAllText(filePath, csvContent);
                Console.WriteLine($"예제 CSV 파일 '{filePath}'이(가) 성공적으로 생성되었습니다.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"예제 CSV 파일 생성 중 오류 발생: {ex.Message}");
            }
        }
    }
}
