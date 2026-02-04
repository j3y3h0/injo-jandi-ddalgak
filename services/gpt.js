import lodash from "lodash";
import logger from "../logger.js";

/** GPT 호출만 담당. openai 클라이언트를 인자로 받아 의존성 주입. 모델/프롬프트 변경 시 이 파일만 수정. */
export async function generateCodeWithGpt(openaiClient, newsContent) {
  const languages = ["C#", "Python", "Javascript", "Java"];
  const randomLang = languages[lodash.random(0, languages.length - 1)];
  const ORDER = `
  You are a programming code generator that creates useful and straightforward code examples daily. Based on the latest news, choose one topic and write a sample code related to it, focusing on relevant libraries or algorithms.
  
  ${newsContent}
  
  1. Randomly select one of the following programming languages: ${randomLang}.  
  2. The code must be practical, demonstrate a specific functionality, and use libraries or algorithms commonly applied in the industry.  
  3. Write the following content in Korean
  4. Ensure all outputs are formatted precisely according to Markdown syntax.  
  5. Omit the initial \`\`\`markdown and ending \`\`\` outputs.  
  6. Use formal Korean writing, including expressions like "~이다."  
  `;

  try {
    const response = await openaiClient.chat.completions.create({
      messages: [{ role: "user", content: ORDER }],
      model: "gpt-4o-mini",
      temperature: 0.7,
    });

    logger.info(`GPT 코드 생성 응답: ${JSON.stringify(response, null, 2)}`);
    return response.choices[0].message.content;
  } catch (error) {
    console.error("Error generating code with GPT:", error);
    throw error;
  }
}
