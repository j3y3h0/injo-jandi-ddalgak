using System;
using System.Collections.Generic;

namespace SalesDataAggregation;

/// <summary>
/// 판매 데이터 집계 애플리케이션의 메인 클래스입니다.
/// </summary>
public class Program
{
    /// <summary>
    /// 애플리케이션의 진입점입니다.
    /// </summary>
    public static void Main(string[] args)
    {
        Console.WriteLine("판매 데이터 분석을 시작합니다.");

        // 1. 가상의 판매 데이터 생성
        // 실제 애플리케이션에서는 데이터베이스나 파일에서 데이터를 로드할 것입니다.
        List<SalesData> salesRecords = new List<SalesData>
        {
            new SalesData("노트북", 2, 1200000m, new DateTime(2026, 3, 20)),
            new SalesData("스마트폰", 5, 800000m, new DateTime(2026, 3, 21)),
            new SalesData("노트북", 1, 1200000m, new DateTime(2026, 3, 22)),
            new SalesData("키보드", 10, 50000m, new DateTime(2026, 3, 23)),
            new SalesData("스마트폰", 3, 800000m, new DateTime(2026, 3, 24)),
            new SalesData("마우스", 15, 25000m, new DateTime(2026, 3, 25)),
            new SalesData("키보드", 5, 50000m, new DateTime(2026, 3, 26)),
            new SalesData("노트북", 3, 1200000m, new DateTime(2026, 3, 27)),
            new SalesData("헤드폰", 7, 150000m, new DateTime(2026, 3, 28))
        };

        Console.WriteLine($"총 {salesRecords.Count}개의 판매 기록을 로드했습니다.");

        // 2. 판매 데이터 처리 (제품별 총 판매액 집계)
        Console.WriteLine("판매 데이터를 집계 중입니다...");
        Dictionary<string, decimal> aggregatedSales = DataProcessor.AggregateSalesByProduct(salesRecords);
        Console.WriteLine("판매 데이터 집계가 완료되었습니다.");

        // 3. 리포트 생성 및 출력
        Console.WriteLine("
리포트를 생성합니다:");
        ReportGenerator.GenerateReport(aggregatedSales);

        Console.WriteLine("
판매 데이터 분석이 종료되었습니다. 프로그램을 종료하려면 아무 키나 누르세요.");
        // Console.ReadKey(); // CLI 환경에서 자동 종료를 위해 주석 처리
    }
}