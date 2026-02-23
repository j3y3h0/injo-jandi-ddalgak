// Program.cs
// 로거 사용 예시

using System;
using System.IO;
using System.Threading.Tasks;

namespace SimpleLoggingSystem
{
    class Program
    {
        static async Task Main(string[] args)
        {
            // 로그 파일이 저장될 디렉터리 경로를 지정합니다.
            // 현재 실행 디렉터리의 'Logs' 폴더에 저장됩니다.
            string logDirectory = Path.Combine(AppContext.BaseDirectory, "Logs");
            string logFileName = "application.log";

            // FileLogger 인스턴스를 생성합니다.
            ILogger logger = new FileLogger(logDirectory, logFileName);

            Console.WriteLine("간단한 로깅 시스템 데모를 시작합니다.");
            Console.WriteLine($"로그는 '{logDirectory}\{logFileName}' 파일에 기록됩니다.");

            // 다양한 수준의 로그 메시지를 기록합니다.
            logger.LogInfo("애플리케이션이 시작되었습니다.");
            await Task.Delay(50); // 비동기 로깅의 순서를 약간 다르게 하기 위해 잠시 대기

            logger.LogDebug("디버그 모드가 활성화되었습니다.");
            logger.LogWarning("구성 파일이 누락되었습니다. 기본 설정을 사용합니다.");

            try
            {
                // 예외가 발생하는 상황을 시뮬레이션합니다.
                throw new InvalidOperationException("데이터베이스 연결에 실패했습니다.");
            }
            catch (Exception ex)
            {
                logger.LogError("치명적인 오류 발생!", ex);
            }

            logger.LogInfo("애플리케이션이 종료됩니다.");

            // 모든 대기 중인 로그가 파일에 기록될 때까지 기다립니다.
            if (logger is FileLogger fileLogger)
            {
                await fileLogger.ShutdownAsync();
            }

            Console.WriteLine("데모가 완료되었습니다. 로그 파일을 확인하세요.");
            Console.ReadKey(); // 사용자가 콘솔을 닫기 전에 로그 메시지를 볼 수 있도록 대기
        }
    }
}
