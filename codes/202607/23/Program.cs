using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace WordFrequencyCounter
{
    public class Program
    {
        public static void Main(string[] args)
        {
            // 사용법 안내
            if (args.Length < 1 || args.Length > 2)
            {
                Console.WriteLine("사용법: WordFrequencyCounter <입력파일경로> [상위N개단어]");
                Console.WriteLine("예시: WordFrequencyCounter input.txt 10");
                return;
            }

            string inputFilePath = args[0];
            int topN = 10; // 기본값

            if (args.Length == 2)
            {
                if (!int.TryParse(args[1], out topN) || topN <= 0)
                {
                    Console.WriteLine("오류: 상위N개단어는 양의 정수여야 합니다.");
                    return;
                }
            }

            if (!File.Exists(inputFilePath))
            {
                Console.WriteLine($"오류: 파일을 찾을 수 없습니다. 경로: {inputFilePath}");
                return;
            }

            Console.WriteLine($"파일 '{inputFilePath}'에서 단어 빈도를 분석합니다...");

            try
            {
                // 파일 내용 읽기
                IEnumerable<string> lines = FileProcessor.ReadLines(inputFilePath);

                // 단어 빈도 분석
                Dictionary<string, int> wordFrequencies = WordFrequencyAnalyzer.Analyze(lines);

                // 상위 N개 단어 출력
                Console.WriteLine($"
상위 {topN}개 단어 빈도:");
                foreach (var entry in wordFrequencies.OrderByDescending(x => x.Value).Take(topN))
                {
                    Console.WriteLine($"- {entry.Key}: {entry.Value}회");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"처리 중 오류가 발생했습니다: {ex.Message}");
            }
        }
    }
}