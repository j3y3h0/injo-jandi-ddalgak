// DataProcessor.java
// 데이터 전처리 기능을 담당하는 유틸리티 클래스입니다.
public class DataProcessor {

    /**
     * 주어진 double 배열의 모든 요소의 합계를 계산합니다.
     * @param data 합계를 계산할 double 배열.
     * @return 배열 요소들의 합계.
     */
    public static double calculateSum(double[] data) {
        double sum = 0;
        for (double d : data) {
            sum += d;
        }
        return sum;
    }

    // 향후 데이터 정규화, 필터링 등 다양한 전처리 메서드를 추가할 수 있습니다.
}
