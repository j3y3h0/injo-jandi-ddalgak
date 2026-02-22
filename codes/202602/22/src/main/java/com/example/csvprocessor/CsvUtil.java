package com.example.csvprocessor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSV 파일 처리 및 데이터 분석을 위한 유틸리티 클래스이다.
 * Apache Commons CSV 라이브러리를 활용하여 CSV를 읽고 파싱한다.
 */
public class CsvUtil {

    private static final String[] HEADERS = {"이름", "과목", "점수"}; // CSV 헤더 정의

    /**
     * 지정된 경로의 CSV 파일을 읽어 DataModel 객체 리스트로 반환한다.
     * 첫 번째 줄을 헤더로 가정하고 스킵한다.
     *
     * @param filePath CSV 파일 경로
     * @return DataModel 객체 리스트
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public static List<DataModel> readCsv(String filePath) throws IOException {
        List<DataModel> dataList = new ArrayList<>();
        try (Reader in = new FileReader(filePath);
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader())) {

            for (CSVRecord record : parser) {
                String name = record.get("이름");
                String subject = record.get("과목");
                int score = Integer.parseInt(record.get("점수"));
                dataList.add(new DataModel(name, subject, score));
            }
        }
        return dataList;
    }

    /**
     * 주어진 DataModel 리스트에서 특정 과목의 평균 점수를 계산한다.
     *
     * @param dataList 처리할 DataModel 리스트
     * @param subject  평균을 계산할 과목
     * @return 특정 과목의 평균 점수
     */
    public static double calculateAverageScore(List<DataModel> dataList, String subject) {
        return dataList.stream()
                .filter(data -> data.getSubject().equals(subject))
                .mapToInt(DataModel::getScore)
                .average()
                .orElse(0.0); // 해당 과목이 없으면 0.0 반환
    }

    /**
     * 주어진 DataModel 리스트에서 특정 과목의 모든 데이터를 필터링하여 반환한다.
     *
     * @param dataList 처리할 DataModel 리스트
     * @param subject  필터링할 과목
     * @return 특정 과목의 DataModel 리스트
     */
    public static List<DataModel> filterBySubject(List<DataModel> dataList, String subject) {
        return dataList.stream()
                .filter(data -> data.getSubject().equals(subject))
                .collect(Collectors.toList());
    }
}
