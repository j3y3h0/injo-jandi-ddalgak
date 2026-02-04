import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("금융 이상 거래 탐지 시스템을 시작합니다...");

        // 1. 샘플 거래 데이터 로드
        List<Transaction> transactions = TransactionData.getSampleTransactions();
        System.out.println("총 " + transactions.size() + "개의 거래 데이터를 로드했습니다.");
        System.out.println("--------------------------------------------------");

        // 2. 이상 거래 탐지기 생성 및 실행
        FraudDetector detector = new FraudDetector();
        List<Transaction> anomalies = detector.detectAnomalies(transactions);

        // 3. 결과 출력
        if (anomalies.isEmpty() || anomalies.get(0) == null) {
            System.out.println("탐지된 이상 거래가 없습니다.");
        } else {
            System.out.println("총 " + anomalies.size() + "개의 이상 의심 거래를 탐지했습니다:");
            anomalies.forEach(t -> {
                System.out.println("  - 사용자: " + t.getUserId() + ", 거래 금액: " + String.format("%,.2f", t.getAmount()) + ", 거래 ID: " + t.getTransactionId());
            });
        }
        System.out.println("--------------------------------------------------");
        System.out.println("시스템을 종료합니다.");
    }
}
