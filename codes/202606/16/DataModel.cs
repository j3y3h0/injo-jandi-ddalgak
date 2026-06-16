// DataModel.cs
// CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다.

using System;

namespace CsvDataProcessor
{
    public class DataModel
    {
        // 데이터의 고유 식별자 또는 이름입니다.
        public string Name { get; set; }

        // 데이터의 카테고리입니다.
        public string Category { get; set; }

        // 데이터와 관련된 숫자 값입니다.
        public int Value { get; set; }

        // DataModel 객체를 문자열로 표현하는 메서드입니다.
        public override string ToString()
        {
            return $"이름: {Name}, 카테고리: {Category}, 값: {Value}";
        }
    }
}
