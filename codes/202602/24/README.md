# 엑셀 데이터 처리 및 분석 프로젝트

## 개요
Apache POI 라이브러리를 활용하여 엑셀 파일을 읽고, 데이터를 분석한 후, 결과를 엑셀 파일 또는 텍스트 보고서로 생성하는 소규모 Java 프로젝트이다. 산업 현장에서 엑셀 데이터 처리 자동화에 유용하게 사용될 수 있다.

## 파일 구조
- `Main.java`: 애플리케이션의 진입점이다. `ExcelProcessor`와 `DataAnalyzer`, `ReportGenerator`를 사용하여 전체 흐름을 제어한다.
- `ExcelProcessor.java`: Apache POI를 이용하여 엑셀 파일에서 데이터를 읽고 쓰는 기능을 제공한다. 특정 시트에서 특정 범위의 셀 데이터를 읽어와 리스트 형태로 반환하거나, 처리된 데이터를 새로운 엑셀 파일로 저장하는 기능을 포함한다.
- `DataAnalyzer.java`: `ExcelProcessor`로부터 받은 데이터를 분석하는 로직을 담고 있다. 예를 들어, 특정 열의 합계, 평균, 최대값, 최소값 등을 계산하는 기능을 구현한다.
- `ReportGenerator.java`: 분석된 데이터를 기반으로 사용자 친화적인 보고서(예: 콘솔 출력 또는 텍스트 파일)를 생성한다.

## 실행 방법
1.  **Apache POI 라이브러리 추가:** 이 프로젝트는 Apache POI 라이브러리에 의존한다. Maven 또는 Gradle과 같은 빌드 도구를 사용하거나, 수동으로 `poi-x.x.x.jar`, `poi-ooxml-x.x.x.jar` 및 관련 의존성 JAR 파일들을 클래스패스에 추가해야 한다. 예시로, `poi-5.2.5.jar`, `poi-ooxml-5.2.5.jar`, `commons-compress-1.25.0.jar` 등이 필요하다.
2.  **컴파일:** Java 소스 파일들을 컴파일한다.
    `javac -cp ".;path/to/poi-x.x.x.jar;path/to/poi-ooxml-x.x.x.jar;..." *.java`
    (Windows의 경우 `;`, Linux/macOS의 경우 `:`로 클래스패스 구분)
3.  **실행:** 컴파일된 클래스 파일들을 실행한다.
    `java -cp ".;path/to/poi-x.x.x.jar;path/to/poi-ooxml-x.x.x.jar;..." Main`
4.  **입력 파일:** 프로그램 실행을 위해 `input.xlsx` 파일을 프로젝트 루트 디렉터리에 준비해야 한다. 이 파일은 최소한 하나의 시트에 숫자 데이터가 포함된 열을 가지고 있어야 한다.
