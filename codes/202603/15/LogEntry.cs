// LogEntry.cs
// 이 파일은 단일 로그 항목의 구조를 정의합니다.

using System;

namespace LogAnomalyDetection
{
    // 로그 엔트리를 나타내는 클래스입니다.
    public class LogEntry
    {
        // 로그가 발생한 시간입니다.
        public DateTime Timestamp { get; }
        // 로그의 레벨 (예: INFO, WARN, ERROR)입니다.
        public LogLevel Level { get; }
        // 로그 메시지의 출처 (예: 서비스 이름, 모듈 이름)입니다.
        public string Source { get; }
        // 실제 로그 메시지 내용입니다.
        public string Message { get; }

        // LogEntry 클래스의 새 인스턴스를 초기화합니다.
        public LogEntry(DateTime timestamp, LogLevel level, string source, string message)
        {
            Timestamp = timestamp;
            Level = level;
            Source = source;
            Message = message;
        }

        // LogEntry 객체를 문자열로 표현합니다.
        public override string ToString()
        {
            return $"[{Timestamp:yyyy-MM-dd HH:mm:ss}] [{Level}] [{Source}] {Message}";
        }
    }

    // 로그 레벨을 정의하는 열거형입니다.
    public enum LogLevel
    {
        INFO,   // 정보성 메시지
        WARN,   // 경고 메시지
        ERROR,  // 에러 메시지
        DEBUG   // 디버그 메시지
    }
}
