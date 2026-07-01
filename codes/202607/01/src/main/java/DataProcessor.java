// DataProcessor.java
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.List;

public class DataProcessor {

    // 주어진 숫자 리스트의 평균을 계산합니다.
    public static double calculateAverage(List<Double> data) {
        if (data == null || data.isEmpty()) {
            return 0.0; // 데이터가 없으면 0.0을 반환
        }
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Double value : data) {
            stats.addValue(value); // 데이터를 통계 객체에 추가
        }
        return stats.getMean(); // 평균 계산 및 반환
    }

    // 주어진 숫자 리스트의 합계를 계산합니다.
    public static double calculateSum(List<Double> data) {
        if (data == null || data.isEmpty()) {
            return 0.0; // 데이터가 없으면 0.0을 반환
        }
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Double value : data) {
            stats.addValue(value); // 데이터를 통계 객체에 추가
        }
        return stats.getSum(); // 합계 계산 및 반환
    }
}