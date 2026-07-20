using System.Text.RegularExpressions;

namespace TextAnalysisApp
{
    /// <summary>
    /// 텍스트 처리와 관련된 유틸리티 함수를 제공합니다.
    /// </summary>
    public static class Utilities
    {
        /// <summary>
        /// 주어진 텍스트에서 구두점 및 특수 문자를 제거하고 소문자로 변환하여 반환합니다.
        /// </summary>
        /// <param name="text">처리할 원본 텍스트입니다.</param>
        /// <returns>정제된 텍스트입니다.</returns>
        public static string CleanText(string text)
        {
            if (string.IsNullOrWhiteSpace(text))
            {
                return string.Empty;
            }

            // 모든 문자를 소문자로 변환
            text = text.ToLowerInvariant();

            // 숫자와 문자를 제외한 모든 문자 제거
            // Regex.Replace(text, "[^a-z0-9가-힣\s]", "") 한글 지원 추가
            text = Regex.Replace(text, "[^a-z0-9가-힣\s]", "");

            // 여러 공백을 단일 공백으로 치환
            text = Regex.Replace(text, "\s+", " ").Trim();

            return text;
        }
    }
}
