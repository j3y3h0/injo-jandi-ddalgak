// FraudDetector.java
// 이 클래스는 등록된 여러 사기 감지 규칙을 관리하고,
// 들어오는 각 거래에 대해 이 규칙들을 적용하여 사기성 여부를 종합적으로 판단합니다.
// Industry Standard: 파사드(Facade) 패턴과 옵저버(Observer) 패턴의 요소를 가집니다 (여기서는 간단한 파사드 형태).
// 또한, 확장 가능한 규칙 기반 시스템의 핵심 구성 요소입니다.

import java.util.ArrayList;
import java.util.List;

public class FraudDetector {
    private final List<Rule> rules; // 등록된 사기 감지 규칙 목록

    // 생성자: 새로운 FraudDetector 인스턴스를 초기화합니다.
    public FraudDetector() {
        this.rules = new ArrayList<>();
    }

    /**
     * 사기 감지 규칙을 시스템에 추가합니다.
     *
     * @param rule 추가할 Rule 인터페이스 구현체
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * 주어진 거래에 대해 등록된 모든 규칙을 적용하여 사기성 여부를 판단합니다.
     * 하나의 규칙이라도 거래를 사기성으로 판단하면, 해당 거래는 사기성으로 간주됩니다.
     *
     * @param transaction 감지할 거래 객체
     * @return 거래가 하나 이상의 규칙에 의해 사기성으로 판단되면 true, 그렇지 않으면 false
     */
    public boolean detect(Transaction transaction) {
        System.out.println("거래 " + transaction.getTransactionId() + " 감지 중...");
        for (Rule rule : rules) {
            if (rule.isFraudulent(transaction)) {
                System.out.println("  - 규칙 위반: " + rule.getDescription());
                return true; // 첫 번째 사기성 규칙 발견 시 즉시 반환
            }
        }
        return false; // 모든 규칙을 통과했으므로 사기성이 아님
    }
}
