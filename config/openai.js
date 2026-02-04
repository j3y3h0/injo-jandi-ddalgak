import dotenv from "dotenv";
import OpenAIApi from "openai";

dotenv.config();

/** OpenAI 클라이언트 생성만 담당. LLM 교체 시 이 파일만 수정하면 됨. */
export function createOpenAIClient(apiKey) {
  return new OpenAIApi({ apiKey });
}

export const openai = createOpenAIClient(process.env.OPENAI_API_KEY);
