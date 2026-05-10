using System;

namespace SentimentAnalysisTool
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8; // 한글 출력을 위해 UTF-8 설정

            Console.WriteLine("C# 감정 분석 도구입니다.");
            Console.WriteLine("텍스트를 입력하면 긍정, 부정 또는 중립으로 분석해 드립니다.");
            Console.WriteLine("종료하려면 'exit'을 입력하세요.");
            Console.WriteLine("--------------------------------------------------");

            SentimentAnalyzer analyzer = new SentimentAnalyzer();

            while (true)
            {
                Console.Write("텍스트를 입력하세요: ");
                string inputText = Console.ReadLine();

                if (inputText.ToLower() == "exit")
                {
                    break;
                }

                string sentiment = analyzer.Analyze(inputText);
                Console.WriteLine($"감정: {sentiment}");
                Console.WriteLine("--------------------------------------------------");
            }

            Console.WriteLine("프로그램을 종료합니다.");
        }
    }
}
