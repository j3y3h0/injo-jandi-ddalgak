// chatbot.js
// 챗봇의 핵심 로직을 구현한다.

const chatbotData = require('./data');

/**
 * 사용자 입력에 따라 챗봇의 응답을 반환한다.
 * @param {string} userInput - 사용자 입력 문자열이다.
 * @returns {string} - 챗봇의 응답 문자열이다.
 */
function getBotResponse(userInput) {
  for (const item of chatbotData) {
    if (item.pattern.test(userInput)) {
      const randomIndex = Math.floor(Math.random() * item.responses.length);
      return item.responses[randomIndex];
    }
  }
  // 일치하는 패턴이 없을 경우 기본 응답을 반환한다.
  const defaultResponses = chatbotData.find(item => item.pattern.toString().includes('^(?!.*')).responses;
  const randomIndex = Math.floor(Math.random() * defaultResponses.length);
  return defaultResponses[randomIndex];
}

module.exports = {
  getBotResponse
};
