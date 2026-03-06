# CSV 데이터 처리 프로젝트

이 프로젝트는 C#을 사용하여 CSV(Comma Separated Values) 파일에서 데이터를 읽고, 처리한 후, 그 결과를 새로운 CSV 파일로 작성하는 간단한 예제입니다. 특정 제품의 총 판매 수량을 계산하는 시나리오를 구현하였습니다.

## 코드 구조

프로젝트는 다음 파일들로 구성되어 있습니다:

- `Program.cs`: 애플리케이션의 진입점입니다. 샘플 데이터를 생성하고, `CsvProcessor`를 사용하여 CSV 파일을 처리하며, 결과를 출력합니다.
- `CsvProcessor.cs`: CSV 파일 읽기, 데이터 처리 (그룹화 및 합산), 그리고 결과 CSV 파일 작성 기능을 담당하는 핵심 로직을 포함합니다.
- `input.csv`: 처리할 샘플 원본 데이터가 담긴 CSV 파일입니다. `Program.cs`에서 생성됩니다.

## 기능 설명

1.  `input.csv` 파일에 제품 이름, 수량, 가격 정보가 저장됩니다.
2.  `CsvProcessor`는 이 `input.csv` 파일을 읽어 각 제품별 총 수량을 계산합니다.
3.  계산된 결과는 `output.csv` 파일로 저장됩니다. 이 파일에는 제품 이름과 해당 제품의 총 수량이 포함됩니다.

## 실행 방법

이 프로젝트를 실행하기 위해서는 .NET SDK가 설치되어 있어야 합니다.

1.  **프로젝트 빌드:**
    터미널 또는 명령 프롬프트에서 프로젝트 디렉터리로 이동한 후 다음 명령어를 실행합니다:
    ```bash
    dotnet build
    ```
2.  **프로젝트 실행:**
    빌드가 성공하면 다음 명령어를 실행하여 애플리케이션을 실행할 수 있습니다:
    ```bash
    dotnet run
    ```
    실행이 완료되면 `output.csv` 파일이 프로젝트 디렉터리에 생성된 것을 확인할 수 있습니다.

## 예제 데이터 및 결과

`input.csv` (예시):

```csv
Product,Quantity,Price
Apple,10,1.5
Banana,5,0.75
Apple,3,1.5
Orange,8,1.2
Banana,2,0.75
```

`output.csv` (생성될 예시):

```csv
Product,TotalQuantity
Apple,13
Banana,7
Orange,8
```
