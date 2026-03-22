// CsvProcessor.cs

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CsvProcessorApp
{
    /// <summary>
    /// CSV 파일을 읽고 쓰고 처리하는 유틸리티 클래스입니다.
    /// </summary>
    public class CsvProcessor
    {
        /// <summary>
        /// CSV 파일 경로에서 Person 객체 리스트를 읽어옵니다.
        /// </summary>
        /// <param name="filePath">읽을 CSV 파일의 경로입니다.</param>
        /// <returns>Person 객체 리스트입니다.</returns>
        public List<Person> ReadCsv(string filePath)
        {
            List<Person> people = new List<Person>();
            if (!File.Exists(filePath))
            {
                Console.WriteLine($"오류: 파일 '{filePath}'을 찾을 수 없습니다.");
                return people;
            }

            try
            {
                // 첫 번째 줄은 헤더이므로 건너뜁니다.
                var lines = File.ReadAllLines(filePath).Skip(1);
                foreach (var line in lines)
                {
                    var parts = line.Split(',');
                    if (parts.Length == 3)
                    {
                        if (int.TryParse(parts[1], out int age))
                        {
                            people.Add(new Person
                            {
                                Name = parts[0],
                                Age = age,
                                City = parts[2]
                            });
                        }
                        else
                        {
                            Console.WriteLine($"경고: 유효하지 않은 나이 형식입니다: {parts[1]} (줄: {line})");
                        }
                    }
                    else
                    {
                        Console.WriteLine($"경고: 유효하지 않은 CSV 줄 형식입니다: {line}");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"CSV 파일 읽기 중 오류 발생: {ex.Message}");
            }

            return people;
        }

        /// <summary>
        /// Person 객체 리스트를 CSV 파일로 저장합니다.
        /// </summary>
        /// <param name="filePath">저장할 CSV 파일의 경로입니다.</param>
        /// <param name="people">저장할 Person 객체 리스트입니다.</param>
        public void WriteCsv(string filePath, List<Person> people)
        {
            try
            {
                List<string> lines = new List<string>
                {
                    "Name,Age,City" // 헤더 줄
                };

                foreach (var person in people)
                {
                    lines.Add($"{person.Name},{person.Age},{person.City}");
                }

                File.WriteAllLines(filePath, lines);
                Console.WriteLine($"CSV 파일 '{filePath}'에 성공적으로 저장되었습니다.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"CSV 파일 쓰기 중 오류 발생: {ex.Message}");
            }
        }

        /// <summary>
        /// 특정 나이 이상인 사람들을 필터링합니다.
        /// </summary>
        /// <param name="people">원본 Person 객체 리스트입니다.</param>
        /// <param name="minAge">최소 나이입니다.</param>
        /// <returns>필터링된 Person 객체 리스트입니다.</returns>
        public List<Person> FilterByAge(List<Person> people, int minAge)
        {
            return people.Where(p => p.Age >= minAge).ToList();
        }

        /// <summary>
        /// 특정 도시에 거주하는 사람들을 필터링합니다.
        /// </summary>
        /// <param name="people">원본 Person 객체 리스트입니다.</param>
        /// <param name="city">필터링할 도시 이름입니다.</param>
        /// <returns>필터링된 Person 객체 리스트입니다.</returns>
        public List<Person> FilterByCity(List<Person> people, string city)
        {
            return people.Where(p => p.City.Equals(city, StringComparison.OrdinalIgnoreCase)).ToList();
        }

        /// <summary>
        /// Person 객체 리스트의 평균 나이를 계산합니다.
        /// </summary>
        /// <param name="people">Person 객체 리스트입니다.</param>
        /// <returns>평균 나이입니다. 리스트가 비어있으면 0을 반환합니다.</returns>
        public double CalculateAverageAge(List<Person> people)
        {
            if (!people.Any())
            {
                return 0;
            }
            return people.Average(p => p.Age);
        }
    }
}
