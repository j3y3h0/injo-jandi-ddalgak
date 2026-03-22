// Person.cs

using System;

namespace CsvProcessorApp
{
    /// <summary>
    /// CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다.
    /// </summary>
    public class Person
    {
        /// <summary>
        /// 사람의 이름입니다.
        /// </summary>
        public string Name { get; set; }

        /// <summary>
        /// 사람의 나이입니다.
        /// </summary>
        public int Age { get; set; }

        /// <summary>
        /// 사람이 거주하는 도시입니다.
        /// </summary>
        public string City { get; set; }

        /// <summary>
        /// Person 객체의 문자열 표현을 반환합니다.
        /// </summary>
        /// <returns>Person 객체의 문자열</returns>
        public override string ToString()
        {
            return $"이름: {Name}, 나이: {Age}, 도시: {City}";
        }
    }
}
