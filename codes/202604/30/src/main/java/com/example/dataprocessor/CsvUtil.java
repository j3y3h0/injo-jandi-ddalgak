package com.example.dataprocessor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV 파일 처리를 위한 유틸리티 클래스입니다.
 * Apache Commons CSV 라이브러리를 사용하여 CSV 파일을 읽습니다.
 */
public class CsvUtil {

    /**
     * 지정된 경로의 CSV 파일을 읽고 CSVRecord 리스트를 반환합니다.
     * 첫 번째 줄을 헤더로 간주합니다.
     *
     * @param filePath CSV 파일 경로
     * @return CSVRecord 객체의 리스트
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public static List<CSVRecord> readCsvFile(String filePath) throws IOException {
        List<CSVRecord> records = new ArrayList<>();
        // FileReader를 사용하여 파일 읽기. try-with-resources로 자동 닫기 처리.
        try (Reader in = new FileReader(filePath)) {
            // CSVFormat.DEFAULT는 일반적인 쉼표로 구분된 CSV 형식을 사용합니다.
            // .withFirstRecordAsHeader()는 첫 번째 줄을 헤더로 처리하도록 설정합니다.
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            for (CSVRecord record : parser) {
                records.add(record);
            }
        }
        return records;
    }

    /**
     * CSVRecord 리스트에서 특정 헤더에 해당하는 값을 출력합니다. (디버깅 용도)
     *
     * @param records CSVRecord 리스트
     * @param header 출력할 헤더 이름
     */
    public static void printRecordsByHeader(List<CSVRecord> records, String header) {
        System.out.println("--- '" + header + "' 값 목록 ---");
        for (CSVRecord record : records) {
            // 헤더 이름으로 값에 접근합니다.
            if (record.isSet(header)) {
                System.out.println(record.get(header));
            } else {
                System.out.println("헤더 '" + header + "'를 찾을 수 없습니다.");
            }
        }
        System.out.println("-------------------------");
    }
}
