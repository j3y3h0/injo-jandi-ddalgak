// AnomalyDetector.cs
// 이 파일은 로그 스트림에서 이상 징후를 탐지하는 핵심 로직을 구현합니다.

using System;
using System.Collections.Generic;
using System.Linq;

namespace LogAnomalyDetection
{
    // 로그 스트림에서 이상 징후를 탐지하는 클래스입니다.
    public class AnomalyDetector
    {
        // 로그를 저장할 큐 (슬라이딩 윈도우 역할)
        private readonly Queue<LogEntry> _logBuffer;
        // 슬라이딩 윈도우의 크기 (예: 지난 1분)
        private readonly TimeSpan _windowSize;
        // 이상 징후로 간주할 오류 로그의 임계값
        private readonly int _errorThreshold;

        /// <summary>
        /// AnomalyDetector 클래스의 새 인스턴스를 초기화합니다.
        /// </summary>
        /// <param name="windowSizeMinutes">슬라이딩 윈도우의 크기 (분 단위)입니다.</param>
        /// <param name="errorThreshold">지정된 윈도우 내에서 허용되는 최대 오류 로그 수입니다.</param>
        public AnomalyDetector(int windowSizeMinutes, int errorThreshold)
        {
            _logBuffer = new Queue<LogEntry>();
            _windowSize = TimeSpan.FromMinutes(windowSizeMinutes);
            _errorThreshold = errorThreshold;
            Console.WriteLine($"[이상 탐지기] 윈도우 크기: {windowSizeMinutes}분, 오류 임계값: {errorThreshold}");
        }

        /// <summary>
        /// 단일 로그 엔트리를 분석하여 이상 징후를 탐지합니다.
        /// </summary>
        /// <param name="logEntry">분석할 LogEntry 객체입니다.</param>
        /// <returns>이상 징후가 탐지되면 true, 그렇지 않으면 false를 반환합니다.</returns>
        public bool Detect(LogEntry logEntry)
        {
            // 새 로그를 버퍼에 추가합니다.
            _logBuffer.Enqueue(logEntry);

            // 윈도우 크기를 초과하는 오래된 로그를 제거합니다.
            DateTime windowStartTime = DateTime.Now.Subtract(_windowSize);
            while (_logBuffer.Any() && _logBuffer.Peek().Timestamp < windowStartTime)
            {
                _logBuffer.Dequeue();
            }

            // 현재 윈도우 내의 오류 로그 수를 계산합니다.
            int errorCount = _logBuffer.Count(log => log.Level == LogLevel.ERROR);

            // 오류 로그 수가 임계값을 초과하는지 확인합니다.
            if (errorCount >= _errorThreshold)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine($"[이상 탐지] !!! 경고: 지난 {_windowSize.TotalMinutes}분 동안 오류 로그가 {errorCount}개 탐지되었습니다. (임계값: {_errorThreshold}) !!!");
                Console.ResetColor();
                return true;
            }
            return false;
        }
    }
}
