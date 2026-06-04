# 데이터 처리 애플리케이션 프로젝트

## 소개
이 프로젝트는 간단한 Java 기반 데이터 처리 애플리케이션입니다. `DataModel` 클래스를 사용하여 데이터를 정의하고, `DataProcessorApp` 클래스에서 이 데이터를 생성하고 기본적인 통계 분석(평균 계산)을 수행합니다. 이 예제는 Maven을 사용하여 프로젝트를 빌드하고 실행하는 방법을 보여줍니다.

## 프로젝트 구조
프로젝트는 Maven 표준 디렉토리 구조를 따르며, 핵심 파일은 다음과 같습니다:
- `pom.xml`: Maven 빌드 설정 파일로, 프로젝트의 의존성과 빌드 방식을 정의합니다.
- `src/main/java/com/example/dataprocessor/DataModel.java`: 이름(`String`)과 값(`double`)을 가지는 데이터 모델 클래스입니다.
- `src/main/java/com/example/dataprocessor/DataProcessorApp.java`: 애플리케이션의 진입점(main 메서드)을 포함하는 메인 클래스입니다. 샘플 데이터를 생성하고, 데이터 값의 평균을 계산하는 로직을 포함합니다.

```
.
├── pom.xml
├── README.md
└── src
    └── main
        └── java
            └── com
                └── example
                    └── dataprocessor
                        ├── DataModel.java
                        └── DataProcessorApp.java
```

## 실행 방법

이 프로젝트는 Maven을 사용하여 빌드하고 실행할 수 있습니다.

### 전제 조건
- Java Development Kit (JDK) 8 이상이 설치되어 있어야 합니다.
- Apache Maven이 설치되어 있어야 합니다.

### 빌드 및 실행 단계

1.  **프로젝트 디렉토리로 이동:**
    명령 프롬프트 또는 터미널을 열고 `pom.xml` 파일이 있는 프로젝트의 루트 디렉토리로 이동하십시오.
    ```bash
    cd C:\server\gpt-code-diary\codes\202606\04
    ```

2.  **프로젝트 빌드:**
    Maven을 사용하여 프로젝트를 빌드합니다. 이 명령은 필요한 모든 의존성을 다운로드하고 소스 코드를 컴파일하여 JAR 파일을 생성합니다.
    ```bash
    mvn clean install
    ```
    빌드가 성공적으로 완료되면 `target/dataprocessor-1.0-SNAPSHOT.jar` 파일이 생성됩니다.

3.  **애플리케이션 실행:**
    생성된 JAR 파일을 사용하여 애플리케이션을 실행합니다. `java -jar` 명령을 사용하여 메인 클래스를 지정하지 않고도 실행할 수 있습니다.
    ```bash
    java -jar target/dataprocessor-1.0-SNAPSHOT.jar
    ```

위 단계를 따르면 애플리케이션이 실행되고, 콘솔에 샘플 데이터 생성 및 평균 계산 결과가 출력됩니다.
