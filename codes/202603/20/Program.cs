using JsonDataProcessor;

namespace JsonDataProcessor;

/// <summary>
/// JSON 데이터 처리 유틸리티의 메인 프로그램 클래스이다.
/// 명령줄 인수를 파싱하고, JsonProcessor를 사용하여 파일 처리 작업을 오케스트레이션한다.
/// </summary>
public class Program
{
    public static async Task Main(string[] args)
    {
        // 명령줄 인수가 올바르게 제공되었는지 확인한다.
        if (args.Length != 2)
        {
            Console.WriteLine("사용법: dotnet run -- <입력_JSON_파일_경로> <출력_JSON_파일_경로>");
            Console.WriteLine("예시: dotnet run -- input.json output.json");
            return;
        }

        string inputFilePath = args[0];
        string outputFilePath = args[1];

        Console.WriteLine($"입력 파일: {inputFilePath}");
        Console.WriteLine($"출력 파일: {outputFilePath}");

        JsonProcessor processor = new JsonProcessor();

        try
        {
            // 입력 JSON 파일을 읽는다.
            List<Item>? items = await processor.ReadJsonFile<Item>(inputFilePath);

            if (items == null || !items.Any())
            {
                Console.WriteLine("경고: 처리할 데이터가 없거나 파일이 비어있다.");
                return;
            }

            // 데이터를 처리한다. (예시: Value > 100 필터링)
            List<Item> processedItems = processor.ProcessData(items);

            // 처리된 데이터를 출력 JSON 파일로 저장한다.
            await processor.WriteJsonFile(processedItems, outputFilePath);

            Console.WriteLine("JSON 데이터 처리 완료.");
        }
        catch (FileNotFoundException ex)
        {
            Console.Error.WriteLine($"오류: {ex.Message}");
        }
        catch (JsonException ex)
        {
            Console.Error.WriteLine($"오류: {ex.Message}");
        }
        catch (Exception ex)
        {
            Console.Error.WriteLine($"예상치 못한 오류 발생: {ex.Message}");
        }
    }
}
