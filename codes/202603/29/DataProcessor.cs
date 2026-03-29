using System;
using System.Collections.Generic;
using System.Linq;

namespace SalesDataAggregation;

/// <summary>
/// 판매 데이터를 처리하고 집계하는 기능을 제공합니다.
/// </summary>
public static class DataProcessor
{
    /// <summary>
    /// 주어진 판매 데이터 목록에서 제품별 총 판매액을 집계합니다.
    /// </summary>
    /// <param name="salesRecords">판매 기록 목록입니다.</param>
    /// <returns>제품명을 키로 하고 총 판매액을 값으로 하는 사전입니다.</returns>
    public static Dictionary<string, decimal> AggregateSalesByProduct(List<SalesData> salesRecords)
    {
        // 제품별 총 판매액을 저장할 사전입니다.
        var aggregatedSales = new Dictionary<string, decimal>();

        foreach (var record in salesRecords)
        {
            // 각 판매 기록의 총 판매액을 계산합니다.
            decimal saleAmount = record.Quantity * record.UnitPrice;

            // 이미 해당 제품이 사전에 있는지 확인합니다.
            if (aggregatedSales.ContainsKey(record.ProductName))
            {
                // 있으면 기존 판매액에 더합니다.
                aggregatedSales[record.ProductName] += saleAmount;
            }
            else
            {
                // 없으면 새로 추가합니다.
                aggregatedSales.Add(record.ProductName, saleAmount);
            }
        }

        return aggregatedSales;
    }
}