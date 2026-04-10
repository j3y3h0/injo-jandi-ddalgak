using System;
using System.Text.Json.Serialization; // JSON 직렬화를 위해 필요

namespace SalesAnalyzer
{
    /// <summary>
    /// 단일 판매 기록을 나타내는 모델입니다.
    /// </summary>
    public class SaleRecord
    {
        // 제품 이름을 나타냅니다.
        [JsonPropertyName("productName")] // JSON 키와 매핑
        public string ProductName { get; set; } = string.Empty;

        // 판매 금액을 나타냅니다.
        [JsonPropertyName("amount")] // JSON 키와 매핑
        public decimal Amount { get; set; }

        public override string ToString()
        {
            return $"제품: {ProductName}, 금액: {Amount:C}";
        }
    }

    /// <summary>
    /// 제품별 총 판매액을 나타내는 모델입니다.
    /// </summary>
    public class ProductSales
    {
        // 제품 이름을 나타냅니다.
        public string ProductName { get; set; } = string.Empty;

        // 해당 제품의 총 판매 금액을 나타냅니다.
        public decimal TotalAmount { get; set; }

        public override string ToString()
        {
            return $"제품: {ProductName}, 총 판매액: {TotalAmount:C}";
        }
    }
}
