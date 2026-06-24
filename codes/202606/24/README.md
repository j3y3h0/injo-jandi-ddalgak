# 텍스트 감성 분석 프로젝트 (Java)

이 프로젝트는 주어진 한국어 텍스트의 감성(긍정, 부정, 중립)을 분석하는 간단한 Java 애플리케이션이다. 산업에서 사용되는 고급 자연어 처리(NLP) 라이브러리 대신, 교육 목적으로 간단한 키워드 기반 감성 사전을 활용하여 기본적인 감성 분석 원리를 보여준다.

## 프로젝트 구조

프로젝트는 다음 파일들로 구성되어 있다.

*   `src/com/example/sentiment/Main.java`: 애플리케이션의 진입점이다. SentimentAnalyzer를 사용하여 여러 샘플 텍스트의 감성을 분석하고 결과를 출력한다.
*   `src/com/example/sentiment/SentimentAnalyzer.java`: 텍스트 감성 분석의 핵심 로직을 포함하는 클래스이다. 내부적으로 긍정 및 부정 단어 사전을 가지고 있으며, 입력 텍스트 내의 단어를 기반으로 감성 점수를 계산한다.
*   `src/com/example/sentiment/SentimentResult.java`: 감성 분석 결과를 나타내는 데이터 클래스이다. 감성 레이블(예: "긍정")과 점수를 저장한다.

## 실행 방법

이 프로젝트는 표준 Java 개발 키트(JDK)만 있다면 별도의 외부 라이브러리 설치 없이 컴파일하고 실행할 수 있다.

1.  **소스 코드 디렉터리 생성:**
    현재 작업 디렉터리에 `src/com/example/sentiment` 경로를 생성한다.

    ```bash
    mkdir -p src/com/example/sentiment
    ```

2.  **Java 파일 저장:**
    위에서 설명된 `Main.java`, `SentimentAnalyzer.java`, `SentimentResult.java` 파일들을 각각 `src/com/example/sentiment/` 경로에 저장한다.

3.  **컴파일:**
    프로젝트의 루트 디렉터리(이 `README.md` 파일이 있는 곳)에서 다음 명령어를 실행하여 Java 소스 파일들을 컴파일한다.

    ```bash
    javac -d . src/com/example/sentiment/*.java
    ```

    이 명령어는 `src` 디렉터리 아래의 모든 Java 파일을 컴파일하고, 생성된 `.class` 파일들을 현재 디렉터리(즉, `.`)를 기준으로 패키지 구조에 맞게 (`com/example/sentiment/` 디렉터리 안에) 저장한다.

4.  **실행:**
    컴파일이 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행할 수 있다.

    ```bash
    java com.example.sentiment.Main
    ```

    이 명령어를 실행하면 `Main` 클래스의 `main` 메서드가 호출되어 감성 분석 결과가 콘솔에 출력될 것이다.

## 주요 알고리즘 및 라이브러리

*   **키워드 기반 감성 분석:** `SentimentAnalyzer` 클래스는 미리 정의된 긍정/부정 키워드 사전을 사용하여 텍스트 내 단어들의 감성을 판별한다. 이는 가장 기본적인 형태의 감성 분석 기법 중 하나이다.
*   **표준 Java 라이브러리:** 이 프로젝트는 외부 라이브러리 의존성 없이 표준 Java Development Kit (JDK)만을 사용한다. `java.util.HashMap`과 같은 기본적인 컬렉션 클래스가 활용되었다.

## 개선 가능성

이 프로젝트는 기본적인 감성 분석 원리를 보여주기 위한 예시이므로, 다음과 같은 개선점을 가질 수 있다.

*   **형태소 분석기 연동:** 한국어 텍스트 분석의 정확도를 높이려면 Okt(Open Korean Text)나 Komoran과 같은 한국어 형태소 분석기를 연동하여 단어의 의미 단위를 정확히 추출해야 한다.
*   **머신러닝 모델 적용:** Naive Bayes, Support Vector Machine(SVM), 또는 딥러닝(RNN, Transformer) 기반의 감성 분석 모델을 학습시켜 적용하면 훨씬 더 정교한 분석이 가능하다.
*   **감성 사전 확장:** 더 풍부하고 상황에 맞는 감성 사전을 구축하여 분석 정확도를 높일 수 있다.
*   **웹 서비스 API 통합:** 감성 분석 기능을 RESTful API 형태로 제공하여 다른 애플리케이션에서 쉽게 사용할 수 있도록 확장할 수 있다.
