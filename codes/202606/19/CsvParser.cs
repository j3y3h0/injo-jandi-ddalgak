using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace DataAnalysisProject
{
    public static class CsvParser
    {
        /// <summary>
        /// 지정된 파일 경로에서 CSV 데이터를 파싱하여 double형 숫자의 리스트를 반환합니다.
        /// 각 줄은 숫자의 리스트로 처리됩니다.
        /// </summary>
        /// <param name="filePath">CSV 파일의 경로입니다.</param>
        /// <returns>각 줄이 double 리스트인 리스트를 반환합니다. 파싱 실패 시 null을 반환할 수 있습니다.</returns>
        public static List<List<double>> Parse(string filePath)
        {
            List<List<double>> data = new List<List<double>>();

            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: CSV 파서 - '{filePath}' 파일을 찾을 수 없습니다.");
                return null;
            }

            try
            {
                var lines = File.ReadAllLines(filePath);
                foreach (var line in lines)
                {
                    if (string.IsNullOrWhiteSpace(line)) continue; // 빈 줄은 건너뛰기

                    List<double> rowNumbers = new List<double>();
                    var values = line.Split(',', StringSplitOptions.RemoveEmptyEntries); // 쉼표로 분리

                    foreach (var value in values)
                    {
                        if (double.TryParse(value.Trim(), out double number))
                        {
                            rowNumbers.Add(number);
                        }
                        else
                        {
                            // 숫자로 파싱할 수 없는 값이 있으면 경고 메시지 출력
                            Console.WriteLine($"경고: '{value.Trim()}'는 유효한 숫자가 아닙니다. 이 값은 무시됩니다.");
                        }
                    }
                    if (rowNumbers.Count > 0)
                    {
                        data.Add(rowNumbers);
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"CSV 파일 파싱 중 오류 발생: {ex.Message}");
                return null;
            }

            return data;
        }
    }
}
