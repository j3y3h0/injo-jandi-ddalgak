// main.js

const { analyzeText, getTopNWords } = require('./textProcessor'); // textProcessor.js에서 분석 함수들을 가져온다.

// 분석할 샘플 텍스트이다. 이 텍스트를 수정하여 다양한 분석을 시도할 수 있다.
const sampleText = `
    이것은 샘플 텍스트입니다. 이 텍스트는 텍스트 분석기 예시를 위해 사용됩니다.
    텍스트 분석기는 단어의 빈도를 계산하고, 가장 많이 나오는 단어를 찾습니다.
    샘플 텍스트를 통해 텍스트 처리의 기본을 이해할 수 있습니다.
    이 텍스트는 반복되는 단어를 포함하여 분석 결과를 더 명확하게 보여줍니다.
`;

console.log("텍스트 분석을 시작합니다...");

// 텍스트를 분석한다.
const analysisResult = analyzeText(sampleText);

console.log("
--- 분석 결과 ---");
console.log(`총 단어 수: ${analysisResult.wordCount}개`);

console.log("
각 단어의 빈도:");
// 단어 빈도수를 보기 좋게 출력한다.
for (const word in analysisResult.wordFrequencies) {
    console.log(`  "${word}": ${analysisResult.wordFrequencies[word]}회`);
}

// 가장 많이 나온 상위 3개 단어를 가져온다.
const topWords = getTopNWords(analysisResult.wordFrequencies, 3);

console.log("
가장 많이 나온 단어 (상위 3개):");
// 상위 단어들을 출력한다.
if (topWords.length > 0) {
    for (const item of topWords) {
        console.log(`  "${item.word}": ${item.frequency}회`);
    }
} else {
    console.log("  분석할 단어가 없습니다.");
}

console.log("
텍스트 분석이 완료되었습니다.");
