# CSV 성적 처리기 (CSV Score Processor)

이 프로젝트는 자바(Java) 언어를 사용하여 CSV 파일을 읽고, 학생들의 성적 데이터를 처리하며, 특정 과목의 평균 점수를 계산하는 간단한 예제 애플리케이션이다. Apache Commons CSV 라이브러리를 활용하여 실제 산업 환경에서 사용될 수 있는 CSV 처리 방식을 보여준다.

## 프로젝트 구조

```
.
├── pom.xml
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── csvprocessor
│                       ├── CsvProcessor.java
│                       ├── CsvUtil.java
│                       └── DataModel.java
└── README.md
```

-   `pom.xml`: Maven 빌드 설정 파일이다. 프로젝트의 의존성(Apache Commons CSV)과 빌드 플러그인이 정의되어 있다.
-   `src/main/java/com/example/csvprocessor/DataModel.java`: CSV 파일의 각 행을 나타내는 자바 객체(POJO)이다. 학생의 이름, 과목, 점수 정보를 담고 있다.
-   `src/main/java/com/example/csvprocessor/CsvUtil.java`: CSV 파일 읽기 및 데이터 분석(평균 점수 계산 등)을 위한 유틸리티 메서드들을 제공한다. Apache Commons CSV 라이브러리를 사용하여 CSV 파싱을 처리한다.
-   `src/main/java/com/example/csvprocessor/CsvProcessor.java`: 애플리케이션의 메인 진입점이다. `data.csv`라는 샘플 CSV 파일을 읽어 데이터를 처리하고 결과를 콘솔에 출력한다. 만약 `data.csv` 파일이 없으면 자동으로 샘플 파일을 생성한다.

## 빌드 및 실행 방법

이 프로젝트는 Maven을 사용하여 빌드된다. 자바 개발 환경(JDK 8 이상)과 Maven이 설치되어 있어야 한다.

1.  **프로젝트 클론 및 이동 (이 단계는 이미 완료되었다고 가정)**
    이 프로젝트 코드는 현재 작업 디렉터리에 직접 생성되었다.

2.  **Maven을 사용하여 프로젝트 빌드**
    프로젝트 루트 디렉터리(`pom.xml` 파일이 있는 곳)에서 다음 명령어를 실행한다:

    ```bash
    mvn clean install
    ```
    이 명령어는 프로젝트를 컴파일하고 모든 의존성을 다운로드하며, 실행 가능한 JAR 파일을 `target` 디렉터리에 생성한다.

3.  **애플리케이션 실행**
    빌드가 성공적으로 완료되면, `target` 디렉터리에 `csv-processor-1.0-SNAPSHOT.jar` (또는 유사한 이름) 파일이 생성된다. 다음 명령어를 사용하여 애플리케이션을 실행할 수 있다:

    ```bash
    java -jar target/csv-processor-1.0-SNAPSHOT.jar
    ```
    애플리케이션은 `data.csv` 파일이 존재하지 않으면 이를 자동으로 생성하고, 해당 파일에서 데이터를 읽어 처리한 후 결과를 콘솔에 출력한다.

## 사용된 라이브러리

-   **Apache Commons CSV**: CSV 파일을 읽고 쓰는 데 사용되는 강력한 라이브러리이다. (`org.apache.commons:commons-csv:1.9.0`)
