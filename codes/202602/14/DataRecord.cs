// DataRecord.cs
using System.Collections.Generic;

namespace CsvAnalyzer
{
    /// <summary>
    /// CSV 파일의 한 행 데이터를 나타내는 클래스입니다.
    /// 헤더 이름을 키로 하여 각 셀의 값을 저장합니다.
    /// </summary>
    public class DataRecord
    {
        public Dictionary<string, string> Fields { get; private set; }

        public DataRecord(Dictionary<string, string> fields)
        {
            Fields = fields;
        }

        /// <summary>
        /// 특정 헤더에 해당하는 값을 가져옵니다.
        /// </summary>
        /// <param name="header">찾을 헤더 이름입니다.</param>
        /// <returns>해당하는 값, 또는 헤더가 없을 경우 null을 반환합니다.</returns>
        public string GetValue(string header)
        {
            if (Fields.TryGetValue(header, out string value))
            {
                return value;
            }
            return null;
        }
    }
}
