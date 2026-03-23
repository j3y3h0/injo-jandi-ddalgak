package com.gemini.stockpredictor;

import java.util.List;
import java.util.stream.Collectors;

public class StockPredictor {
    /**
     * 이동 평균(Moving Average) 알고리즘을 사용하여 다음 주가를 예측합니다.
     * 이 예제에서는 주어진 데이터의 단순 평균을 다음 날 주가로 예측합니다.
     * 실제 산업에서는 더 복잡한 이동 평균(SMA, EMA 등)과 다른 통계 모델을 사용합니다.
     *
     * @param historicalData 예측에 사용될 과거 주식 데이터 리스트
     * @return 예측된 다음 날 주가
     */
    public double predictNextDayPrice(List<StockData> historicalData) {
        if (historicalData == null || historicalData.isEmpty()) {
            System.out.println("과거 데이터가 없어 예측할 수 없습니다.");
            return 0.0; // 데이터가 없을 경우 0 반환
        }

        // 모든 과거 주가의 합계를 계산합니다.
        double sumPrices = historicalData.stream()
                                        .mapToDouble(StockData::getPrice)
                                        .sum();

        // 평균을 계산하여 다음 날 주가로 예측합니다.
        // 이는 매우 단순한 접근 방식이며, 실제로는 회귀 모델이나 시계열 분석 등이 사용됩니다.
        return sumPrices / historicalData.size();
    }
}
