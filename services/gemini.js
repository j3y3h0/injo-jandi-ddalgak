import path from "path";
import { spawn } from "child_process";
import logger from "../logger.js";
import { getDateParts } from "../utils/date.js";
import lodash from "lodash";

const LANGUAGES = ["C#", "Python", "Javascript", "Java"];

/** Gemini CLI 최대 실행 시간(ms). auto-project용 기본. */
const DEFAULT_GEMINI_TIMEOUT_MS = 120000;

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
4. 모든 .md 문서와 코드 주석은 한글로 작성해줘.

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

  const raw = process.env.CODE_DIARY_TIMEOUT_MS?.trim();
  const timeoutMs =
    raw !== undefined && raw !== ""
      ? Number(raw)
      : null;
  const useTimeout = typeof timeoutMs === "number" && timeoutMs > 0;

  return new Promise((resolve, reject) => {
    let settled = false;
    let timeoutId = null;
    const finish =
      (fn) =>
      (...args) => {
        if (settled) return;
        settled = true;
        if (timeoutId) clearTimeout(timeoutId);
        fn(...args);
      };

    const args = ["--output-format", "json", "--yolo"];
    const isWin = process.platform === "win32";
    const childEnv = { ...process.env, GEMINI_SANDBOX: "false" };
    const child = spawn(geminiBin, args, {
      stdio: ["pipe", "pipe", "pipe"],
      shell: isWin,
      windowsHide: true,
      cwd: saveDirAbsolute,
      env: childEnv,
    });
    logger.info("Gemini 프로세스 생성됨", {
      codeDiaryTimeout: useTimeout ? timeoutMs : "없음",
    });

    if (useTimeout) {
      timeoutId = setTimeout(() => {
        if (settled) return;
        settled = true;
        child.kill("SIGTERM");
        logger.warn(
          `generateCodeWithGemini: ${timeoutMs}ms 타임아웃, 프로세스 종료`
        );
        reject(
          new Error(`Gemini CLI timeout (${timeoutMs}ms). CODE_DIARY_TIMEOUT_MS 조정 가능.`)
        );
      }, timeoutMs);
    }

    let stdout = "";
    let stderr = "";
    child.stdout.setEncoding("utf-8").on("data", (chunk) => (stdout += chunk));
    child.stderr.setEncoding("utf-8").on("data", (chunk) => (stderr += chunk));

    child.on("error", (err) => {
      finish(() => {
        logger.error(`generateCodeWithGemini: spawn error - ${err.message}`);
        console.error("Error spawning Gemini CLI:", err);
        reject(err);
      })();
    });
    child.on("close", (code) => {
      if (settled) return;
      logger.info(
        `Gemini 프로세스 종료: code=${code}, stdout길이=${stdout.length}, stderr길이=${stderr.length}`
      );
      if (stderr) logger.info(`Gemini CLI stderr: ${stderr}`);
      try {
        const out = JSON.parse(stdout);
        if (out.error) {
          finish(() => {
            const msg = out.error.message || "Gemini CLI error";
            logger.error(`generateCodeWithGemini: response error - ${msg}`);
            reject(new Error(msg));
          })();
          return;
        }
        const response = out.response ?? stdout;
        logger.info(`Gemini 응답 성공, 응답 길이=${response?.length ?? 0}`);
        finish(() => resolve({ response, saveDir: saveDirAbsolute }))();
      } catch (parseErr) {
        if (code !== 0) {
          finish(() => {
            const msg = `Gemini CLI exited ${code}. ${stderr || stdout}`;
            logger.error(`generateCodeWithGemini: ${msg}`);
            reject(new Error(msg));
          })();
          return;
        }
        logger.info("Gemini stdout가 JSON 아님, 원문 stdout 사용");
        finish(() =>
          resolve({ response: stdout.trim(), saveDir: saveDirAbsolute })
        )();
      }
    });

    child.stdin.write(prompt, "utf-8", (err) => {
      if (err) {
        finish(() => {
          logger.error(
            `generateCodeWithGemini: stdin write error - ${err.message}`
          );
          reject(err);
        })();
      } else {
        child.stdin.end();
        logger.info("Gemini stdin 전송 및 종료됨");
      }
    });
  });
}

/** 프로젝트 시딩용 프롬프트. PROJECT.md 없을 때 첫 실행에 사용. */
export function buildSeedProjectPrompt(targetDir) {
  return `작업 디렉터리: ${targetDir}

**할 일:** 이 디렉터리에 실행 가능한 소규모 프로젝트를 시딩해줘.
1. PROJECT.md 한 개를 만들어줘. 다음 구조를 포함해줘: 목표, 완료된 작업, 다음 할 일, 기술 스택.
2. README.md와 메인 진입점 코드(2~5개 파일)를 이 디렉터리에 생성해줘. 언어는 하나만 사용해줘.
3. 모든 .md 문서와 코드 주석은 한글로 작성해줘.
4. 모든 파일은 위 작업 디렉터리 안에만 생성해줘.`;
}

