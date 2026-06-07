// Program.cs
using System;
using System.Linq;
using System.Collections.Generic;

namespace InventoryManagementSystem
{
    // 재고 관리 시스템 애플리케이션의 메인 진입점이다.
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 재고 관리 시스템 시작 ===");

            // InventoryManager 인스턴스를 생성한다.
            InventoryManager manager = new InventoryManager();

            // 품목 추가
            Console.WriteLine("
--- 품목 추가 ---");
            manager.AddItem(new InventoryItem(101, "노트북", 5, 1200.50m));
            manager.AddItem(new InventoryItem(102, "마우스", 20, 25.00m));
            manager.AddItem(new InventoryItem(103, "키보드", 10, 75.20m));
            manager.AddItem(new InventoryItem(101, "중복 노트북", 1, 1000.00m)); // 중복 ID 시도

            // 모든 품목 리스트 출력
            Console.WriteLine("
--- 현재 재고 목록 ---");
            PrintInventory(manager.ListAllItems());

            // 품목 수량 업데이트
            Console.WriteLine("
--- 품목 수량 업데이트 ---");
            manager.UpdateItemQuantity(102, 15);
            manager.UpdateItemQuantity(104, 5); // 없는 품목 업데이트 시도
            manager.UpdateItemQuantity(103, -1); // 음수 수량 업데이트 시도

            // 특정 품목 조회
            Console.WriteLine("
--- 특정 품목 조회 (ID: 101) ---");
            InventoryItem item = manager.GetItem(101);
            if (item != null)
            {
                Console.WriteLine($"조회된 품목: {item}");
            }
            else
            {
                Console.WriteLine("품목을 찾을 수 없다.");
            }

            // 품목 제거
            Console.WriteLine("
--- 품목 제거 (ID: 102) ---");
            manager.RemoveItem(102);
            manager.RemoveItem(999); // 없는 품목 제거 시도

            // 제거 후 모든 품목 리스트 출력
            Console.WriteLine("
--- 제거 후 재고 목록 ---");
            PrintInventory(manager.ListAllItems());

            // 총 재고 가치 계산
            Console.WriteLine("
--- 총 재고 가치 ---");
            Console.WriteLine($"총 재고 가치: {manager.CalculateTotalInventoryValue():C}");

            Console.WriteLine("
=== 재고 관리 시스템 종료 ===");
        }

        // 재고 목록을 콘솔에 출력하는 헬퍼 메서드이다.
        static void PrintInventory(List<InventoryItem> inventory)
        {
            if (inventory.Any())
            {
                foreach (var item in inventory)
                {
                    Console.WriteLine(item);
                }
            }
            else
            {
                Console.WriteLine("재고 품목이 없다.");
            }
        }
    }
}
