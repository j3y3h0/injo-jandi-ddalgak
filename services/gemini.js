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

  return `You are a programming code generator that creates useful and straightforward code examples daily.

**Today's date (작성일):** ${dateLabel}

**Latest tech news (아래 뉴스 중 하나를 골라 해당 주제와 연관된 샘플 코드를 작성해 주세요):**

${newsContent}

**Requirements:**
1. Use one of these languages: ${randomLang}.
2. The code must be practical, demonstrate a specific functionality, and use libraries or algorithms commonly applied in the industry.
3. Write all explanatory content in Korean.
4. Format output in Markdown. Omit the initial \`\`\`markdown and ending \`\`\`.
5. Use formal Korean (e.g. "~이다.").

Generate the daily code diary entry now.`;
}

/** Gemini CLI를 실행해 프롬프트에 대한 응답 텍스트 반환. CLI 호출만 담당. */
export function generateCodeWithGemini(newsContent) {
  const prompt = buildCodeDiaryPrompt(newsContent);
  const geminiBin = path.join(
    process.cwd(),
    "node_modules",
    ".bin",
    process.platform === "win32" ? "gemini.cmd" : "gemini"
  );

  return new Promise((resolve, reject) => {
    const args = ["--output-format", "json"];
    const child = spawn(geminiBin, args, {
      stdio: ["pipe", "pipe", "pipe"],
      shell: false,
      windowsHide: true,
    });

    let stdout = "";
    let stderr = "";
    child.stdout.setEncoding("utf-8").on("data", (chunk) => (stdout += chunk));
    child.stderr.setEncoding("utf-8").on("data", (chunk) => (stderr += chunk));

    child.on("error", (err) => {
      console.error("Error spawning Gemini CLI:", err);
      reject(err);
    });
    child.on("close", (code) => {
      if (stderr) logger.info(`Gemini CLI stderr: ${stderr}`);
      try {
        const out = JSON.parse(stdout);
        if (out.error) {
          reject(new Error(out.error.message || "Gemini CLI error"));
          return;
        }
        resolve(out.response ?? stdout);
      } catch {
        if (code !== 0) {
          reject(new Error(`Gemini CLI exited ${code}. ${stderr || stdout}`));
          return;
        }
        resolve(stdout.trim());
      }
    });

    child.stdin.write(prompt, "utf-8", (err) => {
      if (err) reject(err);
      else child.stdin.end();
    });
  });
}
