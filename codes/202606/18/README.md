# Simple Text Analyzer (간단한 텍스트 분석기)

이 프로젝트는 주어진 텍스트 파일의 내용을 분석하여 단어 빈도를 계산하고, 가장 자주 나타나는 단어들을 보고서 형태로 출력하는 간단한 Java 애플리케이션이다. 산업에서 텍스트 데이터 분석의 기초적인 단계에 활용될 수 있는 실용적인 예시를 제공한다.

## 프로젝트 개요

'간단한 텍스트 분석기'는 다음의 핵심 기능을 수행한다:
1.  텍스트 파일 읽기: 지정된 경로의 텍스트 파일 내용을 읽어온다.
2.  단어 빈도 계산: 읽어온 텍스트에서 단어들을 추출하고, 각 단어의 출현 횟수를 계산한다. 이 과정에서 단어는 소문자로 처리되며, 불필요한 구두점은 제거된다.
3.  분석 보고서 생성: 계산된 단어 빈도를 바탕으로 가장 빈번하게 나타나는 상위 N개의 단어를 포함하는 보고서를 생성한다.

## 프로젝트 구조

```
.
├── pom.xml
├── README.md
├── input.txt
└── src
    └── main
        └── java
            ├── AnalysisReportGenerator.java
            ├── FileProcessor.java
            ├── TextAnalyzer.java
            └── WordCounter.java
```

*   `pom.xml`: Maven 빌드 도구 설정을 담고 있는 파일이다. 프로젝트의 빌드, 의존성 관리 및 실행 가능한 JAR 파일 생성에 사용된다.
*   `README.md`: 이 프로젝트에 대한 설명, 사용 방법 등을 담고 있는 문서이다.
*   `input.txt`: 텍스트 분석을 위한 예시 입력 파일이다. 사용자는 이 파일을 수정하여 다양한 텍스트를 분석할 수 있다.
*   `src/main/java/`: Java 소스 코드가 위치하는 디렉토리이다.
    *   `FileProcessor.java`: 파일 I/O를 담당하는 클래스이다. 파일 경로를 받아 그 내용을 문자열로 읽어오는 기능을 제공한다.
    *   `WordCounter.java`: 텍스트에서 단어 빈도를 계산하는 클래스이다. 텍스트를 정제하고 단어를 추출하여 빈도를 맵 형태로 반환한다.
    *   `AnalysisReportGenerator.java`: 단어 빈도 분석 결과를 바탕으로 보기 좋은 형태의 보고서를 생성하는 클래스이다. 상위 N개의 단어를 정렬하여 출력한다.
    *   `TextAnalyzer.java`: 이 애플리케이션의 메인 진입점이다. `FileProcessor`, `WordCounter`, `AnalysisReportGenerator` 클래스들을 조율하여 전체 텍스트 분석 과정을 실행한다.

## 실행 방법

이 프로젝트는 Maven을 사용하여 빌드되고 실행될 수 있다.

### 1. 프로젝트 빌드

프로젝트 루트 디렉토리(pom.xml 파일이 있는 곳)에서 다음 명령어를 실행하여 프로젝트를 빌드한다.

```bash
mvn clean install
```

이 명령어는 프로젝트를 컴파일하고, 의존성을 다운로드하며, 실행 가능한 JAR 파일을 `target` 디렉토리 안에 생성한다.

### 2. 애플리케이션 실행

빌드가 성공적으로 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행할 수 있다.

```bash
java -jar target/simple-text-analyzer-1.0-SNAPSHOT.jar [분석할_파일_경로]
```

*   `[분석할_파일_경로]`는 선택 사항이다. 이 인자를 제공하지 않으면, 기본적으로 현재 디렉토리의 `input.txt` 파일을 분석한다.
*   예시:
    *   기본 `input.txt` 파일 분석:
        ```bash
        java -jar target/simple-text-analyzer-1.0-SNAPSHOT.jar
        ```
    *   특정 파일(`my_document.txt`) 분석:
        ```bash
        java -jar target/simple-text-analyzer-1.0-SNAPSHOT.jar my_document.txt
        ```

### 3. 출력 결과 확인

애플리케이션이 실행되면, 콘솔에 텍스트 분석 과정과 최종 보고서가 출력된다. 보고서에는 가장 자주 등장하는 단어들의 목록과 그 빈도, 총 단어 수, 그리고 고유 단어 수가 포함된다.
