using System;
using System.Collections.Generic;
using System.Linq;

namespace DataAnalysisProject
{
    public static class DataProcessor
    {
        /// <summary>
        /// 주어진 숫자 리스트에서 유효하지 않은 데이터(음수 또는 NaN)를 제거하고 양수만 반환합니다.
        /// </summary>
        /// <param name="rawData">처리할 원시 데이터 리스트.</param>
        /// <returns>유효한(0 또는 양수) 데이터만 포함된 새로운 리스트.</returns>
        public static List<double> CleanData(List<double> rawData)
        {
            // Linq를 사용하여 NaN이 아니고 음수가 아닌 데이터만 필터링합니다.
            // 산업에서 널리 사용되는 데이터 필터링 기법입니다.
            return rawData.Where(d => !double.IsNaN(d) && d >= 0).ToList();
        }
    }
}
