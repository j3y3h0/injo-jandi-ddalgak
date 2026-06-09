// Main.java
// 이 파일은 이상 거래 감지 시스템의 메인 실행 진입점입니다.
// 가상의 거래 데이터를 생성하고, FraudDetector를 초기화하며,
// 정의된 규칙에 따라 거래의 사기성 여부를 감지하는 과정을 시뮬레이션합니다.
// Industry Standard: 간단한 콘솔 애플리케이션의 시작점으로 활용됩니다.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 이상 거래 감지 시스템 시작 ---");

        // 1. FraudDetector 인스턴스 생성
        FraudDetector detector = new FraudDetector();

        // 2. 사기 감지 규칙 추가
        // 예시: 1000.00을 초과하는 거래는 사기성으로 간주하는 규칙
        detector.addRule(new AmountThresholdRule(1000.00));
        System.out.println("규칙 추가됨: " + new AmountThresholdRule(1000.00).getDescription());

        // 3. 테스트용 거래 데이터 생성
        List<Transaction> transactions = Arrays.asList(
            new Transaction("TX001", "userA", 150.00, System.currentTimeMillis()),
            new Transaction("TX002", "userB", 1200.50, System.currentTimeMillis() + 1000), // 사기성
            new Transaction("TX003", "userA", 50.00, System.currentTimeMillis() + 2000),
            new Transaction("TX004", "userC", 2500.00, System.currentTimeMillis() + 3000), // 사기성
            new Transaction("TX005", "userB", 750.00, System.currentTimeMillis() + 4000)
        );

        // 4. 각 거래에 대해 사기 감지 수행 및 결과 출력
        System.out.println("
--- 거래 감지 시작 ---");
        for (Transaction tx : transactions) {
            boolean isFraud = detector.detect(tx);
            if (isFraud) {
                System.out.println("--> [주의] 사기성 거래 감지됨: " + tx.getTransactionId() + "
");
            } else {
                System.out.println("--> 정상 거래: " + tx.getTransactionId() + "
");
            }
        }

        System.out.println("--- 이상 거래 감지 시스템 종료 ---");
    }
}
