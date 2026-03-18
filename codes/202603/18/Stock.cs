using System;

namespace StockPortfolioAnalyzer
{
    /// <summary>
    /// 개별 주식의 정보를 나타내는 클래스입니다.
    /// </summary>
    public class Stock
    {
        public string Symbol { get; set; } // 주식 코드 (예: AAPL, MSFT)
        public string Name { get; set; } // 주식 이름 (예: Apple Inc., Microsoft Corp.)
        public decimal PurchasePrice { get; set; } // 매수 가격
        public decimal CurrentPrice { get; set; } // 현재 가격
        public int Quantity { get; set; } // 보유 수량

        /// <summary>
        /// Stock 클래스의 새 인스턴스를 초기화합니다.
        /// </summary>
        /// <param name="symbol">주식 코드</param>
        /// <param name="name">주식 이름</param>
        /// <param name="purchasePrice">매수 가격</param>
        /// <param name="currentPrice">현재 가격</param>
        /// <param name="quantity">보유 수량</param>
        public Stock(string symbol, string name, decimal purchasePrice, decimal currentPrice, int quantity)
        {
            Symbol = symbol;
            Name = name;
            PurchasePrice = purchasePrice;
            CurrentPrice = currentPrice;
            Quantity = quantity;
        }

        /// <summary>
        /// 이 주식의 현재 총 가치를 계산합니다.
        /// </summary>
        /// <returns>현재 총 가치</returns>
        public decimal GetCurrentValue()
        {
            return CurrentPrice * Quantity;
        }

        /// <summary>
        /// 이 주식의 매수 총 가치를 계산합니다.
        /// </summary>
        /// <returns>매수 총 가치</returns>
        public decimal GetPurchaseValue()
        {
            return PurchasePrice * Quantity;
        }
    }
}
