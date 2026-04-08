using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace DataAnalysis
{
    public class DataProcessor
    {
        // CSV 파일 경로
        private readonly string _filePath;

        // 생성자: 파일 경로를 초기화합니다.
        public DataProcessor(string filePath)
        {
            _filePath = filePath ?? throw new ArgumentNullException(nameof(filePath));
        }

        /// <summary>
        /// CSV 파일에서 숫자 데이터를 읽어옵니다.
        /// 첫 번째 줄(헤더)을 건너뛰고 각 줄을 숫자로 파싱합니다.
        /// </summary>
        /// <returns>CSV 파일에서 읽어온 숫자 데이터 목록</returns>
        public List<double> ReadData()
        {
            var data = new List<double>();

            // 파일이 존재하는지 확인합니다.
            if (!File.Exists(_filePath))
            {
                Console.WriteLine($"오류: 파일을 찾을 수 없습니다 - {_filePath}");
                return data;
            }

            try
            {
                // 파일을 모든 줄을 읽어옵니다.
                var lines = File.ReadAllLines(_filePath);

                // 첫 번째 줄(헤더)을 건너뛰고 데이터를 파싱합니다.
                foreach (var line in lines.Skip(1)) // Skip(1)은 헤더를 건너뜁니다.
                {
                    // 각 줄에서 숫자를 파싱합니다.
                    if (double.TryParse(line.Trim(), out double value))
                    {
                        data.Add(value);
                    }
                    else
                    {
                        Console.WriteLine($"경고: 유효하지 않은 데이터가 발견되었습니다 - '{line.Trim()}'");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"파일을 읽는 중 오류가 발생했습니다: {ex.Message}");
            }

            return data;
        }

        /// <summary>
        /// 주어진 숫자 데이터 목록에 대한 통계 정보를 계산합니다.
        /// (평균, 최소값, 최대값)
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 목록</param>
        /// <returns>통계 정보를 담은 튜플 (평균, 최소, 최대)</returns>
        public (double average, double min, double max) CalculateStatistics(List<double> data)
        {
            if (data == null || !data.Any())
            {
                // 데이터가 없을 경우 기본값 반환
                return (0, 0, 0);
            }

            // LINQ를 사용하여 평균, 최소값, 최대값을 계산합니다.
            double average = data.Average();
            double min = data.Min();
            double max = data.Max();

            return (average, min, max);
        }
    }
}
