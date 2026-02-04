# llm-auto-code

MySQL에 저장된 최신 뉴스를 읽어와 **Gemini CLI**로 일자별 코드 다이어리를 자동 생성하고, 지정 경로에 코드·README를 쓴 뒤 GitHub에 커밋·푸시하는 Node.js 자동화 프로젝트이다.

## 준비물

- **Node.js** v20 이상
- **MySQL**: 뉴스 데이터가 들어 있는 DB (Prisma로 연결)
- **Gemini CLI** 인증: `npx gemini` 한 번 실행 후 로그인 또는 `GEMINI_API_KEY` 설정
- **push.bat**: Windows에서 Git 커밋·푸시용 (운영 시 사용)
- 24시간 구동 가능한 PC 또는 클라우드 서버 (스케줄 실행용)

## 환경변수 (`.env`)

`.env.template`을 참고해 `.env`를 만들고 아래 값을 채운다.

| 변수             | 설명                                                                                    |
| ---------------- | --------------------------------------------------------------------------------------- |
| `IS_TEST`        | `true`: 개발 모드(시작 시 1회 실행, git push 생략) / `false`: 운영(스케줄만, push 실행) |
| `DATABASE_URL`   | MySQL 연결 문자열. 예: `mysql://USER:PW@HOST:PORT/DB_NAME`                              |
| `OPENAI_API_KEY` | (선택) OpenAI 사용 시에만 필요. 기본 흐름은 Gemini CLI 사용                             |

## 기능

1. **뉴스 조회**: MySQL `news_main`에서 최신 뉴스 N건을 가져온다.
2. **코드 다이어리 생성**: Gemini CLI를 해당 일자 경로(`codes/YYYYMM/DD`)에서 실행해, 뉴스 기반 소규모 프로젝트(코드 파일 + README.md)를 그 경로에 직접 생성한다.
3. **Git 푸시**: `IS_TEST`가 아니면 `push.bat`으로 커밋·푸시한다.
4. **스케줄**: 매일 09:30에 `saveRandomCodeDiary`가 실행된다.

## 디렉터리 구조 (요약)

```
├── index.js           # 엔트리: 스케줄 등록, IS_TEST 시 1회 실행
├── jobs/
│   ├── saveRandomCodeDiary.js  # 뉴스 → Gemini → push 오케스트레이션
│   └── checkNews.js            # DB 뉴스 조회만 실행 (수동)
├── services/
│   ├── gemini.js      # Gemini CLI 호출, 프롬프트·저장 경로 지정
│   ├── newsDb.js      # MySQL 뉴스 SELECT
│   ├── news.js        # 뉴스 포맷
│   ├── file.js        # 저장 디렉터리 경로 (getSaveDir)
│   └── batch.js       # push.bat 실행
├── config/            # OpenAI 설정 (선택)
├── lib/prisma.js      # Prisma 클라이언트
├── prisma/schema.prisma
└── codes/YYYYMM/DD/  # 일자별 생성 결과 (Gemini가 직접 생성)
```

## 명령어

```bash
# 저장소 클론
git clone https://github.com/j3y3h0/llm-auto-code.git
cd llm-auto-code

# 패키지 설치
npm install

# Prisma 클라이언트 생성
npm run db:generate

# 개발 모드 (IS_TEST=true 권장): 시작 시 1회 실행, push 없음
npm run dev

# 운영: 스케줄만 동작 (IS_TEST=false 또는 미설정 시)
npm start

# DB 뉴스만 확인 (수동)
node jobs/checkNews.js

# DB 마이그레이션 / Studio (필요 시)
npm run db:migrate
npm run db:studio

# Gemini CLI 전역 설치
npm install -g @google/gemini-cli

# 설치 없이 일회성으로 바로 실행해보고 싶다면 npx를 사용
npx @google/gemini-cli
```

## 백그라운드 실행 (운영)

```bash
npm install -g forever
# .env 에 IS_TEST=false 설정 후
forever start index.js
forever list
forever stop index.js
```
