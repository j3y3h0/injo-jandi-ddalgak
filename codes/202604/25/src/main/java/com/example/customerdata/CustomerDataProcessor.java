package com.example.customerdata;

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

// 고객 데이터를 CSV 파일에서 읽고, 처리(필터링) 후 다른 CSV 파일로 저장하는 클래스
public class CustomerDataProcessor {

    private static final String[] HEADERS = {"ID", "이름", "이메일", "회원등급"};

    /**
     * CSV 파일에서 고객 데이터를 읽어옵니다.
     *
     * @param filePath 읽을 CSV 파일 경로
     * @return Customer 객체 리스트
     * @throws IOException 파일 읽기 오류 발생 시
     */
    public List<Customer> readCustomersFromCsv(String filePath) throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVFormat.Builder(CSVFormat.DEFAULT)
                                            .setHeader(HEADERS)
                                            .setSkipHeaderRecord(true)
                                            .setTrim(true)
                                            .build()
                                            .parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                // 헤더에 기반하여 각 필드에 접근
                String id = csvRecord.get("ID");
                String name = csvRecord.get("이름");
                String email = csvRecord.get("이메일");
                String membershipLevel = csvRecord.get("회원등급");

                customers.add(new Customer(id, name, email, membershipLevel));
            }
        }
        return customers;
    }

    /**
     * 특정 회원 등급의 고객만 필터링합니다.
     *
     * @param allCustomers 전체 고객 리스트
     * @param targetMembershipLevel 필터링할 회원 등급
     * @return 필터링된 Customer 객체 리스트
     */
    public List<Customer> filterCustomersByMembershipLevel(List<Customer> allCustomers, String targetMembershipLevel) {
        List<Customer> filteredCustomers = new ArrayList<>();
        for (Customer customer : allCustomers) {
            if (customer.getMembershipLevel().equalsIgnoreCase(targetMembershipLevel)) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }

    /**
     * 고객 데이터를 CSV 파일로 저장합니다.
     *
     * @param customers 저장할 Customer 객체 리스트
     * @param filePath 저장할 CSV 파일 경로
     * @throws IOException 파일 쓰기 오류 발생 시
     */
    public void writeCustomersToCsv(List<Customer> customers, String filePath) throws IOException {
        try (FileWriter out = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVFormat.Builder(CSVFormat.DEFAULT)
                                            .setHeader(HEADERS)
                                            .build()
                                            .print(out)) {
            for (Customer customer : customers) {
                csvPrinter.printRecord(customer.getId(), customer.getName(), customer.getEmail(), customer.getMembershipLevel());
            }
        }
    }
}
