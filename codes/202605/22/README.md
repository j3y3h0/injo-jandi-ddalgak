# 로그 분석기 프로젝트

이 프로젝트는 Apache Log4j2를 사용하여 샘플 로그 파일을 생성하고, 간단한 정규 표현식 기반 파서를 통해 로그를 분석하는 Java 애플리케이션입니다.

## 프로젝트 목표

*   산업에서 널리 사용되는 로깅 프레임워크인 Log4j2의 활용 방법을 시연합니다.
*   파일에서 로그 데이터를 읽고 파싱하는 기본적인 방법을 보여줍니다.
*   파싱된 로그 데이터에 대한 간단한 분석(예: 오류 메시지 수 집계)을 수행합니다.

## 코드 구조

```
.
├── pom.xml                     # Maven 빌드 설정 파일
├── README.md                   # 프로젝트 설명 파일
└── src
    └── main
        ├── java
        │   ├── LogEntry.java   # 파싱된 로그 항목을 나타내는 데이터 클래스
        │   ├── LogGenerator.java # 샘플 로그 파일을 생성하는 유틸리티 클래스
        │   ├── LogProcessor.java # 로그 파일을 읽고 분석하는 핵심 로직 클래스
        │   └── Main.java       # 애플리케이션의 진입점
        └── resources
            └── log4j2.xml      # Log4j2 로깅 설정 파일
```

*   `LogEntry.java`: 로그의 타임스탬프, 로그 레벨, 메시지를 저장하는 간단한 자바 객체(POJO)입니다.
*   `LogGenerator.java`: `Log4j2`를 사용하여 `application.log`라는 이름의 샘플 로그 파일을 생성합니다. 다양한 로그 레벨(INFO, WARN, ERROR)의 메시지를 포함합니다.
*   `LogProcessor.java`: `application.log` 파일을 읽고, 각 로그 라인을 정규 표현식을 사용하여 `LogEntry` 객체로 파싱합니다. 파싱 후, 각 로그 레벨별(특히 ERROR) 메시지 수를 집계하여 출력합니다.
*   `Main.java`: `LogGenerator`를 호출하여 로그 파일을 생성하고, `LogProcessor`를 호출하여 해당 파일을 분석하는 전체 워크플로우를 실행합니다.
*   `pom.xml`: 프로젝트의 의존성(Log4j2)과 빌드 방법을 정의하는 Maven 설정 파일입니다.
*   `log4j2.xml`: Log4j2의 로깅 동작(콘솔 출력, 파일 저장 등)을 구성하는 XML 파일입니다.

## 실행 방법

이 프로젝트는 Apache Maven을 사용하여 빌드 및 실행됩니다.

### 1. 전제 조건

*   Java Development Kit (JDK) 8 이상이 설치되어 있어야 합니다.
*   Apache Maven이 설치되어 있어야 합니다.

### 2. 프로젝트 빌드

프로젝트 루트 디렉터리(`pom.xml` 파일이 있는 곳)에서 다음 명령어를 실행하여 프로젝트를 빌드합니다:

```bash
mvn clean install
```

이 명령어는 모든 의존성을 다운로드하고, 소스 코드를 컴파일하며, 실행 가능한 JAR 파일을 `target` 디렉터리에 생성합니다.

### 3. 애플리케이션 실행

빌드가 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행할 수 있습니다:

```bash
java -jar target/LogAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar
```

애플리케이션은 먼저 `application.log` 파일을 생성한 다음, 해당 파일을 읽고 분석 결과를 콘솔에 출력합니다.

### 4. 로그 파일 확인

`application.log` 파일은 프로젝트 루트 디렉터리에 생성됩니다. 이 파일을 열어 생성된 로그 내용을 직접 확인할 수 있습니다.
