// DataProcessor.java

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * 숫자 목록에 대한 기본적인 통계(평균, 최소값, 최대값)를 계산하는 유틸리티 클래스입니다.
 */
public class DataProcessor {

    /**
     * 주어진 정수 목록의 평균을 계산합니다.
     * 목록이 비어 있으면 OptionalDouble.empty()를 반환합니다.
     * @param numbers 통계를 계산할 정수 목록.
     * @return 목록의 평균을 포함하는 OptionalDouble 객체.
     */
    public OptionalDouble calculateAverage(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return OptionalDouble.empty();
        }
        return numbers.stream()
                      .mapToDouble(Integer::doubleValue)
                      .average();
    }

    /**
     * 주어진 정수 목록에서 최소값을 찾습니다.
     * 목록이 비어 있으면 OptionalInt.empty()를 반환합니다.
     * @param numbers 최소값을 찾을 정수 목록.
     * @return 목록의 최소값을 포함하는 OptionalInt 객체.
     */
    public OptionalInt findMin(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return OptionalInt.empty();
        }
        return numbers.stream()
                      .mapToInt(Integer::intValue)
                      .min();
    }

    /**
     * 주어진 정수 목록에서 최대값을 찾습니다.
     * 목록이 비어 있으면 OptionalInt.empty()를 반환합니다.
     * @param numbers 최대값을 찾을 정수 목록.
     * @return 목록의 최대값을 포함하는 OptionalInt 객체.
     */
    public OptionalInt findMax(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return OptionalInt.empty();
        }
        return numbers.stream()
                      .mapToInt(Integer::intValue)
                      .max();
    }
}
