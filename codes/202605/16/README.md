# C# 태스크 관리 웹 API 프로젝트

이 프로젝트는 ASP.NET Core를 사용하여 간단한 태스크(할 일) 관리 웹 API를 구현한 예제입니다. 사용자는 이 API를 통해 태스크를 생성, 조회, 수정 및 삭제할 수 있습니다.

## 프로젝트 구조

*   `TaskManagerApi.csproj`: 프로젝트 설정 및 종속성 관리 파일입니다.
*   `Program.cs`: 웹 애플리케이션의 진입점입니다. ASP.NET Core 웹 호스트를 설정하고 미들웨어를 구성하며, 종속성 주입(DI) 컨테이너를 설정합니다. 컨트롤러를 등록하고 애플리케이션을 실행하는 역할을 합니다.
*   `TaskItem.cs`: 태스크 항목의 데이터 모델을 정의합니다. 각 태스크는 고유한 ID, 제목, 그리고 완료 여부를 나타내는 속성을 가집니다.
*   `TasksController.cs`: 태스크 리소스에 대한 HTTP 요청을 처리하는 API 컨트롤러입니다. CRUD(생성, 읽기, 업데이트, 삭제) 작업을 위한 엔드포인트를 제공하며, 현재는 메모리 내 컬렉션을 사용하여 데이터를 관리합니다.

## 사용된 기술 및 라이브러리

*   **ASP.NET Core**: 마이크로소프트의 오픈 소스 웹 프레임워크입니다. 본 프로젝트에서는 웹 API 개발에 사용되었습니다.
*   **System.Collections.Generic**: 태스크 항목을 저장하기 위한 `List<T>` 컬렉션을 사용합니다. (산업 표준 데이터 구조)
*   **Microsoft.AspNetCore.Mvc**: 웹 API 컨트롤러를 정의하고 HTTP 요청을 처리하기 위한 기본 기능을 제공합니다.
*   **Swashbuckle.AspNetCore**: API 문서를 자동으로 생성하고 상호작용할 수 있는 Swagger UI를 제공합니다.

## 실행 방법

이 프로젝트는 .NET SDK가 설치된 환경에서 실행할 수 있습니다.

1.  **프로젝트 경로로 이동:**
    명령 프롬프트 또는 터미널에서 프로젝트 파일들이 있는 디렉터리(`C:\server\gpt-code-diary\codes\202605\16`)로 이동합니다.

    ```bash
    cd C:\server\gpt-code-diary\codes\202605\16
    ```

2.  **종속성 복원 및 빌드:**
    다음 명령을 실행하여 필요한 패키지를 복원하고 프로젝트를 빌드합니다.

    ```bash
    dotnet restore
    dotnet build
    ```

3.  **애플리케이션 실행:**
    다음 명령으로 애플리케이션을 실행합니다.

    ```bash
    dotnet run
    ```
    애플리케이션이 실행되면, 일반적으로 `http://localhost:5000` 또는 `http://localhost:5001` (HTTPS)와 같은 주소에서 API에 접근할 수 있습니다. Swagger UI는 `/swagger` 경로에서 접근할 수 있습니다 (예: `http://localhost:5000/swagger`).

## API 엔드포인트 예시

*   **모든 태스크 조회 (GET):** `http://localhost:5000/api/tasks`
*   **특정 태스크 조회 (GET):** `http://localhost:5000/api/tasks/{id}`
*   **새 태스크 생성 (POST):** `http://localhost:5000/api/tasks` (JSON 본문: `{"title": "새 태스크", "isComplete": false}`)
*   **태스크 업데이트 (PUT):** `http://localhost:5000/api/tasks/{id}` (JSON 본문: `{"id": 1, "title": "업데이트된 태스크", "isComplete": true}`)
*   **태스크 삭제 (DELETE):** `http://localhost:5000/api/tasks/{id}`
