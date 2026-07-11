// CsvProcessor.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvDataProcessor
{
    /// <summary>
    /// CSV 파일을 처리하고 분석하는 기능을 제공하는 클래스이다.
    /// </summary>
    public class CsvProcessor
    {
        /// <summary>
        /// CSV 파일을 읽어와 지정된 컬럼의 숫자 값 평균을 계산한다.
        /// </summary>
        /// <param name="filePath">처리할 CSV 파일의 경로이다.</param>
        /// <param name="columnName">평균을 계산할 컬럼의 이름이다.</param>
        /// <returns>지정된 컬럼의 숫자 값 평균이다. 파일을 찾을 수 없거나 데이터가 유효하지 않으면 0을 반환한다.</returns>
        public double CalculateColumnAverage(string filePath, string columnName)
        {
            // 파일이 존재하는지 확인한다.
            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: 파일을 찾을 수 없습니다 - {filePath}");
                return 0;
            }

            // CSV 파일의 모든 라인을 읽어온다.
            List<string> lines = File.ReadAllLines(filePath).ToList();

            // 파일에 내용이 없으면 처리하지 않는다.
            if (lines.Count == 0)
            {
                Console.WriteLine("오류: CSV 파일이 비어 있습니다.");
                return 0;
            }

            // 첫 번째 라인은 헤더로 간주한다.
            string headerLine = lines[0];
            string[] headers = headerLine.Split(','); // 쉼표로 헤더를 분리한다.

            // 분석할 컬럼의 인덱스를 찾는다.
            int columnIndex = Array.IndexOf(headers, columnName);
            if (columnIndex == -1)
            {
                Console.WriteLine($"오류: CSV 파일에서 컬럼 '{columnName}'을(를) 찾을 수 없습니다.");
                return 0;
            }

            List<double> values = new List<double>();
            // 헤더를 제외한 나머지 라인을 처리한다.
            for (int i = 1; i < lines.Count; i++)
            {
                string[] fields = lines[i].Split(','); // 각 라인을 쉼표로 분리한다.
                if (fields.Length > columnIndex)
                {
                    // 해당 컬럼의 값을 가져와 숫자로 변환한다.
                    if (double.TryParse(fields[columnIndex], out double value))
                    {
                        values.Add(value);
                    }
                    else
                    {
                        Console.WriteLine($"경고: 라인 {i + 1}의 '{columnName}' 컬럼 값 '{fields[columnIndex]}'이(가) 유효한 숫자가 아닙니다. 이 값은 무시됩니다.");
                    }
                }
            }

            // 유효한 숫자 값이 없으면 평균을 계산할 수 없다.
            if (values.Count == 0)
            {
                Console.WriteLine($"경고: '{columnName}' 컬럼에 유효한 숫자 값이 없습니다.");
                return 0;
            }

            // 평균을 계산하고 반환한다.
            return values.Average();
        }
    }
}
