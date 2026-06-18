import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

/**
 * FileProcessor 클래스는 파일에서 텍스트 내용을 읽는 기능을 제공합니다.
 * 파일 I/O 작업을 처리하며, 발생할 수 있는 예외를 적절히 처리합니다.
 */
public class FileProcessor {

    /**
     * 지정된 경로의 파일 내용을 UTF-8 인코딩으로 읽어 문자열로 반환합니다.
     * 파일 읽기 중 오류가 발생하면 IOException을 발생시킵니다.
     *
     * @param filePath 읽을 파일의 경로
     * @return 파일의 전체 내용
     * @throws IOException 파일 읽기 중 오류가 발생할 경우
     */
    public String readFile(String filePath) throws IOException {
        // Paths.get()을 사용하여 파일 경로를 Path 객체로 변환합니다.
        // Files.readAllBytes()로 파일의 모든 바이트를 읽어옵니다.
        // StandardCharsets.UTF_8을 사용하여 바이트 배열을 UTF-8 문자열로 디코딩합니다.
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }
}
