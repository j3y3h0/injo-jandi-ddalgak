import java.util.List;
import java.util.stream.Collectors;

/**
 * Z-score 알고리즘을 사용한 금융 이상 거래 탐지기.
 */
public class FraudDetector {

    // Z-score 임계값. 이 값을 초과하면 이상 거래로 간주한다.
    private static final double Z_SCORE_THRESHOLD = 2.5;

    /**
     * 전체 거래 목록에서 사용자별로 이상 거래를 탐지한다.
     * @param allTransactions 모든 사용자의 거래 목록
     * @return 탐지된 이상 거래의 리스트
     */
    public List<Transaction> detectAnomalies(List<Transaction> allTransactions) {

        // 사용자 ID별로 거래를 그룹화하여 처리
        return allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getUserId))
                .values().stream()
                .flatMap(userTransactions -> {
                    // 거래가 2건 미만이면 통계적 의미가 없으므로 분석에서 제외
                    if (userTransactions.size() < 2) {
                        return null; 
                    }

                    // 1. 특정 사용자의 평균 거래 금액(Mean) 계산
                    double sum = userTransactions.stream().mapToDouble(Transaction::getAmount).sum();
                    double mean = sum / userTransactions.size();

                    // 2. 특정 사용자의 거래 금액 표준편차(Standard Deviation) 계산
                    double standardDeviation = 0.0;
                    for (Transaction t : userTransactions) {
                        standardDeviation += Math.pow(t.getAmount() - mean, 2);
                    }
                    standardDeviation = Math.sqrt(standardDeviation / userTransactions.size());

                    // 3. 각 거래의 Z-score를 계산하고 임계값을 초과하는 거래를 필터링
                    return userTransactions.stream()
                        .filter(t -> {
                            // 모든 거래 금액이 동일하면 표준편차가 0이 되므로, 이상 거래가 없는 것으로 처리
                            if (standardDeviation == 0) return false; 
                            
                            double zScore = (t.getAmount() - mean) / standardDeviation;
                            return zScore > Z_SCORE_THRESHOLD;
                        });
                })
                .collect(Collectors.toList());
    }
}
