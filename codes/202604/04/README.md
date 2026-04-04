# C# JSON 직렬화 및 역직렬화 예제

이 프로젝트는 C#에서 JSON(JavaScript Object Notation) 데이터를 직렬화(Serialize)하고 역직렬화(Deserialize)하는 방법을 시연하는 소규모 예제이다. 산업 표준 라이브러리인 `Newtonsoft.Json`을 활용하여 .NET 객체를 JSON 문자열로 변환하고, 그 반대의 작업도 수행하는 과정을 보여준다.

## 프로젝트 구조

프로젝트는 다음과 같은 파일들로 구성되어 있다:

-   `JsonExample.csproj`: C# 프로젝트 파일로, .NET SDK 설정 및 `Newtonsoft.Json` 라이브러리 참조를 포함한다.
-   `DataModel.cs`: JSON으로 직렬화 및 역직렬화될 `UserProfile` 클래스를 정의한다. 사용자의 ID, 이름, 이메일, 활성 상태, 생성일 등의 속성을 가진다. `JsonProperty` 속성을 사용하여 JSON 필드 이름을 명시적으로 지정하였다.
-   `JsonProcessor.cs`: JSON 직렬화 및 역직렬화를 위한 정적 헬퍼(Helper) 메서드를 제공하는 유틸리티 클래스이다. `Newtonsoft.Json`의 `JsonConvert`를 래핑(Wrapping)하여 사용하기 쉽게 만들었다.
-   `Program.cs`: 애플리케이션의 메인 진입점이다. `UserProfile` 객체를 생성하고, `JsonProcessor`를 사용하여 JSON으로 직렬화한 다음, 다시 객체로 역직렬화하여 그 결과를 콘솔에 출력한다.

## 실행 방법

이 프로젝트는 .NET SDK가 설치된 환경에서 실행할 수 있다.

1.  **프로젝트 디렉터리로 이동:**
    명령 프롬프트 또는 터미널을 열고 `JsonExample.csproj` 파일이 있는 디렉터리로 이동한다. (현재 이 파일들이 생성된 디렉터리이다.)

    ```bash
    cd C:\server\gpt-code-diary\codes\202604\04
    ```

2.  **의존성 복원:**
    `Newtonsoft.Json` 패키지를 포함한 프로젝트의 모든 의존성을 복원해야 한다.

    ```bash
    dotnet restore
    ```

3.  **프로젝트 빌드 및 실행:**
    프로젝트를 빌드하고 실행하려면 다음 명령어를 사용한다.

    ```bash
    dotnet run
    ```

    이 명령어를 실행하면 `Program.cs`에 정의된 로직에 따라 `UserProfile` 객체가 JSON으로 변환되고, 다시 역직렬화되어 그 결과가 콘솔에 출력되는 것을 확인할 수 있다.

## 활용 예시

이 예제는 웹 API 통신, 설정 파일 관리, 데이터 저장 등 JSON 데이터를 다루는 다양한 실제 산업 시나리오에 응용될 수 있다. `Newtonsoft.Json`은 C# 개발에서 JSON 처리를 위한 강력하고 유연한 도구이다.
