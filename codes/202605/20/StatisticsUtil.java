// StatisticsUtil.java
// 숫자 배열에 대한 평균, 중앙값, 표준편차와 같은 통계적 계산을 수행하는 유틸리티 클래스입니다.

import java.util.Arrays; // 배열 정렬을 위해 필요합니다.

public class StatisticsUtil {

    /**
     * 주어진 double 배열의 평균을 계산합니다.
     *
     * @param data 평균을 계산할 double 배열.
     * @return 배열 요소들의 평균. 데이터가 비어있으면 0.0을 반환합니다.
     */
    public static double calculateMean(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }
        double sum = DataProcessor.calculateSum(data); // DataProcessor를 활용하여 합계를 계산합니다.
        return sum / data.length;
    }

    /**
     * 주어진 double 배열의 중앙값을 계산합니다.
     * 중앙값은 데이터를 정렬했을 때 중앙에 위치하는 값입니다.
     * 데이터의 개수가 홀수이면 중앙값은 가운데 숫자이고, 짝수이면 가운데 두 숫자의 평균입니다.
     *
     * @param data 중앙값을 계산할 double 배열.
     * @return 배열 요소들의 중앙값. 데이터가 비어있으면 0.0을 반환합니다.
     */
    public static double calculateMedian(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        // 중앙값을 계산하기 위해 배열을 정렬합니다.
        double[] sortedData = Arrays.copyOf(data, data.length);
        Arrays.sort(sortedData);

        int middle = sortedData.length / 2;
        if (sortedData.length % 2 == 1) { // 데이터 개수가 홀수인 경우
            return sortedData[middle];
        } else { // 데이터 개수가 짝수인 경우
            return (sortedData[middle - 1] + sortedData[middle]) / 2.0;
        }
    }

    /**
     * 주어진 double 배열의 표준편차를 계산합니다.
     * 표준편차는 데이터가 평균으로부터 얼마나 퍼져 있는지를 나타내는 척도입니다.
     *
     * @param data 표준편차를 계산할 double 배열.
     * @return 배열 요소들의 표준편차. 데이터가 비어있거나 요소가 하나뿐이면 0.0을 반환합니다.
     */
    public static double calculateStandardDeviation(double[] data) {
        if (data == null || data.length <= 1) {
            return 0.0;
        }

        double mean = calculateMean(data);
        double sumOfSquaredDifferences = 0;

        // 각 데이터 포인트와 평균의 차이를 제곱하여 모두 더합니다.
        for (double d : data) {
            sumOfSquaredDifferences += Math.pow(d - mean, 2);
        }

        // 분산(variance)을 계산하고 제곱근을 취하여 표준편차를 얻습니다.
        // 여기서는 표본 표준편차 (n-1) 대신 모집단 표준편차 (n) 공식을 사용합니다.
        // 실제 애플리케이션에서는 상황에 따라 적절한 공식을 선택해야 합니다.
        return Math.sqrt(sumOfSquaredDifferences / data.length);
    }
}
