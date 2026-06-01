using System;

namespace LogAnomalyDetection
{
    /// <summary>
    /// 단일 로그 엔트리(항목)를 나타내는 클래스입니다.
    /// </summary>
    public class LogEntry
    {
        /// <summary>
        /// 로그가 발생한 시간입니다.
        /// </summary>
        public DateTime Timestamp { get; set; }

        /// <summary>
        /// 로그의 중요도 레벨입니다 (예: INFO, WARN, ERROR).
        /// </summary>
        public string Level { get; set; }

        /// <summary>
        /// 로그 메시지 내용입니다.
        /// </summary>
        public string Message { get; set; }

        public LogEntry(DateTime timestamp, string level, string message)
        {
            Timestamp = timestamp;
            Level = level;
            Message = message;
        }

        public override string ToString()
        {
            return $"[{Timestamp:yyyy-MM-dd HH:mm:ss}] [{Level}] {Message}";
        }
    }
}
