import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CsvParser 클래스는 CSV 파일에서 데이터를 읽어와 파싱하는 기능을 제공합니다.
 * 파일 경로를 입력받아 각 줄을 읽고 쉼표(`,`)를 기준으로 문자열을 분리하여 숫자 데이터 목록으로 변환합니다.
 */
public class CsvParser {

    /**
     * 지정된 CSV 파일에서 숫자 데이터를 읽어 리스트로 반환합니다.
     * 파일의 각 줄을 읽고 쉼표로 구분된 문자열을 Double 타입으로 변환합니다.
     * 숫자로 변환할 수 없는 값은 무시됩니다.
     *
     * @param filePath CSV 파일의 경로.
     * @return 파일에서 파싱된 숫자 데이터(Double) 리스트. 파일이 없거나 읽을 수 없는 경우 빈 리스트 반환.
     */
    public static List<Double> parseCsv(String filePath) {
        List<Double> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 각 줄을 쉼표로 분리하고 공백 제거 후 숫자로 변환 시도
                List<Double> lineData = Arrays.stream(line.split(","))
                                            .map(String::trim)
                                            .filter(s -> !s.isEmpty()) // 빈 문자열 필터링
                                            .map(s -> {
                                                try {
                                                    return Double.parseDouble(s);
                                                } catch (NumberFormatException e) {
                                                    // 숫자로 변환할 수 없는 값은 무시
                                                    System.err.println("경고: '" + s + "'는 숫자로 변환할 수 없어 무시됩니다.");
                                                    return null;
                                                }
                                            })
                                            .filter(d -> d != null) // null 값(변환 실패한 경우) 필터링
                                            .collect(Collectors.toList());
                data.addAll(lineData);
            }
        } catch (IOException e) {
            System.err.println("오류: CSV 파일을 읽는 도중 문제가 발생했습니다. " + e.getMessage());
            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
        }
        return data;
    }
}
