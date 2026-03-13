using System;
using System.Collections.Generic;
using System.Linq;

namespace NewsSentimentAnalysis
{
    /// <summary>
    /// 텍스트의 감성을 분석하는 클래스이다.
    /// 이 예제에서는 간단한 키워드 매칭 방식을 사용한다.
    /// </summary>
    public class SentimentAnalyzer
    {
        private readonly List<string> _positiveKeywords;
        private readonly List<string> _negativeKeywords;

        /// <summary>
        /// SentimentAnalyzer 클래스의 새 인스턴스를 초기화한다.
        /// 긍정 및 부정 키워드 목록을 정의한다.
        /// </summary>
        public SentimentAnalyzer()
        {
            // 실제 애플리케이션에서는 더 정교한 키워드 목록이나 머신러닝 모델을 사용한다.
            _positiveKeywords = new List<string> { "좋은", "성공", "발전", "개선", "긍정적", "기쁨", "행복", "증가", "상승" };
            _negativeKeywords = new List<string> { "나쁜", "실패", "하락", "문제", "우려", "부정적", "슬픔", "감소", "논란" };
        }

        /// <summary>
        /// 주어진 텍스트의 감성을 분석한다.
        /// </summary>
        /// <param name="text">분석할 텍스트</param>
        /// <returns>감성 결과 (긍정, 부정, 중립)</returns>
        public Sentiment AnalyzeSentiment(string text)
        {
            if (string.IsNullOrWhiteSpace(text))
            {
                return Sentiment.Neutral;
            }

            string lowerText = text.ToLower();
            int positiveScore = 0;
            int negativeScore = 0;

            foreach (string keyword in _positiveKeywords)
            {
                if (lowerText.Contains(keyword))
                {
                    positiveScore++;
                }
            }

            foreach (string keyword in _negativeKeywords)
            {
                if (lowerText.Contains(keyword))
                {
                    negativeScore++;
                }
            }

            if (positiveScore > negativeScore)
            {
                return Sentiment.Positive;
            }
            else if (negativeScore > positiveScore)
            {
                return Sentiment.Negative;
            }
            else
            {
                return Sentiment.Neutral;
            }
        }
    }

    /// <summary>
    /// 감성 분석 결과를 나타내는 열거형이다.
    /// </summary>
    public enum Sentiment
    {
        Positive,
        Negative,
        Neutral
    }
}
