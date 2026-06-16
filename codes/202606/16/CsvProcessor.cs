// CsvProcessor.cs
// CSV 파일을 읽고 파싱하며, 데이터 분석 기능을 제공하는 클래스입니다.

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvDataProcessor
{
    public class CsvProcessor
    {
        /// <summary>
        /// 주어진 CSV 파일 경로에서 데이터를 읽어 DataModel 객체 리스트로 파싱합니다.
        /// 첫 번째 줄은 헤더로 간주하고 건너뜁니다.
        /// </summary>
        /// <param name="filePath">CSV 파일의 경로입니다.</param>
        /// <returns>파싱된 DataModel 객체 리스트입니다.</returns>
        public List<DataModel> ReadCsv(string filePath)
        {
            List<DataModel> dataList = new List<DataModel>();

            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: 파일이 존재하지 않습니다 - {filePath}");
                return dataList;
            }

            try
            {
                // 모든 라인을 읽고, 첫 번째(헤더) 라인은 건너뜁니다.
                var lines = File.ReadAllLines(filePath).Skip(1);

                foreach (var line in lines)
                {
                    if (string.IsNullOrWhiteSpace(line)) continue;

                    var parts = line.Split(',');

                    // 예상되는 컬럼 수 (Name, Category, Value)
                    if (parts.Length == 3)
                    {
                        if (int.TryParse(parts[2], out int value))
                        {
                            dataList.Add(new DataModel
                            {
                                Name = parts[0].Trim(),
                                Category = parts[1].Trim(),
                                Value = value
                            });
                        }
                        else
                        {
                            Console.WriteLine($"경고: 유효하지 않은 값 형식, 라인 건너뜀: {line}");
                        }
                    }
                    else
                    {
                        Console.WriteLine($"경고: 유효하지 않은 컬럼 수, 라인 건너뜀: {line}");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"CSV 파일을 읽는 중 오류 발생: {ex.Message}");
            }

            return dataList;
        }

        /// <summary>
        /// 카테고리별로 데이터의 합계를 계산합니다.
        /// </summary>
        /// <param name="dataList">처리할 DataModel 객체 리스트입니다.</param>
        /// <returns>카테고리별 합계를 담은 딕셔너리입니다.</returns>
        public Dictionary<string, int> AnalyzeByCategory(List<DataModel> dataList)
        {
            // LINQ를 사용하여 카테고리별로 그룹화하고 값의 합계를 계산합니다.
            var results = dataList.GroupBy(data => data.Category)
                                  .ToDictionary(group => group.Key,
                                                group => group.Sum(data => data.Value));
            return results;
        }
    }
}
