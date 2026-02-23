// Logger.cs
// 파일 기반 로거 구현

using System;
using System.IO;
using System.Collections.Concurrent;
using System.Threading.Tasks;

namespace SimpleLoggingSystem
{
    /// <summary>
    /// 파일에 로그를 기록하는 클래스입니다. ILogger 인터페이스를 구현합니다.
    /// 비동기 방식으로 로그를 처리하여 애플리케이션 성능에 영향을 최소화합니다.
    /// </summary>
    public class FileLogger : ILogger
    {
        private readonly string _logFilePath;
        private readonly ConcurrentQueue<LogEntry> _logQueue;
        private readonly Task _logProcessingTask;
        private volatile bool _isShuttingDown;

        /// <summary>
        /// 새 FileLogger 인스턴스를 초기화합니다.
        /// </summary>
        /// <param name="logDirectory">로그 파일이 저장될 디렉터리 경로입니다.</param>
        /// <param name="logFileName">로그 파일 이름입니다 (예: "application.log").</param>
        public FileLogger(string logDirectory, string logFileName)
        {
            if (string.IsNullOrWhiteSpace(logDirectory))
            {
                throw new ArgumentException("로그 디렉터리 경로는 비어 있을 수 없습니다.", nameof(logDirectory));
            }
            if (string.IsNullOrWhiteSpace(logFileName))
            {
                throw new ArgumentException("로그 파일 이름은 비어 있을 수 없습니다.", nameof(logFileName));
            }

            // 로그 디렉터리가 없으면 생성합니다.
            Directory.CreateDirectory(logDirectory);
            _logFilePath = Path.Combine(logDirectory, logFileName);

            _logQueue = new ConcurrentQueue<LogEntry>();
            _isShuttingDown = false;
            _logProcessingTask = Task.Run(ProcessLogQueue);
        }

        /// <summary>
        /// 로그 큐를 비동기적으로 처리하고 파일에 기록합니다.
        /// </summary>
        private async Task ProcessLogQueue()
        {
            while (!_isShuttingDown || !_logQueue.IsEmpty)
            {
                if (_logQueue.TryDequeue(out var entry))
                {
                    await WriteLogToFile(entry);
                }
                else
                {
                    // 큐가 비어 있으면 잠시 대기하여 CPU 사용량을 줄입니다.
                    await Task.Delay(100);
                }
            }
        }

        /// <summary>
        /// 단일 로그 엔트리를 파일에 기록합니다.
        /// </summary>
        /// <param name="entry">기록할 LogEntry 객체입니다.</param>
        private async Task WriteLogToFile(LogEntry entry)
        {
            try
            {
                // 파일 잠금을 피하기 위해 File.AppendText를 사용합니다.
                // using을 사용하여 스트림이 자동으로 닫히도록 합니다.
                await File.AppendAllTextAsync(_logFilePath, entry.ToString() + Environment.NewLine);
            }
            catch (Exception ex)
            {
                // 로깅 중 오류가 발생하면 콘솔에 출력합니다.
                Console.Error.WriteLine($"[로거 오류] 로그 파일 기록 중 오류 발생: {ex.Message}");
            }
        }

        /// <summary>
        /// 정보 메시지를 기록 큐에 추가합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        public void LogInfo(string message)
        {
            _logQueue.Enqueue(new LogEntry(LogLevel.Info, message));
        }

        /// <summary>
        /// 경고 메시지를 기록 큐에 추가합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        public void LogWarning(string message)
        {
            _logQueue.Enqueue(new LogEntry(LogLevel.Warning, message));
        }

        /// <summary>
        /// 에러 메시지를 기록 큐에 추가합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        /// <param name="exception">관련 예외 객체입니다 (선택 사항).</param>
        public void LogError(string message, Exception exception = null)
        {
            _logQueue.Enqueue(new LogEntry(LogLevel.Error, message, exception));
        }

        /// <summary>
        /// 디버그 메시지를 기록 큐에 추가합니다.
        /// </summary>
        /// <param name="message">기록할 메시지입니다.</param>
        public void LogDebug(string message)
        {
            _logQueue.Enqueue(new LogEntry(LogLevel.Debug, message));
        }

        /// <summary>
        /// 로거를 안전하게 종료하고 모든 대기 중인 로그를 기록합니다.
        /// </summary>
        public async Task ShutdownAsync()
        {
            _isShuttingDown = true;
            await _logProcessingTask; // 모든 로그가 처리될 때까지 기다립니다.
        }
    }
}
