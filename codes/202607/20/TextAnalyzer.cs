using System;
using System.Collections.Generic;
using System.Linq;

namespace TextAnalysisApp
{
    /// <summary>
    /// 텍스트를 분석하여 단어 빈도와 주요 구문을 추출하는 클래스입니다.
    /// </summary>
    public class TextAnalyzer
    {
        /// <summary>
        /// 주어진 텍스트를 분석하여 단어 빈도와 주요 구문 정보를 포함하는 AnalysisResult 객체를 반환합니다.
        /// </summary>
        /// <param name="text">분석할 원본 텍스트입니다.</param>
        /// <returns>텍스트 분석 결과 객체입니다.</returns>
        public AnalysisResult Analyze(string text)
        {
            var result = new AnalysisResult();

            if (string.IsNullOrWhiteSpace(text))
            {
                return result;
            }

            // 텍스트 정제 (소문자 변환, 특수문자 제거 등)
            string cleanedText = Utilities.CleanText(text);

            // 단어 분리
            string[] words = cleanedText.Split(new[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);

            // 단어 빈도 계산
            result.WordFrequencies = CalculateWordFrequencies(words);

            // 주요 구문 식별 (여기서는 간단하게 가장 빈번한 2-단어 구문 추출)
            result.CommonPhrases = IdentifyCommonPhrases(words, 2, 5); // 2-단어 구문, 상위 5개

            return result;
        }

        /// <summary>
        /// 주어진 단어 배열에서 각 단어의 빈도를 계산합니다.
        /// </summary>
        /// <param name="words">단어 배열입니다.</param>
        /// <returns>단어별 빈도를 담은 사전입니다.</returns>
        private Dictionary<string, int> CalculateWordFrequencies(string[] words)
        {
            var wordFrequencies = new Dictionary<string, int>();
            foreach (string word in words)
            {
                if (wordFrequencies.ContainsKey(word))
                {
                    wordFrequencies[word]++;
                }
                else
                {
                    wordFrequencies[word] = 1;
                }
            }
            return wordFrequencies.OrderByDescending(x => x.Value)
                                  .ToDictionary(x => x.Key, x => x.Value);
        }

        /// <summary>
        /// 주어진 단어 배열에서 지정된 길이의 주요 구문을 식별합니다.
        /// </summary>
        /// <param name="words">단어 배열입니다.</param>
        /// <param name="phraseLength">식별할 구문의 단어 길이입니다 (예: 2는 2-단어 구문).</param>
        /// <param name="topN">반환할 주요 구문의 최대 개수입니다.</param>
        /// <returns>주요 구문 목록입니다.</returns>
        private List<string> IdentifyCommonPhrases(string[] words, int phraseLength, int topN)
        {
            var phraseFrequencies = new Dictionary<string, int>();

            if (words.Length < phraseLength)
            {
                return new List<string>();
            }

            for (int i = 0; i <= words.Length - phraseLength; i++)
            {
                string[] currentPhraseWords = new string[phraseLength];
                Array.Copy(words, i, currentPhraseWords, 0, phraseLength);
                string phrase = string.Join(" ", currentPhraseWords);

                if (phraseFrequencies.ContainsKey(phrase))
                {
                    phraseFrequencies[phrase]++;
                }
                else
                {
                    phraseFrequencies[phrase] = 1;
                }
            }

            return phraseFrequencies.OrderByDescending(x => x.Value)
                                    .Take(topN)
                                    .Select(x => x.Key)
                                    .ToList();
        }
    }
}
