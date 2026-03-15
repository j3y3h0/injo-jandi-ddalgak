// LogGenerator.cs
// 이 파일은 가상의 로그 데이터를 생성하는 역할을 합니다.

using System;
using System.Collections.Generic;
using System.Threading;

namespace LogAnomalyDetection
{
    // 가상의 로그 엔트리를 생성하는 클래스입니다.
    public class LogGenerator
    {
        private readonly Random _random;
        private readonly string[] _sources = { "WebServer", "Database", "AuthService", "PaymentGateway" };
        private readonly string[] _infoMessages = { "User logged in", "Data retrieved successfully", "Health check passed", "Transaction initiated" };
        private readonly string[] _warnMessages = { "Disk space low", "High CPU usage detected", "API response slow", "Service unavailable" };
        private readonly string[] _errorMessages = { "Database connection failed", "Unhandled exception", "Authentication failed", "Network timeout" };

        // LogGenerator 클래스의 새 인스턴스를 초기화합니다.
        public LogGenerator()
        {
            _random = new Random();
        }

        /// <summary>
        /// 지정된 시간 간격으로 로그 엔트리를 무한히 생성하는 열거자를 반환합니다.
        /// </summary>
        /// <param name="intervalMs">로그 생성 간격 (밀리초)입니다.</param>
        /// <returns>생성된 LogEntry 객체의 열거자입니다.</returns>
        public IEnumerable<LogEntry> GenerateLogs(int intervalMs)
        {
            while (true)
            {
                yield return GenerateSingleLog();
                Thread.Sleep(intervalMs); // 지정된 간격만큼 대기
            }
        }

        /// <summary>
        /// 단일 로그 엔트리를 생성합니다.
        /// 로그 레벨은 INFO(70%), WARN(20%), ERROR(10%) 확률로 결정됩니다.
        /// </summary>
        /// <returns>새로 생성된 LogEntry 객체입니다.</returns>
        private LogEntry GenerateSingleLog()
        {
            DateTime timestamp = DateTime.Now;
            string source = _sources[_random.Next(_sources.Length)];
            LogLevel level;
            string message;

            int levelChance = _random.Next(100);
            if (levelChance < 70) // 70% 확률로 INFO
            {
                level = LogLevel.INFO;
                message = _infoMessages[_random.Next(_infoMessages.Length)];
            }
            else if (levelChance < 90) // 20% 확률로 WARN
            {
                level = LogLevel.WARN;
                message = _warnMessages[_random.Next(_warnMessages.Length)];
            }
            else // 10% 확률로 ERROR
            {
                level = LogLevel.ERROR;
                message = _errorMessages[_random.Next(_errorMessages.Length)];
            }

            // 가끔 특정 소스에서 더 많은 에러를 발생시켜 이상 징후를 시뮬레이션할 수 있습니다.
            // 이 예제에서는 단순함을 위해 추가적인 로직을 넣지 않았습니다.

            return new LogEntry(timestamp, level, source, message);
        }
    }
}
