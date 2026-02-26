// DataProcessor.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace CsvProcessor
{
    /// <summary>
    /// 데이터를 처리하는 다양한 유틸리티 메서드를 제공하는 클래스입니다.
    /// </summary>
    public static class DataProcessor
    {
        /// <summary>
        /// 지정된 컬럼의 숫자 값들에 대한 평균을 계산합니다.
        /// </summary>
        /// <param name="records">처리할 데이터 레코드 리스트입니다.</param>
        /// <param name="columnName">평균을 계산할 컬럼의 이름입니다.</param>
        /// <returns>지정된 컬럼의 평균 값입니다.</returns>
        public static double CalculateAverage(List<Dictionary<string, string>> records, string columnName)
        {
            if (records == null || !records.Any())
            {
                return 0.0;
            }

            double sum = 0.0;
            int count = 0;

            foreach (var record in records)
            {
                if (record.TryGetValue(columnName, out string valueString) && double.TryParse(valueString, out double value))
                {
                    sum += value;
                    count++;
                }
            }

            return count > 0 ? sum / count : 0.0;
        }

        /// <summary>
        /// 지정된 컬럼의 값이 특정 기준 값과 일치하는 레코드들을 필터링합니다.
        /// </summary>
        /// <param name="records">필터링할 데이터 레코드 리스트입니다.</param>
        /// <param name="columnName">필터링 기준이 될 컬럼의 이름입니다.</param>
        /// <param name="filterValue">컬럼 값이 일치해야 하는 기준 값입니다.</param>
        /// <returns>필터링된 레코드 리스트입니다.</returns>
        public static List<Dictionary<string, string>> FilterData(List<Dictionary<string, string>> records, string columnName, string filterValue)
        {
            if (records == null || !records.Any())
            {
                return new List<Dictionary<string, string>>();
            }

            return records.Where(record =>
                record.TryGetValue(columnName, out string value) && value.Equals(filterValue, StringComparison.OrdinalIgnoreCase))
                .ToList();
        }
    }
}