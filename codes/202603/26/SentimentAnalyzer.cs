using System;
using System.Collections.Generic;
using System.Linq;

namespace NewsSentimentAnalyzer
{
    public class SentimentAnalyzer
    {
        private readonly HashSet<string> _positiveWords;
        private readonly HashSet<string> _negativeWords;

        public SentimentAnalyzer()
        {
            // 간단한 긍정 및 부정 단어 사전 (한국어)
            // 실제 프로젝트에서는 훨씬 더 크고 정교한 사전을 사용합니다.
            _positiveWords = new HashSet<string>
            {
                "좋다", "긍정적", "행복", "기쁘다", "성공", "발전", "희망", "탁월", "뛰어나다", "칭찬",
                "환영", "개선", "성장", "강화", "지원", "해결", "기대", "도움", "안정", "회복"
            };

            _negativeWords = new HashSet<string>
            {
                "나쁘다", "부정적", "슬픔", "화나다", "실패", "문제", "우려", "악화", "비판", "불만",
                "논란", "축소", "약화", "반대", "어렵다", "위험", "부담", "침체", "하락", "갈등"
            };
        }

        public string AnalyzeSentiment(string text)
        {
            if (string.IsNullOrWhiteSpace(text))
            {
                return "분석할 텍스트가 없습니다.";
            }

            // 텍스트를 소문자로 변환하고 단어 단위로 분리
            // 간단화를 위해 구두점은 무시하고 공백으로만 분리합니다.
            var words = text.ToLowerInvariant()
                            .Split(new[] { ' ', ',', '.', '!', '?', ';' }, StringSplitOptions.RemoveEmptyEntries);

            int positiveScore = 0;
            int negativeScore = 0;

            foreach (var word in words)
            {
                if (_positiveWords.Contains(word))
                {
                    positiveScore++;
                }
                else if (_negativeWords.Contains(word))
                {
                    negativeScore++;
                }
            }

            if (positiveScore > negativeScore)
            {
                return "긍정적";
            }
            else if (negativeScore > positiveScore)
            {
                return "부정적";
            }
            else
            {
                return "중립적";
            }
        }
    }
}
