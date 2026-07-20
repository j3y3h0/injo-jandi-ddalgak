using System.Collections.Generic;

namespace TextAnalysisApp
{
    /// <summary>
    /// 텍스트 분석 결과를 담는 클래스입니다.
    /// </summary>
    public class AnalysisResult
    {
        /// <summary>
        /// 텍스트에 포함된 각 단어의 빈도를 저장하는 사전입니다.
        /// 키는 단어, 값은 해당 단어의 출현 횟수입니다.
        /// </summary>
        public Dictionary<string, int> WordFrequencies { get; set; }

        /// <summary>
        /// 텍스트에서 발견된 주요 구문 목록입니다.
        /// (예: 가장 빈번하게 나타나는 2개 이상의 단어 조합)
        /// </summary>
        public List<string> CommonPhrases { get; set; }

        /// <summary>
        /// AnalysisResult 클래스의 새 인스턴스를 초기화합니다.
        /// </summary>
        public AnalysisResult()
        {
            WordFrequencies = new Dictionary<string, int>();
            CommonPhrases = new List<string>();
        }
    }
}
