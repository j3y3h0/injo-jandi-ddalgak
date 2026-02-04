import dotenv from "dotenv";
import schedule from "node-schedule";
import { saveRandomCodeDiary } from "./jobs/saveRandomCodeDiary.js";

dotenv.config();

schedule.scheduleJob(
  "saveRandomCodeDiary",
  "0 30 9 * * *",
  saveRandomCodeDiary
);

const isTest = process.env.IS_TEST === "true";
if (isTest) {
  saveRandomCodeDiary().then(() => {
    console.log("saveRandomCodeDiary (IS_TEST) 1회 실행 완료");
  });
}

console.log("saveRandomCodeDiary started");
