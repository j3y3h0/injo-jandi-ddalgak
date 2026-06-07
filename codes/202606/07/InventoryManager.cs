// InventoryManager.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace InventoryManagementSystem
{
    // 재고 품목들을 관리하는 비즈니스 로직을 처리하는 클래스이다.
    public class InventoryManager
    {
        // 재고 품목들을 저장하는 리스트이다.
        private List<InventoryItem> _inventory;

        // InventoryManager 클래스의 새 인스턴스를 초기화한다.
        public InventoryManager()
        {
            _inventory = new List<InventoryItem>();
        }

        // 새 품목을 재고에 추가한다.
        // 품목 ID가 이미 존재하면 추가되지 않는다.
        public void AddItem(InventoryItem item)
        {
            if (_inventory.Any(i => i.Id == item.Id))
            {
                Console.WriteLine($"오류: ID {item.Id}를 가진 품목이 이미 존재한다.");
                return;
            }
            _inventory.Add(item);
            Console.WriteLine($"품목 '{item.Name}'이(가) 재고에 추가되었다.");
        }

        // 지정된 ID의 품목을 재고에서 제거한다.
        // 품목이 없으면 제거되지 않는다.
        public void RemoveItem(int itemId)
        {
            var itemToRemove = _inventory.FirstOrDefault(i => i.Id == itemId);
            if (itemToRemove == null)
            {
                Console.WriteLine($"오류: ID {itemId}를 가진 품목을 찾을 수 없다.");
                return;
            }
            _inventory.Remove(itemToRemove);
            Console.WriteLine($"ID {itemId} 품목이 재고에서 제거되었다.");
        }

        // 지정된 ID의 품목 수량을 업데이트한다.
        // 품목이 없으면 업데이트되지 않는다.
        public void UpdateItemQuantity(int itemId, int newQuantity)
        {
            var itemToUpdate = _inventory.FirstOrDefault(i => i.Id == itemId);
            if (itemToUpdate == null)
            {
                Console.WriteLine($"오류: ID {itemId}를 가진 품목을 찾을 수 없다.");
                return;
            }
            if (newQuantity < 0)
            {
                Console.WriteLine("오류: 수량은 음수가 될 수 없다.");
                return;
            }
            itemToUpdate.Quantity = newQuantity;
            Console.WriteLine($"ID {itemId} 품목의 수량이 {newQuantity}로 업데이트되었다.");
        }

        // 지정된 ID의 품목을 반환한다.
        public InventoryItem GetItem(int itemId)
        {
            return _inventory.FirstOrDefault(i => i.Id == itemId);
        }

        // 현재 재고에 있는 모든 품목을 리스트로 반환한다.
        public List<InventoryItem> ListAllItems()
        {
            return new List<InventoryItem>(_inventory); // 원본 리스트 보호를 위해 복사본 반환
        }

        // 재고의 총 가치를 계산한다.
        public decimal CalculateTotalInventoryValue()
        {
            return _inventory.Sum(item => item.Quantity * item.Price);
        }
    }
}
