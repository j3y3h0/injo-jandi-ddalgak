using System;
using System.Collections.Generic;
using System.Linq;

namespace LogAnomalyDetection
{
    /// <summary>
    /// 로그 데이터에서 이상 징후를 감지하는 클래스입니다.
    /// </summary>
    public class AnomalyDetector
    {
        private readonly TimeSpan _timeWindow; // 이상 감지에 사용할 시간 범위
        private readonly int _errorThreshold; // 에러 로그 발생 임계값

        /// <summary>
        /// AnomalyDetector 클래스의 새 인스턴스를 초기화합니다.
        /// </summary>
        /// <param name="timeWindowInMinutes">이상 감지 시간 범위 (분 단위).</param>
        /// <param name="errorThreshold">시간 범위 내에서 허용되는 최대 ERROR 로그 수.</param>
        public AnomalyDetector(int timeWindowInMinutes, int errorThreshold)
        {
            _timeWindow = TimeSpan.FromMinutes(timeWindowInMinutes);
            _errorThreshold = errorThreshold;
        }

        /// <summary>
        /// 주어진 로그 엔트리 리스트에서 이상 징후를 감지합니다.
        /// 여기서는 특정 시간 범위 내의 ERROR 로그 수가 임계값을 초과하는 경우를 이상으로 판단합니다.
        /// </summary>
        /// <param name="logEntries">분석할 LogEntry 객체 리스트입니다.</param>
        /// <returns>감지된 이상 징후에 대한 설명 리스트입니다.</returns>
        public List<string> DetectAnomalies(List<LogEntry> logEntries)
        {
            var anomalies = new List<string>();

            // 로그 엔트리를 시간순으로 정렬합니다.
            var sortedEntries = logEntries.OrderBy(e => e.Timestamp).ToList();

            if (!sortedEntries.Any())
            {
                return anomalies;
            }

            // 슬라이딩 윈도우 방식으로 로그를 분석합니다.
            for (int i = 0; i < sortedEntries.Count; i++)
            {
                DateTime windowStart = sortedEntries[i].Timestamp;
                DateTime windowEnd = windowStart.Add(_timeWindow);

                // 현재 윈도우에 포함되는 로그 엔트리들을 필터링합니다.
                var currentWindowLogs = sortedEntries
                    .Where(e => e.Timestamp >= windowStart && e.Timestamp < windowEnd)
                    .ToList();

                // 윈도우 내의 ERROR 로그 수를 계산합니다.
                int errorCount = currentWindowLogs.Count(e => e.Level == "ERROR");

                // 임계값을 초과하는지 확인합니다.
                if (errorCount >= _errorThreshold)
                {
                    anomalies.Add($"경고: {windowStart:yyyy-MM-dd HH:mm} 부터 {_timeWindow.TotalMinutes}분 동안 {errorCount}개의 ERROR 로그가 감지되었습니다. (임계값: {_errorThreshold})");
                    // 중복 감지를 피하기 위해 이미 처리된 로그는 건너뜁니다.
                    // 이 부분은 실제 구현 시 더 정교한 로직이 필요할 수 있습니다.
                    i += currentWindowLogs.Count - 1;
                }
            }

            return anomalies;
        }
    }
}
