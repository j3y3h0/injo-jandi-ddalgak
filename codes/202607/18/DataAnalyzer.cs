// DataAnalyzer.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace DataAnalysisApp
{
    /// <summary>
    /// 숫자 데이터에 대한 기본적인 통계 분석 기능을 제공하는 클래스입니다.
    /// </summary>
    public class DataAnalyzer
    {
        /// <summary>
        /// 숫자 리스트에서 최소값을 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트입니다.</param>
        /// <returns>리스트의 최소값입니다.</returns>
        /// <exception cref="ArgumentException">데이터 리스트가 비어 있을 경우 발생합니다.</exception>
        public double CalculateMinimum(List<double> data)
        {
            if (data == null || !data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있거나 null입니다.", nameof(data));
            }
            // LINQ의 Min() 메서드를 사용하여 최소값을 찾습니다.
            return data.Min();
        }

        /// <summary>
        /// 숫자 리스트에서 최대값을 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트입니다.</param>
        /// <returns>리스트의 최대값입니다.</returns>
        /// <exception cref="ArgumentException">데이터 리스트가 비어 있을 경우 발생합니다.</exception>
        public double CalculateMaximum(List<double> data)
        {
            if (data == null || !data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있거나 null입니다.", nameof(data));
            }
            // LINQ의 Max() 메서드를 사용하여 최대값을 찾습니다.
            return data.Max();
        }

        /// <summary>
        /// 숫자 리스트에서 평균값을 계산합니다.
        /// </summary>
        /// <param name="data">분석할 숫자 데이터 리스트입니다.</param>
        /// <returns>리스트의 평균값입니다.</returns>
        /// <exception cref="ArgumentException">데이터 리스트가 비어 있을 경우 발생합니다.</exception>
        public double CalculateAverage(List<double> data)
        {
            if (data == null || !data.Any())
            {
                throw new ArgumentException("데이터 리스트가 비어 있거나 null입니다.", nameof(data));
            }
            // LINQ의 Average() 메서드를 사용하여 평균값을 계산합니다.
            return data.Average();
        }
    }
}
