// dataFetcher.js

/**
 * @module dataFetcher
 * @description 외부 API로부터 데이터를 비동기적으로 가져오는 모듈입니다.
 */

/**
 * 가상의 외부 API에서 데이터를 가져옵니다.
 * 실제 프로젝트에서는 fetch API 등을 사용하여 실제 엔드포인트와 통신합니다.
 * @returns {Promise<Array<Object>>} 가공되지 않은 데이터 배열을 반환하는 Promise입니다.
 */
export async function fetchData() {
    console.log("데이터를 가져오는 중...");
    try {
        // 실제 API 호출을 시뮬레이션합니다.
        // 예를 들어, JSONPlaceholder와 같은 무료 API를 사용할 수 있습니다.
        // const response = await fetch('https://jsonplaceholder.typicode.com/posts?_limit=10');
        // const data = await response.json();
        // return data;

        // 여기서는 가상 데이터를 2초 지연 후 반환합니다.
        return new Promise(resolve => {
            setTimeout(() => {
                const mockData = [
                    { id: 1, category: 'A', value: 30, timestamp: '2023-01-01' },
                    { id: 2, category: 'B', value: 50, timestamp: '2023-01-02' },
                    { id: 3, category: 'A', value: 45, timestamp: '2023-01-03' },
                    { id: 4, category: 'C', value: 20, timestamp: '2023-01-04' },
                    { id: 5, category: 'B', value: 60, timestamp: '2023-01-05' },
                    { id: 6, category: 'A', value: 35, timestamp: '2023-01-06' },
                    { id: 7, category: 'C', value: 25, timestamp: '2023-01-07' },
                    { id: 8, category: 'B', value: 55, timestamp: '2023-01-08' },
                ];
                console.log("데이터 가져오기 완료:", mockData);
                resolve(mockData);
            }, 2000); // 2초 지연 시뮬레이션
        });

    } catch (error) {
        console.error("데이터 가져오기 실패:", error);
        throw new Error("데이터를 가져오는 중 오류가 발생했습니다.");
    }
}
