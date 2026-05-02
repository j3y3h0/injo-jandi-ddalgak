// main.js

const Summarizer = require('./summarizer');

// 예제 텍스트이다.
const exampleText = `
인공지능(AI)은 현대 기술의 가장 중요한 혁신 중 하나이다. AI는 머신러닝, 딥러닝, 자연어 처리 등 다양한 하위 분야를 포함한다. 이러한 기술들은 데이터 분석, 이미지 인식, 음성 인식, 자율 주행 등 여러 분야에서 혁신적인 발전을 이끌고 있다. 특히 자연어 처리는 텍스트 데이터를 이해하고 생성하는 데 중점을 둔다. 최근에는 대규모 언어 모델(LLM)이 등장하여 인간과 유사한 텍스트를 생성하고 복잡한 질문에 답변하는 능력을 보여주고 있다. AI의 발전은 산업 전반에 걸쳐 효율성을 높이고 새로운 비즈니스 기회를 창출하고 있다. 하지만 AI의 윤리적 사용, 데이터 프라이버시, 일자리 변화 등 사회적 문제에 대한 논의도 활발하게 이루어지고 있다. 따라서 AI 기술의 발전과 함께 책임감 있는 개발 및 활용 방안 모색이 중요하다.
`;

// Summarizer 인스턴스를 생성한다.
const summarizer = new Summarizer();

// 텍스트를 3문장으로 요약한다.
const summarizedText = summarizer.summarize(exampleText, 3);

console.log('--- 원본 텍스트 ---');
console.log(exampleText);
console.log('
--- 요약된 텍스트 (3문장) ---');
console.log(summarizedText);
