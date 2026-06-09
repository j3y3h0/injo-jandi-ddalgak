// AmountThresholdRule.java
// 이 클래스는 특정 금액 임계값을 초과하는 거래를 사기성으로 감지하는 규칙을 구현합니다.
// Industry Standard: 도메인 특정 비즈니스 규칙을 캡슐화하는 예시입니다.

public class AmountThresholdRule implements Rule {
    private final double thresholdAmount; // 사기성으로 간주할 금액 임계값

    // 생성자: 규칙이 적용될 임계 금액을 설정합니다.
    public AmountThresholdRule(double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    /**
     * 거래 금액이 설정된 임계값을 초과하는지 확인하여 사기성 여부를 평가합니다.
     *
     * @param transaction 평가할 거래 객체
     * @return 거래 금액이 임계값을 초과하면 true (사기성), 그렇지 않으면 false
     */
    @Override
    public boolean isFraudulent(Transaction transaction) {
        return transaction.getAmount() > thresholdAmount;
    }

    /**
     * 이 규칙에 대한 설명을 반환합니다.
     *
     * @return 규칙에 대한 설명 문자열
     */
    @Override
    public String getDescription() {
        return "금액이 " + thresholdAmount + "을(를) 초과하는 거래 감지";
    }
}
