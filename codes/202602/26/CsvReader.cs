// CsvReader.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvProcessor
{
    /// <summary>
    /// CSV 파일을 읽고 파싱하는 유틸리티 클래스입니다.
    /// </summary>
    public static class CsvReader
    {
        /// <summary>
        /// 지정된 경로의 CSV 파일을 읽어 각 레코드를 Dictionary<string, string> 리스트로 반환합니다.
        /// 첫 번째 줄은 헤더로 간주됩니다.
        /// </summary>
        /// <param name="filePath">읽을 CSV 파일의 경로입니다.</param>
        /// <returns>CSV 파일의 레코드 리스트입니다.</returns>
        /// <exception cref="FileNotFoundException">파일을 찾을 수 없는 경우 발생합니다.</exception>
        /// <exception cref="InvalidDataException">CSV 형식이 올바르지 않은 경우 발생합니다.</exception>
        public static List<Dictionary<string, string>> ReadCsv(string filePath)
        {
            if (!File.Exists(filePath))
            {
                throw new FileNotFoundException($"파일을 찾을 수 없습니다: {filePath}");
            }

            List<Dictionary<string, string>> records = new List<Dictionary<string, string>>();
            string[] lines = File.ReadAllLines(filePath);

            if (lines.Length == 0)
            {
                return records; // 빈 파일
            }

            // 첫 번째 줄은 헤더입니다.
            string[] headers = lines[0].Split(',');

            for (int i = 1; i < lines.Length; i++)
            {
                string[] values = lines[i].Split(',');

                if (values.Length != headers.Length)
                {
                    // 헤더와 값의 개수가 일치하지 않으면 오류를 발생시킵니다.
                    throw new InvalidDataException($"CSV 데이터 형식이 올바르지 않습니다. {i + 1}번째 줄에서 헤더 수({headers.Length})와 값 수({values.Length})가 일치하지 않습니다.");
                }

                Dictionary<string, string> record = new Dictionary<string, string>();
                for (int j = 0; j < headers.Length; j++)
                {
                    record[headers[j].Trim()] = values[j].Trim();
                }
                records.Add(record);
            }

            return records;
        }
    }
}