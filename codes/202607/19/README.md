# CSV 데이터 분석 프로젝트

## 프로젝트 설명
이 프로젝트는 CSV(Comma Separated Values) 파일로부터 데이터를 읽어와 기본적인 통계 분석(합계, 평균)을 수행하는 간단한 자바 애플리케이션입니다. 데이터 처리 및 분석의 기본적인 과정을 보여주기 위해 개발되었습니다. 산업에서 널리 사용되는 Apache Commons CSV 라이브러리를 활용하여 CSV 파싱의 안정성과 효율성을 확보하였습니다.

## 코드 구조
*   `Main.java`: 애플리케이션의 시작점이다. `CsvProcessor`와 `DataAnalyzer`를 사용하여 전체 데이터 처리 흐름을 조정한다.
*   `CsvProcessor.java`: CSV 파일을 읽고 파싱하는 역할을 담당한다. Apache Commons CSV 라이브러리를 이용하여 CSV 레코드를 추출한다.
*   `DataAnalyzer.java`: 파싱된 데이터에 대해 기본적인 통계 분석(예: 특정 열의 합계 및 평균 계산)을 수행한다.
*   `sample.csv`: 분석에 사용될 샘플 CSV 데이터 파일이다.

## 실행 방법

### 전제 조건
*   JDK 8 이상이 설치되어 있어야 한다.
*   Apache Commons CSV 라이브러리 JAR 파일이 필요하다. (예: `commons-csv-1.10.0.jar`)
    *   다음에서 다운로드할 수 있다: [https://commons.apache.org/proper/commons-csv/download_csv.cgi](https://commons.apache.org/proper/commons-csv/download_csv.cgi)
    *   다운로드한 JAR 파일을 프로젝트 루트 디렉터리에 두거나, `CLASSPATH`에 추가해야 한다.

### 컴파일 및 실행

1.  **JAR 파일 준비**: `commons-csv-1.10.0.jar` 파일을 현재 프로젝트 디렉터리(`C:\server\gpt-code-diary\codes\202607\19`)에 복사한다.
2.  **소스 코드 컴파일**: 터미널을 열고 프로젝트 디렉터리로 이동한 후 다음 명령어를 실행한다.
    ```bash
    javac -cp commons-csv-1.10.0.jar:. Main.java CsvProcessor.java DataAnalyzer.java
    ```
    *   `-cp` (또는 `-classpath`) 옵션은 컴파일러가 클래스 파일을 찾을 경로를 지정한다. 여기서는 `commons-csv-1.10.0.jar`와 현재 디렉터리(`.`)를 포함한다.
3.  **애플리케이션 실행**: 컴파일이 완료되면 다음 명령어로 애플리케이션을 실행한다.
    ```bash
    java -cp commons-csv-1.10.0.jar:. Main
    ```
    *   실행 시에도 `CLASSPATH`에 `commons-csv-1.10.0.jar`를 포함시켜야 한다.

## 샘플 데이터 (`sample.csv`) 형식
샘플 CSV 파일은 다음과 같은 헤더를 가질 수 있다:
`Name,Value`
`ItemA,100`
`ItemB,150`
`ItemC,200`

프로그램은 'Value'라는 헤더를 가진 열의 데이터를 분석할 것이다.
