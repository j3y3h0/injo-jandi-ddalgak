# 간단한 감성 분석기 (Simple Sentiment Analyzer)

이 프로젝트는 주어진 텍스트의 감성(긍정적, 부정적, 중립적)을 분석하는 간단한 자바 애플리케이션입니다.
주요 뉴스로 '감성 분석' 주제를 선택하여, 산업에서 널리 활용되는 자연어 처리의 기초 개념을 실습할 수 있도록 구현되었습니다.

## 기능

- 텍스트 입력 시 긍정/부정 키워드 매칭을 통한 감성 분석을 제공합니다.
- 분석 결과는 '긍정적', '부정적', '중립적'으로 분류됩니다.

## 프로젝트 구조

```
.
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── sentiment/
                        ├── Main.java
                        ├── SentimentAnalyzer.java
                        └── SentimentResult.java
```

- `pom.xml`: Maven 빌드 설정 파일입니다. 프로젝트 의존성 관리 및 빌드 방법을 정의합니다.
- `README.md`: 이 프로젝트에 대한 설명서입니다.
- `src/main/java/com/example/sentiment/`: 자바 소스 코드 파일들이 위치하는 디렉터리입니다.
    - `Main.java`: 애플리케이션의 메인 진입점입니다. 사용자 입력을 받아 감성을 분석하고 결과를 출력합니다.
    - `SentimentAnalyzer.java`: 감성 분석의 핵심 로직을 포함하는 클래스입니다. 텍스트 내에서 미리 정의된 긍정/부정 키워드를 찾아 감성을 판단합니다.
    - `SentimentResult.java`: 감성 분석 결과를 나타내는 열거형(Enum)입니다. 긍정, 부정, 중립의 세 가지 상태를 정의합니다.

## 실행 방법

이 프로젝트는 Maven을 사용하여 빌드 및 실행할 수 있습니다.

1.  **프로젝트 클론 또는 다운로드:**
    현재 작업 디렉터리에 파일들이 이미 생성되어 있으므로 이 단계는 생략합니다.

2.  **프로젝트 디렉터리로 이동:**
    명령 프롬프트 또는 터미널에서 프로젝트의 루트 디렉터리로 이동합니다.
    ```bash
    cd C:\server\gpt-code-diary\codes\202606\14
    ```

3.  **프로젝트 빌드:**
    Maven을 사용하여 프로젝트를 빌드하고 실행 가능한 JAR 파일을 생성합니다.
    ```bash
    mvn clean install
    ```
    이 명령은 `target` 디렉터리 내에 `simple-sentiment-analyzer-1.0-SNAPSHOT.jar` 파일을 생성합니다.

4.  **애플리케이션 실행:**
    생성된 JAR 파일을 실행합니다.
    ```bash
    java -jar target/simple-sentiment-analyzer-1.0-SNAPSHOT.jar
    ```

5.  **사용:**
    애플리케이션이 실행되면 텍스트를 입력하라는 프롬프트가 나타납니다. 분석할 문장을 입력하고 Enter 키를 누르면 감성 분석 결과가 출력됩니다. `exit`를 입력하여 프로그램을 종료할 수 있습니다.

## 사용된 기술

-   **Java 11**: 프로그래밍 언어
-   **Maven**: 프로젝트 빌드 및 종속성 관리 도구