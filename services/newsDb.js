import prisma from "../lib/prisma.js";

/** news_main에서 최신 뉴스 SELECT. limit 건, published_dt 내림차순. 단일 책임: DB 조회. */
export async function selectNews(limit = 9) {
  const rows = await prisma.newsMain.findMany({
    orderBy: { publishedDt: "desc" },
    take: limit,
  });
  return rows.map((row) => ({
    title: row.titleCt,
    sub_title: row.summaryCt ?? null,
  }));
}
