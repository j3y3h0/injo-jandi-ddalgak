package com.example.employment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("미국 1월 민간고용 변화 분석 프로그램");
        System.out.println("----------------------------------");

        System.out.print("실제 고용 증가 수치(예: 22000): ");
        double actualEmployment = scanner.nextDouble();

        System.out.print("예상 고용 증가 수치(예: 150000): ");
        double expectedEmployment = scanner.nextDouble();

        double percentageDifference = EmploymentCalculator.calculatePercentageDifference(actualEmployment, expectedEmployment);
        String comparisonDescription = EmploymentCalculator.getComparisonDescription(percentageDifference);

        System.out.println("\n--- 분석 결과 ---");
        System.out.printf("실제 고용 증가: %.0f명\n", actualEmployment);
        System.out.printf("예상 고용 증가: %.0f명\n", expectedEmployment);
        
        if (Double.isNaN(percentageDifference)) {
            System.out.println("비교할 수 없습니다. (예상치가 0입니다)");
        } else {
            System.out.printf("예상치 대비 차이: %.2f%%\n", percentageDifference);
            System.out.println("결과: " + comparisonDescription);
        }
        
        scanner.close();
    }
}