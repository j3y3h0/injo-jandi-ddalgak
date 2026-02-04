const DATE_OPTION = { timeZone: "Asia/Seoul" };

/** Asia/Seoul 기준 현재 날짜 부분 { year, month, day } 반환. 단일 책임: 날짜 파싱. */
export function getDateParts() {
  const nowDate = new Date().toLocaleString("en-US", DATE_OPTION);
  const d = new Date(nowDate);
  return {
    year: d.getFullYear(),
    month: String(d.getMonth() + 1).padStart(2, "0"),
    day: String(d.getDate()).padStart(2, "0"),
  };
}

/** 현재날짜 yyyyMMdd 문자열 반환 */
export function getyyyyMMdd() {
  const { year, month, day } = getDateParts();
  return `${year}${month}${day}`;
}
