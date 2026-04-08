using System;
using System.IO;
using System.Linq; // List<double>의 확장 메서드(Average, Min, Max 등)를 사용하기 위해 필요

namespace DataAnalysis
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("C# 데이터 분석 예제 프로그램 시작...");

            // 현재 실행 디렉토리를 기준으로 CSV 파일 경로를 설정합니다.
            string currentDirectory = AppContext.BaseDirectory;
            string csvFilePath = Path.Combine(currentDirectory, "ExampleData.csv");

            // DataProcessor 인스턴스를 생성합니다.
            DataProcessor processor = new DataProcessor(csvFilePath);

            // 데이터를 읽어옵니다.
            List<double> data = processor.ReadData();

            if (data.Any())
            {
                Console.WriteLine("
--- 데이터 분석 결과 ---");

                // 통계 정보를 계산합니다.
                (double average, double min, double max) statistics = processor.CalculateStatistics(data);

                // 결과를 출력합니다.
                Console.WriteLine($"평균: {statistics.average:F2}"); // 소수점 둘째 자리까지 표시
                Console.WriteLine($"최소값: {statistics.min:F2}");
                Console.WriteLine($"최대값: {statistics.max:F2}");
            }
            else
            {
                Console.WriteLine("
분석할 데이터가 없거나 파일을 읽는 데 실패했습니다.");
            }

            Console.WriteLine("
C# 데이터 분석 예제 프로그램 종료.");
        }
    }
}
