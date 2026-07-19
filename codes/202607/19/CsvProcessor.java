// CsvProcessor.java
package com.example.csvproject;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * CSV 파일을 읽고 파싱하는 유틸리티 클래스이다.
 * Apache Commons CSV 라이브러리를 사용하여 안정적으로 CSV 데이터를 처리한다.
 */
public class CsvProcessor {

    /**
     * 지정된 파일 경로에서 CSV 파일을 읽어 CSVRecord 리스트를 반환한다.
     * 첫 번째 줄은 헤더로 간주한다.
     *
     * @param filePath CSV 파일의 경로이다.
     * @return 파싱된 CSVRecord 리스트이다.
     * @throws IOException 파일 읽기 중 오류가 발생할 경우 발생한다.
     */
    public List<CSVRecord> readCsvFile(String filePath) throws IOException {
        Reader in = null;
        CSVParser parser = null;
        try {
            in = new FileReader(filePath);
            // CSVFormat.DEFAULT는 일반적인 CSV 형식을 가정한다.
            // withFirstRecordAsHeader()를 사용하여 첫 번째 레코드를 헤더로 처리한다.
            parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            return parser.getRecords();
        } finally {
            // 리소스를 안전하게 닫는다.
            if (parser != null) {
                parser.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
