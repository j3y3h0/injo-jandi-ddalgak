// DataGenerator.cs

using System;
using System.Collections.Generic;
using System.IO;

namespace CsvProcessorApp
{
    /// <summary>
    /// 샘플 CSV 데이터를 생성하는 유틸리티 클래스입니다.
    /// </summary>
    public class DataGenerator
    {
        private static readonly Random _random = new Random();
        private static readonly string[] _names = { "김철수", "이영희", "박지성", "손흥민", "김연아", "유재석", "강호동", "신동엽", "아이유", "BTS" };
        private static readonly string[] _cities = { "서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종" };

        /// <summary>
        /// 지정된 경로에 샘플 Person 데이터를 포함하는 CSV 파일을 생성합니다.
        /// </summary>
        /// <param name="filePath">생성될 CSV 파일의 경로입니다.</param>
        /// <param name="count">생성할 데이터의 수입니다.</param>
        public void GenerateSampleCsv(string filePath, int count)
        {
            List<string> lines = new List<string>
            {
                "Name,Age,City" // 헤더 줄
            };

            for (int i = 0; i < count; i++)
            {
                string name = _names[_random.Next(_names.Length)];
                int age = _random.Next(20, 60); // 20세에서 59세 사이
                string city = _cities[_random.Next(_cities.Length)];
                lines.Add($"{name},{age},{city}");
            }

            try
            {
                File.WriteAllLines(filePath, lines);
                Console.WriteLine($"샘플 CSV 파일 '{filePath}' ({count} 줄)이 성공적으로 생성되었습니다.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"샘플 CSV 파일 생성 중 오류 발생: {ex.Message}");
            }
        }
    }
}
