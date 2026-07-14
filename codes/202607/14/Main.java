// Main.java

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * 데이터 처리 예제를 실행하는 메인 클래스입니다.
 * DataProcessor 클래스를 사용하여 숫자 목록의 평균, 최소값, 최대값을 계산하고 출력합니다.
 */
public class Main {
    public static void main(String[] args) {
        // 처리할 숫자 목록을 정의합니다.
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50, 15, 25, 35);
        
        // DataProcessor 인스턴스를 생성합니다.
        DataProcessor processor = new DataProcessor();

        System.out.println("--- 데이터 통계 분석 프로그램 ---");
        System.out.println("원본 데이터: " + numbers);

        // 평균을 계산하고 출력합니다.
        OptionalDouble average = processor.calculateAverage(numbers);
        if (average.isPresent()) {
            System.out.printf("평균: %.2f%n", average.getAsDouble());
        } else {
            System.out.println("평균을 계산할 데이터가 없습니다.");
        }

        // 최소값을 찾고 출력합니다.
        OptionalInt min = processor.findMin(numbers);
        if (min.isPresent()) {
            System.out.println("최소값: " + min.getAsInt());
        } else {
            System.out.println("최소값을 찾을 데이터가 없습니다.");
        }

        // 최대값을 찾고 출력합니다.
        OptionalInt max = processor.findMax(numbers);
        if (max.isPresent()) {
            System.out.println("최대값: " + max.getAsInt());
        } else {
            System.out.println("최대값을 찾을 데이터가 없습니다.");
        }

        // 비어있는 목록으로 테스트
        List<Integer> emptyNumbers = Arrays.asList();
        System.out.println("
--- 빈 데이터 목록으로 테스트 ---");
        System.out.println("원본 데이터: " + emptyNumbers);

        OptionalDouble emptyAverage = processor.calculateAverage(emptyNumbers);
        if (emptyAverage.isPresent()) {
            System.out.printf("평균: %.2f%n", emptyAverage.getAsDouble());
        } else {
            System.out.println("평균을 계산할 데이터가 없습니다.");
        }
    }
}
