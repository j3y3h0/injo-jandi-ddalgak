# 간단한 숫자 데이터 분석기

이 프로젝트는 지정된 파일에서 숫자 목록을 읽어와 필터링하고, 그 결과에 대한 기본적인 통계(최소값, 최대값, 합계, 평균)를 계산하는 C# 콘솔 애플리케이션입니다. 산업에서 흔히 사용되는 데이터 처리 및 분석의 기초적인 예시를 제공합니다.

## 프로젝트 구조

프로젝트는 다음 파일들로 구성되어 있습니다:

-   `NumberCruncher.csproj`: C# 프로젝트 파일입니다. 프로젝트의 설정 및 의존성을 정의합니다.
-   `Program.cs`: 애플리케이션의 진입점입니다. 명령줄 인수를 파싱하고, `DataProcessor` 및 `StatisticsCalculator` 클래스를 사용하여 전체 작업 흐름을 조정합니다.
-   `DataProcessor.cs`: 데이터 읽기 및 필터링 로직을 담당합니다. 파일에서 숫자를 읽고, 지정된 조건에 따라 필터링하는 기능을 제공합니다.
-   `StatisticsCalculator.cs`: 숫자 목록에 대한 통계(합계, 평균, 최소값, 최대값)를 계산하는 기능을 제공합니다.
-   `Logger.cs`: 간단한 로깅 유틸리티를 제공합니다. 애플리케이션의 실행 과정에서 발생하는 정보나 오류를 콘솔에 출력합니다.

## 실행 방법

이 애플리케이션은 .NET Core SDK가 설치된 환경에서 실행할 수 있습니다.

1.  **프로젝트 빌드**:
    명령 프롬프트 또는 터미널을 열고, 프로젝트 루트 디렉터리(`C:\server\gpt-code-diary\codes\202607\24`)로 이동합니다.
    다음 명령어를 사용하여 프로젝트를 빌드합니다:
    ```bash
    dotnet build
    ```

2.  **애플리케이션 실행**:
    빌드가 성공적으로 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행할 수 있습니다.
    예시로, `data.txt` 파일에 숫자가 포함되어 있고 10보다 큰 숫자만 필터링하여 통계를 보고 싶다면:

    **예제 입력 파일 (`data.txt`)**:
    ```
    1
    5
    10
    15
    20
    -3
    7
    ```

    **실행 명령어**:
    ```bash
    dotnet run -- data.txt --filter-min 10
    ```
    (또는 `dotnet run --project NumberCruncher.csproj -- data.txt --filter-min 10`)

    **명령줄 인수 설명**:
    -   `data.txt`: 처리할 숫자 데이터가 포함된 파일의 경로입니다. (필수)
    -   `--filter-min <숫자>`: 지정된 숫자보다 크거나 같은 값만 포함하도록 데이터를 필터링합니다. (선택 사항)
    -   `--filter-max <숫자>`: 지정된 숫자보다 작거나 같은 값만 포함하도록 데이터를 필터링합니다. (선택 사항)

## 코드 설명

### `Program.cs`

애플리케이션의 시작점입니다. `Main` 메서드에서 명령줄 인수를 처리하고, `DataProcessor`를 이용해 데이터를 로드 및 필터링하며, `StatisticsCalculator`를 이용해 통계를 계산한 후 결과를 `Logger`를 통해 출력합니다.

### `DataProcessor.cs`

`LoadNumbersFromFile` 메서드는 지정된 경로의 파일에서 각 줄을 읽어 숫자로 파싱합니다. 파싱에 실패한 줄은 건너뜁니다. `FilterNumbers` 메서드는 `filterMin` 및 `filterMax` 값을 기반으로 숫자 목록을 필터링합니다.

### `StatisticsCalculator.cs`

`CalculateStatistics` 메서드는 `IEnumerable<double>`을 인수로 받아, 해당 목록의 합계, 평균, 최소값, 최대값을 계산하여 `StatisticsResult` 객체로 반환합니다.

### `Logger.cs`

간단한 정적 클래스로, `LogInfo` 및 `LogError` 메서드를 통해 메시지를 콘솔에 출력합니다. 각 메시지에는 타임스탬프와 메시지 유형이 포함됩니다.
