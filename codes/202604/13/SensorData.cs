// SensorData.cs
// 센서 측정값을 나타내는 데이터 모델입니다.

using System;

namespace SensorAnalyzer
{
    /// <summary>
    /// 단일 센서 측정값을 저장하는 구조체입니다.
    /// </summary>
    public struct SensorData
    {
        /// <summary>
        /// 센서 측정 시간입니다.
        /// </summary>
        public DateTime Timestamp { get; set; }

        /// <summary>
        /// 센서 측정 값입니다.
        /// </summary>
        public double Value { get; set; }

        public SensorData(DateTime timestamp, double value)
        {
            Timestamp = timestamp;
            Value = value;
        }

        public override string ToString()
        {
            return $"[{Timestamp:yyyy-MM-dd HH:mm:ss}] Value: {Value}";
        }
    }
}
