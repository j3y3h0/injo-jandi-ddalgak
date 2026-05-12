package com.example.data;

import com.example.data.model.DataRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV 파일을 읽고 DataRecord 객체 리스트로 변환하는 클래스입니다.
 * Apache Commons CSV 라이브러리를 사용합니다.
 */
public class CsvProcessor {

    private static final String[] HEADERS = {"이름", "나이", "점수"}; // CSV 헤더 정의

    /**
     * 지정된 파일 경로에서 CSV 파일을 읽어 DataRecord 리스트를 반환합니다.
     *
     * @param filePath CSV 파일 경로
     * @return DataRecord 객체 리스트
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public List<DataRecord> readCsvFile(String filePath) throws IOException {
        List<DataRecord> records = new ArrayList<>();
        Reader in = null;
        CSVParser parser = null;

        try {
            in = new FileReader(filePath);
            parser = new CSVParser(in, CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS) // 헤더 설정
                    .setSkipHeaderRecord(true) // 첫 번째 레코드(헤더) 건너뛰기
                    .setTrim(true) // 공백 제거
                    .build());

            for (CSVRecord csvRecord : parser) {
                try {
                    String name = csvRecord.get("이름");
                    int age = Integer.parseInt(csvRecord.get("나이"));
                    double score = Double.parseDouble(csvRecord.get("점수"));
                    records.add(new DataRecord(name, age, score));
                } catch (NumberFormatException e) {
                    System.err.println("경고: 유효하지 않은 숫자 형식의 데이터를 건너뜁니다 - " + csvRecord);
                }
            }
        } finally {
            if (parser != null) {
                parser.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return records;
    }
}
