using System.Text.Json;

namespace JsonDataProcessor;

/// <summary>
/// JSON 데이터를 처리하는 핵심 로직을 포함하는 클래스이다.
/// JSON 파일 읽기, 데이터 처리, JSON 파일 쓰기 기능을 제공한다.
/// </summary>
public class JsonProcessor
{
    private readonly JsonSerializerOptions _options;

    public JsonProcessor()
    {
        // JSON 직렬화/역직렬화 옵션을 설정한다.
        // Pretty Print를 위해 WriteIndented를 true로 설정한다.
        // 속성 이름을 대소문자 구분 없이 처리하도록 설정한다.
        _options = new JsonSerializerOptions
        {
            WriteIndented = true,
            PropertyNameCaseInsensitive = true
        };
    }

    /// <summary>
    /// 지정된 경로에서 JSON 파일을 읽어 제네릭 리스트 타입으로 역직렬화한다.
    /// </summary>
    /// <typeparam name="T">역직렬화될 리스트의 요소 타입이다.</typeparam>
    /// <param name="filePath">읽을 JSON 파일의 경로이다.</param>
    /// <returns>역직렬화된 데이터 객체의 리스트이다.</returns>
    /// <exception cref="FileNotFoundException">파일을 찾을 수 없을 때 발생한다.</exception>
    /// <exception cref="JsonException">JSON 역직렬화 중 오류가 발생할 때 발생한다.</exception>
    public async Task<List<T>?> ReadJsonFile<T>(string filePath)
    {
        if (!File.Exists(filePath))
        {
            throw new FileNotFoundException($"오류: 파일을 찾을 수 없습니다 - {filePath}");
        }

        try
        {
            await using FileStream openStream = File.OpenRead(filePath);
            return await JsonSerializer.DeserializeAsync<List<T>>(openStream, _options);
        }
        catch (JsonException ex)
        {
            throw new JsonException($"오류: JSON 파일 역직렬화 중 오류 발생 - {ex.Message}");
        }
    }

    /// <summary>
    /// 데이터를 처리하는 메서드이다.
    /// 이 예시에서는 Value가 100보다 큰 Item만 필터링한다.
    /// </summary>
    /// <param name="items">처리할 Item 객체의 리스트이다.</param>
    /// <returns>처리된 Item 객체의 리스트이다.</returns>
    public List<Item> ProcessData(List<Item> items)
    {
        Console.WriteLine($"총 {items.Count}개의 항목을 처리한다.");
        // 예시: Value가 100보다 큰 항목만 필터링한다.
        List<Item> processedItems = items.Where(item => item.Value > 100).ToList();
        Console.WriteLine($"처리 후 {processedItems.Count}개의 항목이 남았다 (Value > 100).");
        return processedItems;
    }

    /// <summary>
    /// 제네릭 리스트 타입의 데이터를 JSON 파일로 직렬화하여 지정된 경로에 저장한다.
    /// </summary>
    /// <typeparam name="T">직렬화될 리스트의 요소 타입이다.</typeparam>
    /// <param name="data">직렬화할 데이터 객체의 리스트이다.</param>
    /// <param name="filePath">저장할 JSON 파일의 경로이다.</param>
    /// <returns>비동기 작업을 나타내는 Task이다.</returns>
    /// <exception cref="JsonException">JSON 직렬화 중 오류가 발생할 때 발생한다.</exception>
    public async Task WriteJsonFile<T>(List<T> data, string filePath)
    {
        try
        {
            await using FileStream createStream = File.Create(filePath);
            await JsonSerializer.SerializeAsync(createStream, data, _options);
            Console.WriteLine($"성공: 데이터를 '{filePath}'에 저장했다.");
        }
        catch (JsonException ex)
        {
            throw new JsonException($"오류: JSON 파일 직렬화 중 오류 발생 - {ex.Message}");
        }
    }
}
