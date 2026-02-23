// ILogger.cs
// 로거 인터페이스 정의

using System;

namespace SimpleLoggingSystem
{
    /// <summary>
    /// 로깅 기능을 제공하는 인터페이스입니다.
    /// </summary>
    public interface ILogger
    {
        /// <summary>
        /// 정보 메시지를 기록합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        void LogInfo(string message);

        /// <summary>
        /// 경고 메시지를 기록합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        void LogWarning(string message);

        /// <summary>
        /// 에러 메시지를 기록합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        /// <param name="exception">관련 예외 객체입니다 (선택 사항).</param>
        void LogError(string message, Exception exception = null);

        /// <summary>
        /// 디버그 메시지를 기록합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        void LogDebug(string message);
    }
}
