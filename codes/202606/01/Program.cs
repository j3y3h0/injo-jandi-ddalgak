using System;
using System.Collections.Generic;

namespace LogAnomalyDetection
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("--- 로그 분석 및 이상 감지 시스템 시작 ---");

            // 1. 샘플 로그 데이터 생성 (실제로는 파일에서 읽어오거나 스트리밍 데이터 사용)
            var sampleLogLines = new List<string>
            {
                "[2026-06-01 10:00:01] [INFO] 사용자 'admin' 로그인 성공",
                "[2026-06-01 10:00:05] [DEBUG] 데이터베이스 쿼리 실행: SELECT * FROM users",
                "[2026-06-01 10:00:10] [INFO] 파일 업로드 완료: document.pdf",
                "[2026-06-01 10:00:15] [WARN] CPU 사용량 80% 초과",
                "[2026-06-01 10:00:20] [INFO] 사용자 'guest' 로그아웃",
                "[2026-06-01 10:01:01] [ERROR] 데이터베이스 연결 실패: Connection refused",
                "[2026-06-01 10:01:05] [INFO] 백업 작업 시작",
                "[2026-06-01 10:01:10] [ERROR] 파일 시스템 오류: Disk full",
                "[2026-06-01 10:01:12] [ERROR] API 호출 실패: Service unavailable",
                "[2026-06-01 10:01:15] [INFO] 시스템 재시작 요청",
                "[2026-06-01 10:01:20] [ERROR] 메모리 부족 오류", // 1분 20초 안에 에러 4개 (임계값 3 초과)
                "[2026-06-01 10:02:00] [INFO] 시스템 재시작 완료",
                "[2026-06-01 10:02:30] [WARN] 네트워크 지연 감지",
                "[2026-06-01 10:03:00] [INFO] 정기 유지보수 시작",
                "[2026-06-01 10:03:05] [ERROR] 인증 실패: Invalid credentials", // 또 다른 에러
                "[2026-06-01 10:03:10] [DEBUG] 캐시 정리 완료",
                "[2026-06-01 10:04:00] [INFO] 정기 유지보수 완료",
                "[2026-06-01 10:04:05] [WARN] 디스크 공간 부족 경고",
                "[2026-06-01 10:04:10] [ERROR] 알 수 없는 오류 발생" // 또 다른 에러
            };

            Console.WriteLine("
--- 샘플 로그 데이터 ---");
            foreach (var log in sampleLogLines)
            {
                Console.WriteLine(log);
            }

            // 2. 로그 처리기를 사용하여 로그 파싱
            var processor = new LogProcessor();
            List<LogEntry> logEntries = processor.ProcessLogs(sampleLogLines);

            Console.WriteLine("
--- 파싱된 로그 엔트리 ---");
            foreach (var entry in logEntries)
            {
                Console.WriteLine(entry);
            }

            // 3. 이상 감지기 초기화 및 실행
            // 1분(1) 동안 3개 이상의 ERROR 로그 발생 시 이상으로 간주
            var detector = new AnomalyDetector(timeWindowInMinutes: 1, errorThreshold: 3);
            List<string> anomalies = detector.DetectAnomalies(logEntries);

            Console.WriteLine("
--- 감지된 이상 징후 ---");
            if (anomalies.Count == 0)
            {
                Console.WriteLine("감지된 이상 징후가 없습니다.");
            }
            else
            {
                foreach (var anomaly in anomalies)
                {
                    Console.WriteLine(anomaly);
                }
            }

            Console.WriteLine("
--- 로그 분석 및 이상 감지 시스템 종료 ---");
        }
    }
}
