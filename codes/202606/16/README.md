# C# CSV 데이터 처리 및 분석 프로젝트

이 프로젝트는 C#을 사용하여 CSV (Comma Separated Values) 파일을 읽고, 데이터를 파싱하며, 간단한 분석을 수행하는 소규모 애플리케이션입니다. 특정 뉴스 주제와 연결하기보다는, 산업에서 흔히 사용되는 데이터 처리 기술을 실용적으로 보여주기 위해 고안되었습니다.

## 프로젝트 구조

*   **DataModel.cs**:
    CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다. `Name`, `Category`, `Value` 세 가지 속성을 가지고 있습니다.

*   **CsvProcessor.cs**:
    CSV 파일에서 데이터를 읽어 `DataModel` 객체 리스트로 파싱하는 기능을 제공합니다. 또한, 파싱된 데이터를 기반으로 카테고리별 값의 합계를 계산하는 간단한 분석 기능도 포함하고 있습니다.

*   **Program.cs**:
    애플리케이션의 메인 진입점입니다. `sample.csv`라는 예제 CSV 파일을 자동으로 생성하고, `CsvProcessor`를 사용하여 해당 파일을 읽고 분석 결과를 콘솔에 출력합니다.

## 실행 방법

이 프로젝트는 .NET Core 또는 .NET Framework 환경에서 빌드 및 실행할 수 있습니다.

1.  **프로젝트 복사**:
    제공된 모든 `.cs` 파일과 `README.md` 파일을 단일 디렉터리(`C:\server\gpt-code-diary\codes\202606\16` 등)에 복사합니다.

2.  **프로젝트 파일 생성 (선택 사항)**:
    Visual Studio 또는 .NET CLI를 사용하는 경우, 해당 디렉터리에서 새 콘솔 애플리케이션 프로젝트를 생성하고 기존 파일을 추가하거나, 수동으로 `.csproj` 파일을 생성할 수 있습니다.

    **`.csproj` 파일 예시:**
    ```xml
    <Project Sdk="Microsoft.NET.Sdk">

      <PropertyGroup>
        <OutputType>Exe</OutputType>
        <TargetFramework>net8.0</TargetFramework>
        <ImplicitUsings>enable</ImplicitUsings>
        <Nullable>enable</Nullable>
      </PropertyGroup>

    </Project>
    ```
    이 `.csproj` 파일을 프로젝트 루트 디렉터리에 `CsvDataProcessor.csproj`와 같은 이름으로 저장하면 됩니다.

3.  **빌드 및 실행**:

    *   **.NET CLI 사용**:
        프로젝트 파일(`.csproj`가 있는 디렉터리)에서 다음 명령어를 실행합니다.
        ```bash
        dotnet build
        dotnet run
        ```
        `dotnet run` 명령어는 자동으로 프로젝트를 빌드하고 실행합니다.

    *   **Visual Studio 사용**:
        프로젝트를 Visual Studio에서 열고 '시작' 버튼(F5)을 눌러 실행합니다.

애플리케이션이 실행되면 `sample.csv` 파일이 자동으로 생성되고, 해당 파일의 데이터가 처리 및 분석되어 콘솔에 결과가 출력됩니다.
