// FileHandler.cs
// 센서 데이터를 파일로부터 읽거나 파일에 쓰는 기능을 제공합니다.

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace SensorAnalyzer
{
    /// <summary>
    /// 파일 I/O 작업을 처리하는 클래스입니다.
    /// </summary>
    public class FileHandler
    {
        /// <summary>
        /// 지정된 파일 경로에서 센서 데이터를 읽어옵니다.
        /// 각 줄은 "yyyy-MM-dd HH:mm:ss,value" 형식이어야 합니다.
        /// </summary>
        /// <param name="filePath">읽을 파일의 경로입니다.</param>
        /// <returns>읽어온 SensorData 객체 목록입니다.</returns>
        public static List<SensorData> ReadSensorData(string filePath)
        {
            List<SensorData> sensorDataList = new List<SensorData>();

            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: 파일 '{filePath}'를 찾을 수 없습니다.");
                return sensorDataList;
            }

            try
            {
                var lines = File.ReadAllLines(filePath);
                foreach (var line in lines)
                {
                    if (string.IsNullOrWhiteSpace(line) || line.TrimStart().StartsWith(";"))
                    {
                        continue; // 빈 줄 또는 주석 건너뛰기
                    }

                    var parts = line.Split(',');
                    if (parts.Length == 2)
                    {
                        if (DateTime.TryParse(parts[0].Trim(), out DateTime timestamp) &&
                            double.TryParse(parts[1].Trim(), out double value))
                        {
                            sensorDataList.Add(new SensorData(timestamp, value));
                        }
                        else
                        {
                            Console.WriteLine($"경고: 유효하지 않은 데이터 형식 감지: '{line}'. 이 줄은 건너뜁니다.");
                        }
                    }
                    else
                    {
                        Console.WriteLine($"경고: 유효하지 않은 줄 형식 감지: '{line}'. 이 줄은 건너뜁니다.");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"파일 읽기 중 오류 발생: {ex.Message}");
            }

            return sensorDataList;
        }

        // TODO: 필요하다면 WriteSensorData 메서드를 추가할 수 있습니다.
    }
}
