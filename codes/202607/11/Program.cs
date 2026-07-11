// Program.cs
using System;
using System.IO;

namespace CsvDataProcessor
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 처리 유틸리티를 시작합니다.");

            // 처리할 CSV 파일 경로와 분석할 컬럼 이름을 정의한다.
            // 실행 파일을 포함하는 디렉토리에 sample.csv 파일이 있다고 가정한다.
            string currentDirectory = AppDomain.CurrentDomain.BaseDirectory;
            string csvFilePath = Path.Combine(currentDirectory, "sample.csv");
            string columnNameToAnalyze = "Score"; // 예시: "Score" 컬럼의 평균을 계산한다.

            // CsvProcessor 인스턴스를 생성한다.
            CsvProcessor processor = new CsvProcessor();

            // 지정된 컬럼의 평균을 계산한다.
            double average = processor.CalculateColumnAverage(csvFilePath, columnNameToAnalyze);

            // 결과를 출력한다.
            if (average > 0) // 유효한 평균값이 계산되었을 경우
            {
                Console.WriteLine($"CSV 파일 '{Path.GetFileName(csvFilePath)}'의 '{columnNameToAnalyze}' 컬럼 평균: {average:F2}");
            }
            else
            {
                Console.WriteLine($"'{columnNameToAnalyze}' 컬럼의 평균을 계산하는 데 실패했거나 유효한 데이터가 없었습니다.");
            }

            Console.WriteLine("CSV 데이터 처리 유틸리티가 종료됩니다.");
        }
    }
}
