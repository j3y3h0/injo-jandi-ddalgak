using System;

namespace NewsSentimentAnalyzer
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("뉴스 헤드라인 감성 분석기");
            Console.WriteLine("분석할 뉴스 헤드라인이나 문장을 입력하세요 (종료하려면 'exit' 입력):");

            var analyzer = new SentimentAnalyzer();

            while (true)
            {
                Console.Write("> ");
                string? input = Console.ReadLine();

                if (string.IsNullOrWhiteSpace(input) || input.ToLowerInvariant() == "exit")
                {
                    Console.WriteLine("감성 분석기를 종료합니다.");
                    break;
                }

                string sentiment = analyzer.AnalyzeSentiment(input);
                Console.WriteLine($"분석 결과: {sentiment}");
                Console.WriteLine();
            }
        }
    }
}
