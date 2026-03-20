using System.Text.Json.Serialization;

namespace JsonDataProcessor;

/// <summary>
/// JSON 데이터의 단일 항목을 나타내는 모델 클래스이다.
/// </summary>
public class Item
{
    /// <summary>
    /// 항목의 고유 식별자이다.
    /// </summary>
    [JsonPropertyName("Id")]
    public int Id { get; set; }

    /// <summary>
    /// 항목의 이름이다.
    /// </summary>
    [JsonPropertyName("Name")]
    public string? Name { get; set; }

    /// <summary>
    /// 항목의 값이다. 데이터 처리 예시에서 사용될 숫자 값이다.
    /// </summary>
    [JsonPropertyName("Value")]
    public int Value { get; set; }
}
