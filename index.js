import dotenv from "dotenv";
import schedule from "node-schedule";
import { saveRandomCodeDiary } from "./jobs/saveRandomCodeDiary.js";

dotenv.config();

schedule.scheduleJob(
  "saveRandomCodeDiary",
  "0 30 9 * * *",
  saveRandomCodeDiary
);

const isDev = process.env.NODE_ENV === "development";
if (isDev) {
  saveRandomCodeDiary().then(() => {
    console.log("saveRandomCodeDiary (dev) 1회 실행 완료");
  });
}

console.log("saveRandomCodeDiary started");
