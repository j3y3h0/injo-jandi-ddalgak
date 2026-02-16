package com.gemini.cli.project;

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

/**
 * CSV 파일의 읽기 및 쓰기 작업을 처리하는 유틸리티 클래스이다.
 * Apache Commons CSV 라이브러리를 활용한다.
 */
public class CsvProcessor {

    /**
     * 지정된 경로의 CSV 파일을 읽어 레코드 리스트를 반환한다.
     * 첫 번째 줄은 헤더로 간주한다.
     *
     * @param filePath 읽을 CSV 파일의 경로
     * @return CSV 레코드의 리스트. 각 레코드는 헤더를 키로 하는 Map<String, String> 형태이다.
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public List<Map<String, String>> readCsv(String filePath) throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        // CSVFormat.DEFAULT는 쉼표로 구분된 기본 CSV 형식을 사용하며, WITH_HEADER는 첫 줄을 헤더로 인식하게 한다.
        try (Reader in = new FileReader(filePath);
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim())) {

            for (CSVRecord csvRecord : parser) {
                // 각 레코드를 Map<String, String> 형태로 변환하여 저장한다.
                Map<String, String> recordMap = new HashMap<>(csvRecord.toMap());
                records.add(recordMap);
            }
        }
        return records;
    }

    /**
     * 레코드 리스트를 지정된 경로의 CSV 파일로 작성한다.
     * 첫 번째 레코드의 키를 헤더로 사용하여 CSV 파일을 생성한다.
     *
     * @param filePath 작성할 CSV 파일의 경로
     * @param records  작성할 레코드 리스트
     * @throws IOException 파일 쓰기 중 오류 발생 시
     */
    public void writeCsv(String filePath, List<Map<String, String>> records) throws IOException {
        if (records == null || records.isEmpty()) {
            System.out.println("작성할 레코드가 없어 CSV 파일을 생성하지 않는다.");
            return;
        }

        // 첫 번째 레코드의 키를 사용하여 헤더를 추출한다.
        String[] headers = records.get(0).keySet().toArray(new String[0]);

        // CSVFormat.DEFAULT는 쉼표로 구분된 기본 CSV 형식을 사용하며, WITH_HEADER는 첫 줄을 헤더로 인식하게 한다.
        try (FileWriter out = new FileWriter(filePath);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {

            for (Map<String, String> record : records) {
                // 각 레코드의 값을 헤더 순서에 맞게 작성한다.
                List<String> values = new ArrayList<>();
                for (String header : headers) {
                    values.add(record.getOrDefault(header, "")); // 값이 없는 경우 빈 문자열로 처리
                }
                printer.printRecord(values);
            }
        }
    }
}
