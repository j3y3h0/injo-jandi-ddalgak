using System;
using System.Linq;

namespace TextAnalysisApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("텍스트 분석 시뮬레이터입니다.");
            Console.WriteLine("분석할 텍스트를 입력하거나, 'exit'를 입력하여 종료하세요.");

            var analyzer = new TextAnalyzer();

            while (true)
            {
                Console.WriteLine("
----------------------------------------");
                Console.Write("입력 (또는 'exit'): ");
                string inputText = Console.ReadLine();

                if (inputText?.ToLower() == "exit")
                {
                    Console.WriteLine("프로그램을 종료합니다.");
                    break;
                }

                if (string.IsNullOrWhiteSpace(inputText))
                {
                    Console.WriteLine("분석할 텍스트를 입력해주세요.");
                    continue;
                }

                // 텍스트 분석 수행
                AnalysisResult result = analyzer.Analyze(inputText);

                // 분석 결과 출력
                Console.WriteLine("
--- 텍스트 분석 결과 ---");

                if (result.WordFrequencies.Any())
                {
                    Console.WriteLine("단어 빈도:");
                    foreach (var entry in result.WordFrequencies.Take(10)) // 상위 10개 단어만 표시
                    {
                        Console.WriteLine($"- {entry.Key}: {entry.Value}회");
                    }
                }
                else
                {
                    Console.WriteLine("분석할 단어가 없습니다.");
                }

                if (result.CommonPhrases.Any())
                {
                    Console.WriteLine("
주요 구문 (상위 5개):");
                    foreach (var phrase in result.CommonPhrases)
                    {
                        Console.WriteLine($"- {phrase}");
                    }
                }
                else
                {
                    Console.WriteLine("분석할 구문이 없습니다.");
                }
            }
        }
    }
}
