// main.js

// 필요한 모듈들을 불러옵니다.
const { generateSensorData } = require('./dataGenerator');
const { calculateMovingAverage } = require('./dataProcessor');
const { detectAnomaly } = require('./anomalyDetector');

// 시뮬레이션 설정
const SIMULATION_INTERVAL_MS = 1000; // 1초마다 데이터 생성 및 처리
const MOVING_AVERAGE_WINDOW_SIZE = 5; // 이동 평균 계산을 위한 윈도우 크기
const ANOMALY_THRESHOLD = 10; // 이상 징후 탐지를 위한 임계값 (이동 평균과의 차이)

let sensorDataHistory = []; // 과거 센서 데이터를 저장할 배열

/**
 * 실시간 센서 데이터 스트리밍 시뮬레이션을 시작합니다.
 */
function startSimulation() {
    console.log('--- 실시간 센서 데이터 스트리밍 시뮬레이션 시작 ---');
    console.log(`데이터 생성 주기: ${SIMULATION_INTERVAL_MS / 1000}초`);
    console.log(`이동 평균 윈도우 크기: ${MOVING_AVERAGE_WINDOW_SIZE}`);
    console.log(`이상 징후 탐지 임계값: ${ANOMALY_THRESHOLD}`);
    console.log('----------------------------------------------------');

    setInterval(() => {
        // 1. 새로운 센서 데이터 생성
        const newData = generateSensorData();
        sensorDataHistory.push(newData.temperature); // 온도 데이터만 기록 (예시)

        // 윈도우 크기를 초과하는 과거 데이터는 제거
        if (sensorDataHistory.length > MOVING_AVERAGE_WINDOW_SIZE) {
            sensorDataHistory.shift();
        }

        console.log(`
[${new Date().toISOString()}]`);
        console.log(`수신된 데이터: 온도=${newData.temperature.toFixed(2)}°C, 습도=${newData.humidity.toFixed(2)}%, 압력=${newData.pressure.toFixed(2)}hPa`);

        // 2. 이동 평균 계산 (충분한 데이터가 있을 경우)
        if (sensorDataHistory.length === MOVING_AVERAGE_WINDOW_SIZE) {
            const movingAverage = calculateMovingAverage(sensorDataHistory);
            console.log(`온도 이동 평균 (${MOVING_AVERAGE_WINDOW_SIZE}개): ${movingAverage.toFixed(2)}°C`);

            // 3. 이상 징후 탐지
            const isAnomaly = detectAnomaly(newData.temperature, movingAverage, ANOMALY_THRESHOLD);
            if (isAnomaly) {
                console.warn(`!!!! 경고: 이상 온도 감지! 현재 온도: ${newData.temperature.toFixed(2)}°C, 이동 평균: ${movingAverage.toFixed(2)}°C !!!!`);
            }
        } else {
            console.log(`이동 평균 계산을 위한 데이터 수집 중... (${sensorDataHistory.length}/${MOVING_AVERAGE_WINDOW_SIZE})`);
        }

    }, SIMULATION_INTERVAL_MS);
}

// 시뮬레이션 시작
startSimulation();
