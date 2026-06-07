// InventoryItem.cs
using System;

namespace InventoryManagementSystem
{
    // 재고 품목의 데이터 구조를 정의하는 클래스이다.
    public class InventoryItem
    {
        // 품목의 고유 식별자이다.
        public int Id { get; set; }
        // 품목의 이름이다.
        public string Name { get; set; }
        // 품목의 현재 수량이다.
        public int Quantity { get; set; }
        // 품목의 단가이다.
        public decimal Price { get; set; }

        // InventoryItem 클래스의 새 인스턴스를 초기화한다.
        public InventoryItem(int id, string name, int quantity, decimal price)
        {
            Id = id;
            Name = name;
            Quantity = quantity;
            Price = price;
        }

        // 품목 정보를 문자열로 반환한다.
        public override string ToString()
        {
            return $"ID: {Id}, 이름: {Name}, 수량: {Quantity}, 가격: {Price:C}";
        }
    }
}
