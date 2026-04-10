using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks; // 비동기 파일 읽기를 위해 필요

namespace SalesAnalyzer
{
    /// <summary>
    /// 판매 데이터를 처리하고 분석하는 클래스입니다.
    /// </summary>
    public class DataProcessor
    {
        private readonly JsonSerializerOptions _jsonOptions;

        public DataProcessor()
        {
            // JSON 역직렬화 옵션 설정
            _jsonOptions = new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true // JSON 속성 이름의 대소문자를 구분하지 않습니다.
            };
        }

        /// <summary>
        /// 지정된 파일 경로에서 판매 데이터를 비동기적으로 로드합니다.
        /// </summary>
        /// <param name="filePath">판매 데이터 파일의 경로입니다.</param>
        /// <returns>로드된 SaleRecord 객체 리스트를 반환합니다.</returns>
        /// <exception cref="FileNotFoundException">파일을 찾을 수 없을 때 발생합니다.</exception>
        /// <exception cref="JsonException">JSON 데이터 역직렬화 중 오류가 발생할 때 발생합니다.</exception>
        public async Task<List<SaleRecord>> LoadSalesData(string filePath)
        {
            if (!File.Exists(filePath))
            {
                throw new FileNotFoundException($"파일을 찾을 수 없습니다: {filePath}");
            }

            // 파일에서 모든 텍스트를 비동기적으로 읽습니다.
            string jsonString = await File.ReadAllTextAsync(filePath);

            // JSON 문자열을 SaleRecord 리스트로 역직렬화합니다.
            // null을 반환할 수 있으므로 기본값으로 새 리스트를 제공합니다.
            return JsonSerializer.Deserialize<List<SaleRecord>>(jsonString, _jsonOptions) ?? new List<SaleRecord>();
        }

        /// <summary>
        /// 제공된 판매 기록 리스트를 분석하여 제품별 총 판매액을 계산합니다.
        /// </summary>
        /// <param name="sales">분석할 SaleRecord 객체 리스트입니다.</param>
        /// <returns>제품별 총 판매액을 포함하는 ProductSales 객체 리스트를 반환합니다.</returns>
        public List<ProductSales> AnalyzeSalesData(List<SaleRecord> sales)
        {
            if (sales == null)
            {
                return new List<ProductSales>();
            }

            // LINQ를 사용하여 제품별로 그룹화하고 총 판매액을 계산합니다.
            var analyzedResults = sales.GroupBy(s => s.ProductName)
                                       .Select(g => new ProductSales
                                       {
                                           ProductName = g.Key,
                                           TotalAmount = g.Sum(s => s.Amount)
                                       })
                                       .OrderByDescending(ps => ps.TotalAmount) // 총 판매액이 높은 순으로 정렬
                                       .ToList();

            return analyzedResults;
        }
    }
}
