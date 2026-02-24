// ReportGenerator.java

/**
 * 분석된 데이터를 기반으로 보고서를 생성하는 기능을 제공합니다.
 * 현재는 콘솔에 보고서를 출력하는 기능만 포함합니다.
 */
public class ReportGenerator {

    /**
     * 분석된 주요 지표들을 콘솔에 출력하여 보고서를 생성합니다.
     *
     * @param sum 합계
     * @param average 평균
     * @param max 최대값
     * @param min 최소값
     */
    public void generateConsoleReport(double sum, double average, double max, double min) {
        System.out.println("
--- 분석 결과 보고서 ---");
        System.out.printf("  총 합계: %.2f%n", sum);
        System.out.printf("  평균:    %.2f%n", average);
        System.out.printf("  최대값:  %.2f%n", max);
        System.out.printf("  최소값:  %.2f%n", min);
        System.out.println("---------------------
");
    }

    // 향후 파일로 보고서를 생성하는 기능 등을 추가할 수 있습니다.
    // public void generateFileReport(String filePath, double sum, double average, double max, double min) {
    //     // 파일에 보고서 내용을 작성하는 로직
    // }
}
