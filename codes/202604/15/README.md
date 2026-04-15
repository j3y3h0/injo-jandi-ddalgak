# 감성 분석기 프로젝트

이 프로젝트는 간단한 키워드 기반 감성 분석기를 구현한 소규모 Java 애플리케이션입니다. 실제 산업에서 사용되는 복잡한 자연어 처리(NLP) 알고리즘의 개념을 이해하고, 이를 Java 코드로 구현하는 방법을 보여주기 위해 제작되었습니다.

## 프로젝트 목표
사용자가 입력한 문장의 감성(긍정, 부정, 중립)을 분석하여 반환하는 기능을 제공합니다. 이는 고객 리뷰 분석, 소셜 미디어 모니터링 등 다양한 분야에서 활용될 수 있는 기본적인 기술입니다.

## 파일 구조

```
.
├── com
│   └── gemini
│       └── sentiment
│           ├── Main.java
│           ├── Sentiment.java
│           ├── SentimentAnalyzer.java
│           └── Sentence.java
└── README.md
```

*   `README.md`: 프로젝트에 대한 설명, 사용 방법 등이 담긴 문서입니다.
*   `com/gemini/sentiment/Sentiment.java`: 감성을 나타내는 `POSITIVE`, `NEGATIVE`, `NEUTRAL` 열거형(enum)을 정의합니다.
*   `com/gemini/sentiment/Sentence.java`: 분석 대상 문장과 분석된 감성 결과를 캡슐화하는 데이터 클래스입니다.
*   `com/gemini/sentiment/SentimentAnalyzer.java`: 감성 분석의 핵심 로직을 포함합니다. 미리 정의된 긍정/부정 키워드 목록을 사용하여 문장의 감성을 판단합니다. 실제 시스템에서는 더 정교한 감성 사전 또는 머신러닝 모델이 사용됩니다.
*   `com/gemini/sentiment/Main.java`: 애플리케이션의 진입점으로, `SentimentAnalyzer`를 사용하여 여러 예제 문장을 분석하고 결과를 콘솔에 출력합니다.

## 코드 설명 (주요 클래스)

### `SentimentAnalyzer.java`
이 클래스는 키워드 매칭 방식을 사용하여 문장의 감성을 분석합니다.
`POSITIVE_WORDS`와 `NEGATIVE_WORDS`라는 두 개의 `Set`을 가지고 있으며, 이 집합에 포함된 단어의 출현 빈도를 기반으로 감성을 결정합니다. 이 방식은 실제 NLP 분야에서 렉시콘 기반(lexicon-based) 감성 분석의 매우 단순화된 형태라고 볼 수 있습니다.

**핵심 메서드:**
*   `public Sentence analyze(Sentence sentence)`: `Sentence` 객체를 받아 문장 내의 단어들을 분석하고, 긍정/부정 단어의 개수를 세어 최종 감성을 결정하여 `Sentence` 객체에 설정 후 반환합니다.

## 빌드 및 실행 방법

이 프로젝트는 별도의 빌드 도구(Maven, Gradle) 없이 표준 Java 컴파일러와 런타임 환경에서 실행 가능하도록 구성되었습니다.

1.  **터미널 열기**: 현재 프로젝트 디렉터리(`C:\server\gpt-code-diary\codes\202604\15`)로 이동합니다.

2.  **컴파일**: 다음 명령어를 사용하여 모든 Java 소스 파일을 컴파일합니다.
    ```bash
    javac com/gemini/sentiment/*.java
    ```

3.  **실행**: 컴파일이 완료되면 다음 명령어를 사용하여 `Main` 클래스를 실행합니다.
    ```bash
    java com.gemini.sentiment.Main
    ```

위 명령어를 실행하면 `Main` 클래스에 정의된 예제 문장들이 감성 분석기를 통해 분석되고, 그 결과가 콘솔에 출력될 것입니다.

## 추가 개선 사항 (향후 고려사항)
*   **고급 NLP 라이브러리 연동**: Apache OpenNLP, Stanford CoreNLP, Komoran(한국어 형태소 분석기) 등과 같은 전문 NLP 라이브러리를 통합하여 형태소 분석, 구문 분석 기반의 더 정확한 감성 분석을 수행할 수 있습니다.
*   **머신러닝 모델 적용**: 텍스트 분류를 위한 머신러닝 모델(예: 나이브 베이즈, SVM, BERT 등 딥러닝 모델)을 학습시켜 감성 분석의 정확도를 높일 수 있습니다.
*   **대규모 감성 사전 구축**: 특정 도메인에 특화된 긍정/부정 단어 사전을 구축하거나, 기존 사전을 확장하여 분석 성능을 개선할 수 있습니다.
*   **API 엔드포인트 제공**: Spring Boot 같은 프레임워크를 사용하여 감성 분석 기능을 RESTful API로 노출함으로써 다른 애플리케이션에서 쉽게 활용할 수 있도록 할 수 있습니다.
