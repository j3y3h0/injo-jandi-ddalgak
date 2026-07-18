// CsvProcessor.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace DataAnalysisApp
{
    /// <summary>
    /// CSV 파일 처리 기능을 제공하는 클래스입니다.
    /// </summary>
    public class CsvProcessor
    {
        /// <summary>
        /// 지정된 경로의 CSV 파일을 읽어 파싱합니다.
        /// </summary>
        /// <param name="filePath">읽을 CSV 파일의 경로입니다.</param>
        /// <returns>각 행이 문자열 리스트로 표현된 CSV 데이터 리스트를 반환합니다. 오류 발생 시 null을 반환합니다.</returns>
        public List<List<string>> ReadCsv(string filePath)
        {
            List<List<string>> data = new List<List<string>>();

            try
            {
                // 파일의 모든 줄을 읽습니다.
                string[] lines = File.ReadAllLines(filePath);

                foreach (string line in lines)
                {
                    // 쉼표(,)를 기준으로 각 줄을 분리합니다.
                    // 간단한 CSV 파싱을 위해 큰따옴표 처리 등은 생략합니다.
                    List<string> row = line.Split(',').ToList();
                    data.Add(row);
                }
                Console.WriteLine($"'{filePath}' 파일에서 {data.Count}개의 행을 성공적으로 읽었습니다.");
            }
            catch (FileNotFoundException)
            {
                Console.WriteLine($"오류: 지정된 CSV 파일 '{filePath}'을(를) 찾을 수 없습니다.");
                return null;
            }
            catch (IOException ex)
            {
                Console.WriteLine($"오류: CSV 파일 읽기 중 입출력 오류 발생: {ex.Message}");
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"오류: CSV 파일 처리 중 알 수 없는 오류 발생: {ex.Message}");
                return null;
            }

            return data;
        }
    }
}
