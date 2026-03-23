package com.gemini.stockpredictor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 과거 주식 데이터 생성 (실제 데이터 대신 예제 데이터 사용)
        List<StockData> historicalData = new ArrayList<>();
        historicalData.add(new StockData("2026-03-17", 100.50));
        historicalData.add(new StockData("2026-03-18", 101.20));
        historicalData.add(new StockData("2026-03-19", 102.00));
        historicalData.add(new StockData("2026-03-20", 101.80));
        historicalData.add(new StockData("2026-03-21", 103.10));
        historicalData.add(new StockData("2026-03-22", 102.50)); // 오늘 날짜 2026-03-23 기준 전날 데이터

        // 주가 예측기 인스턴스 생성
        StockPredictor predictor = new StockPredictor();

        // 다음 날(2026-03-23) 주가 예측
        double predictedPrice = predictor.predictNextDayPrice(historicalData);

        System.out.println("--- 주가 예측 프로젝트 ---");
        System.out.println("과거 데이터:");
        historicalData.forEach(System.out::println);
        System.out.printf("예측된 다음 날(2026-03-23) 주가: %.2f%n", predictedPrice);
        System.out.println("------------------------");
    }
}
