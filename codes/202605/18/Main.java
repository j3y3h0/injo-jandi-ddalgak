// Main.java
// 이 파일은 로그 처리 및 이상 징후 감지 시스템의 메인 실행 파일입니다.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("로그 분석 및 이상 징후 감지 시스템을 시작합니다.");

        // 1. 원시 로그 데이터 준비
        List<String> rawLogs = Arrays.asList(
                "[2026-05-18T10:00:01] [INFO] 사용자 'admin' 로그인 성공",
                "[2026-05-18T10:00:05] [DEBUG] 데이터베이스 쿼리 실행: SELECT * FROM users",
                "[2026-05-18T10:00:10] [INFO] 보고서 생성 완료: report_20260518.pdf",
                "[2026-05-18T10:00:15] [WARN] 디스크 공간 부족 경고: 80% 사용됨",
                "[2026-05-18T10:00:20] [ERROR] 인증 실패: 잘못된 비밀번호",
                "[2026-05-18T10:00:22] [INFO] 사용자 'guest' 로그인 성공",
                "[2026-05-18T10:00:25] [ERROR] 데이터베이스 연결 실패",
                "[2026-05-18T10:00:28] [ERROR] 서비스 'PaymentGateway' 응답 없음",
                "[2026-05-18T10:00:30] [INFO] 시스템 재시작 요청",
                "[2026-05-18T10:00:35] [ERROR] 파일 시스템 오류: /var/log 쓰기 실패"
        );

        // 2. 로그 처리기 초기화 및 로그 파싱
        LogProcessor processor = new LogProcessor();
        processor.processLogs(rawLogs);
        List<LogEntry> processedLogEntries = processor.getLogEntries();

        System.out.println("
--- 처리된 로그 목록 ---");
        processedLogEntries.forEach(System.out::println);

        // 3. 이상 징후 감지기 초기화 및 감지 실행
        // 임계값을 3으로 설정: ERROR 로그가 3개 이상이면 이상 징후로 판단
        AnomalyDetector detector = new AnomalyDetector(3);
        boolean anomalyDetected = detector.detect(processedLogEntries);

        if (anomalyDetected) {
            System.out.println("
!!! 시스템에 이상 징후가 감지되었습니다. 추가 조사가 필요합니다. !!!");
        } else {
            System.out.println("
시스템은 현재 정상적으로 동작하고 있습니다.");
        }

        // 4. 로그 레벨 분포 분석 (추가 분석)
        detector.analyzeLogLevelDistribution(processedLogEntries);

        System.out.println("
로그 분석 및 이상 징후 감지 시스템을 종료합니다.");
    }
}
