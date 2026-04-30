package com.example.dataprocessor;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

/**
 * 메인 애플리케이션 클래스입니다.
 * CSV 파일을 읽고 특정 컬럼의 값을 집계하는 기능을 수행합니다.
 */
public class DataProcessor {

    public static void main(String[] args) {
        // 예시 CSV 파일 경로. 실제 사용 시에는 사용자 입력 또는 설정 파일에서 경로를 받도록 할 수 있습니다.
        // 이 예제에서는 프로젝트 루트에 'data.csv' 파일이 있다고 가정합니다.
        String csvFilePath = "data.csv";
        String targetColumnHeader = "Amount"; // 집계할 컬럼의 헤더 이름

        System.out.println("데이터 처리 애플리케이션을 시작합니다.");
        System.out.println("처리할 CSV 파일: " + csvFilePath);
        System.out.println("집계할 컬럼: " + targetColumnHeader);

        try {
            // CsvUtil을 사용하여 CSV 파일 읽기
            List<CSVRecord> records = CsvUtil.readCsvFile(csvFilePath);

            if (records.isEmpty()) {
                System.out.println("CSV 파일에서 읽을 데이터가 없습니다.");
                return;
            }

            // 특정 컬럼의 값 합계 계산
            double totalAmount = calculateSum(records, targetColumnHeader);
            System.out.println("
------------------------------------");
            System.out.println("총 '" + targetColumnHeader + "' 합계: " + totalAmount);
            System.out.println("------------------------------------");

        } catch (IOException e) {
            System.err.println("CSV 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
            // 디버깅을 위해 스택 트레이스를 출력합니다.
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 변환 오류: '" + targetColumnHeader + "' 컬럼의 값이 유효한 숫자가 아닙니다. " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("잘못된 인자 오류: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("
데이터 처리 애플리케이션을 종료합니다.");
    }

    /**
     * CSVRecord 리스트에서 특정 헤더의 컬럼 값을 숫자로 변환하여 합계를 계산합니다.
     *
     * @param records CSVRecord 리스트
     * @param header 합계를 계산할 컬럼의 헤더 이름
     * @return 컬럼 값의 총합
     * @throws NumberFormatException 컬럼 값이 숫자로 변환될 수 없을 때 발생
     * @throws IllegalArgumentException 지정된 헤더가 존재하지 않을 때 발생
     */
    private static double calculateSum(List<CSVRecord> records, String header) {
        double sum = 0.0;
        for (CSVRecord record : records) {
            // 레코드에 해당 헤더가 존재하는지 확인
            if (!record.isSet(header)) {
                throw new IllegalArgumentException("CSV 파일에 '" + header + "' 헤더가 존재하지 않습니다.");
            }
            String value = record.get(header);
            try {
                sum += Double.parseDouble(value);
            } catch (NumberFormatException e) {
                // 어떤 레코드에서 숫자가 아닌 값이 발견되었는지 메시지에 포함
                throw new NumberFormatException("레코드 번호 " + record.getRecordNumber() + "의 '" + header + "' 컬럼 값 '" + value + "'이 유효한 숫자가 아닙니다.");
            }
        }
        return sum;
    }
}
