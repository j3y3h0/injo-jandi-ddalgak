using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvProcessorApp
{
    // CSV 데이터를 처리하는 클래스입니다.
    public class CsvProcessor
    {
        // CSV 파일 경로를 받아 데이터를 읽고 처리 후, 결과를 다른 CSV 파일에 저장합니다.
        // inputFilePath: 원본 CSV 파일 경로
        // outputFilePath: 처리된 결과를 저장할 CSV 파일 경로
        public void ProcessCsvData(string inputFilePath, string outputFilePath)
        {
            // 입력 파일이 존재하는지 확인합니다.
            if (!File.Exists(inputFilePath))
            {
                Console.WriteLine($"오류: 입력 파일 '{inputFilePath}'을(를) 찾을 수 없습니다.");
                return;
            }

            var productSales = new Dictionary<string, int>(); // 제품별 총 수량을 저장할 딕셔너리입니다.

            try
            {
                // CSV 파일의 모든 라인을 읽어옵니다.
                // 첫 번째 라인은 헤더로 간주하여 건너뜁니다.
                var lines = File.ReadAllLines(inputFilePath).Skip(1);

                foreach (var line in lines)
                {
                    var parts = line.Split(','); // 쉼표(,)를 기준으로 라인을 분리합니다.

                    // 데이터 형식이 올바른지 확인합니다. (최소 2개의 컬럼: 제품, 수량)
                    if (parts.Length >= 2)
                    {
                        string product = parts[0].Trim(); // 제품 이름입니다.
                        if (int.TryParse(parts[1].Trim(), out int quantity)) // 수량을 정수로 파싱합니다.
                        {
                            // 딕셔너리에 제품이 이미 존재하면 수량을 더하고, 없으면 새로 추가합니다.
                            if (productSales.ContainsKey(product))
                            {
                                productSales[product] += quantity;
                            }
                            else
                            {
                                productSales.Add(product, quantity);
                            }
                        }
                        else
                        {
                            Console.WriteLine($"경고: 유효하지 않은 수량 데이터 발견: '{parts[1]}' (라인: {line})");
                        }
                    }
                    else
                    {
                        Console.WriteLine($"경고: 유효하지 않은 CSV 라인 형식 발견: '{line}'");
                    }
                }

                // 처리된 데이터를 출력 파일에 작성합니다.
                WriteProcessedDataToCsv(outputFilePath, productSales);
                Console.WriteLine($"CSV 데이터 처리가 완료되었습니다. 결과는 '{outputFilePath}'에 저장되었습니다.");
            }
            catch (Exception ex)
            {
                // 파일 처리 중 발생할 수 있는 예외를 처리합니다.
                Console.WriteLine($"CSV 처리 중 오류가 발생했습니다: {ex.Message}");
            }
        }

        // 처리된 제품별 총 수량 데이터를 CSV 파일로 작성합니다.
        // outputFilePath: 결과를 저장할 CSV 파일 경로
        // data: 제품별 총 수량 딕셔너리
        private void WriteProcessedDataToCsv(string outputFilePath, Dictionary<string, int> data)
        {
            var outputLines = new List<string>();
            outputLines.Add("Product,TotalQuantity"); // 헤더 라인을 추가합니다.

            // 딕셔너리의 각 항목을 CSV 라인 형식으로 변환하여 추가합니다.
            foreach (var entry in data)
            {
                outputLines.Add($"{entry.Key},{entry.Value}");
            }

            // 모든 라인을 출력 파일에 작성합니다.
            File.WriteAllLines(outputFilePath, outputLines);
        }
    }
}
