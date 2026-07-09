# CSV 데이터 처리 및 분석 애플리케이션 (Java)

## 개요

이 프로젝트는 Java와 Apache Commons CSV 라이브러리를 활용하여 CSV 파일을 읽고, 데이터를 분석한 후, 그 결과를 다시 CSV 파일로 저장하는 소규모 애플리케이션입니다. 산업 환경에서 흔히 접하는 데이터 처리 작업을 시뮬레이션하기 위해 고안되었습니다.

## 주요 기능

*   **CSV 파일 읽기**: `input.csv` 파일에서 데이터를 읽어 Java 객체로 변환합니다.
*   **데이터 분석**: 카테고리별 평균 값을 계산합니다.
*   **데이터 필터링**: 특정 조건(예: 값 50.0 이상)에 맞는 레코드만 필터링합니다.
*   **CSV 파일 쓰기**: 필터링된 데이터를 `filtered_output.csv` 파일로 저장합니다.
*   **샘플 데이터 생성**: 실행 시 자동으로 `input.csv` 파일을 생성하여 바로 테스트할 수 있습니다.

## 코드 구조

*   `Main.java`: 애플리케이션의 진입점입니다. 샘플 CSV 파일을 생성하고, `CsvProcessor`와 `DataAnalyzer`를 사용하여 전체 데이터 처리 흐름을 조정합니다.
*   `Record.java`: CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다. 이름, 카테고리, 값을 저장합니다.
*   `CsvProcessor.java`: Apache Commons CSV 라이브러리를 사용하여 실제 CSV 파일 읽기 및 쓰기 작업을 수행합니다.
*   `DataAnalyzer.java`: 데이터를 분석하는 비즈니스 로직을 포함합니다. 여기서는 카테고리별 평균 값 계산 및 값 필터링 기능을 제공합니다.

## 사용 라이브러리

이 프로젝트는 Apache Commons CSV 라이브러리를 사용합니다.
*   **groupId**: `org.apache.commons`
*   **artifactId**: `commons-csv`
*   **version**: `1.10.0` (또는 최신 안정 버전)

## 실행 방법

1.  **Apache Commons CSV 라이브러리 다운로드**:
    Apache Commons CSV 라이브러리의 JAR 파일을 다운로드해야 합니다. [Maven Central](https://mvnrepository.com/artifact/org.apache.commons/commons-csv/1.10.0) 등에서 `commons-csv-1.10.0.jar` 파일을 다운로드할 수 있습니다. 다운로드한 JAR 파일은 이 프로젝트 폴더 내에 `lib` 디렉터리를 만들고 그 안에 저장하는 것을 권장합니다.

2.  **컴파일**:
    프로젝트의 모든 `.java` 파일을 컴파일합니다. `commons-csv` JAR 파일이 클래스패스에 포함되어야 합니다.
    ```bash
    # (선택 사항) lib 디렉터리 생성 및 JAR 파일 복사
    # mkdir lib
    # mv commons-csv-1.10.0.jar lib/

    # 컴파일 명령 (현재 디렉터리에서 실행)
    javac -cp ".;lib/*" com/example/csvprocessor/*.java
    ```
    *   `.;lib/*` 부분은 Windows 환경에서 현재 디렉터리와 `lib` 디렉터리 내의 모든 JAR 파일을 클래스패스에 추가하도록 지시합니다. Linux/macOS에서는 `.:lib/*` 대신 `./lib/*` 또는 `lib/*`를 사용합니다.

3.  **실행**:
    컴파일된 클래스 파일들을 실행합니다. `Main` 클래스가 애플리케이션의 시작점입니다.
    ```bash
    java -cp ".;lib/*" com.example.csvprocessor.Main
    ```

## 예상 출력 (콘솔)

프로그램 실행 시, `input.csv` 파일이 자동으로 생성되고, 읽기, 분석, 필터링, 쓰기 과정에 대한 메시지가 콘솔에 출력됩니다. 최종적으로는 `filtered_output.csv` 파일이 생성됩니다.

## input.csv (자동 생성 예시)

```csv
Name,Category,Value
ItemA,Electronics,100.5
ItemB,Books,25.0
ItemC,Electronics,75.2
ItemD,Home,120.0
ItemE,Books,40.0
ItemF,Electronics,150.75
```

## filtered_output.csv (생성 예시)

```csv
Name,Category,Value
ItemA,Electronics,100.5
ItemC,Electronics,75.2
ItemD,Home,120.0
ItemF,Electronics,150.75
```