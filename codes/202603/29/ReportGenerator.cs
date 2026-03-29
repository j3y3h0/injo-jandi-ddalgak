using System;
using System.Collections.Generic;
using System.Linq;

namespace SalesDataAggregation;

/// <summary>
/// 집계된 판매 데이터를 기반으로 리포트를 생성하고 출력합니다.
/// </summary>
public static class ReportGenerator
{
    /// <summary>
    /// 제품별 총 판매액을 콘솔에 형식화하여 출력합니다.
    /// </summary>
    /// <param name="aggregatedSales">제품명과 총 판매액이 담긴 사전입니다.</param>
    public static void GenerateReport(Dictionary<string, decimal> aggregatedSales)
    {
        Console.WriteLine("----------------------------------------");
        Console.WriteLine("          판매 데이터 요약 리포트         ");
        Console.WriteLine("----------------------------------------");
        Console.WriteLine("{0,-20} {1,15}", "제품명", "총 판매액");
        Console.WriteLine("----------------------------------------");

        // 판매액이 높은 순서대로 정렬하여 출력합니다.
        var sortedSales = aggregatedSales.OrderByDescending(kvp => kvp.Value);

        foreach (var entry in sortedSales)
        {
            Console.WriteLine("{0,-20} {1,15:N0}원", entry.Key, entry.Value);
        }

        Console.WriteLine("----------------------------------------");
        Console.WriteLine("리포트 생성 완료.");
    }
}