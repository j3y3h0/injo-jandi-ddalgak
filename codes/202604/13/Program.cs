// Program.cs
// 애플리케이션의 진입점입니다. 센서 데이터를 읽고 분석 결과를 출력합니다.

using System;
using System.Collections.Generic;
using System.IO;

namespace SensorAnalyzer
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 센서 데이터 분석기 시작 ===");

            string filePath = "sensor_data.csv"; // 분석할 데이터 파일 경로

            // 1. 센서 데이터 파일 읽기
            Console.WriteLine($"'{filePath}' 파일에서 센서 데이터를 읽는 중...");
            List<SensorData> sensorDataList = FileHandler.ReadSensorData(filePath);

            if (sensorDataList == null || sensorDataList.Count == 0)
            {
                Console.WriteLine("분석할 센서 데이터가 없습니다. 프로그램을 종료합니다.");
                return;
            }

            Console.WriteLine($"총 {sensorDataList.Count}개의 센서 데이터를 읽었습니다.");

            // 2. 데이터 분석
            Console.WriteLine("
--- 데이터 분석 시작 ---");
            DataAnalyzer analyzer = new DataAnalyzer(sensorDataList);

            double average = analyzer.CalculateAverage();
            double minimum = analyzer.FindMinimum();
            double maximum = analyzer.FindMaximum();
            List<SensorData> anomalies = analyzer.DetectAnomalies(0.15); // 15% 임계값으로 이상치 탐지

            Console.WriteLine($"평균 값: {average:F2}");
            Console.WriteLine($"최소 값: {minimum:F2}");
            Console.WriteLine($"최대 값: {maximum:F2}");

            // 3. 이상치 출력
            if (anomalies.Count > 0)
            {
                Console.WriteLine("
--- 탐지된 이상치 ---");
                foreach (var anomaly in anomalies)
                {
                    Console.WriteLine($"- {anomaly.ToString()} (평균 {average:F2}와 큰 차이 발생)");
                }
            }
            else
            {
                Console.WriteLine("
이상치(Anomalies)가 탐지되지 않았습니다.");
            }

            Console.WriteLine("
=== 센서 데이터 분석 완료 ===");
        }
    }
}
