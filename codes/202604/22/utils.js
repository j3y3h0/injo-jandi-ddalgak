// utils.js
// 유틸리티 함수들을 모아둔 모듈입니다.
const fs = require('fs');
const path = require('path');

/**
 * 데이터를 JSON 형식으로 파일에 저장합니다.
 * @param {string} filename - 저장할 파일의 이름.
 * @param {Array<object>} data - 저장할 데이터.
 * @returns {Promise<void>}
 */
async function saveDataToFile(filename, data) {
  const filePath = path.join(__dirname, filename);
  try {
    await fs.promises.writeFile(filePath, JSON.stringify(data, null, 2), 'utf8');
    console.log(`[유틸리티] 데이터가 ${filePath} 에 성공적으로 저장되었습니다.`);
  } catch (error) {
    console.error(`[유틸리티 오류] 파일 저장 실패 (${filePath}):`, error.message);
    throw error;
  }
}

module.exports = {
  saveDataToFile
};
