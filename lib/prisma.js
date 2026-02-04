import { PrismaClient } from "@prisma/client";

/** Prisma Client 싱글톤. DB 연결 풀은 한 인스턴스만 사용. */
const globalForPrisma = globalThis;
export const prisma = globalForPrisma.prisma ?? new PrismaClient();
if (process.env.NODE_ENV !== "production") globalForPrisma.prisma = prisma;

export default prisma;
