import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 텍스트 파일에서 이름을 읽어오는 유틸리티 클래스입니다.
 */
public class NameReader {

    /**
     * 지정된 파일 경로에서 이름 목록을 읽어옵니다.
     * 파일의 각 줄을 하나의 이름으로 간주합니다.
     *
     * @param filePath 읽어올 파일의 경로
     * @return 파일에서 읽어온 이름들의 리스트
     * @throws IOException 파일 읽기 중 오류가 발생할 경우
     */
    public static List<String> readNames(String filePath) throws IOException {
        List<String> names = new ArrayList<>();
        // try-with-resources 구문을 사용하여 BufferedReader를 자동으로 닫습니다.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 파일의 끝에 도달할 때까지 한 줄씩 읽어들입니다.
            while ((line = reader.readLine()) != null) {
                // 읽어온 줄을 이름 리스트에 추가합니다.
                names.add(line);
            }
        }
        return names;
    }
}
