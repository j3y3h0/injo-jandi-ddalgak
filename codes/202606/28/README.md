# CSV 데이터 분석 도구

이 프로젝트는 Node.js를 사용하여 CSV 파일에서 특정 컬럼의 데이터를 분석하고, 해당 데이터의 평균(Mean)과 중앙값(Median)을 계산하는 간단한 명령줄 도구입니다. `papaparse` 라이브러리를 활용하여 CSV 파싱을 효율적으로 처리합니다.

## 프로젝트 구조

```
.
├── package.json
├── main.js
├── data_processor.js
├── utils.js
└── README.md
```

*   `package.json`: 프로젝트 메타데이터와 의존성(`papaparse`)을 정의합니다.
*   `main.js`: 애플리케이션의 주 진입점입니다. 명령줄 인자를 파싱하고, CSV 파일을 읽고, `data_processor.js`의 함수들을 호출하여 통계를 계산하고 결과를 출력합니다.
*   `data_processor.js`: CSV 파일 파싱(`papaparse` 사용) 및 데이터의 평균, 중앙값 등 통계 계산 로직을 포함합니다.
*   `utils.js`: 파일 존재 여부 확인 및 문자열 유효성 검사와 같은 유틸리티 함수들을 제공합니다.
*   `README.md`: 이 문서입니다. 프로젝트 설명, 설정 및 사용 방법을 안내합니다.

## 설정 및 실행 방법

### 1. 의존성 설치

프로젝트를 실행하기 전에 필요한 Node.js 패키지들을 설치해야 합니다. 프로젝트 루트 디렉터리에서 다음 명령어를 실행합니다.

```bash
npm install
```

### 2. CSV 파일 준비

분석하고자 하는 CSV 파일을 준비합니다. 예시 CSV 파일(`data.csv`)은 다음과 같을 수 있습니다:

```csv
항목,수량,가격
A,10,1000
B,20,1500
C,15,2000
D,5,1200
E,25,1800
```

### 3. 도구 실행

다음 명령어를 사용하여 도구를 실행합니다. `CSV_파일_경로`와 `분석할_컬럼_이름`을 적절히 대체해야 합니다.

```bash
node main.js <CSV_파일_경로> <분석할_컬럼_이름>
```

**예시:**

`data.csv` 파일에서 "가격" 컬럼을 분석하려면:

```bash
node main.js data.csv 가격
```

## 코드 설명

### `data_processor.js`

*   **`parseCsvFile(filePath)`**: 주어진 CSV 파일 경로에서 데이터를 읽어 `papaparse`를 이용하여 파싱합니다. `header: true` 옵션으로 첫 줄을 헤더로 인식하고, `dynamicTyping: true`로 숫자나 불리언 값을 자동으로 변환합니다. Promise를 반환하여 비동기 처리를 지원합니다.
*   **`calculateMean(data, column)`**: 데이터 배열과 컬럼 이름을 인자로 받아 해당 컬럼의 숫자 값들의 평균을 계산합니다. 유효한 숫자 값만 필터링하여 사용합니다.
*   **`calculateMedian(data, column)`**: 데이터 배열과 컬럼 이름을 인자로 받아 해당 컬럼의 숫자 값들의 중앙값을 계산합니다. 숫자 값들을 정렬한 후 중앙에 위치한 값을 찾습니다. 데이터의 개수에 따라 홀수/짝수 경우를 처리합니다.

### `utils.js`

*   **`fileExists(filePath)`**: 지정된 경로의 파일이 존재하는지 동기적으로 확인합니다.
*   **`isEmptyString(str)`**: 문자열이 비어있거나 `null`, `undefined`인지 확인합니다.

### `main.js`

*   명령줄 인자를 `process.argv`를 통해 파싱하여 CSV 파일 경로와 분석할 컬럼 이름을 추출합니다.
*   `utils.fileExists`를 사용하여 파일 존재 여부를 검증하고, `utils.isEmptyString`을 사용하여 컬럼 이름의 유효성을 검증합니다.
*   `data_processor.parseCsvFile`을 호출하여 CSV 데이터를 비동기적으로 로드합니다.
*   `data_processor.calculateMean`과 `data_processor.calculateMedian`을 호출하여 통계를 계산합니다.
*   계산된 평균과 중앙값을 콘솔에 출력합니다.
*   파일 읽기나 데이터 처리 중 오류가 발생하면 적절한 오류 메시지를 출력하고 프로그램을 종료합니다.

## 추가 정보

이 도구는 기본적인 CSV 데이터 분석 기능을 제공하며, 필요에 따라 다른 통계 분석(예: 표준 편차, 최댓값, 최솟값) 또는 데이터 시각화 기능을 추가하여 확장할 수 있습니다.
