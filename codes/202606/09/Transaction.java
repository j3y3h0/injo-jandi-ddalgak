// Transaction.java
// 이 파일은 시스템 내에서 처리되는 개별 금융 거래를 나타내는 객체를 정의합니다.
// 각 거래는 고유한 ID, 사용자 ID, 금액 및 발생 시각을 가집니다.
// Industry Standard: POJO (Plain Old Java Object) 패턴을 따릅니다.

public class Transaction {
    private String transactionId; // 거래 고유 ID
    private String userId;        // 거래를 수행한 사용자 ID
    private double amount;        // 거래 금액
    private long timestamp;       // 거래 발생 시각 (Unix 타임스탬프, 밀리초)

    // 생성자: 새로운 거래 객체를 초기화합니다.
    public Transaction(String transactionId, String userId, double amount, long timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Getter 메서드: 각 필드의 값을 반환합니다.
    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // toString 메서드: 거래 객체의 정보를 문자열로 표현하여 쉽게 출력할 수 있도록 합니다.
    @Override
    public String toString() {
        return "Transaction{" +
               "transactionId='" + transactionId + ''' +
               ", userId='" + userId + ''' +
               ", amount=" + amount +
               ", timestamp=" + timestamp +
               '}';
    }
}
