// CsvParser.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvAnalyzer
{
    /// <summary>
    /// CSV 파일을 파싱하는 기능을 제공하는 클래스입니다.
    /// </summary>
    public class CsvParser
    {
        /// <summary>
        /// 지정된 경로의 CSV 파일을 읽고 파싱합니다.
        /// </summary>
        /// <param name="filePath">CSV 파일의 경로입니다.</param>
        /// <returns>파싱된 데이터 레코드 목록입니다.</returns>
        public List<DataRecord> Parse(string filePath)
        {
            var records = new List<DataRecord>();
            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: 파일을 찾을 수 없습니다 - {filePath}");
                return records;
            }

            try
            {
                var lines = File.ReadAllLines(filePath);
                if (lines.Length == 0)
                {
                    Console.WriteLine("경고: 파일이 비어 있습니다.");
                    return records;
                }

                // 첫 번째 줄은 헤더로 간주합니다.
                string[] headers = lines[0].Split(',');

                // 나머지 줄을 데이터 레코드로 파싱합니다.
                for (int i = 1; i < lines.Length; i++)
                {
                    string[] values = lines[i].Split(',');
                    if (values.Length != headers.Length)
                    {
                        Console.WriteLine($"경고: {i + 1}번째 줄의 열 개수가 헤더와 일치하지 않습니다. 이 줄은 건너킵니다.");
                        continue;
                    }

                    var fields = new Dictionary<string, string>();
                    for (int j = 0; j < headers.Length; j++)
                    {
                        fields[headers[j].Trim()] = values[j].Trim();
                    }
                    records.Add(new DataRecord(fields));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"CSV 파일 파싱 중 오류 발생: {ex.Message}");
            }

            return records;
        }
    }
}
