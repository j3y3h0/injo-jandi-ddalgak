import java.time.LocalDateTime;

/**
 * 거래 정보를 표현하는 데이터 클래스 (POJO)
 */
public class Transaction {
    private final String transactionId;
    private final String userId;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String userId, double amount, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + transactionId + ''' +
                ", userId='" + userId + ''' +
                ", amount=" + String.format("%.2f", amount) +
                ", timestamp=" + timestamp +
                '}';
    }
}