/** 프로젝트 개선용 프롬프트 문자열만 생성. (SRP: 문구 책임) newsContent 있으면 사회 이슈 반영 지시. */
export function buildImproveProjectPrompt(
  projectMdContent,
  targetDir,
  optionalFileList = [],
  optionalNewsContent = ""
) {
  const fileListText =
    optionalFileList.length > 0
      ? `\n현재 디렉터리 파일 목록:\n${optionalFileList.join("\n")}\n`
      : "";
  const newsBlock =
    optionalNewsContent.trim().length > 0
      ? `
**최신 뉴스 (아래 중 하나의 사회 이슈를 골라 이번 개선에 반영해줘):**
${optionalNewsContent.trim()}
위 뉴스 중 하나를 골라, 그 주제/사회 이슈를 프로젝트에 재밌게 반영하면서 다음 한 단계를 이어가줘. (기능 추가, 예시 데이터, 주석, README 스토리 등 자유롭게)
`
      : "";
  return `작업 디렉터리: ${targetDir}
${fileListText}
아래는 이 프로젝트의 PROJECT.md 내용이다.

---
${projectMdContent}
---
${newsBlock}
**할 일:** 위 PROJECT.md를 보고 "다음 한 단계"만 수행해줘.
1. 코드·문서를 수정하거나 새 파일을 추가해도 된다. 모두 위 작업 디렉터리 안에만 생성해줘.
2. 진행한 작업을 반영해 PROJECT.md를 반드시 갱신해줘. (목표, 완료된 작업, 다음 할 일, 기술 스택, 이번에 반영한 이슈 등)
3. 모든 .md 문서와 코드 주석은 한글로 작성해줘.
한 번에 한 단계만 하고, PROJECT.md를 업데이트한 뒤 끝내줘.`;
}

/** 지정 디렉터리에서 prompt로 Gemini CLI 실행. cwd=targetDir, stdin=prompt. 타임아웃 초과 시 프로세스 종료. */
export function runGeminiInDir(targetDir, prompt) {
  const targetDirAbsolute = path.resolve(targetDir);
  logger.info(`runGeminiInDir: targetDir=${targetDirAbsolute}`);
  const geminiBin = path.join(
    process.cwd(),
    "node_modules",
    ".bin",
    process.platform === "win32" ? "gemini.cmd" : "gemini"
  );
  const timeoutMs =
    Number(process.env.GEMINI_TIMEOUT_MS) || DEFAULT_GEMINI_TIMEOUT_MS;

  return new Promise((resolve, reject) => {
    let settled = false;
    const finish =
      (fn) =>
      (...args) => {
        if (settled) return;
        settled = true;
        if (timeoutId) clearTimeout(timeoutId);
        fn(...args);
      };

    const args = ["--output-format", "json", "--yolo"];
    const isWin = process.platform === "win32";
    const childEnv = { ...process.env, GEMINI_SANDBOX: "false" };
    const child = spawn(geminiBin, args, {
      stdio: ["pipe", "pipe", "pipe"],
      shell: isWin,
      windowsHide: true,
      cwd: targetDirAbsolute,
      env: childEnv,
    });

    const timeoutId = setTimeout(() => {
      if (settled) return;
      settled = true;
      child.kill("SIGTERM");
      logger.warn(`runGeminiInDir: ${timeoutMs}ms 타임아웃, 프로세스 종료`);
      reject(
        new Error(
          `Gemini CLI timeout (${timeoutMs}ms). 한 번에 한 단계만 수행하도록 GEMINI_TIMEOUT_MS 조정 가능.`
        )
      );
    }, timeoutMs);

    let stdout = "";
    let stderr = "";
    child.stdout.setEncoding("utf-8").on("data", (chunk) => (stdout += chunk));
    child.stderr.setEncoding("utf-8").on("data", (chunk) => (stderr += chunk));
    child.on("error", (err) => {
      finish(() => {
        logger.error(`runGeminiInDir: spawn error - ${err.message}`);
        reject(err);
      })();
    });
    child.on("close", (code) => {
      if (settled) return;
      logger.info(
        `runGeminiInDir 종료: code=${code}, stdout길이=${stdout.length}`
      );
      try {
        const out = JSON.parse(stdout);
        if (out.error) {
          finish(() =>
            reject(new Error(out.error.message || "Gemini CLI error"))
          )();
          return;
        }
        finish(() =>
          resolve({
            response: out.response ?? stdout,
            saveDir: targetDirAbsolute,
          })
        )();
      } catch {
        if (code !== 0) {
          finish(() =>
            reject(new Error(`Gemini CLI exited ${code}. ${stderr || stdout}`))
          )();
          return;
        }
        finish(() =>
          resolve({ response: stdout.trim(), saveDir: targetDirAbsolute })
        )();
      }
    });
    child.stdin.write(prompt, "utf-8", (err) => {
      if (err) finish(() => reject(err))();
      else child.stdin.end();
    });
  });
}
