using System;
using System.IO;
using System.Text.Json;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace NewsSentimentAnalysis
{
    /// <summary>
    /// 파일 I/O 및 데이터 처리에 유용한 정적 메서드를 제공하는 유틸리티 클래스이다.
    /// </summary>
    public static class Utility
    {
        /// <summary>
        /// 지정된 파일 경로에서 JSON 데이터를 읽어 NewsArticle 객체 목록으로 역직렬화한다.
        /// </summary>
        /// <param name="filePath">읽을 JSON 파일의 경로</param>
        /// <returns>NewsArticle 객체 목록</returns>
        public static async Task<List<NewsArticle>> LoadNewsArticlesFromJsonAsync(string filePath)
        {
            if (!File.Exists(filePath))
            {
                Console.WriteLine($"경고: 파일 '{filePath}'을(를) 찾을 수 없다. 빈 목록을 반환한다.");
                return new List<NewsArticle>();
            }

            try
            {
                string jsonString = await File.ReadAllTextAsync(filePath);
                var options = new JsonSerializerOptions { PropertyNameCaseInsensitive = true };
                List<NewsArticle>? articles = JsonSerializer.Deserialize<List<NewsArticle>>(jsonString, options);
                return articles ?? new List<NewsArticle>();
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"오류: JSON 파일 읽기 중 문제가 발생했다: {ex.Message}");
                return new List<NewsArticle>();
            }
            catch (Exception ex)
            {
                Console.WriteLine($"오류: 파일을 읽는 중 예기치 않은 오류가 발생했다: {ex.Message}");
                return new List<NewsArticle>();
            }
        }

        /// <summary>
        /// NewsArticle 객체 목록을 JSON 형식으로 파일에 저장한다.
        /// </summary>
        /// <param name="filePath">저장할 파일의 경로</param>
        /// <param name="articles">저장할 NewsArticle 객체 목록</param>
        /// <returns>저장 성공 여부</returns>
        public static async Task<bool> SaveNewsArticlesToJsonAsync(string filePath, List<NewsArticle> articles)
        {
            try
            {
                var options = new JsonSerializerOptions { WriteIndented = true };
                string jsonString = JsonSerializer.Serialize(articles, options);
                await File.WriteAllTextAsync(filePath, jsonString);
                Console.WriteLine($"정보: 뉴스 기사 데이터가 '{filePath}'에 성공적으로 저장되었다.");
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"오류: 파일을 저장하는 중 오류가 발생했다: {ex.Message}");
                return false;
            }
        }
    }
}
