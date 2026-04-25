package com.example.customerdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// 애플리케이션의 메인 진입점
public class Main {
    private static final String INPUT_CSV_FILE = "input_customers.csv";
    private static final String OUTPUT_CSV_FILE = "vip_customers.csv";
    private static final String TARGET_MEMBERSHIP = "VIP";

    public static void main(String[] args) {
        CustomerDataProcessor processor = new CustomerDataProcessor();

        // 예시 입력 CSV 파일 생성
        createSampleInputCsv();

        try {
            // 1. CSV 파일에서 고객 데이터 읽기
            System.out.println("DEBUG: '" + INPUT_CSV_FILE + "' 파일에서 고객 데이터 읽는 중...");
            List<Customer> allCustomers = processor.readCustomersFromCsv(INPUT_CSV_FILE);
            System.out.println("DEBUG: 총 " + allCustomers.size() + "명의 고객 데이터 읽음.");

            // 2. VIP 고객 필터링
            System.out.println("DEBUG: 회원등급 '" + TARGET_MEMBERSHIP + "' 고객 필터링 중...");
            List<Customer> vipCustomers = processor.filterCustomersByMembershipLevel(allCustomers, TARGET_MEMBERSHIP);
            System.out.println("DEBUG: 필터링된 VIP 고객: " + vipCustomers.size() + "명.");

            // 3. 필터링된 VIP 고객 데이터를 새 CSV 파일로 저장
            System.out.println("DEBUG: '" + OUTPUT_CSV_FILE + "' 파일에 VIP 고객 데이터 저장 중...");
            processor.writeCustomersToCsv(vipCustomers, OUTPUT_CSV_FILE);
            System.out.println("DEBUG: VIP 고객 데이터가 '" + OUTPUT_CSV_FILE + "' 에 성공적으로 저장되었습니다.");

            System.out.println("
--- 처리 완료 ---");
            System.out.println("총 " + allCustomers.size() + "명의 고객 중 " + TARGET_MEMBERSHIP + " 고객 " + vipCustomers.size() + "명이 " + OUTPUT_CSV_FILE + " 파일로 추출되었습니다.");

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 예시 입력 CSV 파일을 생성합니다.
     * 이 메서드는 실제 데이터를 읽는 대신 테스트를 위해 가상의 데이터를 생성합니다.
     */
    private static void createSampleInputCsv() {
        Path inputFilePath = Paths.get(INPUT_CSV_FILE);
        if (!Files.exists(inputFilePath)) {
            System.out.println("DEBUG: 예시 입력 CSV 파일 '" + INPUT_CSV_FILE + "' 생성 중...");
            String csvContent = "ID,이름,이메일,회원등급
" +
                                "C001,김철수,kim.cs@example.com,VIP
" +
                                "C002,박영희,park.yh@example.com,NORMAL
" +
                                "C003,이민수,lee.ms@example.com,VIP
" +
                                "C004,최수진,choi.sj@example.com,PLATINUM
" +
                                "C005,정하은,jung.he@example.com,VIP
";
            try {
                Files.write(inputFilePath, csvContent.getBytes());
                System.out.println("DEBUG: 예시 입력 CSV 파일 생성 완료.");
            } catch (IOException e) {
                System.err.println("예시 입력 CSV 파일 생성 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("DEBUG: 예시 입력 CSV 파일 '" + INPUT_CSV_FILE + "'이 이미 존재합니다. 새로 생성하지 않습니다.");
        }
    }
}
