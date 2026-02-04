import path from "path";
import { spawn } from "child_process";
import logger from "../logger.js";
import { getDateParts } from "../utils/date.js";
import lodash from "lodash";

const LANGUAGES = ["C#", "Python", "Javascript", "Java"];

/** 특정 일자 + 뉴스 기반 코드 다이어리용 프롬프트 생성. 단일 책임: 프롬프트 문구. */
export function buildCodeDiaryPrompt(newsContent) {
  const { year, month, day } = getDateParts();
  const dateLabel = `${year}-${month}-${day}`;
  const randomLang = LANGUAGES[lodash.random(0, LANGUAGES.length - 1)];
  logger.info(`프롬프트 생성: 일자=${dateLabel}, 언어=${randomLang}`);

  return `You are a programming code generator. For **today (${dateLabel})**, you must **directly design and write multiple code files** that form a small, runnable project. Do not only describe—output the actual file contents.

**Today's date (작성일):** ${dateLabel}

**Latest tech news (아래 뉴스 중 하나를 골라, 해당 주제와 연관된 기능을 구현하는 프로젝트를 설계·작성하세요):**

${newsContent}

**Your task:**
1. **Design** a minimal project (2~5 files): e.g. main/entry file, util/helper file, config or test if needed, and **exactly one README.md**.
2. **Use only this language:** ${randomLang}.
3. Code must be practical, runnable, and use common libraries or algorithms.
4. **README.md** must be a single .md file containing (in Korean, formal "~이다."):
   - **코드 구조 설명**: 프로젝트 디렉터리/파일 구성, 각 파일의 역할과 관계를 설명.
   - **실행 방법**: 실행에 필요한 환경·명령어·순서를 설명.
   Do NOT output a separate file named "실행 방법" or "4. 실행 방법"; only README.md.
5. **Output format (strict):** For each file, write exactly two lines then the code block:
   - Line 1: \`## \` followed by the file path (e.g. \`## main.py\`, \`## utils/helper.js\`, \`## README.md\`).
   - Line 2: \`\`\`lang (e.g. \`\`\`python or \`\`\`javascript).
   - Next lines: full file content.
   - Then close with \`\`\`.
   Example:
   \`\`\`
   ## main.py
   \`\`\`python
   # content here
   \`\`\`
   ## README.md
   \`\`\`markdown
   # 프로젝트명
   ## 코드 구조 설명
   ...
   ## 실행 방법
   ...
   \`\`\`
   \`\`\`
6. Do not wrap the whole output in \`\`\`markdown.

Design and write the daily code files now.`;
}

/** Gemini CLI를 실행해 프롬프트에 대한 응답 텍스트 반환. CLI 호출만 담당. */
export function generateCodeWithGemini(newsContent) {
  logger.info("Gemini 코드 생성 시작");
  const prompt = buildCodeDiaryPrompt(newsContent);
  const geminiBin = path.join(
    process.cwd(),
    "node_modules",
    ".bin",
    process.platform === "win32" ? "gemini.cmd" : "gemini"
  );
  logger.info(`Gemini 실행 경로=${geminiBin}, 프롬프트 길이=${prompt.length}`);

  return new Promise((resolve, reject) => {
    const args = ["--output-format", "json"];
    const child = spawn(geminiBin, args, {
      stdio: ["pipe", "pipe", "pipe"],
      shell: false,
      windowsHide: true,
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
        resolve(response);
      } catch (parseErr) {
        if (code !== 0) {
          const msg = `Gemini CLI exited ${code}. ${stderr || stdout}`;
          logger.error(`generateCodeWithGemini: ${msg}`);
          reject(new Error(msg));
          return;
        }
        logger.info("Gemini stdout가 JSON 아님, 원문 stdout 사용");
        resolve(stdout.trim());
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
