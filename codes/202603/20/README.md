# JSON 데이터 처리 유틸리티

## 프로젝트 설명
본 프로젝트는 JSON 파일을 읽어 특정 조건에 따라 데이터를 필터링하거나 변환한 후, 그 결과를 새로운 JSON 파일로 저장하는 콘솔 애플리케이션이다. `System.Text.Json` 라이브러리를 활용하여 효율적인 JSON 직렬화 및 역직렬화를 수행한다. 산업에서 자주 사용되는 데이터 처리 시나리오를 시뮬레이션하고, C# 콘솔 애플리케이션 개발의 기본적인 패턴을 보여준다.

## 코드 구조
*   **`Program.cs`**:
    *   애플리케이션의 진입점이다.
    *   명령줄 인수를 파싱하여 입력 및 출력 파일 경로를 확인한다.
    *   `JsonProcessor` 클래스의 인스턴스를 생성하고, 데이터 처리 작업을 시작한다.
    *   오류 처리 및 사용자에게 피드백을 제공하는 역할을 한다.
*   **`JsonProcessor.cs`**:
    *   JSON 파일 처리의 핵심 로직을 담당하는 클래스이다.
    *   `ReadJsonFile<T>` 메서드를 통해 지정된 경로의 JSON 파일을 읽어 C# 객체로 역직렬화한다.
    *   `ProcessData` 메서드는 예시 데이터 처리 로직을 포함하며, 필요에 따라 데이터를 필터링하거나 변환할 수 있다.
    *   `WriteJsonFile<T>` 메서드를 통해 처리된 C# 객체를 JSON 파일로 직렬화하여 저장한다.
*   **`DataModel.cs`**:
    *   처리할 JSON 데이터의 구조를 정의하는 C# 클래스이다.
    *   `System.Text.Json.Serialization` 네임스페이스의 `JsonPropertyName` 어트리뷰트를 사용하여 JSON 속성 이름과 C# 속성 이름 간의 매핑을 정의할 수 있다.
    *   예시로 간단한 `Item` 객체를 포함한다.

## 실행 방법
이 프로젝트를 실행하기 위해서는 .NET 8 (또는 그 이상) SDK가 시스템에 설치되어 있어야 한다.

1.  **프로젝트 디렉터리로 이동**:
    ```bash
    cd C:\server\gpt-code-diary\codes\202603\20
    ```

2.  **예시 입력 JSON 파일 생성 (`input.json`)**:
    프로젝트 루트 디렉터리에 `input.json` 파일을 다음과 같은 내용으로 생성한다:
    ```json
    [
      {
        "Id": 1,
        "Name": "아이템 A",
        "Value": 100
      },
      {
        "Id": 2,
        "Name": "아이템 B",
        "Value": 250
      },
      {
        "Id": 3,
        "Name": "아이템 C",
        "Value": 50
      },
      {
        "Id": 4,
        "Name": "아이템 D",
        "Value": 300
      }
    ]
    ```

3.  **애플리케이션 실행**:
    명령 프롬프트 또는 터미널에서 다음 명령어를 실행한다. `input.json`은 원본 JSON 파일이며, `output.json`은 처리된 데이터를 저장할 파일이다.
    ```bash
    dotnet run -- input.json output.json
    ```
    *   `input.json`: 처리할 원본 JSON 파일의 경로이다.
    *   `output.json`: 처리 결과를 저장할 JSON 파일의 경로이다.

4.  **결과 확인**:
    `output.json` 파일이 생성되며, 필터링된 데이터가 포함되어 있을 것이다 (예시에서는 `Value`가 100보다 큰 아이템만 포함).
