// utils.js
// 범용적으로 사용될 유틸리티 함수들을 포함합니다.

export class Utils {
    /**
     * 숫자를 통화 형식으로 포맷팅합니다. (예: 15000 -> ₩15,000)
     * @param {number} amount - 포맷팅할 숫자입니다.
     * @returns {string} 통화 형식으로 포맷팅된 문자열입니다.
     */
    static formatCurrency(amount) {
        // 한국 원화(KRW)로 포맷팅합니다.
        return new Intl.NumberFormat('ko-KR', {
            style: 'currency',
            currency: 'KRW'
        }).format(amount);
    }

    /**
     * Date 객체를 YYYY-MM-DD 형식의 문자열로 포맷팅합니다.
     * @param {Date} date - 포맷팅할 Date 객체입니다.
     * @returns {string} YYYY-MM-DD 형식의 문자열입니다.
     */
    static formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    /**
     * 문자열이 YYYY-MM-DD 형식인지 간단히 확인합니다.
     * 정규식을 사용하여 기본적인 형식만 검사합니다.
     * @param {string} dateString - 확인할 날짜 문자열입니다.
     * @returns {boolean} 유효한 형식인지 여부입니다.
     */
    static isValidDateFormat(dateString) {
        // YYYY-MM-DD 형식을 검사하는 정규식입니다. (윤년 등 복잡한 날짜 유효성은 검사하지 않습니다.)
        const regex = /^\d{4}-\d{2}-\d{2}$/;
        return regex.test(dateString);
    }
}
