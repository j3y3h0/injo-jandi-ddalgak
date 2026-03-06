using System;
using System.IO;

namespace CsvProcessorApp
{
    class Program
    {
        // 애플리케이션의 주 진입점입니다.
        static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 처리 애플리케이션을 시작합니다.");

            string inputFileName = "input.csv";  // 입력 파일 이름
            string outputFileName = "output.csv"; // 출력 파일 이름

            // 1. 샘플 입력 CSV 파일을 생성합니다.
            CreateSampleInputCsv(inputFileName);

            // 2. CsvProcessor 인스턴스를 생성하고 CSV 데이터를 처리합니다.
            CsvProcessor processor = new CsvProcessor();
            processor.ProcessCsvData(inputFileName, outputFileName);

            Console.WriteLine("CSV 데이터 처리 애플리케이션이 종료되었습니다.");
        }

        // 샘플 입력 CSV 파일을 생성하는 도우미 함수입니다.
        // fileName: 생성할 CSV 파일의 이름입니다.
        static void CreateSampleInputCsv(string fileName)
        {
            Console.WriteLine($"샘플 입력 파일 '{fileName}'을(를) 생성합니다.");

            // CSV 파일에 작성될 내용입니다.
            string[] lines = new string[]
            {
                "Product,Quantity,Price",
                "Apple,10,1.5",
                "Banana,5,0.75",
                "Apple,3,1.5",
                "Orange,8,1.2",
                "Banana,2,0.75",
                "Apple,5,1.5" // 추가 데이터
            };

            try
            {
                // 모든 라인을 파일에 작성합니다. 파일이 이미 존재하면 덮어씁니다.
                File.WriteAllLines(fileName, lines);
                Console.WriteLine($"'{fileName}' 파일이 성공적으로 생성되었습니다.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"샘플 CSV 파일 생성 중 오류가 발생했습니다: {ex.Message}");
            }
        }
    }
}
