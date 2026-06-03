using System.Collections.Generic;

namespace StockPredictorApp
{
    public class DataProcessor
    {
        // 모델 훈련에 사용될 가상의 주식 데이터를 생성합니다.
        // 실제 애플리케이션에서는 CSV 파일, 데이터베이스 또는 API에서 데이터를 로드합니다.
        public static List<StockFeature> GetSampleData()
        {
            var data = new List<StockFeature>
            {
                new StockFeature { Open = 100, High = 105, Low = 98, Volume = 100000, Close = 103 },
                new StockFeature { Open = 103, High = 108, Low = 101, Volume = 110000, Close = 106 },
                new StockFeature { Open = 106, High = 112, Low = 104, Volume = 120000, Close = 109 },
                new StockFeature { Open = 109, High = 115, Low = 107, Volume = 130000, Close = 113 },
                new StockFeature { Open = 113, High = 118, Low = 111, Volume = 140000, Close = 116 },
                new StockFeature { Open = 116, High = 121, Low = 114, Volume = 150000, Close = 119 },
                new StockFeature { Open = 119, High = 125, Low = 117, Volume = 160000, Close = 122 },
                new StockFeature { Open = 122, High = 128, Low = 120, Volume = 170000, Close = 125 },
                new StockFeature { Open = 125, High = 131, Low = 123, Volume = 180000, Close = 128 },
                new StockFeature { Open = 128, High = 134, Low = 126, Volume = 190000, Close = 131 },
                new StockFeature { Open = 131, High = 137, Low = 129, Volume = 200000, Close = 134 },
                new StockFeature { Open = 134, High = 140, Low = 132, Volume = 210000, Close = 137 },
                new StockFeature { Open = 137, High = 143, Low = 135, Volume = 220000, Close = 140 },
                new StockFeature { Open = 140, High = 146, Low = 138, Volume = 230000, Close = 143 },
                new StockFeature { Open = 143, High = 149, Low = 141, Volume = 240000, Close = 146 }
            };
            return data;
        }
    }
}