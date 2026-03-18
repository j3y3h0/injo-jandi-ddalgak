using System;

namespace StockPortfolioAnalyzer
{
    /// <summary>
    /// 재무 관련 계산을 위한 유틸리티 클래스입니다.
    /// </summary>
    public static class FinancialAnalysis
    {
        /// <summary>
        /// 특정 주식의 수익률을 백분율로 계산합니다.
        /// </summary>
        /// <param name="purchasePrice">매수 가격</param>
        /// <param name="currentPrice">현재 가격</param>
        /// <returns>수익률 (백분율)</returns>
        public static decimal CalculateReturnPercentage(decimal purchasePrice, decimal currentPrice)
        {
            if (purchasePrice == 0)
            {
                return 0; // 매수 가격이 0이면 수익률도 0으로 간주
            }
            return ((currentPrice - purchasePrice) / purchasePrice) * 100;
        }

        /// <summary>
        /// 총 수익 또는 손실 금액을 계산합니다.
        /// </summary>
        /// <param name="purchaseValue">총 매수 가치</param>
        /// <param name="currentValue">총 현재 가치</param>
        /// <returns>총 수익 또는 손실 금액</returns>
        public static decimal CalculateProfitLoss(decimal purchaseValue, decimal currentValue)
        {
            return currentValue - purchaseValue;
        }
    }
}
