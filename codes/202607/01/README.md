# CSV 데이터 분석기

## 프로젝트 개요
이 프로젝트는 CSV 파일을 읽어 기본적인 데이터 분석(평균, 합계 계산)을 수행하고, 그 결과를 새로운 CSV 파일로 저장하는 자바 애플리케이션입니다. 빅데이터 처리 및 인공지능 관련 뉴스에서 자주 언급되는 데이터 분석의 기초를 이해하는 데 도움이 되도록 고안되었습니다. 산업 표준 라이브러리인 Apache Commons CSV와 Apache Commons Math를 활용하여 견고하고 실용적인 코드 예시를 제공합니다.

## 코드 구조
*   `pom.xml`: Maven 프로젝트 설정 파일입니다. 필요한 라이브러리 의존성을 정의합니다.
*   `src/main/java/Main.java`: 애플리케이션의 시작점입니다. `DataAnalyzer` 클래스를 사용하여 데이터 분석 흐름을 제어합니다.
*   `src/main/java/DataAnalyzer.java`: CSV 파일을 읽고, `DataProcessor`를 통해 데이터를 처리한 후, 결과를 CSV 파일로 작성하는 핵심 로직을 포함합니다.
*   `src/main/java/DataProcessor.java`: 실제 데이터 분석 로직(예: 평균 계산, 합계 계산)을 구현한 유틸리티 클래스입니다.

## 실행 방법
이 프로젝트는 Maven을 사용하여 빌드 및 실행됩니다.

### 전제 조건
*   JDK 8 이상
*   Apache Maven

### 빌드 및 실행 단계
1.  **프로젝트 클론 (또는 파일 생성)**: 이 프로젝트 파일을 로컬 디렉터리에 복사합니다.
2.  **의존성 설치**: 프로젝트 루트 디렉터리에서 다음 명령어를 실행하여 필요한 라이브러리를 다운로드합니다.
    ```bash
    mvn clean install
    ```
3.  **애플리케이션 실행**: 다음 명령어를 사용하여 애플리케이션을 실행합니다.
    ```bash
    mvn exec:java -Dexec.mainClass="Main"
    ```
    또는 먼저 JAR 파일을 빌드한 후 실행할 수 있습니다.
    ```bash
    mvn package
    java -jar target/csv-data-analyzer-1.0-SNAPSHOT.jar
    ```
    (참고: `target/csv-data-analyzer-1.0-SNAPSHOT.jar` 파일명은 빌드 시점에 따라 달라질 수 있습니다. `pom.xml`의 `<artifactId>`와 `<version>`을 확인하십시오.)

### 입력 파일 예시
프로젝트 루트 디렉터리에 `input.csv` 파일을 다음과 같은 형식으로 생성하여 사용해볼 수 있습니다.
```csv
Header1,Value1,Value2
Row1,10,20
Row2,15,25
Row3,12,22
```

### 출력 파일
분석 결과는 `output.csv` 파일로 생성됩니다.

## 주요 사용 라이브러리
*   **Apache Commons CSV**: CSV 파일 파싱 및 작성을 위한 강력한 라이브러리입니다.
*   **Apache Commons Math**: 통계 및 수학적 연산을 위한 다양한 기능을 제공합니다.
