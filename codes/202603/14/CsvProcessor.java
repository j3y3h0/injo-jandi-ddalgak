// CsvProcessor.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvProcessor {

    /**
     * 지정된 CSV 파일에서 데이터를 읽어와 List<Map<String, String>> 형태로 반환한다.
     * 첫 번째 줄은 헤더로 간주한다.
     *
     * @param filePath CSV 파일 경로
     * @return 각 행을 Map으로 표현한 데이터 리스트
     * @throws IOException 파일 읽기 오류 발생 시
     */
    public static List<Map<String, String>> readCsv(String filePath) throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = null;

            // 헤더 읽기
            if ((line = br.readLine()) != null) {
                headers = line.split(",");
            }

            // 데이터 읽기
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (headers != null && values.length == headers.length) {
                    Map<String, String> record = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        record.put(headers[i].trim(), values[i].trim());
                    }
                    records.add(record);
                }
            }
        }
        return records;
    }
}
