// DataProcessor.java
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * CSV 파일을 처리하고 데이터에 대한 기본적인 분석을 수행하는 클래스입니다.
 * Apache Commons CSV 라이브러리를 사용하여 CSV 파일을 파싱합니다.
 */
public class DataProcessor {

    /**
     * 지정된 경로의 CSV 파일을 읽어 DataModel 객체 리스트로 변환합니다.
     * 첫 번째 행은 헤더로 간주하고 건너뜁니다.
     *
     * @param filePath CSV 파일의 경로
     * @return DataModel 객체 리스트
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public List<DataModel> readCsvFile(String filePath) throws IOException {
        List<DataModel> dataList = new ArrayList<>();
        try (Reader in = new FileReader(filePath);
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : parser) {
                try {
                    String name = record.get("Name");
                    int age = Integer.parseInt(record.get("Age"));
                    double score = Double.parseDouble(record.get("Score"));
                    dataList.add(new DataModel(name, age, score));
                } catch (NumberFormatException e) {
                    System.err.println("경고: 유효하지 않은 숫자 형식입니다. 레코드 건너뛰기: " + record);
                } catch (IllegalArgumentException e) {
                    System.err.println("경고: 필수 헤더(Name, Age, Score)가 누락되었습니다. 레코드 건너뛰기: " + record);
                }
            }
        }
        return dataList;
    }

    /**
     * 주어진 DataModel 리스트에서 평균 점수를 계산합니다.
     *
     * @param dataList DataModel 객체 리스트
     * @return 평균 점수 (데이터가 없으면 0.0)
     */
    public double calculateAverageScore(List<DataModel> dataList) {
        OptionalDouble average = dataList.stream()
                                         .mapToDouble(DataModel::getScore)
                                         .average();
        return average.orElse(0.0);
    }

    /**
     * 주어진 DataModel 리스트에서 가장 높은 점수를 찾습니다.
     *
     * @param dataList DataModel 객체 리스트
     * @return 가장 높은 점수 (데이터가 없으면 0.0)
     */
    public double findMaxScore(List<DataModel> dataList) {
        OptionalDouble max = dataList.stream()
                                     .mapToDouble(DataModel::getScore)
                                     .max();
        return max.orElse(0.0);
    }

    /**
     * 주어진 DataModel 리스트에서 가장 낮은 점수를 찾습니다.
     *
     * @param dataList DataModel 객체 리스트
     * @return 가장 낮은 점수 (데이터가 없으면 0.0)
     */
    public double findMinScore(List<DataModel> dataList) {
        OptionalDouble min = dataList.stream()
                                     .mapToDouble(DataModel::getScore)
                                     .min();
        return min.orElse(0.0);
    }
}
