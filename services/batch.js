import { exec } from "child_process";
import logger from "../logger.js";

/** 배치 스크립트 실행만 담당. 실행 방식 변경 시 이 파일만 수정. */
export function runBatchScript(batchFilePath) {
  exec(batchFilePath, (error, stdout, stderr) => {
    if (error) {
      logger.info(`배치 스크립트 오류: ${error}`);
      console.error(`Batch script error: ${error}`);
      return;
    }
    if (stderr) {
      logger.info(`배치 스크립트 stderr: ${stderr}`);
      console.error(`Batch script stderr: ${stderr}`);
      return;
    }
    logger.info(`배치 스크립트 stdout: ${stdout}`);
    console.log(`Batch script stdout: ${stdout}`);
  });
}
