using System;
using System.Collections.Generic;
using System.Threading;
using System.Linq;

namespace StockAnomalyDetection
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("실시간 주식 데이터 이상 감지 시스템 시작...");

            // 주식 분석기 및 이상 감지기 인스턴스 생성
            var stockAnalyzer = new StockAnalyzer();
            // 20개 데이터 포인트를 기반으로 이동 평균 계산, 표준 편차의 2.5배를 임계값으로 설정
            var anomalyDetector = new AnomalyDetector(windowSize: 20, thresholdMultiplier: 2.5m);

            // 주식 데이터 시뮬레이션
            SimulateStockData(stockAnalyzer, anomalyDetector);

            Console.WriteLine("실시간 주식 데이터 이상 감지 시스템 종료.");
        }

        /// <summary>
        /// 주식 데이터를 시뮬레이션하고 이상 감지 로직을 적용한다.
        /// </summary>
        /// <param name="analyzer">StockAnalyzer 인스턴스</param>
        /// <param name="detector">AnomalyDetector 인스턴스</param>
        static void SimulateStockData(StockAnalyzer analyzer, AnomalyDetector detector)
        {
            string symbol = "GOOGL"; // 시뮬레이션할 주식 종목
            decimal currentPrice = 1500.00m; // 시작 가격
            Random random = new Random();
            DateTime currentTime = DateTime.Now;

            // 100개의 데이터 틱을 시뮬레이션한다.
            for (int i = 0; i < 100; i++)
            {
                // 가격 변동 시뮬레이션 (작은 랜덤 변동)
                currentPrice += (decimal)(random.NextDouble() * 10 - 5); // -5.00 ~ +5.00 사이 변동
                currentPrice = Math.Round(currentPrice, 2); // 소수점 둘째 자리까지 반올림

                // 특정 시점에 의도적인 이상 값 생성
                if (i == 30) // 30번째 틱에서 가격 급등
                {
                    currentPrice += 100.00m;
                    Console.WriteLine($"[시뮬레이션]: {symbol} 의도적인 급등 발생! 현재 가격: {currentPrice:F2}");
                }
                else if (i == 70) // 70번째 틱에서 가격 급락
                {
                    currentPrice -= 80.00m;
                    Console.WriteLine($"[시뮬레이션]: {symbol} 의도적인 급락 발생! 현재 가격: {currentPrice:F2}");
                }

                // 새로운 StockTick 생성 및 추가
                var newTick = new StockTick(currentTime.AddSeconds(i), symbol, currentPrice, random.Next(100, 1000));
                analyzer.AddTick(newTick);
                Console.WriteLine($"처리 중: {newTick}");

                // 최근 가격 데이터로 이상 감지 수행
                var recentPrices = analyzer.GetRecentPrices(detector.WindowSize); 
                string anomalyMessage = detector.Detect(newTick, recentPrices);

                if (!string.IsNullOrEmpty(anomalyMessage))
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine(anomalyMessage);
                    Console.ResetColor();
                }

                // 약간의 지연으로 실시간 데이터 유입 시뮬레이션
                Thread.Sleep(50); // 50ms 지연
            }
        }
    }
}
