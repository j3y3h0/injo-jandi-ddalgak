package com.example.csvprocessor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Apache Commons CSV 라이브러리를 사용하여 CSV 파일을 읽고 쓰는 유틸리티 클래스입니다.
 * CSV 데이터 처리의 핵심 로직을 담당합니다.
 */
public class CsvProcessor {

    /**
     * 지정된 CSV 파일에서 데이터를 읽어 Record 객체 리스트로 반환합니다.
     * 파일은 헤더를 포함하며, "Name", "Category", "Value" 컬럼을 예상합니다.
     *
     * @param filePath 읽을 CSV 파일의 경로
     * @return Record 객체 리스트
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public List<Record> readCsv(String filePath) throws IOException {
        List<Record> records = new ArrayList<>();
        // CSVFormat.DEFAULT에 헤더 옵션을 추가하여 첫 줄을 헤더로 인식
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("Name", "Category", "Value") // 예상 헤더 정의 (순서 중요)
                .setSkipHeaderRecord(true) // 헤더 레코드 건너뛰기
                .build();

        try (Reader in = new FileReader(filePath);
             CSVParser parser = new CSVParser(in, csvFormat)) {

            for (CSVRecord csvRecord : parser) {
                String name = csvRecord.get("Name");
                String category = csvRecord.get("Category");
                double value = Double.parseDouble(csvRecord.get("Value"));
                records.add(new Record(name, category, value));
            }
        }
        return records;
    }

    /**
     * Record 객체 리스트를 지정된 경로에 CSV 파일로 작성합니다.
     * 헤더 "Name", "Category", "Value"를 포함합니다.
     *
     * @param filePath 작성할 CSV 파일의 경로
     * @param records 작성할 Record 객체 리스트
     * @throws IOException 파일 작성 중 오류 발생 시
     */
    public void writeCsv(String filePath, List<Record> records) throws IOException {
        // CSVFormat.DEFAULT에 헤더 옵션을 추가하여 첫 줄을 헤더로 인식
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("Name", "Category", "Value") // 헤더 정의
                .build();

        try (FileWriter out = new FileWriter(filePath);
             CSVPrinter printer = new CSVPrinter(out, csvFormat)) {

            for (Record record : records) {
                printer.printRecord(record.getName(), record.getCategory(), record.getValue());
            }
        }
    }
}
