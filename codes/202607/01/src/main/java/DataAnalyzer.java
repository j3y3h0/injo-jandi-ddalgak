// DataAnalyzer.java
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAnalyzer {
    private final String inputFilePath;
    private final String outputFilePath;

    // 생성자: 입력 및 출력 파일 경로를 초기화합니다.
    public DataAnalyzer(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    // CSV 파일을 분석하고 결과를 저장하는 메인 메서드입니다.
    public void analyzeAndSave() throws IOException {
        List<Map<String, String>> records = readCsv(); // CSV 파일 읽기
        Map<String, Double> analysisResults = analyzeData(records); // 데이터 분석

        writeCsv(analysisResults); // 분석 결과 CSV 파일로 저장
    }

    // CSV 파일을 읽어 각 행을 Map<String, String> 형태로 반환합니다.
    private List<Map<String, String>> readCsv() throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        // FileReader를 사용하여 CSV 파일 읽기 (UTF-8 인코딩)
        try (Reader in = new FileReader(inputFilePath);
             // CSVParser를 사용하여 CSV 데이터 파싱. 첫 번째 행은 헤더로 간주합니다.
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim())) {

            // CSV 헤더 정보를 가져옵니다.
            List<String> headers = parser.getHeaderNames();

            // 각 레코드를 반복하며 데이터를 Map 형태로 저장합니다.
            for (CSVRecord csvRecord : parser) {
                Map<String, String> recordMap = new HashMap<>();
                for (String header : headers) {
                    recordMap.put(header, csvRecord.get(header));
                }
                records.add(recordMap);
            }
        }
        System.out.println(inputFilePath + " 파일에서 " + records.size() + " 개의 레코드를 읽었습니다.");
        return records;
    }

    // 읽어온 데이터를 기반으로 통계 분석을 수행합니다.
    private Map<String, Double> analyzeData(List<Map<String, String>> records) {
        // Value1과 Value2 열의 데이터를 추출합니다.
        List<Double> value1Data = new ArrayList<>();
        List<Double> value2Data = new ArrayList<>();

        for (Map<String, String> record : records) {
            try {
                // Value1 데이터를 숫자로 변환하여 리스트에 추가합니다.
                value1Data.add(Double.parseDouble(record.get("Value1")));
                // Value2 데이터를 숫자로 변환하여 리스트에 추가합니다.
                value2Data.add(Double.parseDouble(record.get("Value2")));
            } catch (NumberFormatException e) {
                System.err.println("숫자 변환 오류: 유효하지 않은 숫자 값 스킵 - " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("누락된 열 오류: 'Value1' 또는 'Value2' 열이 없습니다. 스킵합니다.");
            }
        }

        Map<String, Double> results = new HashMap<>();
        // DataProcessor를 사용하여 Value1의 평균과 합계를 계산합니다.
        if (!value1Data.isEmpty()) {
            results.put("Value1_평균", DataProcessor.calculateAverage(value1Data));
            results.put("Value1_합계", DataProcessor.calculateSum(value1Data));
        }
        // DataProcessor를 사용하여 Value2의 평균과 합계를 계산합니다.
        if (!value2Data.isEmpty()) {
            results.put("Value2_평균", DataProcessor.calculateAverage(value2Data));
            results.put("Value2_합계", DataProcessor.calculateSum(value2Data));
        }
        System.out.println("데이터 분석을 완료했습니다.");
        return results;
    }

    // 분석 결과를 새로운 CSV 파일로 작성합니다.
    private void writeCsv(Map<String, Double> results) throws IOException {
        // FileWriter를 사용하여 CSV 파일 작성 (UTF-8 인코딩)
        try (FileWriter out = new FileWriter(outputFilePath);
             // CSVPrinter를 사용하여 CSV 데이터 작성
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("통계_항목", "결과값"))) {

            // 분석 결과를 CSV 파일에 한 줄씩 작성합니다.
            for (Map.Entry<String, Double> entry : results.entrySet()) {
                printer.printRecord(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("분석 결과가 " + outputFilePath + " 에 성공적으로 저장되었습니다.");
    }
}