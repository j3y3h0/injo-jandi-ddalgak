# Todo-App: 간단한 할 일 관리 REST API

## 프로젝트 소개

이 프로젝트는 Spring Boot 프레임워크를 사용하여 개발된 간단한 할 일(Todo) 관리 RESTful API 서버입니다. 사용자는 이 API를 통해 할 일 항목을 생성하고, 조회하고, 업데이트하고, 삭제할 수 있습니다. 데이터는 현재 애플리케이션 메모리(인메모리 저장소)에 저장되므로, 애플리케이션이 종료되면 모든 데이터는 소실됩니다.

**주요 기능:**

*   **할 일 목록 조회:** 모든 할 일 항목을 가져옵니다.
*   **특정 할 일 조회:** 특정 ID를 가진 할 일 항목을 가져옵니다.
*   **할 일 생성:** 새로운 할 일 항목을 추가합니다.
*   **할 일 업데이트:** 기존 할 일 항목의 내용을 수정합니다.
*   **할 일 삭제:** 특정 할 일 항목을 삭제합니다.

## 기술 스택

*   **언어:** Java 17
*   **프레임워크:** Spring Boot 3.2.0
*   **빌드 도구:** Apache Maven

## 프로젝트 구조

```
.
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── example
                    └── todo
                        ├── TodoApplication.java     # Spring Boot 애플리케이션 메인 클래스
                        ├── controller
                        │   └── TodoController.java  # REST API 엔드포인트 처리
                        ├── model
                        │   └── TodoItem.java        # 할 일 항목 데이터 모델
                        └── repository
                            └── TodoRepository.java  # 인메모리 데이터 저장소
```

## 실행 방법

이 애플리케이션을 실행하려면 로컬 환경에 Java 17 및 Apache Maven이 설치되어 있어야 합니다.

1.  **프로젝트 클론 (가정)**: 현재 디렉터리에 파일들이 이미 있다고 가정합니다.
    ```bash
    # 현재 작업 디렉터리에 이 프로젝트의 파일들이 있습니다.
    ```

2.  **의존성 설치 및 빌드**: 프로젝트 루트 디렉토리(pom.xml 파일이 있는 곳)에서 다음 명령어를 실행하여 필요한 의존성을 다운로드하고 프로젝트를 빌드합니다.
    ```bash
    mvn clean install
    ```

3.  **애플리케이션 실행**: 빌드가 성공적으로 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행할 수 있습니다.
    ```bash
    mvn spring-boot:run
    ```
    또는, 빌드된 JAR 파일을 직접 실행할 수도 있습니다.
    ```bash
    java -jar target/todo-app-0.0.1-SNAPSHOT.jar
    ```

4.  **API 테스트**: 애플리케이션이 성공적으로 시작되면, 기본적으로 8080 포트에서 실행됩니다. 다음과 같은 `curl` 명령어를 사용하여 API를 테스트할 수 있습니다.

    *   **모든 할 일 조회 (GET)**
        ```bash
        curl -X GET http://localhost:8080/api/todos
        ```

    *   **새로운 할 일 생성 (POST)**
        ```bash
        curl -X POST -H "Content-Type: application/json" -d "{"title":"점심 식사하기", "completed":false}" http://localhost:8080/api/todos
        ```

    *   **특정 할 일 조회 (GET)** - `{id}`는 생성된 할 일의 ID로 대체하세요.
        ```bash
        curl -X GET http://localhost:8080/api/todos/1
        ```

    *   **할 일 업데이트 (PUT)** - `{id}`는 업데이트할 할 일의 ID로 대체하세요.
        ```bash
        curl -X PUT -H "Content-Type: application/json" -d "{"id":1, "title":"저녁 식사하기", "completed":true}" http://localhost:8080/api/todos/1
        ```

    *   **할 일 삭제 (DELETE)** - `{id}`는 삭제할 할 일의 ID로 대체하세요.
        ```bash
        curl -X DELETE http://localhost:8080/api/todos/1
        ```

## 기여

이 프로젝트는 학습 목적으로 생성되었습니다. 기능 개선이나 버그 수정에 대한 기여는 환영합니다.
