# 뉴스 기사 감성 분석 프로젝트

이 프로젝트는 간단한 규칙 기반 감성 분석을 통해 뉴스 기사의 텍스트에서 긍정적, 부정적, 또는 중립적 감성을 판별하는 Java 애플리케이션이다. 소규모 예제로, 텍스트 처리 및 기본 감성 분석의 원리를 이해하는 데 도움이 되도록 제작되었다.

## 프로젝트 구조

```
.
├── src
│   └── com
│       └── example
│           └── sentiment
│               ├── NewsArticle.java
│               ├── KoreanSentimentDictionary.java
│               ├── SentimentAnalyzer.java
│               └── Main.java
└── README.md
```

*   `src/com/example/sentiment/NewsArticle.java`: 뉴스 기사의 제목과 내용을 저장하는 데이터 모델 클래스이다.
*   `src/com/example/sentiment/KoreanSentimentDictionary.java`: 한국어 긍정 및 부정 단어 목록을 포함하는 정적 사전 클래스이다. 실제 사용 시에는 더욱 방대하고 정교한 사전이 필요하다.
*   `src/com/example/sentiment/SentimentAnalyzer.java`: `KoreanSentimentDictionary`를 활용하여 뉴스 기사 내용의 감성을 분석하는 핵심 로직을 담고 있는 클래스이다. 내용 내 긍정/부정 단어의 출현 빈도를 기반으로 감성을 결정한다.
*   `src/com/example/sentiment/Main.java`: 프로젝트의 진입점(Entry Point)이다. 샘플 뉴스 기사들을 생성하고, `SentimentAnalyzer`를 사용하여 각 기사의 감성을 분석한 후 결과를 콘솔에 출력한다.
*   `README.md`: 이 프로젝트에 대한 설명과 실행 방법을 제공하는 문서이다.

## 실행 방법

이 프로젝트는 별도의 외부 라이브러리 의존성 없이 순수 Java로 작성되었으므로, JDK (Java Development Kit)만 설치되어 있다면 쉽게 컴파일하고 실행할 수 있다.

1.  **소스 파일 준비**:
    `src/com/example/sentiment/` 경로를 포함하여 위에서 언급된 모든 `.java` 파일을 현재 작업 디렉터리에 생성한다.

    ```
    C:\server\gpt-code-diary\codes\202602\18
    ├── src
    │   └── com
    │       └── example
    │           └── sentiment
    │               ├── NewsArticle.java
    │               ├── KoreanSentimentDictionary.java
    │               ├── SentimentAnalyzer.java
    │               └── Main.java
    └── README.md
    ```

2.  **컴파일**:
    명령 프롬프트(또는 터미널)를 열고 현재 작업 디렉터리(`C:\server\gpt-code-diary\codes\202602\18`)로 이동한다. 다음 명령어를 사용하여 Java 소스 파일들을 컴파일한다.

    ```bash
    javac -d . src/com/example/sentiment/*.java
    ```

    이 명령어는 `src` 디렉터리 아래의 모든 Java 파일을 컴파일하고, 생성된 `.class` 파일들을 현재 디렉터리(`C:\server\gpt-code-diary\codes\202602\18`) 아래의 `com/example/sentiment/` 구조로 배치한다.

3.  **실행**:
    컴파일이 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행한다.

    ```bash
    java com.example.sentiment.Main
    ```

    콘솔에 각 샘플 뉴스 기사에 대한 감성 분석 결과가 출력될 것이다.

## 추가 정보

*   이 프로젝트는 교육 및 예시 목적을 위해 매우 단순화된 감성 분석 모델을 사용한다. 실제 상용 감성 분석 시스템은 훨씬 더 복잡한 자연어 처리(NLP) 기술, 머신러닝 모델, 그리고 방대한 어휘 및 문맥 분석 능력을 필요로 한다.
*   감성 사전은 극히 일부 단어만 포함하고 있다. 실제 분석의 정확도를 높이려면 더 크고 정교한 감성 사전 구축이 필수적이다.
*   패키지 구조는 표준 Java 관례를 따랐다.