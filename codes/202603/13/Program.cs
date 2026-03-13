using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.IO;

namespace NewsSentimentAnalysis
{
    class Program
    {
        private const string NewsDataFileName = "sample_news_data.json";

        static async Task Main(string[] args)
        {
            Console.WriteLine("뉴스 기사 감성 분석 프로그램을 시작한다.");

            // 샘플 뉴스 데이터 파일이 없으면 생성한다.
            if (!File.Exists(NewsDataFileName))
            {
                await CreateSampleNewsDataFile(NewsDataFileName);
            }

            // 뉴스 기사 데이터를 로드한다.
            List<NewsArticle> articles = await Utility.LoadNewsArticlesFromJsonAsync(NewsDataFileName);

            if (articles.Count == 0)
            {
                Console.WriteLine("분석할 뉴스 기사가 없다. 프로그램을 종료한다.");
                return;
            }

            SentimentAnalyzer analyzer = new SentimentAnalyzer();

            Console.WriteLine("
--- 감성 분석 결과 ---");
            foreach (NewsArticle article in articles)
            {
                Sentiment sentiment = analyzer.AnalyzeSentiment(article.Content);
                Console.WriteLine($"
제목: {article.Title}");
                Console.WriteLine($"내용: {article.Content.Substring(0, Math.Min(article.Content.Length, 100))}..."); // 내용 일부만 표시
                Console.WriteLine($"감성: {GetSentimentString(sentiment)}");
            }

            Console.WriteLine("
뉴스 기사 감성 분석 프로그램을 종료한다.");
        }

        /// <summary>
        /// 감성 열거형 값을 한글 문자열로 변환한다.
        /// </summary>
        /// <param name="sentiment">감성 값</param>
        /// <returns>감성을 나타내는 한글 문자열</returns>
        private static string GetSentimentString(Sentiment sentiment)
        {
            return sentiment switch
            {
                Sentiment.Positive => "긍정",
                Sentiment.Negative => "부정",
                Sentiment.Neutral => "중립",
                _ => "알 수 없음",
            };
        }

        /// <summary>
        /// 샘플 뉴스 기사 데이터를 포함하는 JSON 파일을 생성한다.
        /// </summary>
        /// <param name="fileName">생성할 파일 이름</param>
        private static async Task CreateSampleNewsDataFile(string fileName)
        {
            Console.WriteLine($"샘플 뉴스 데이터 파일 '{fileName}'을(를) 생성한다.");
            List<NewsArticle> sampleArticles = new List<NewsArticle>
            {
                new NewsArticle(
                    "기술 기업, 혁신적인 신제품으로 시장 선도",
                    "선도적인 기술 기업이 인공지능 기반의 새로운 제품을 출시하여 시장에서 긍정적인 반응을 얻고 있다. 이 제품은 사용자 경험을 크게 개선하며, 업계 전문가들로부터 높은 평가를 받고 있다. 기업의 주가는 이러한 성공에 힘입어 상승세를 보이고 있다."
                ),
                new NewsArticle(
                    "경제 성장 둔화 우려, 소비자 심리 위축",
                    "최근 발표된 경제 지표들이 예상보다 저조하여 경제 성장 둔화에 대한 우려가 커지고 있다. 이는 소비자 심리 위축으로 이어져 전반적인 소비 감소를 야기할 수 있다는 분석이 나온다. 정부는 문제 해결을 위해 다양한 정책적 노력을 기울이고 있다."
                ),
                new NewsArticle(
                    "환경 보호를 위한 새로운 정책 시행",
                    "정부가 환경 보호를 강화하기 위한 새로운 정책을 발표했다. 이 정책은 기업과 시민 모두에게 긍정적인 영향을 미칠 것으로 기대된다. 전문가들은 이러한 개선 노력이 지속 가능한 미래를 만드는 데 중요한 역할을 할 것이라고 평가했다."
                ),
                new NewsArticle(
                    "기업 실적 발표, 기대에 못 미쳐",
                    "대형 IT 기업의 분기별 실적이 시장의 기대에 미치지 못했다는 발표가 있었다. 이는 투자자들 사이에서 논란이 되고 있으며, 주가 하락으로 이어졌다. 기업은 현재 직면한 문제 해결을 위해 내부적인 검토를 진행 중이다."
                ),
                new NewsArticle(
                    "새로운 문화 예술 공간 개관, 시민들의 관심 집중",
                    "도심에 새로운 문화 예술 공간이 문을 열었다. 이 공간은 다양한 예술 작품을 선보이며 시민들에게 기쁨을 선사하고 있다. 지역 사회의 문화적 발전에 크게 기여할 것으로 기대되며, 개관 첫 주부터 많은 방문객이 몰리고 있다."
                )
            };

            await Utility.SaveNewsArticlesToJsonAsync(fileName, sampleArticles);
        }
    }
}
