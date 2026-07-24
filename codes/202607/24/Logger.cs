// Logger.cs
using System;

namespace NumberCruncher
{
    /// <summary>
    /// 간단한 로깅 유틸리티 클래스입니다.
    /// </summary>
    public static class Logger
    {
        /// <summary>
        /// 정보 메시지를 콘솔에 출력합니다.
        /// </summary>
        /// <param name="message">출력할 메시지입니다.</param>
        public static void LogInfo(string message)
        {
            Console.WriteLine($"[{DateTime.Now:yyyy-MM-dd HH:mm:ss}] [정보] {message}");
        }

        /// <summary>
        /// 오류 메시지를 콘솔에 출력합니다.
        /// </summary>
        /// <param name="message">출력할 오류 메시지입니다.</param>
        public static void LogError(string message)
        {
            Console.Error.WriteLine($"[{DateTime.Now:yyyy-MM-dd HH:mm:ss}] [오류] {message}");
        }
    }
}
