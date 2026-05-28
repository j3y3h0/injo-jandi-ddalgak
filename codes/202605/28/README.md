# REST API 클라이언트 프로젝트

이 프로젝트는 Java를 사용하여 외부 REST API를 호출하고, 응답으로 받은 JSON 데이터를 파싱하여 처리하는 간단한 클라이언트 애플리케이션입니다. `jsonplaceholder.typicode.com`의 공개 API를 사용하여 게시물(posts) 정보를 가져와 콘솔에 출력하는 기능을 수행합니다.

## 프로젝트 구조

프로젝트는 Maven 빌드 시스템을 기반으로 하며, 다음과 같은 주요 파일들로 구성되어 있습니다.

```
.
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── example
                    └── api
                        ├── Main.java
                        ├── ApiClient.java
                        ├── model
                        │   └── Post.java
                        └── util
                            └── JsonProcessor.java
```

*   `pom.xml`: Maven 프로젝트 설정 파일로, 프로젝트의 의존성(Jackson 라이브러리)과 빌드 설정을 정의합니다.
*   `src/main/java/com/example/api/Main.java`: 애플리케이션의 메인 진입점입니다. `ApiClient`와 `JsonProcessor`를 활용하여 API 호출 및 데이터 처리 흐름을 제어합니다.
*   `src/main/java/com/example/api/ApiClient.java`: HTTP GET 요청을 처리하고, 지정된 URL에서 JSON 응답을 받아오는 역할을 담당합니다. `HttpURLConnection`을 사용합니다.
*   `src/main/java/com/example/api/model/Post.java`: API로부터 받는 게시물(Post) 데이터의 구조를 정의하는 자바 POJO(Plain Old Java Object) 클래스입니다. Jackson 라이브러리의 어노테이션을 사용하여 JSON 필드와 매핑됩니다.
*   `src/main/java/com/example/api/util/JsonProcessor.java`: Jackson `ObjectMapper`를 사용하여 JSON 문자열을 자바 객체로 역직렬화(파싱)하거나, 자바 객체를 JSON 문자열로 직렬화하는 유틸리티 클래스입니다.

## 사용 라이브러리

이 프로젝트는 JSON 데이터 처리를 위해 **Jackson** 라이브러리를 사용합니다.

*   `jackson-databind`: Jackson의 핵심 모듈로, 데이터 바인딩 기능을 제공합니다.
*   `jackson-core`: JSON 처리의 저수준(low-level) 스트리밍 API를 제공합니다.
*   `jackson-annotations`: Jackson의 어노테이션을 포함하여 자바 객체와 JSON 간의 매핑을 사용자 정의할 수 있게 합니다.

## 빌드 및 실행 방법

이 프로젝트는 Maven을 사용하여 빌드하고 실행할 수 있습니다.

1.  **프로젝트 클론 (또는 파일 복사)**:
    프로젝트 파일을 로컬 환경으로 가져옵니다.

2.  **Maven 의존성 설치**:
    프로젝트 루트 디렉토리(`pom.xml` 파일이 있는 곳)에서 다음 명령어를 실행하여 필요한 의존성을 다운로드합니다.

    ```bash
    mvn clean install
    ```

    이 명령어는 프로젝트를 빌드하고 `target` 디렉토리에 JAR 파일을 생성합니다.

3.  **애플리케이션 실행**:
    의존성까지 포함된 실행 가능한 JAR 파일을 사용하여 애플리케이션을 실행할 수 있습니다. `target` 디렉토리 내에 `rest-api-client-1.0-SNAPSHOT-jar-with-dependencies.jar`와 같은 이름의 파일이 생성됩니다.

    ```bash
    java -jar target/rest-api-client-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

    명령어를 실행하면 프로그램이 `jsonplaceholder.typicode.com/posts`에서 데이터를 가져와 콘솔에 출력하는 것을 확인할 수 있습니다.

## 코드 설명

*   **Main.java**: `main` 메소드에서 `ApiClient`를 통해 `https://jsonplaceholder.typicode.com/posts`로 GET 요청을 보냅니다. 받은 JSON 응답은 `JsonProcessor`를 통해 `List<Post>` 객체로 변환됩니다. 이후, 파싱된 게시물들 중 일부를 콘솔에 출력합니다.
*   **ApiClient.java**: `HttpURLConnection`을 설정하여 HTTP 요청을 보냅니다. 연결 및 읽기 타임아웃을 설정하고, HTTP 응답 코드를 확인하여 성공적인 응답(200 OK)일 경우 응답 스트림을 읽어 JSON 문자열을 반환합니다.
*   **Post.java**: `userId`, `id`, `title`, `body` 필드를 가지는 불변(immutable) 클래스입니다. `@JsonCreator`와 `@JsonProperty` 어노테이션을 사용하여 Jackson이 JSON 데이터를 이 객체로 정확히 매핑하도록 지시합니다.
*   **JsonProcessor.java**: `ObjectMapper` 인스턴스를 사용하여 JSON 파싱 및 직렬화 기능을 제공합니다. `parseJsonToList` 메소드는 JSON 배열을 특정 타입의 객체 리스트로 변환하는 데 사용됩니다.

## 참고

이 프로젝트는 간단한 예시를 제공하기 위해 최소한의 오류 처리와 로깅을 포함하고 있습니다. 실제 상용 애플리케이션에서는 더 견고한 오류 처리, 로깅 프레임워크 사용, 비동기 처리 등의 고려 사항이 필요할 수 있습니다.
