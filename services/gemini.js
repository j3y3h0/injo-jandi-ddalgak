import path from "path";
import { spawn } from "child_process";
import logger from "../logger.js";
import { getDateParts } from "../utils/date.js";
import lodash from "lodash";

const LANGUAGES = ["C#", "Python", "Javascript", "Java"];

/** 특정 일자 + 뉴스 기반 코드 다이어리용 프롬프트 생성. 단일 책임: 프롬프트 문구. */
export function buildCodeDiaryPrompt(newsContent, saveDirAbsolute) {
  const { year, month, day } = getDateParts();
  const dateLabel = `${year}-${month}-${day}`;
  const randomLang = LANGUAGES[lodash.random(0, LANGUAGES.length - 1)];
  logger.info(`프롬프트 생성: 일자=${dateLabel}, 언어=${randomLang}`);

  return `오늘 날짜: ${dateLabel}.

아래 뉴스 중 하나를 골라, 해당 주제와 연관된 소규모 실행 가능한 프로젝트를 **현재 작업 디렉터리**에 만들어줘.
현재 작업 디렉터리 경로: ${saveDirAbsolute}

**할 일:**
1. 이 디렉터리에 코드 파일들(main, util 등 2~5개)을 직접 생성해줘. 사용 언어: ${randomLang}만.
2. 코드 구조 설명과 실행 방법이 담긴 README.md 한 개를 이 디렉터리에 생성해줘. (한글, 문어체 "~이다.")
3. 실용적이고 실행 가능한 코드로, 산업에서 쓰는 라이브러리·알고리즘을 활용해줘.

파일은 모두 위 경로(현재 디렉터리)에만 생성해줘.`;
}

/** Gemini CLI를 saveDir에서 실행해 해당 경로에 파일 생성. CLI 호출만 담당. */
export function generateCodeWithGemini(newsContent, saveDir) {
  logger.info("Gemini 코드 생성 시작");
  const saveDirAbsolute = path.resolve(saveDir);
  const prompt = buildCodeDiaryPrompt(newsContent, saveDirAbsolute);
  const geminiBin = path.join(
    process.cwd(),
    "node_modules",
    ".bin",
    process.platform === "win32" ? "gemini.cmd" : "gemini"
  );
  logger.info(
    `Gemini 실행 경로=${geminiBin}, 작업 디렉터리=${saveDirAbsolute}`
  );

  return new Promise((resolve, reject) => {
    const args = ["--output-format", "json", "--yolo"]; // --yolo: 코드 생성 시 최적화 옵션
    const child = spawn(geminiBin, args, {
      stdio: ["pipe", "pipe", "pipe"],
      shell: false,
      windowsHide: true,
      cwd: saveDirAbsolute,
    });
    logger.info("Gemini 프로세스 생성됨");

    let stdout = "";
    let stderr = "";
    child.stdout.setEncoding("utf-8").on("data", (chunk) => (stdout += chunk));
    child.stderr.setEncoding("utf-8").on("data", (chunk) => (stderr += chunk));

    child.on("error", (err) => {
      logger.error(`generateCodeWithGemini: spawn error - ${err.message}`);
      console.error("Error spawning Gemini CLI:", err);
      reject(err);
    });
    child.on("close", (code) => {
      logger.info(
        `Gemini 프로세스 종료: code=${code}, stdout길이=${stdout.length}, stderr길이=${stderr.length}`
      );
      if (stderr) logger.info(`Gemini CLI stderr: ${stderr}`);
      try {
        const out = JSON.parse(stdout);
        if (out.error) {
          const msg = out.error.message || "Gemini CLI error";
          logger.error(`generateCodeWithGemini: response error - ${msg}`);
          reject(new Error(msg));
          return;
        }
        const response = out.response ?? stdout;
        logger.info(`Gemini 응답 성공, 응답 길이=${response?.length ?? 0}`);
        resolve({ response, saveDir: saveDirAbsolute });
      } catch (parseErr) {
        if (code !== 0) {
          const msg = `Gemini CLI exited ${code}. ${stderr || stdout}`;
          logger.error(`generateCodeWithGemini: ${msg}`);
          reject(new Error(msg));
          return;
        }
        logger.info("Gemini stdout가 JSON 아님, 원문 stdout 사용");
        resolve({ response: stdout.trim(), saveDir: saveDirAbsolute });
      }
    });

    child.stdin.write(prompt, "utf-8", (err) => {
      if (err) {
        logger.error(
          `generateCodeWithGemini: stdin write error - ${err.message}`
        );
        reject(err);
      } else {
        child.stdin.end();
        logger.info("Gemini stdin 전송 및 종료됨");
      }
    });
  });
}
