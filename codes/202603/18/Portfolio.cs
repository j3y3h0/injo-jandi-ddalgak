using System;
using System.Collections.Generic;
using System.Linq;

namespace StockPortfolioAnalyzer
{
    /// <summary>
    /// 주식 포트폴리오를 관리하는 클래스입니다.
    /// </summary>
    public class Portfolio
    {
        private List<Stock> _stocks; // 포트폴리오에 포함된 주식 목록

        /// <summary>
        /// Portfolio 클래스의 새 인스턴스를 초기화합니다.
        /// </summary>
        public Portfolio()
        {
            _stocks = new List<Stock>();
        }

        /// <summary>
        /// 포트폴리오에 주식을 추가합니다.
        /// </summary>
        /// <param name="stock">추가할 Stock 객체</param>
        public void AddStock(Stock stock)
        {
            _stocks.Add(stock);
        }

        /// <summary>
        /// 포트폴리오의 모든 주식 목록을 반환합니다.
        /// </summary>
        /// <returns>Stock 객체 목록</returns>
        public IEnumerable<Stock> GetStocks()
        {
            return _stocks;
        }

        /// <summary>
        /// 포트폴리오의 현재 총 가치를 계산합니다.
        /// </summary>
        /// <returns>포트폴리오의 현재 총 가치</returns>
        public decimal GetTotalCurrentValue()
        {
            return _stocks.Sum(s => s.GetCurrentValue());
        }

        /// <summary>
        /// 포트폴리오의 총 매수 가치를 계산합니다.
        /// </summary>
        /// <returns>포트폴리오의 총 매수 가치</returns>
        public decimal GetTotalPurchaseValue()
        {
            return _stocks.Sum(s => s.GetPurchaseValue());
        }
    }
}
