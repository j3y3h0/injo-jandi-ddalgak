using System;
using System.Collections.Generic;
using System.Linq; // Linq를 사용하기 위해 추가

namespace StockPortfolioAnalyzer
{
    /// <summary>
    /// 주식 포트폴리오 분석 애플리케이션의 메인 클래스입니다.
    /// 사용자 입력을 처리하고 포트폴리오 분석 결과를 출력합니다.
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8; // 한글 출력을 위해 설정
            Console.WriteLine("=".PadRight(50, '='));
            Console.WriteLine("        주식 포트폴리오 분석기".PadLeft(30));
            Console.WriteLine("=".PadRight(50, '='));
            Console.WriteLine();

            Portfolio portfolio = new Portfolio();

            while (true)
            {
                Console.WriteLine("주식 정보를 입력해주세요 (종료: 'q')");
                Console.Write("주식 코드 (예: AAPL): ");
                string symbol = Console.ReadLine();
                if (string.IsNullOrWhiteSpace(symbol) || symbol?.ToLower() == "q")
                {
                    break;
                }

                Console.Write("주식 이름 (예: Apple Inc.): ");
                string name = Console.ReadLine();
                if (string.IsNullOrWhiteSpace(name))
                {
                    Console.WriteLine("주식 이름은 필수입니다. 다시 입력해주세요.");
                    continue;
                }

                Console.Write("매수 가격: ");
                decimal purchasePrice;
                while (!decimal.TryParse(Console.ReadLine(), out purchasePrice) || purchasePrice < 0)
                {
                    Console.WriteLine("유효하지 않은 가격입니다. 양수 값을 입력해주세요.");
                    Console.Write("매수 가격: ");
                }

                Console.Write("현재 가격: ");
                decimal currentPrice;
                while (!decimal.TryParse(Console.ReadLine(), out currentPrice) || currentPrice < 0)
                {
                    Console.WriteLine("유효하지 않은 가격입니다. 양수 값을 입력해주세요.");
                    Console.Write("현재 가격: ");
                }

                Console.Write("수량: ");
                int quantity;
                while (!int.TryParse(Console.ReadLine(), out quantity) || quantity <= 0)
                {
                    Console.WriteLine("유효하지 않은 수량입니다. 1 이상의 정수를 입력해주세요.");
                    Console.Write("수량: ");
                }

                Stock stock = new Stock(symbol, name, purchasePrice, currentPrice, quantity);
                portfolio.AddStock(stock);
                Console.WriteLine("
주식이 포트폴리오에 추가되었습니다.
");
            }

            Console.WriteLine("

--- 포트폴리오 분석 결과 ---");
            if (!portfolio.GetStocks().Any())
            {
                Console.WriteLine("포트폴리오에 주식이 없습니다.");
            }
            else
            {
                foreach (var stock in portfolio.GetStocks())
                {
                    Console.WriteLine($"
--- {stock.Name} ({stock.Symbol}) ---");
                    Console.WriteLine($"  보유 수량: {stock.Quantity} 주");
                    Console.WriteLine($"  매수 총액: {stock.GetPurchaseValue():C}");
                    Console.WriteLine($"  현재 총액: {stock.GetCurrentValue():C}");
                    decimal profitLoss = FinancialAnalysis.CalculateProfitLoss(stock.GetPurchaseValue(), stock.GetCurrentValue());
                    decimal returnPercentage = FinancialAnalysis.CalculateReturnPercentage(stock.PurchasePrice, stock.CurrentPrice);
                    Console.WriteLine($"  손익: {profitLoss:C} ({returnPercentage:F2}%)");
                }

                Console.WriteLine("
--- 전체 포트폴리오 요약 ---");
                decimal totalPurchaseValue = portfolio.GetTotalPurchaseValue();
                decimal totalCurrentValue = portfolio.GetTotalCurrentValue();
                decimal totalProfitLoss = FinancialAnalysis.CalculateProfitLoss(totalPurchaseValue, totalCurrentValue);
                decimal totalReturnPercentage = FinancialAnalysis.CalculateReturnPercentage(totalPurchaseValue, totalCurrentValue);

                Console.WriteLine($"  총 매수 금액: {totalPurchaseValue:C}");
                Console.WriteLine($"  총 현재 가치: {totalCurrentValue:C}");
                Console.WriteLine($"  총 손익: {totalProfitLoss:C} ({totalReturnPercentage:F2}%)");
            }

            Console.WriteLine("

분석이 완료되었습니다. 아무 키나 눌러 종료하세요.");
            Console.ReadKey();
        }
    }
}
