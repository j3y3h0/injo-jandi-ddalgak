# 간단한 텍스트 분석기 프로젝트 (Java)

## 프로젝트 설명
이 프로젝트는 Java로 구현된 간단한 텍스트 분석기이다. 주어진 텍스트에서 각 단어의 출현 빈도수를 계산하는 기능을 제공한다. 텍스트 정규화, 단어 분리 및 빈도수 계산 로직을 포함하며, 소규모 데이터 처리 및 분석 작업에 활용될 수 있다.

## 코드 구조
프로젝트는 다음 세 개의 Java 파일로 구성되어 있다:

1.  **`Main.java`**:
    *   프로그램의 메인 진입점이다.
    *   예제 텍스트를 정의하고, `TextProcessor`를 사용하여 단어 빈도수를 계산한다.
    *   계산된 결과를 콘솔에 출력한다.

2.  **`TextProcessor.java`**:
    *   텍스트 분석의 핵심 로직을 담당하는 클래스이다.
    *   `getWordFrequency(String text)` 메서드를 통해 단어 빈도수를 계산한다.
    *   내부적으로 `TextUtility`를 사용하여 텍스트를 정규화하고 단어를 추출한다.

3.  **`TextUtility.java`**:
    *   텍스트 처리 전 데이터를 준비하는 유틸리티 클래스이다.
    *   `normalizeText(String text)` 메서드는 텍스트를 소문자로 변환하고 알파벳과 공백을 제외한 모든 문자를 제거한다.
    *   `consolidateSpaces(String text)` 메서드는 여러 개의 공백을 하나의 공백으로 줄여준다.

## 사용된 산업 표준 라이브러리 및 알고리즘
*   **Java Collections Framework**: `java.util.Map` (`HashMap`)을 사용하여 단어와 빈도수를 효율적으로 저장하고 관리한다.
*   **정규 표현식 (Regular Expressions)**: `java.util.regex.Pattern` 및 `java.util.regex.Matcher`를 사용하여 텍스트 정규화 및 단어 분리에 활용한다. 이는 복잡한 문자열 패턴 매칭 및 조작에 널리 사용되는 강력한 도구이다.
*   **Stream API**: Java 8 이상에서 제공되는 Stream API를 사용하여 단어 처리 및 결과 정렬 코드를 간결하고 가독성 높게 작성하였다.

## 실행 방법

### 1. 컴파일
프로젝트 파일을 모두 다운로드한 후, 터미널 또는 명령 프롬프트에서 프로젝트 디렉터리로 이동한다. 그리고 다음 명령어를 사용하여 Java 소스 코드를 컴파일한다:

```bash
javac Main.java TextProcessor.java TextUtility.java
```

### 2. 실행
컴파일이 완료되면, 다음 명령어를 사용하여 프로그램을 실행할 수 있다:

```bash
java Main
```

프로그램은 `Main.java`에 정의된 예제 텍스트를 분석하고, 각 단어의 출현 빈도수를 콘솔에 출력할 것이다.
