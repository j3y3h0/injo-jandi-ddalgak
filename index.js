import dotenv from "dotenv";
import schedule from "node-schedule";
import { saveRandomCodeDiary } from "./jobs/saveRandomCodeDiary.js";
import { improveProjectWithGemini } from "./jobs/improveProjectWithGemini.js";

dotenv.config();

// 환경변수 확인 함수
const isEnabled = (key, defaultValue = true) => {
  const v = process.env[key]?.trim().toLowerCase();
  if (v === "" || v === undefined) return defaultValue;
  return v === "true" || v === "1";
};

// 코드 다이어리 활성화 여부
const enableCodeDiary = isEnabled("ENABLE_CODE_DIARY");
// 프로젝트 자동 개선 활성화 여부
const enableAutoProject = isEnabled("ENABLE_AUTO_PROJECT");

// 코드 다이어리 스케줄 등록
if (enableCodeDiary) {
  const codeDiaryCron = process.env.CODE_DIARY_CRON?.trim() || "0 30 9 * * *";
  schedule.scheduleJob(
    "saveRandomCodeDiary",
    codeDiaryCron,
    saveRandomCodeDiary,
  );
  console.log("saveRandomCodeDiary started", { cron: codeDiaryCron });
}

// 프로젝트 자동 개선 스케줄 등록
if (enableAutoProject) {
  const cronSchedule = process.env.CRON_SCHEDULE?.trim() || "0 */10 * * * *";
  schedule.scheduleJob(
    "improveProjectWithGemini",
    cronSchedule,
    improveProjectWithGemini,
  );
  console.log("improveProjectWithGemini started", { cronSchedule });
}

// 개발 모드 시 1회 실행
const isTest = process.env.IS_TEST === "true";
if (isTest) {
  // 코드 다이어리 1회 실행
  saveRandomCodeDiary().then(() => {
    console.log("saveRandomCodeDiary 시작 시 1회 실행 완료");
  });

  // 프로젝트 자동 개선 1회 실행
  if (enableAutoProject) {
    const delayMs = Number(process.env.GEMINI_STARTUP_DELAY_MS) || 60000;
    setTimeout(() => {
      improveProjectWithGemini().then(() => {
        console.log("improveProjectWithGemini (IS_TEST) 1회 실행 완료");
      });
    }, delayMs);
  }
}

// 코드 다이어리와 프로젝트 자동 개선 모두 비활성 시 경고 메시지
if (!enableCodeDiary && !enableAutoProject) {
  console.log(
    "ENABLE_CODE_DIARY, ENABLE_AUTO_PROJECT 둘 다 비활성. 아무 job도 등록되지 않음.",
  );
}
