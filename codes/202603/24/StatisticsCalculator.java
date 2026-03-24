import java.util.List;
import java.util.OptionalDouble;

/**
 * StatisticsCalculator 클래스는 숫자 데이터 목록에 대한 기본적인 통계(평균, 최솟값, 최댓값)를 계산합니다.
 */
public class StatisticsCalculator {

    /**
     * 주어진 숫자 데이터 목록의 평균을 계산합니다.
     * 목록이 비어있으면 0.0을 반환합니다.
     *
     * @param data 평균을 계산할 숫자(Double) 데이터 목록.
     * @return 데이터 목록의 평균.
     */
    public static double calculateAverage(List<Double> data) {
        if (data == null || data.isEmpty()) {
            return 0.0;
        }
        OptionalDouble average = data.stream()
                                     .mapToDouble(Double::doubleValue)
                                     .average();
        return average.orElse(0.0);
    }

    /**
     * 주어진 숫자 데이터 목록의 최솟값을 찾습니다.
     * 목록이 비어있으면 Double.NaN을 반환합니다.
     *
     * @param data 최솟값을 찾을 숫자(Double) 데이터 목록.
     * @return 데이터 목록의 최솟값.
     */
    public static double findMin(List<Double> data) {
        if (data == null || data.isEmpty()) {
            return Double.NaN; // Not a Number
        }
        OptionalDouble min = data.stream()
                                  .mapToDouble(Double::doubleValue)
                                  .min();
        return min.orElse(Double.NaN);
    }

    /**
     * 주어진 숫자 데이터 목록의 최댓값을 찾습니다.
     * 목록이 비어있으면 Double.NaN을 반환합니다.
     *
     * @param data 최댓값을 찾을 숫자(Double) 데이터 목록.
     * @return 데이터 목록의 최댓값.
     */
    public static double findMax(List<Double> data) {
        if (data == null || data.isEmpty()) {
            return Double.NaN; // Not a Number
        }
        OptionalDouble max = data.stream()
                                  .mapToDouble(Double::doubleValue)
                                  .max();
        return max.orElse(Double.NaN);
    }
}
