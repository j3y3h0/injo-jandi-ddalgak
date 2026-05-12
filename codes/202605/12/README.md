# Java CSV 데이터 분석 프로젝트

이 프로젝트는 Java를 사용하여 CSV 파일을 읽고, 데이터를 분석하며, 기본적인 통계 정보를 출력하는 소규모 애플리케이션이다. Apache Commons CSV 라이브러리를 활용하여 CSV 파싱을 보다 효율적으로 처리한다.

## 주제: 데이터 분석 및 시각화

산업에서 흔히 접할 수 있는 CSV 형식의 데이터를 처리하고 분석하는 과정을 시연한다. 특히, 숫자 데이터에 대한 평균 계산을 통해 데이터의 경향성을 파악하는 예시를 제공한다. 더 나아가, 분석된 데이터를 효과적으로 시각화하는 방법에 대한 아이디어를 제시한다.

## 프로젝트 구조

프로젝트는 Maven을 사용하여 빌드되며, 다음과 같은 주요 구성 요소로 이루어져 있다.

-   `pom.xml`: Maven 빌드 설정 파일. Apache Commons CSV 의존성을 관리한다.
-   `src/main/java/com/example/data/`: Java 소스 코드가 위치한다.
    -   `model/DataRecord.java`: CSV 파일의 각 행을 표현하는 데이터 모델 클래스이다. (이름, 나이, 점수)
    -   `CsvProcessor.java`: CSV 파일을 읽고 `DataRecord` 객체 리스트로 변환하는 역할을 한다. Apache Commons CSV 라이브러리를 사용한다.
    -   `DataAnalysisService.java`: `DataRecord` 리스트를 기반으로 데이터 분석을 수행한다. 현재는 `점수` 필드의 평균을 계산하는 기능을 제공한다.
    -   `Main.java`: 애플리케이션의 진입점이다. `CsvProcessor`와 `DataAnalysisService`를 사용하여 전체 데이터 처리 및 분석 워크플로우를 조정한다.
-   `data.csv`: 애플리케이션이 읽을 샘플 CSV 데이터 파일이다.

## 실행 방법

이 프로젝트는 Maven을 사용하여 빌드하고 실행할 수 있다.

### 1. 프로젝트 빌드

프로젝트 루트 디렉토리 (즉, `pom.xml` 파일이 있는 디렉토리)에서 다음 명령어를 실행하여 프로젝트를 빌드한다. 이 명령어는 모든 의존성을 포함하는 실행 가능한 JAR 파일을 생성한다.

```bash
mvn clean compile assembly:single
```

빌드가 성공적으로 완료되면 `target/data-analysis-project-1.0-SNAPSHOT-jar-with-dependencies.jar` 파일이 생성된다.

### 2. 애플리케이션 실행

생성된 JAR 파일을 다음 명령어로 실행한다.

```bash
java -jar target/data-analysis-project-1.0-SNAPSHOT-jar-with-dependencies.jar
```

애플리케이션은 `data.csv` 파일을 읽고, 점수의 평균을 계산하여 콘솔에 출력할 것이다.

## 추가 시각화 제안

이 프로젝트에서 분석된 데이터를 바탕으로 다양한 시각화를 시도할 수 있다. 예를 들어:

-   나이대별 점수 분포를 나타내는 막대 그래프
-   점수 구간별 인원수를 보여주는 히스토그램
-   (추가적인 데이터가 있다면) 시간 흐름에 따른 점수 변화를 보여주는 선 그래프

이러한 시각화는 Tableau, Power BI와 같은 전문 도구나, Java 생태계의 JFreeChart, XChart와 같은 라이브러리를 통해 구현할 수 있다. 이 프로젝트는 시각화를 위한 데이터 준비 단계로 활용될 수 있다.
