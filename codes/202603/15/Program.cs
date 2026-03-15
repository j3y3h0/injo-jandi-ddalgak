// Program.cs
// 이 파일은 애플리케이션의 주 진입점입니다.
// 로그 생성, 처리, 이상 탐지 과정을 조율하고 결과를 출력합니다.

using System;
using System.Threading;

namespace LogAnomalyDetection
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("실시간 로그 이상 탐지 시스템을 시작합니다.");
            Console.WriteLine("------------------------------------------");

            // 로그 생성기 초기화 (1초마다 로그 생성)
            LogGenerator generator = new LogGenerator();
            // 로그 처리기 초기화
            LogProcessor processor = new LogProcessor();
            // 이상 탐지기 초기화 (지난 1분 동안 오류 로그가 5개 이상이면 이상으로 간주)
            AnomalyDetector detector = new AnomalyDetector(windowSizeMinutes: 1, errorThreshold: 5);

            Console.WriteLine("
로그 생성을 시작합니다. Ctrl+C를 눌러 종료하세요.
");

            // 가상의 로그 스트림 처리
            foreach (LogEntry log in generator.GenerateLogs(intervalMs: 500)) // 0.5초마다 새 로그 생성
            {
                // 1. 로그 처리
                LogEntry processedLog = processor.ProcessLog(log);

                if (processedLog != null)
                {
                    // 2. 이상 탐지
                    detector.Detect(processedLog);

                    // 콘솔에 로그 출력 (간결하게)
                    Console.WriteLine(processedLog.ToString());
                }
            }
        }
    }
}
