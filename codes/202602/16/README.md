# CSV 데이터 처리 및 분석 애플리케이션 프로젝트 개요

이 프로젝트는 Apache Commons CSV 라이브러리를 활용하여 CSV 파일을 읽고, 데이터를 분석하며, 분석 결과를 보고서로 생성하는 소규모 Java 애플리케이션이다. 산업에서 흔히 사용되는 데이터 처리 시나리오를 시뮬레이션하고, 외부 라이브러리 연동 방법을 보여주기 위해 개발되었다.

## 1. 프로젝트 구조

프로젝트는 Maven을 기반으로 하며, 다음과 같은 주요 파일들로 구성되어 있다.

```
.
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── gemini
                    └── cli
                        └── project
                            ├── CsvProcessor.java
                            ├── DataAnalyzer.java
                            ├── Main.java
                            └── ReportGenerator.java
```

*   **`pom.xml`**: Maven 프로젝트 설정 파일이다. 프로젝트의 의존성(Apache Commons CSV) 관리 및 빌드 설정을 포함한다.
*   **`src/main/java/com/gemini/cli/project/Main.java`**: 애플리케이션의 진입점이다. `CsvProcessor`, `DataAnalyzer`, `ReportGenerator` 클래스를 조율하여 CSV 파일 읽기, 데이터 분석, 보고서 생성을 수행한다.
*   **`src/main/java/com/gemini/cli/project/CsvProcessor.java`**: CSV 파일의 읽기 및 쓰기 로직을 담당한다. Apache Commons CSV 라이브러리를 사용하여 CSV 데이터를 `List<Map<String, String>>` 형태로 변환하거나 그 반대로 처리한다.
*   **`src/main/java/com/gemini/cli/project/DataAnalyzer.java`**: CSV 데이터에 대한 기본적인 통계 분석을 수행한다. 예를 들어, 특정 숫자 컬럼의 평균값을 계산하거나, 카테고리 컬럼별 데이터 개수를 집계하는 기능을 제공한다.
*   **`src/main/java/com/gemini/cli/project/ReportGenerator.java`**: 분석된 데이터를 바탕으로 보고서 내용을 문자열 형태로 생성하고, 이를 텍스트 파일로 저장하는 기능을 수행한다.

## 2. 실행 방법

이 프로젝트를 실행하기 위해서는 Maven이 설치되어 있어야 한다.

1.  **입력 CSV 파일 준비**:
    프로젝트의 루트 디렉터리(예: `C:\server\gpt-code-diary\codes\202602\16`)에 `input.csv` 파일을 생성해야 한다. 이 파일은 최소한 `amount`와 `category`라는 헤더를 포함해야 한다.

    예시 `input.csv` 내용:
    ```csv
    id,category,amount
    1,식료품,15000
    2,교통,5000
    3,식료품,20000
    4,오락,10000
    5,교통,7500
    6,식료품,12000
    ```

2.  **프로젝트 빌드**:
    프로젝트 루트 디렉터리에서 다음 Maven 명령어를 실행하여 프로젝트를 빌드한다. 이 명령어는 모든 의존성을 다운로드하고 실행 가능한 JAR 파일을 생성한다.

    ```bash
    mvn clean install
    ```

3.  **애플리케이션 실행**:
    빌드가 성공적으로 완료되면, `target` 디렉터리 안에 `csv-data-processor-1.0-SNAPSHOT-jar-with-dependencies.jar` 파일이 생성된다. 다음 명령어를 사용하여 애플리케이션을 실행한다.

    ```bash
    java -jar target/csv-data-processor-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

4.  **결과 확인**:
    애플리케이션이 실행되면 콘솔에 데이터 처리 과정과 분석 결과가 출력된다. 또한, 프로젝트 루트 디렉터리에 `output_summary.txt` 파일이 생성되며, 이 파일에는 최종 보고서 내용이 저장되어 있다.

## 3. 사용된 라이브러리

*   **Apache Commons CSV**: CSV 파일 파싱 및 생성을 위한 강력한 라이브러리이다. `pom.xml`에 의존성이 명시되어 있으며, Maven을 통해 자동으로 다운로드된다.

이 프로젝트는 간단한 데이터 처리 흐름을 보여주며, 실제 산업 환경에서 더 복잡한 데이터 분석 파이프라인을 구축하기 위한 기초가 될 수 있다.
