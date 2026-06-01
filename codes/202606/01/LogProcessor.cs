using System;
using System.Collections.Generic;
using System.Linq;

namespace LogAnomalyDetection
{
    /// <summary>
    /// 로그 데이터를 처리하고 파싱하는 클래스입니다.
    /// </summary>
    public class LogProcessor
    {
        /// <summary>
        /// 주어진 문자열 형태의 로그 라인들을 LogEntry 객체 리스트로 파싱합니다.
        /// 여기서는 간단한 형식을 가정하며, 실제로는 더 복잡한 파싱 로직이 필요할 수 있습니다.
        /// </summary>
        /// <param name="logLines">로그 데이터 문자열 리스트입니다.</param>
        /// <returns>파싱된 LogEntry 객체 리스트입니다.</returns>
        public List<LogEntry> ProcessLogs(List<string> logLines)
        {
            var logEntries = new List<LogEntry>();
            foreach (var line in logLines)
            {
                // 예시 로그 형식: "[2026-06-01 10:00:00] [INFO] 사용자 로그인 성공"
                // 간단한 파싱을 위해 스플릿을 사용합니다. 실제로는 정규표현식이 더 견고합니다.
                try
                {
                    var parts = line.Split(new[] { "] [" }, StringSplitOptions.RemoveEmptyEntries);
                    if (parts.Length >= 3)
                    {
                        var timestampStr = parts[0].TrimStart('[');
                        var level = parts[1];
                        var message = parts[2].TrimEnd(']');

                        if (DateTime.TryParse(timestampStr, out DateTime timestamp))
                        {
                            logEntries.Add(new LogEntry(timestamp, level, message));
                        }
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"로그 파싱 중 오류 발생: {line} - {ex.Message}");
                }
            }
            return logEntries.OrderBy(e => e.Timestamp).ToList();
        }
    }
}
