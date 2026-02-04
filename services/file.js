import path from "path";
import fs from "fs";
import { getDateParts } from "../utils/date.js";

/** 저장 디렉터리 생성 및 README.md 경로 반환. 경로 규칙 변경 시 이 파일만 수정. */
export function createSavePath() {
  const { year, month, day } = getDateParts();
  const saveDir = `${process.cwd()}/codes/${year}${month}/${day}`;
  if (!fs.existsSync(saveDir)) {
    fs.mkdirSync(saveDir, { recursive: true });
  }
  return path.join(saveDir, "README.md");
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
