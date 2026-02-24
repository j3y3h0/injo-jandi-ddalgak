// DataAnalyzer.java
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 엑셀에서 읽어온 숫자 데이터를 분석하는 기능을 제공합니다.
 * 합계, 평균, 최대값, 최소값 등을 계산합니다.
 */
public class DataAnalyzer {

    /**
     * 주어진 데이터에서 특정 열의 숫자 데이터를 추출하여 List<Double> 형태로 반환합니다.
     * 숫자로 변환할 수 없는 값은 건너뜁니다.
     *
     * @param rawData 원시 데이터 (List<List<String>>)
     * @param columnIndex 추출할 열의 인덱스 (0부터 시작)
     * @return 추출된 숫자 데이터의 리스트
     */
    public List<Double> extractNumericColumn(List<List<String>> rawData, int columnIndex) {
        List<Double> numericData = new ArrayList<>();
        for (List<String> row : rawData) {
            if (columnIndex < row.size()) {
                try {
                    // 공백 또는 빈 문자열이 아닌 경우에만 파싱 시도
                    String cellValue = row.get(columnIndex).trim();
                    if (!cellValue.isEmpty()) {
                        numericData.add(Double.parseDouble(cellValue));
                    }
                } catch (NumberFormatException e) {
                    // 숫자로 변환할 수 없는 값은 무시
                    System.err.println("경고: '" + row.get(columnIndex) + "' 값은 숫자로 변환할 수 없어 무시됩니다.");
                }
            }
        }
        return numericData;
    }

    /**
     * 숫자 데이터 리스트의 합계를 계산합니다.
     *
     * @param data 숫자 데이터 리스트
     * @return 합계
     */
    public double calculateSum(List<Double> data) {
        return data.stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * 숫자 데이터 리스트의 평균을 계산합니다.
     * 데이터가 비어있으면 0.0을 반환합니다.
     *
     * @param data 숫자 데이터 리스트
     * @return 평균
     */
    public double calculateAverage(List<Double> data) {
        if (data.isEmpty()) {
            return 0.0;
        }
        return calculateSum(data) / data.size();
    }

    /**
     * 숫자 데이터 리스트에서 최대값을 찾습니다.
     * 데이터가 비어있으면 NoSuchElementException을 발생시킵니다.
     *
     * @param data 숫자 데이터 리스트
     * @return 최대값
     * @throws NoSuchElementException 데이터가 비어있을 경우
     */
    public double findMaximum(List<Double> data) {
        return data.stream().mapToDouble(Double::doubleValue).max()
                .orElseThrow(() -> new NoSuchElementException("데이터 리스트가 비어 있어 최대값을 찾을 수 없습니다."));
    }

    /**
     * 숫자 데이터 리스트에서 최소값을 찾습니다.
     * 데이터가 비어있으면 NoSuchElementException을 발생시킵니다.
     *
     * @param data 숫자 데이터 리스트
     * @return 최소값
     * @throws NoSuchElementException 데이터가 비어있을 경우
     */
    public double findMinimum(List<Double> data) {
        return data.stream().mapToDouble(Double::doubleValue).min()
                .orElseThrow(() -> new NoSuchElementException("데이터 리스트가 비어 있어 최소값을 찾을 수 없습니다."));
    }
}
