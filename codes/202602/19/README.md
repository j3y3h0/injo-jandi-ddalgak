# 로그 파일 분석기 (Log File Analyzer)

## 프로젝트 개요
이 프로젝트는 특정 형식의 로그 파일을 읽고 분석하여, 로그 레벨별 통계 및 중요한 로그 메시지(예: ERROR, WARN)를 요약한 보고서를 생성하는 간단한 Java 애플리케이션입니다.

## 코드 구조
프로젝트는 다음 4개의 Java 파일로 구성되어 있습니다.

1.  **`LogEntry.java`**:
    *   로그 파일의 각 엔트리(항목)를 나타내는 데이터 모델 클래스입니다.
    *   로그 발생 시각, 로그 레벨 (INFO, WARN, ERROR 등), 로그 메시지 내용을 저장합니다.

2.  **`LogParser.java`**:
    *   단일 로그 라인(줄) 문자열을 파싱(분석)하여 `LogEntry` 객체로 변환하는 정적 유틸리티 클래스입니다.
    *   정규 표현식을 사용하여 미리 정의된 로그 형식에 맞춰 파싱을 수행합니다.
    *   현재 지원하는 로그 형식: `[YYYY-MM-DD HH:MM:SS] LEVEL - MESSAGE` (예: `[2026-02-19 10:00:01] INFO - Application started.`)

3.  **`LogReportGenerator.java`**:
    *   파싱된 `LogEntry` 객체들의 목록을 기반으로 분석 보고서를 파일로 생성하는 정적 유틸리티 클래스입니다.
    *   보고서에는 총 로그 엔트리 수, 로그 레벨별 개수, 최신 ERROR/WARN 로그 메시지 등이 포함됩니다.

4.  **`LogAnalyzer.java`**:
    *   이 애플리케이션의 메인 실행 클래스입니다.
    *   입력 로그 파일 경로와 출력 보고서 파일 경로를 인자로 받아, `LogParser`를 사용하여 로그를 읽고 파싱합니다.
    *   파싱된 로그 엔트리를 `LogReportGenerator`에 전달하여 최종 보고서를 생성합니다.

## 실행 방법
이 프로젝트는 표준 Java 개발 환경(JDK 8 이상)에서 빌드 및 실행할 수 있습니다.

### 1. 소스 파일 준비
현재 작업 디렉터리에 다음 경로 구조로 파일을 생성해야 합니다.
`./com/example/loganalyzer/` 디렉터리 안에 `.java` 파일들을 위치시킵니다.

```
.
├── README.md
└── com
    └── example
        └── loganalyzer
            ├── LogAnalyzer.java
            ├── LogEntry.java
            ├── LogParser.java
            └── LogReportGenerator.java
```

### 2. 컴파일
프로젝트의 루트 디렉터리(예: `C:\server\gpt-code-diary\codes\202602\19`)에서 다음 명령어를 실행하여 Java 소스 파일을 컴파일합니다.

```bash
javac com/example/loganalyzer/*.java
```

### 3. 실행
컴파일이 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행합니다. `[로그_파일_경로]`와 `[보고서_저장_경로]`는 실제 파일 경로로 대체해야 합니다.

예시:
```bash
java com.example.loganalyzer.LogAnalyzer my_application.log analysis_report.txt
```

#### 예시 로그 파일 (`my_application.log` 내용)
실행을 위해 `my_application.log` 파일을 프로젝트 루트 디렉터리에 생성하고 아래 내용을 넣어보세요.

```
[2026-02-19 10:00:01] INFO - Application started.
[2026-02-19 10:00:05] DEBUG - Processing user request: UserID=123
[2026-02-19 10:00:10] INFO - Database connection established.
[2026-02-19 10:00:15] WARN - High memory usage detected. Current: 85%
[2026-02-19 10:00:20] INFO - Data processed for UserID=123.
[2026-02-19 10:00:25] DEBUG - Sending response to client.
[2026-02-19 10:00:30] ERROR - Failed to save user profile. UserID=123, Cause: Database write error.
[2026-02-19 10:00:35] INFO - Application shutting down.
[2026-02-19 10:00:40] WARN - Cache synchronization failed. Retrying...
[2026-02-19 10:00:45] ERROR - Critical service unavailable. Restarting...
[2026-02-19 10:00:50] INFO - Service restarted successfully.
```

위 예시 로그 파일로 실행하면 `analysis_report.txt` 파일에 분석 결과가 저장될 것입니다.

## 추가 정보
*   이 프로젝트는 기본적인 로그 분석 기능을 제공하며, 필요에 따라 로그 형식, 분석 지표, 보고서 형식 등을 확장할 수 있습니다.
*   산업 환경에서는 Apache Log4j, SLF4j 등과 같은 전문 로깅 라이브러리와 Elasticsearch, Kibana와 같은 로그 관리 솔루션이 사용됩니다. 이 프로젝트는 해당 시스템들의 기본적인 개념을 이해하는 데 도움이 될 수 있습니다.
