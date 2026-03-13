using System;
using System.Text.Json.Serialization;

namespace NewsSentimentAnalysis
{
    /// <summary>
    /// 뉴스 기사의 정보를 담는 클래스이다.
    /// </summary>
    public class NewsArticle
    {
        /// <summary>
        /// 뉴스 기사의 제목이다.
        /// </summary>
        [JsonPropertyName("title")]
        public string Title { get; set; }

        /// <summary>
        /// 뉴스 기사의 내용이다.
        /// </summary>
        [JsonPropertyName("content")]
        public string Content { get; set; }

        /// <summary>
        /// 기본 생성자이다.
        /// </summary>
        public NewsArticle()
        {
            Title = string.Empty;
            Content = string.Empty;
        }

        /// <summary>
        /// 제목과 내용으로 뉴스 기사를 초기화하는 생성자이다.
        /// </summary>
        /// <param name="title">기사 제목</param>
        /// <param name="content">기사 내용</param>
        public NewsArticle(string title, string content)
        {
            Title = title;
            Content = content;
        }

        /// <summary>
        /// 뉴스 기사의 정보를 문자열로 반환한다.
        /// </summary>
        /// <returns>기사 제목과 내용</returns>
        public override string ToString()
        {
            return $"제목: {Title}
내용: {Content.Substring(0, Math.Min(Content.Length, 100))}...";
        }
    }
}
