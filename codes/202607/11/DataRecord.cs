// DataRecord.cs
using System.Collections.Generic;

namespace CsvDataProcessor
{
    /// <summary>
    /// CSV 파일의 한 행을 나타내는 데이터 모델 클래스이다.
    /// 각 컬럼의 이름과 값을 저장한다.
    /// </summary>
    public class DataRecord
    {
        // CSV 행의 모든 컬럼 데이터를 저장하는 사전이다.
        // 키는 컬럼 이름이고, 값은 해당 컬럼의 문자열 값이다.
        public Dictionary<string, string> Fields { get; set; }

        public DataRecord()
        {
            Fields = new Dictionary<string, string>();
        }

        /// <summary>
        /// 주어진 컬럼 이름에 해당하는 값을 반환한다.
        /// </summary>
        /// <param name="columnName">가져올 컬럼의 이름이다.</param>
        /// <returns>컬럼의 문자열 값이다. 컬럼이 없으면 null을 반환한다.</returns>
        public string GetValue(string columnName)
        {
            if (Fields.TryGetValue(columnName, out string value))
            {
                return value;
            }
            return null; // 컬럼을 찾을 수 없는 경우
        }

        /// <summary>
        /// 주어진 컬럼 이름과 값으로 필드를 설정한다.
        /// </summary>
        /// <param name="columnName">설정할 컬럼의 이름이다.</param>
        /// <param name="value">설정할 문자열 값이다.</param>
        public void SetValue(string columnName, string value)
        {
            Fields[columnName] = value;
        }
    }
}
