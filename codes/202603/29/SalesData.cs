namespace SalesDataAggregation;

/// <summary>
/// 개별 판매 기록을 나타내는 레코드입니다.
/// </summary>
public record SalesData(
    string ProductName,  // 제품명
    int Quantity,        // 판매 수량
    decimal UnitPrice,   // 단가
    DateTime SaleDate    // 판매 날짜
);