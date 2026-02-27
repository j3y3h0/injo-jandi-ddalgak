// main.js
// 애플리케이션의 주 진입점.
// 데이터를 파싱하고 분석 및 분류 함수를 호출하여 결과를 출력한다.

const { parseCsv } = require('./dataParser');
const { calculateMean, calculateMedian, findMax, findMin } = require('./dataAnalyzer');
const { classifyByThresholds } = require('./dataClassifier');

// 예제 CSV 데이터
const sampleCsvData = `
이름,나이,점수,도시
김철수,28,85,서울
이영희,32,92,부산
박민수,24,78,서울
최지아,30,65,대전
홍길동,26,95,광주
`;

console.log("--- 데이터 분석 및 분류 도구 시작 ---");

// 1. 데이터 파싱
console.log("
[1. 데이터 파싱]");
const parsedData = parseCsv(sampleCsvData);
console.log("파싱된 데이터:", parsedData);

// 2. '점수' 컬럼에 대한 통계 분석
console.log("
[2. 통계 분석 - '점수' 컬럼]");
const scores = parsedData.map(row => row.점수).filter(score => typeof score === 'number');

if (scores.length > 0) {
    const meanScore = calculateMean(scores);
    const medianScore = calculateMedian(scores);
    const maxScore = findMax(scores);
    const minScore = findMin(scores);

    console.log(`평균 점수: ${meanScore.toFixed(2)}`);
    console.log(`중앙값 점수: ${medianScore}`);
    console.log(`최고 점수: ${maxScore}`);
    console.log(`최저 점수: ${minScore}`);
} else {
    console.log("분석할 점수 데이터가 없습니다.");
}


// 3. '점수' 컬럼에 대한 분류
console.log("
[3. 데이터 분류 - '점수' 컬럼]");
const scoreThresholds = {
    high: 90, // 90점 이상은 '높음'
    low: 70   // 70점 이하는 '낮음'
};

parsedData.forEach(row => {
    if (typeof row.점수 === 'number') {
        const classification = classifyByThresholds(row.점수, scoreThresholds);
        console.log(`${row.이름}의 점수 (${row.점수}점): ${classification}`);
    } else {
        console.log(`${row.이름}의 점수는 숫자가 아니므로 분류할 수 없습니다.`);
    }
});

console.log("
--- 데이터 분석 및 분류 도구 완료 ---");
