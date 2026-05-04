// main.js
// 챗봇 애플리케이션의 메인 진입점이다.
// 사용자로부터 입력을 받고, 챗봇 로직을 호출하여 응답을 출력한다.

const readline = require('readline-sync');
const { getBotResponse } = require('./chatbot');

/**
 * 챗봇 애플리케이션을 시작한다.
 */
function startChatbot() {
  console.log("안녕하세요! 간단한 챗봇입니다. '종료'를 입력하면 챗봇이 종료됩니다.");

  while (true) {
    const userInput = readline.question("당신: ");

    if (userInput.toLowerCase() === '종료') {
      console.log(getBotResponse(userInput)); // 종료 메시지 출력
      break;
    }

    const botResponse = getBotResponse(userInput);
    console.log(`챗봇: ${botResponse}`);
  }
}

// 챗봇 애플리케이션을 시작한다.
startChatbot();
