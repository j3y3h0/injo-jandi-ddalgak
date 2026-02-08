// FileUtil.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 파일 읽기 및 쓰기와 같은 유틸리티 기능을 제공하는 클래스입니다.
 * 현재는 파일에서 텍스트 내용을 읽어오는 기능만을 포함합니다.
 */
public class FileUtil {

    /**
     * 지정된 경로의 파일에서 모든 텍스트 라인을 읽어와 문자열로 반환합니다.
     * 파일이 존재하지 않거나 읽기 오류 발생 시 IOException을 발생시킵니다.
     *
     * @param filePath 읽을 파일의 경로
     * @return 파일의 모든 내용을 담은 문자열
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public static String readFileToString(String filePath) throws IOException {
        // Files.readAllLines를 사용하여 파일의 모든 라인을 읽고, 줄바꿈 문자로 연결하여 하나의 문자열로 만듭니다.
        // Files.readString (Java 11+)을 사용하면 더 간단하지만, 호환성을 위해 readAllLines를 사용합니다.
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return String.join(System.lineSeparator(), lines);
    }
}
