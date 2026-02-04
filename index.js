import dotenv from "dotenv";
import schedule from "node-schedule";
import { saveRandomCodeDiary } from "./jobs/saveRandomCodeDiary.js";
import { improveProjectWithGemini } from "./jobs/improveProjectWithGemini.js";

dotenv.config();

const isEnabled = (key, defaultValue = true) => {
  const v = process.env[key]?.trim().toLowerCase();
  if (v === "" || v === undefined) return defaultValue;
  return v === "true" || v === "1";
};

const enableCodeDiary = isEnabled("ENABLE_CODE_DIARY");
const enableAutoProject = isEnabled("ENABLE_AUTO_PROJECT");

if (enableCodeDiary) {
  const codeDiaryCron = process.env.CODE_DIARY_CRON?.trim() || "0 30 9 * * *";
  schedule.scheduleJob(
    "saveRandomCodeDiary",
    codeDiaryCron,
    saveRandomCodeDiary
  );
  console.log("saveRandomCodeDiary started", { cron: codeDiaryCron });
}

if (enableAutoProject) {
  const cronSchedule = process.env.CRON_SCHEDULE?.trim() || "0 */10 * * * *";
  schedule.scheduleJob(
    "improveProjectWithGemini",
    cronSchedule,
    improveProjectWithGemini
  );
  console.log("improveProjectWithGemini started", { cronSchedule });
}

const isTest = process.env.IS_TEST === "true";
if (isTest) {
  if (enableCodeDiary) {
    saveRandomCodeDiary().then(() => {
      console.log("saveRandomCodeDiary (IS_TEST) 1회 실행 완료");
    });
  }
  if (enableAutoProject) {
    const delayMs = Number(process.env.GEMINI_STARTUP_DELAY_MS) || 60000;
    setTimeout(() => {
      improveProjectWithGemini().then(() => {
        console.log("improveProjectWithGemini (IS_TEST) 1회 실행 완료");
      });
    }, delayMs);
  }
}

if (!enableCodeDiary && !enableAutoProject) {
  console.log(
    "ENABLE_CODE_DIARY, ENABLE_AUTO_PROJECT 둘 다 비활성. 아무 job도 등록되지 않음."
  );
}
