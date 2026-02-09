// dataGenerator.js

/**
 * 시뮬레이션된 센서 데이터를 생성하는 함수입니다.
 * 온도, 습도, 압력 데이터를 포함합니다.
 *
 * @returns {object} 센서 데이터 객체
 */
function generateSensorData() {
    // 실제 환경에서는 센서로부터 데이터를 읽어오지만, 여기서는 임의의 값을 생성합니다.
    const temperature = 20 + Math.random() * 10 - 5; // 15 ~ 25 범위의 온도
    const humidity = 40 + Math.random() * 20 - 10;   // 30 ~ 50 범위의 습도
    const pressure = 1000 + Math.random() * 10 - 5;  // 995 ~ 1005 범위의 압력

    return {
        timestamp: new Date(),
        temperature: temperature,
        humidity: humidity,
        pressure: pressure
    };
}

// 모듈 외부로 함수를 노출합니다.
module.exports = {
    generateSensorData
};
