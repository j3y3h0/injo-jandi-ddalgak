// LogEntry.cs
// 로그 엔트리 데이터 모델 정의

using System;

namespace SimpleLoggingSystem
{
    /// <summary>
    /// 단일 로그 엔트리를 나타내는 클래스입니다.
    /// </summary>
    public class LogEntry
    {
        /// <summary>
        /// 로그가 기록된 시간입니다.
        /// </summary>
        public DateTime Timestamp { get; }

        /// <summary>
        /// 로그 메시지의 수준 (정보, 경고, 에러 등)입니다.
        /// </summary>
        public LogLevel Level { get; }

        /// <summary>
        /// 로그 메시지의 내용입니다.
        /// </summary>
        public string Message { get; }

        /// <summary>
        /// 관련 예외 객체입니다 (있는 경우).
        /// </summary>
        public Exception Exception { get; }

        /// <summary>
        /// 새 LogEntry 인스턴스를 초기화합니다.
        /// </summary>
        /// <param name="level">로그 수준입니다.</param>
        /// <param name="message">로그 메시지입니다.</param>
        /// <param name="exception">관련 예외입니다.</param>
        public LogEntry(LogLevel level, string message, Exception exception = null)
        {
            Timestamp = DateTime.Now;
            Level = level;
            Message = message;
            Exception = exception;
        }

        /// <summary>
        /// LogEntry의 문자열 표현을 반환합니다.
        /// </summary>
        /// <returns>로그 엔트리의 형식화된 문자열입니다.</returns>
        public override string ToString()
        {
            var exceptionDetail = Exception != null ? $"
Exception: {Exception.GetType().Name} - {Exception.Message}
StackTrace: {Exception.StackTrace}" : string.Empty;
            return $"[{Timestamp:yyyy-MM-dd HH:mm:ss}] [{Level}] {Message}{exceptionDetail}";
        }
    }

    /// <summary>
    /// 로그 메시지의 심각도 수준을 나타냅니다.
    /// </summary>
    public enum LogLevel
    {
        Info,
        Warning,
        Error,
        Debug
    }
}
