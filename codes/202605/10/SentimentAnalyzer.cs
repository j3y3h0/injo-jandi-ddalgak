using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace SentimentAnalysisTool
{
    /// <summary>
    /// 텍스트의 감정을 분석하는 클래스입니다.
    /// </summary>
    public class SentimentAnalyzer
    {
        /// <summary>
        /// 주어진 텍스트의 감정을 분석합니다.
        /// </summary>
        /// <param name="text">분석할 텍스트입니다.</param>
        /// <returns>분석된 감정 결과 (Positive, Negative, Neutral).</returns>
        public string Analyze(string text)
        {
            if (string.IsNullOrWhiteSpace(text))
            {
                return "Neutral"; // 빈 문자열은 중립으로 처리
            }

            // 텍스트를 소문자로 변환하고 단어로 분리합니다.
            // 간단한 형태소 분석을 위해 구두점을 제거하고 공백으로 분리합니다.
            string cleanedText = Regex.Replace(text.ToLower(), @"[^\p{L}\p{N}\s]", "");
            string[] words = cleanedText.Split(new[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);

            int sentimentScore = 0;

            foreach (string word in words)
            {
                if (Lexicon.PositiveWords.Contains(word))
                {
                    sentimentScore++;
                }
                else if (Lexicon.NegativeWords.Contains(word))
                {
                    sentimentScore--;
                }
            }

            if (sentimentScore > 0)
            {
                return "Positive";
            }
            else if (sentimentScore < 0)
            {
                return "Negative";
            }
            else
            {
                return "Neutral";
            }
        }
    }
}
