import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 이름 목록을 텍스트 파일에 쓰는 유틸리티 클래스입니다.
 */
public class NameWriter {

    /**
     * 지정된 파일 경로에 이름 목록을 씁니다.
     * 각 이름은 파일의 새로운 줄에 기록됩니다.
     *
     * @param filePath 쓸 파일의 경로
     * @param names    파일에 쓸 이름들의 리스트
     * @throws IOException 파일 쓰기 중 오류가 발생할 경우
     */
    public static void writeNames(String filePath, List<String> names) throws IOException {
        // try-with-resources 구문을 사용하여 BufferedWriter를 자동으로 닫습니다.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 리스트의 각 이름을 파일에 한 줄씩 씁니다.
            for (String name : names) {
                writer.write(name);
                writer.newLine(); // 다음 이름을 위해 새 줄을 추가합니다.
            }
        }
    }
}
