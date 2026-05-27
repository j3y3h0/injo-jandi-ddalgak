// visualizer.js
// 분석된 데이터를 텍스트 기반 차트로 시각화하는 함수들을 정의한다.

/**
 * 객체 데이터를 기반으로 간단한 텍스트 기반 막대 차트를 생성한다.
 * @param {Object} data - 시각화할 데이터 객체 (예: { "카테고리명": 값 }).
 * @param {string} title - 차트의 제목.
 * @returns {string} 텍스트 기반 막대 차트 문자열.
 */
export function generateBarChart(data, title = "데이터 시각화") {
    let chart = `
### ${title}
`;
    chart += "--------------------------------------------------
";

    const keys = Object.keys(data);
    if (keys.length === 0) {
        return chart + "표시할 데이터가 없습니다.
--------------------------------------------------
";
    }

    // 최대값 찾기 (스케일링을 위함)
    const values = Object.values(data);
    const maxValue = Math.max(...values);
    const maxBarLength = 50; // 콘솔에서 막대의 최대 길이

    // 키 이름의 최대 길이 찾기 (정렬을 위함)
    const maxKeyLength = Math.max(...keys.map(key => key.length));

    for (const key of keys) {
        const value = data[key];
        // 비례적인 막대 길이 계산
        const barLength = (value / maxValue) * maxBarLength;
        const bar = '█'.repeat(Math.ceil(barLength)); // 최소 1칸은 표시되도록 Math.ceil 사용

        // 키 이름을 균일하게 정렬 (패딩 추가)
        const paddedKey = key.padEnd(maxKeyLength, ' ');

        chart += `${paddedKey} | ${bar} ${value.toLocaleString()}원
`;
    }

    chart += "--------------------------------------------------
";
    return chart;
}
