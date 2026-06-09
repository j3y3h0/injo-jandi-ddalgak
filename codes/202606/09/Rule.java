// Rule.java
// 이 인터페이스는 이상 거래 감지 시스템의 모든 규칙이 구현해야 하는 계약을 정의합니다.
// 각 규칙은 특정 거래가 사기성이 있는지 여부를 결정하는 `isFraudulent` 메서드를 제공해야 합니다.
// Industry Standard: 전략(Strategy) 디자인 패턴의 핵심 부분으로, 다양한 규칙을 유연하게 추가하고 교체할 수 있도록 합니다.

public interface Rule {
    /**
     * 주어진 거래가 사기성으로 의심되는지 여부를 평가합니다.
     *
     * @param transaction 평가할 거래 객체
     * @return 거래가 사기성으로 의심되면 true, 그렇지 않으면 false
     */
    boolean isFraudulent(Transaction transaction);

    /**
     * 이 규칙에 대한 설명을 반환합니다.
     *
     * @return 규칙에 대한 간략한 설명 문자열
     */
    String getDescription();
}
