import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 데모 실행을 위한 샘플 거래 데이터를 생성하는 유틸리티 클래스
 */
public class TransactionData {

    /**
     * 샘플 거래 데이터를 생성한다.
     * 특정 사용자의 일반적인 거래와 통계적으로 벗어나는 이상 거래를 포함한다.
     * @return 거래 데이터 리스트
     */
    public static List<Transaction> getSampleTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // User "user-123"의 일반적인 거래
        transactions.add(new Transaction("txn-001", "user-123", 50.00, now.minusDays(10)));
        transactions.add(new Transaction("txn-002", "user-123", 75.50, now.minusDays(9)));
        transactions.add(new Transaction("txn-003", "user-123", 120.00, now.minusDays(8)));
        transactions.add(new Transaction("txn-004", "user-123", 45.20, now.minusDays(7)));
        transactions.add(new Transaction("txn-005", "user-123", 88.90, now.minusDays(6)));
        
        // User "user-123"의 이상 거래 (금액이 매우 큼)
        transactions.add(new Transaction("txn-006", "user-123", 1500.00, now.minusDays(1)));

        // User "user-456"의 일반적인 거래
        transactions.add(new Transaction("txn-007", "user-456", 250.00, now.minusDays(5)));
        transactions.add(new Transaction("txn-008", "user-456", 300.00, now.minusDays(4)));
        transactions.add(new Transaction("txn-009", "user-456", 275.80, now.minusDays(3)));

        // User "user-456"의 이상 거래 (금액이 매우 큼)
        transactions.add(new Transaction("txn-010", "user-456", 5000.00, now.minusDays(1)));
        
        // User "user-789" (이상 거래 없음)
        transactions.add(new Transaction("txn-011", "user-789", 150.00, now.minusDays(2)));
        transactions.add(new Transaction("txn-012", "user-789", 180.50, now.minusDays(1)));


        return transactions;
    }
}
