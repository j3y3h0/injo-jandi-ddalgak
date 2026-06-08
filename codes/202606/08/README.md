# 간단한 JSON 데이터 분석 유틸리티 프로젝트

이 프로젝트는 Node.js 환경에서 JSON 데이터를 읽고, 특정 조건에 따라 데이터를 필터링하며, 값들을 집계하는 간단한 데이터 분석 유틸리티이다. 산업에서 흔히 사용되는 데이터 처리의 기초적인 개념을 Javascript로 구현하였다.

## 프로젝트 구조

```
.
├── main.js
├── dataProcessor.js
├── sample_data.json
└── README.md
```

*   **`main.js`**:
    *   프로젝트의 진입점이다.
    *   `sample_data.json` 파일에서 원본 데이터를 읽어온다.
    *   `dataProcessor.js`에 정의된 함수들을 사용하여 데이터를 분석하고 결과를 콘솔에 출력한다.
    *   파일 시스템 접근을 위해 Node.js의 `fs` 모듈과 경로 처리를 위해 `path` 모듈을 사용한다.
*   **`dataProcessor.js`**:
    *   데이터 처리 로직을 담고 있는 유틸리티 모듈이다.
    *   `filterByCategory(data, category)` 함수는 주어진 데이터 배열에서 특정 `category`에 해당하는 항목만 걸러내는 기능을 수행한다.
    *   `aggregateValues(data)` 함수는 필터링된 데이터 배열의 모든 항목에 있는 `value` 속성들을 합산하여 총합을 계산한다.
*   **`sample_data.json`**:
    *   분석에 사용될 샘플 JSON 데이터 파일이다.
    *   각 객체는 `id`, `category`, `value` 속성을 포함하고 있다.
*   **`README.md`**:
    *   이 문서로서, 프로젝트의 목적, 구조, 그리고 실행 방법을 설명한다.

## 실행 방법

이 프로젝트는 Node.js 환경에서 실행된다. Node.js가 설치되어 있지 않다면 먼저 설치해야 한다.

1.  **터미널 열기**: 현재 프로젝트 디렉터리(`C:\server\gpt-code-diary\codes\202606\08`)에서 터미널 또는 명령 프롬프트를 연다.
2.  **프로그램 실행**: 다음 명령어를 입력하여 `main.js` 파일을 실행한다.

    ```bash
    node main.js
    ```

위 명령어를 실행하면 `main.js`가 `sample_data.json`의 데이터를 읽어 `dataProcessor.js`를 통해 분석하고, 그 결과를 터미널에 출력하는 것을 확인할 수 있다.
