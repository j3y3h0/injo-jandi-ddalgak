// DataProcessor.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace NumberCruncher
{
    /// <summary>
    /// 숫자 데이터를 처리하고 필터링하는 기능을 제공하는 클래스입니다.
    /// </summary>
    public class DataProcessor
    {
        /// <summary>
        /// 지정된 파일에서 숫자 목록을 로드합니다.
        /// 각 줄을 숫자로 파싱하며, 유효하지 않은 줄은 건너뜁니다.
        /// </summary>
        /// <param name="filePath">숫자 데이터가 포함된 파일의 경로입니다.</param>
        /// <returns>파일에서 읽은 유효한 숫자 목록입니다.</returns>
        public IEnumerable<double> LoadNumbersFromFile(string filePath)
        {
            if (!File.Exists(filePath))
            {
                Logger.LogError($"파일을 찾을 수 없습니다: {filePath}");
                return Enumerable.Empty<double>();
            }

            var numbers = new List<double>();
            foreach (var line in File.ReadLines(filePath))
            {
                if (double.TryParse(line.Trim(), out double number))
                {
                    numbers.Add(number);
                }
                else
                {
                    Logger.LogInfo($"유효하지 않은 데이터 줄을 건너뜁니다: '{line.Trim()}'");
                }
            }
            Logger.LogInfo($"{filePath} 파일에서 {numbers.Count}개의 숫자를 성공적으로 로드했습니다.");
            return numbers;
        }

        /// <summary>
        /// 숫자 목록을 지정된 최소값 및 최대값 기준으로 필터링합니다.
        /// </summary>
        /// <param name="numbers">필터링할 원본 숫자 목록입니다.</param>
        /// <param name="filterMin">포함할 최소 숫자 값입니다 (포함). null인 경우 최소 필터링 없음.</param>
        /// <param name="filterMax">포함할 최대 숫자 값입니다 (포함). null인 경우 최대 필터링 없음.</param>
        /// <returns>필터링된 숫자 목록입니다.</returns>
        public IEnumerable<double> FilterNumbers(IEnumerable<double> numbers, double? filterMin, double? filterMax)
        {
            var filteredNumbers = numbers;

            if (filterMin.HasValue)
            {
                filteredNumbers = filteredNumbers.Where(n => n >= filterMin.Value);
                Logger.LogInfo($"최소값 {filterMin.Value} 기준으로 필터링을 적용했습니다.");
            }
            if (filterMax.HasValue)
            {
                filteredNumbers = filteredNumbers.Where(n => n <= filterMax.Value);
                Logger.LogInfo($"최대값 {filterMax.Value} 기준으로 필터링을 적용했습니다.");
            }

            Logger.LogInfo($"필터링 결과 {filteredNumbers.Count()}개의 숫자가 남았습니다.");
            return filteredNumbers;
        }
    }
}
