import path from "path";
import fs from "fs";
import { getDateParts } from "../utils/date.js";

const DEFAULT_TARGET_PROJECT_DIR = "codes/auto-project";

/** 자동 개선 대상 프로젝트 디렉터리 반환 및 생성. TARGET_PROJECT_DIR 또는 기본값 사용. */
export function getTargetProjectDir() {
  const raw =
    process.env.TARGET_PROJECT_DIR?.trim() || DEFAULT_TARGET_PROJECT_DIR;
  const resolved = path.isAbsolute(raw) ? raw : path.join(process.cwd(), raw);
  if (!fs.existsSync(resolved)) {
    fs.mkdirSync(resolved, { recursive: true });
  }
  return resolved;
}

/** 저장 디렉터리 경로 반환 및 생성. 경로 규칙 변경 시 이 파일만 수정. */
export function getSaveDir() {
  const { year, month, day } = getDateParts();
  const saveDir = path.join(process.cwd(), "codes", year + month, day);
  if (!fs.existsSync(saveDir)) {
    fs.mkdirSync(saveDir, { recursive: true });
  }
  return saveDir;
}

/** 저장 디렉터리 생성 및 README.md 경로 반환. (하위 호환) */
export function createSavePath() {
  return path.join(getSaveDir(), "README.md");
}

/** 내용을 지정 경로에 동기 저장. 파일 I/O만 담당. */
export function saveToFile(filePath, content) {
  try {
    fs.writeFileSync(filePath, content, "utf-8");
  } catch (error) {
    console.error("Error saving file:", error);
    throw error;
  }
}

/** Gemini 응답에서 ## path + ``` block 형태로 파싱해 saveDir 아래에 다중 파일 저장. 파싱 실패 시 null 반환. */
export function saveCodeFilesFromResponse(saveDir, response) {
  const sections = response.split(/\n##\s+/);
  const written = [];
  for (const section of sections) {
    const firstLineEnd = section.indexOf("\n");
    let filePath =
      firstLineEnd >= 0
        ? section.slice(0, firstLineEnd).trim()
        : section.trim();
    filePath = filePath.replace(/^#\s*/, "").trim();
    if (!filePath || filePath.startsWith("`")) continue;
    const codeMatch = section.match(/```\w*\n([\s\S]*?)```/);
    if (!codeMatch) continue;
    const fullPath = path.join(saveDir, filePath);
    const dir = path.dirname(fullPath);
    if (!fs.existsSync(dir)) fs.mkdirSync(dir, { recursive: true });
    fs.writeFileSync(fullPath, codeMatch[1].trimEnd(), "utf-8");
    written.push(filePath);
  }
  return written.length > 0 ? written : null;
}
