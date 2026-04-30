# 데이터 처리 애플리케이션

이 프로젝트는 간단한 Java 콘솔 애플리케이션으로, CSV 파일에서 데이터를 읽어 특정 컬럼의 값을 집계하는 기능을 수행합니다. "데이터 분석 자동화 도구 개발"이라는 가상의 뉴스 주제에 기반하여, 산업에서 흔히 사용되는 데이터 처리 방식을 모방하여 개발되었습니다.

## 사용 기술

*   **Java 8+**: 애플리케이션 개발 언어
*   **Maven**: 프로젝트 빌드 및 의존성 관리 도구
*   **Apache Commons CSV**: CSV 파일 파싱 및 처리를 위한 라이브러리

## 프로젝트 구조

```
.
├── pom.xml                     # Maven 프로젝트 설정 파일
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── dataprocessor
│                       ├── CsvUtil.java    # CSV 파일 읽기 유틸리티
│                       └── DataProcessor.java # 메인 애플리케이션 로직
└── data.csv                    # 예제 입력 CSV 파일
```

*   `pom.xml`: 프로젝트의 의존성(Apache Commons CSV)과 빌드 설정을 정의합니다.
*   `src/main/java/com/example/dataprocessor/CsvUtil.java`: CSV 파일을 읽고 `CSVRecord` 객체 리스트로 반환하는 정적 메서드를 제공합니다. Apache Commons CSV 라이브러리를 활용합니다.
*   `src/main/java/com/example/dataprocessor/DataProcessor.java`: 애플리케이션의 진입점(main 메서드)을 포함합니다. `CsvUtil`을 사용하여 `data.csv` 파일을 읽고, "Amount" 컬럼의 총합을 계산하여 출력합니다.
*   `data.csv`: 애플리케이션이 처리할 샘플 CSV 데이터 파일입니다.

## 실행 방법

### 1. 전제 조건

*   Java Development Kit (JDK) 8 이상이 설치되어 있어야 합니다.
*   Maven이 설치되어 있어야 합니다.

### 2. 프로젝트 빌드

프로젝트 루트 디렉터리(이 `README.md` 파일이 있는 곳)에서 다음 명령어를 실행하여 프로젝트를 빌드합니다.

```bash
mvn clean install
```

이 명령어는 프로젝트를 컴파일하고, 필요한 의존성을 다운로드하며, 실행 가능한 JAR 파일을 `target/` 디렉터리에 생성합니다. 모든 의존성이 포함된 단일 JAR 파일인 `data-processor-1.0-SNAPSHOT-jar-with-dependencies.jar`가 생성될 것입니다.

### 3. 애플리케이션 실행

빌드가 성공적으로 완료되면, `target/` 디렉터리에 생성된 JAR 파일을 사용하여 애플리케이션을 실행할 수 있습니다.

```bash
java -jar target/data-processor-1.0-SNAPSHOT-jar-with-dependencies.jar
```

애플리케이션은 `data.csv` 파일을 읽어 "Amount" 컬럼의 합계를 계산하여 콘솔에 출력합니다. `DataProcessor.java` 파일 내에서 `csvFilePath` 및 `targetColumnHeader` 변수를 수정하여 다른 CSV 파일이나 다른 컬럼을 처리하도록 변경할 수 있습니다.
