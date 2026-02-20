# 로그 파일 분석기 (Log File Analyzer)

## 프로젝트 개요

이 프로젝트는 지정된 로그 파일 (`sample.log`)을 읽고, 파일 내에서 특정 키워드들(예: ERROR, INFO, WARN 등)의 발생 횟수를 분석하는 간단한 자바 애플리케이션이다. 이는 대규모 시스템 로그에서 중요한 정보를 빠르게 추출하거나 문제 발생 빈도를 파악하는 데 유용하게 활용될 수 있다.

## 프로젝트 구조

```
.
├── sample.log
├── KeywordCounter.java
└── LogAnalyzer.java
```

*   `sample.log`: 분석에 사용될 샘플 로그 파일이다. 다양한 로그 레벨과 메시지를 포함하고 있다.
*   `KeywordCounter.java`: 로그 라인에서 특정 키워드의 발생 횟수를 세는 정적 유틸리티 메서드를 제공하는 클래스이다.
*   `LogAnalyzer.java`: 애플리케이션의 메인 진입점이다. `sample.log` 파일을 읽고, `KeywordCounter`를 사용하여 키워드를 분석한 후, 그 결과를 콘솔에 출력한다.

## 사용 기술

*   Java (표준 라이브러리)
    *   `java.io`, `java.nio.file`: 파일 입출력을 처리한다.
    *   `java.util.HashMap`, `java.util.List`: 데이터 구조를 관리한다.

## 실행 방법

이 프로젝트는 별도의 빌드 도구(예: Maven, Gradle) 없이 표준 자바 컴파일러와 런타임 환경에서 직접 실행할 수 있다.

1.  **컴파일**:
    터미널 또는 명령 프롬프트를 열고, 이 프로젝트의 루트 디렉터리(`C:\server\gpt-code-diary\codes\202602\20`)로 이동한다. 다음 명령어를 사용하여 자바 소스 파일들을 컴파일한다.

    ```bash
    javac LogAnalyzer.java KeywordCounter.java
    ```

    성공적으로 컴파일되면, `LogAnalyzer.class` 및 `KeywordCounter.class` 파일이 같은 디렉터리에 생성될 것이다.

2.  **실행**:
    컴파일이 완료된 후, 다음 명령어를 사용하여 애플리케이션을 실행한다.

    ```bash
    java LogAnalyzer
    ```

## 예상 결과

애플리케이션이 실행되면 `sample.log` 파일의 내용을 분석하고, 설정된 키워드들(예: ERROR, INFO, WARN, DEBUG, 실패, 성공)이 로그 파일에서 몇 번 나타났는지에 대한 통계를 콘솔에 출력할 것이다.

예시 출력:
```
로그 파일 [sample.log] 분석 결과이다.
키워드 'ERROR': N회 발생하였다.
키워드 'INFO': N회 발생하였다.
...
```
