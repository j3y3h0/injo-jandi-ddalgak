// dataProcessor.js

/**
 * @module dataProcessor
 * @description 가져온 원시 데이터를 시각화에 적합한 형태로 가공하는 모듈입니다.
 */

/**
 * 원시 데이터를 받아 카테고리별로 값을 집계합니다.
 * @param {Array<Object>} rawData - dataFetcher에서 가져온 원시 데이터 배열입니다.
 * @returns {Object} { labels: Array<string>, datasets: Array<{ label: string, data: Array<number> }> } 시각화에 사용될 가공된 데이터 객체입니다.
 */
export function processData(rawData) {
    console.log("데이터를 가공하는 중:", rawData);

    if (!rawData || rawData.length === 0) {
        return { labels: [], datasets: [{ label: '값', data: [] }] };
    }

    const categoryMap = {};
    rawData.forEach(item => {
        if (categoryMap[item.category]) {
            categoryMap[item.category] += item.value;
        } else {
            categoryMap[item.category] = item.value;
        }
    });

    const labels = Object.keys(categoryMap).sort(); // 카테고리 라벨 정렬
    const data = labels.map(label => categoryMap[label]);

    const processed = {
        labels: labels,
        datasets: [{
            label: '카테고리별 총 값',
            data: data,
            backgroundColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(255, 159, 64, 0.6)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    };

    console.log("데이터 가공 완료:", processed);
    return processed;
}
