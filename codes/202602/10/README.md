# 데이터 분석 콘솔 애플리케이션

이 프로젝트는 C#을 사용하여 간단한 숫자 데이터의 처리(정제) 및 분석(통계 계산)을 수행하는 콘솔 애플리케이션입니다.

## 프로젝트 개요

주어진 원시 숫자 데이터에서 유효하지 않은 값(음수 또는 `NaN`)을 제거하고, 정제된 데이터를 기반으로 평균, 최소값, 최대값, 표준 편차를 계산하여 출력합니다. 이는 간단한 데이터 전처리 및 기초 통계 분석 과정을 시뮬레이션합니다.

## 파일 구성

*   `DataAnalysisProject.csproj`: C# 프로젝트 파일. 프로젝트의 설정 및 종속성을 정의합니다.
*   `Program.cs`: 애플리케이션의 진입점(Entry Point)입니다. 원시 데이터를 정의하고, `DataProcessor`와 `DataAnalyzer` 클래스의 메서드를 호출하여 데이터 처리 및 분석 과정을 Orchestrate(조정)합니다.
*   `DataProcessor.cs`: 데이터 정제 로직을 포함하는 클래스입니다. `CleanData` 메서드를 통해 유효하지 않은 데이터를 필터링합니다. C#의 `System.Linq` 기능을 활용하여 효율적인 데이터 처리를 수행합니다.
*   `DataAnalyzer.cs`: 데이터 분석 로직을 포함하는 클래스입니다. `CalculateAverage`, `CalculateMin`, `CalculateMax`, `CalculateStandardDeviation` 메서드를 통해 통계 분석을 수행합니다. 이 또한 `System.Linq`를 적극적으로 활용합니다.

## 기술 스택

*   **언어**: C#
*   **프레임워크**: .NET 8.0 콘솔 애플리케이션

## 실행 방법

이 프로젝트는 .NET 8.0 SDK가 설치된 환경에서 실행 가능합니다.

1.  **현재 디렉터리로 이동**:
    ```bash
    cd C:\server\gpt-code-diary\codes\202602\10
    ```

2.  **애플리케이션 실행**:
    ```bash
    dotnet run
    ```

위 명령어를 실행하면 콘솔에 데이터 처리 과정과 분석 결과가 출력됩니다.

## 코드 예시

`Program.cs` 파일 내의 `rawData` 리스트를 수정하여 다양한 데이터셋으로 테스트해 볼 수 있습니다.

```csharp
// Program.cs 내의 원시 데이터 예시
List<double> rawData = new List<double> { 10.5, 20.0, -5.0, 30.2, 15.8, double.NaN, 25.1, 0.0, 12.3, -8.1, 18.7 };
```

## 기여

이 프로젝트는 기본적인 데이터 처리 및 분석 예시를 제공합니다. 더 복잡한 데이터셋이나 추가적인 분석 기능을 구현하여 확장할 수 있습니다.
