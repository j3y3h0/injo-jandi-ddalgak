// Program.cs

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvProcessorApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("CSV 데이터 처리 애플리케이션 시작.");

            string inputFilePath = "sample_data.csv";
            string filteredFilePath = "filtered_data.csv";
            string processedFilePath = "processed_data.csv";
            
            // 데이터 생성기 인스턴스 생성
            DataGenerator dataGenerator = new DataGenerator();
            // 샘플 데이터 파일이 없으면 생성합니다.
            if (!File.Exists(inputFilePath))
            {
                dataGenerator.GenerateSampleCsv(inputFilePath, 10); // 10개의 샘플 데이터 생성
            }

            // CsvProcessor 인스턴스 생성
            CsvProcessor processor = new CsvProcessor();

            // 1. CSV 파일 읽기
            Console.WriteLine($"
'{inputFilePath}' 파일 읽기...");
            List<Person> people = processor.ReadCsv(inputFilePath);
            Console.WriteLine($"총 {people.Count}명의 데이터를 읽었습니다.");

            if (people.Any())
            {
                // 2. 데이터 처리: 나이가 30세 이상인 사람들 필터링
                Console.WriteLine("
나이가 30세 이상인 사람들을 필터링합니다.");
                List<Person> adults = processor.FilterByAge(people, 30);
                Console.WriteLine($"필터링된 성인 {adults.Count}명:");
                foreach (var person in adults)
                {
                    Console.WriteLine($"- {person}");
                }

                // 필터링된 데이터를 새로운 CSV 파일로 저장
                processor.WriteCsv(filteredFilePath, adults);

                // 3. 데이터 처리: 서울에 거주하는 사람들 필터링
                Console.WriteLine("
서울에 거주하는 사람들을 필터링합니다.");
                List<Person> seoulResidents = processor.FilterByCity(people, "서울");
                Console.WriteLine($"서울 거주자 {seoulResidents.Count}명:");
                foreach (var person in seoulResidents)
                {
                    Console.WriteLine($"- {person}");
                }

                // 4. 데이터 요약: 전체 인구의 평균 나이 계산
                double averageAge = processor.CalculateAverageAge(people);
                Console.WriteLine($"
전체 인구의 평균 나이: {averageAge:F2}세");

                // 5. 데이터 요약: 30세 이상 성인 그룹의 평균 나이 계산
                double averageAgeAdults = processor.CalculateAverageAge(adults);
                Console.WriteLine($"30세 이상 성인 그룹의 평균 나이: {averageAgeAdults:F2}세");
                
                // 처리된 데이터를 원본 데이터에 추가 (예시)
                // 실제 시나리오에서는 원본 데이터를 수정하거나, 다른 방식으로 처리할 수 있습니다.
                // 여기서는 예시를 위해 필터링된 성인 데이터를 'processed_data.csv'에 저장합니다.
                processor.WriteCsv(processedFilePath, adults);
            }
            else
            {
                Console.WriteLine("처리할 데이터가 없습니다.");
            }

            Console.WriteLine("
CSV 데이터 처리 애플리케이션 종료.");
        }
    }
}
