using System.Collections.Generic;

namespace SentimentAnalysisTool
{
    /// <summary>
    /// 감정 분석에 사용될 긍정 및 부정 단어 목록을 정의합니다.
    /// </summary>
    public static class Lexicon
    {
        /// <summary>
        /// 긍정적인 감정을 나타내는 단어들의 집합입니다.
        /// </summary>
        public static readonly HashSet<string> PositiveWords = new HashSet<string>
        {
            "좋은", "최고", "훌륭한", "만족", "기쁨", "행복", "사랑", "감사", "완벽한", "아름다운",
            "재미있는", "신나는", "멋진", "성공적인", "칭찬", "추천", "특별한", "인상적인", "뛰어난", "환상적인"
        };

        /// <summary>
        /// 부정적인 감정을 나타내는 단어들의 집합입니다.
        /// </summary>
        public static readonly HashSet<string> NegativeWords = new HashSet<string>
        {
            "나쁜", "최악", "실망", "불만", "슬픔", "분노", "혐오", "짜증", "형편없는", "끔찍한",
            "지루한", "힘든", "실패", "비난", "문제", "어려운", "부족한", "복잡한", "불쾌한", "고통스러운"
        };
    }
}
