# 간단한 Express.js 웹 애플리케이션

이 프로젝트는 Node.js와 Express 프레임워크를 사용하여 구축된 간단한 웹 API 애플리케이션이다. 기본적인 라우팅, 컨트롤러, 유틸리티 모듈의 구성을 보여주기 위해 만들어졌다.

## 프로젝트 구조

```
.
├── package.json
├── README.md
└── src/
    ├── app.js
    ├── controllers/
    │   └── greetingController.js
    ├── routes/
    │   └── index.js
    └── utils/
        └── logger.js
```

*   `package.json`: 프로젝트 메타데이터 및 의존성 정보를 포함한다.
*   `README.md`: 이 문서를 포함하여 프로젝트에 대한 설명이다.
*   `src/`: 모든 소스 코드가 위치하는 디렉터리이다.
    *   `app.js`: Express 애플리케이션의 메인 진입점이다. 서버를 설정하고 미들웨어 및 라우트를 구성한다.
    *   `controllers/`: 라우트 핸들러의 비즈니스 로직을 포함하는 컨트롤러 파일들을 담고 있다.
        *   `greetingController.js`: 환영 메시지와 현재 시간을 반환하는 함수를 제공한다.
    *   `routes/`: API 엔드포인트를 정의하는 라우트 파일들을 담고 있다.
        *   `index.js`: `/api` 경로에 대한 라우트를 정의하며, `greetingController`의 함수들을 연결한다.
    *   `utils/`: 재사용 가능한 유틸리티 함수나 모듈을 담고 있다.
        *   `logger.js`: 간단한 콘솔 로깅 기능을 제공한다.

## 실행 방법

이 프로젝트를 실행하기 위해서는 Node.js와 npm(Node Package Manager)이 시스템에 설치되어 있어야 한다.

1.  **의존성 설치**:
    프로젝트 루트 디렉터리에서 다음 명령어를 실행하여 필요한 Node.js 패키지(Express)를 설치한다.

    ```bash
    npm install
    ```

2.  **애플리케이션 실행**:
    의존성 설치가 완료되면, 다음 명령어를 사용하여 애플리케이션을 시작한다.

    ```bash
    npm start
    ```

    서버가 성공적으로 시작되면, 콘솔에 다음과 유사한 메시지가 출력될 것이다.

    ```
    [INFO] YYYY-MM-DDTHH:MM:SS.SSSZ - 서버가 http://localhost:3000 에서 실행 중이다.
    [INFO] YYYY-MM-DDTHH:MM:SS.SSSZ - 환영 메시지: http://localhost:3000/api/hello?name=Gemini 로 접속해 보세요.
    [INFO] YYYY-MM-DDTHH:MM:SS.SSSZ - 현재 시간: http://localhost:3000/api/time 로 접속해 보세요.
    ```

## API 엔드포인트

*   **`GET /api/hello`**
    *   **설명**: 사용자에게 환영 메시지를 반환한다.
    *   **쿼리 파라미터**:
        *   `name` (선택 사항): 환영 메시지에 포함될 이름이다. 예: `/api/hello?name=World`
    *   **응답 예시**:
        ```json
        {
          "message": "안녕하세요, 방문객님! Simple Express App에 오신 것을 환영합니다."
        }
        ```
        또는
        ```json
        {
          "message": "안녕하세요, World님! Simple Express App에 오신 것을 환영합니다."
        }
        ```

*   **`GET /api/time`**
    *   **설명**: 현재 서버의 시간을 반환한다.
    *   **응답 예시**:
        ```json
        {
          "currentTime": "2026. 3. 25. 오전 9:30:00"
        }
        ```
