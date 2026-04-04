using Newtonsoft.Json;
using System;

namespace JsonExample
{
    /// <summary>
    /// JSON 직렬화 및 역직렬화를 위한 유틸리티 클래스입니다.
    /// Newtonsoft.Json 라이브러리를 사용합니다.
    /// </summary>
    public static class JsonProcessor
    {
        /// <summary>
        /// .NET 객체를 JSON 문자열로 직렬화합니다.
        /// </summary>
        /// <typeparam name="T">직렬화할 객체의 타입입니다.</typeparam>
        /// <param name="obj">직렬화할 객체입니다.</param>
        /// <param name="formatting">JSON 출력 서식입니다 (예: Indented, None).</param>
        /// <returns>JSON 형식의 문자열입니다.</returns>
        public static string SerializeObject<T>(T obj, Formatting formatting = Formatting.Indented)
        {
            if (obj == null)
            {
                throw new ArgumentNullException(nameof(obj), "직렬화할 객체는 null이 될 수 없습니다.");
            }
            try
            {
                return JsonConvert.SerializeObject(obj, formatting);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"객체 직렬화 중 오류 발생: {ex.Message}");
                throw;
            }
        }

        /// <summary>
        /// JSON 문자열을 .NET 객체로 역직렬화합니다.
        /// </summary>
        /// <typeparam name="T">역직렬화할 객체의 타입입니다.</typeparam>
        /// <param name="jsonString">역직렬화할 JSON 문자열입니다.</param>
        /// <returns>JSON 문자열에서 생성된 .NET 객체입니다.</returns>
        public static T DeserializeObject<T>(string jsonString)
        {
            if (string.IsNullOrWhiteSpace(jsonString))
            {
                throw new ArgumentException("역직렬화할 JSON 문자열은 비어 있거나 null이 될 수 없습니다.", nameof(jsonString));
            }
            try
            {
                T? obj = JsonConvert.DeserializeObject<T>(jsonString);
                if (obj == null)
                {
                    throw new JsonSerializationException("JSON 문자열이 유효한 객체로 역직렬화되지 않았습니다.");
                }
                return obj;
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"JSON 역직렬화 중 오류 발생: {ex.Message}");
                throw;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"객체 역직렬화 중 알 수 없는 오류 발생: {ex.Message}");
                throw;
            }
        }
    }
}
