using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace WordFrequencyCounter
{
    public static class WordFrequencyAnalyzer
    {
        /// <summary>
        /// 주어진 텍스트 줄들에서 단어 빈도를 분석합니다.
        /// </summary>
        /// <param name="lines">분석할 텍스트 줄들의 컬렉션입니다.</param>
        /// <returns>각 단어와 해당 빈도를 담은 딕셔너리입니다.</returns>
        public static Dictionary<string, int> Analyze(IEnumerable<string> lines)
        {
            var wordFrequencies = new Dictionary<string, int>();

            // 단어를 추출하기 위한 정규식 (알파벳 문자만 포함)
            // 실제 산업에서는 더 복잡한 단어 토큰화 규칙을 사용합니다.
            // 예: RegexOptions.Compiled를 사용하여 성능 최적화 가능
            Regex wordRegex = new Regex(@"[a-zA-Z가-힣]+", RegexOptions.Compiled);

            foreach (string line in lines)
            {
                // 줄을 소문자로 변환하여 대소문자 구분을 없앱니다.
                string lowerCaseLine = line.ToLowerInvariant();

                // 정규식을 사용하여 단어들을 찾습니다.
                foreach (Match match in wordRegex.Matches(lowerCaseLine))
                {
                    string word = match.Value;
                    if (!string.IsNullOrWhiteSpace(word))
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
                }
            }

            return wordFrequencies;
        }
    }
}