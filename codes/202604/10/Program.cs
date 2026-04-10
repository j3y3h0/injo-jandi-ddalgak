using System;
using System.Collections.Generic;
using System.IO;
using System.Threading.Tasks; // 비동기 작업을 위해 필요

namespace SalesAnalyzer
{
    class Program
    {
        // 메인 진입점
        static async Task Main(string[] args) // 비동기 Main 메서드 사용
        {
            Console.WriteLine("판매 데이터 분석을 시작합니다.");

            // 데이터 파일 경로 설정
            string dataFilePath = "SampleData.json";

            // DataProcessor 인스턴스 생성
            DataProcessor processor = new DataProcessor();

            try
            {
                // 판매 데이터 로드
                Console.WriteLine($"'{dataFilePath}'에서 판매 데이터를 로드 중...");
                List<SaleRecord> sales = await processor.LoadSalesData(dataFilePath); // 비동기 호출

                if (sales == null || sales.Count == 0)
                {
                    Console.WriteLine("로드할 판매 데이터가 없습니다.");
                    return;
                }

                Console.WriteLine($"총 {sales.Count}개의 판매 기록이 로드되었습니다.");

                // 판매 데이터 분석 (제품별 총 판매액 계산)
                Console.WriteLine("판매 데이터를 분석 중...");
                List<ProductSales> analyzedSales = processor.AnalyzeSalesData(sales);

                // 분석 결과 출력
                Console.WriteLine("
--- 판매 분석 결과 ---");
                foreach (var item in analyzedSales)
                {
                    Console.WriteLine($"제품: {item.ProductName}, 총 판매액: {item.TotalAmount:C}");
                }
                Console.WriteLine("----------------------");
            }
            catch (FileNotFoundException)
            {
                Console.WriteLine($"오류: 데이터 파일 '{dataFilePath}'을(를) 찾을 수 없습니다. 파일이 존재하고 올바른 위치에 있는지 확인하세요.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"데이터 처리 중 오류가 발생했습니다: {ex.Message}");
                Console.WriteLine(ex.ToString()); // 자세한 오류 정보 출력
            }

            Console.WriteLine("
판매 데이터 분석이 완료되었습니다.");
        }
    }
}
