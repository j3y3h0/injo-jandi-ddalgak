using System;
using System.Collections.Generic;
using System.Linq;

namespace StockAnomalyDetection
{
    /// <summary>
    /// 개별 주식 거래 시점의 데이터를 나타내는 클래스.
    /// </summary>
    public class StockTick
    {
        public DateTime Timestamp { get; set; } // 거래 발생 시각
        public string Symbol { get; set; }       // 주식 종목 코드 (예: "AAPL")
        public decimal Price { get; set; }       // 거래 가격
        public long Volume { get; set; }         // 거래량

        public StockTick(DateTime timestamp, string symbol, decimal price, long volume)
        {
            Timestamp = timestamp;
            Symbol = symbol;
            Price = price;
            Volume = volume;
        }

        public override string ToString()
        {
            return $"[{Timestamp:HH:mm:ss}] {Symbol}: 가격={Price:F2}, 거래량={Volume}";
        }
    }

    /// <summary>
    /// 특정 주식 종목의 데이터 컬렉션을 관리하고 기본 통계 계산을 수행하는 클래스.
    /// 이 예시에서는 주로 AnomalyDetector에서 사용될 가격 데이터 추출 기능을 제공한다.
    /// </summary>
    public class StockAnalyzer
    {
        private readonly List<StockTick> _ticks = new List<StockTick>();

        /// <summary>
        /// 새로운 주식 데이터를 추가한다.
        /// </summary>
        /// <param name="tick">추가할 StockTick 객체</param>
        public void AddTick(StockTick tick)
        {
            _ticks.Add(tick);
        }

        /// <summary>
        /// 최근 N개의 주식 가격 데이터를 반환한다.
        /// </summary>
        /// <param name="windowSize">가져올 데이터의 개수</param>
        /// <returns>최근 N개의 가격 목록</returns>
        public IEnumerable<decimal> GetRecentPrices(int windowSize)
        {
            // 가장 최근 데이터부터 windowSize 만큼의 가격을 가져온다.
            return _ticks.Skip(Math.Max(0, _ticks.Count - windowSize)).Select(t => t.Price);
        }

        /// <summary>
        /// 현재까지 기록된 모든 주식 데이터를 반환한다.
        /// </summary>
        public IReadOnlyList<StockTick> AllTicks => _ticks;
    }
}
