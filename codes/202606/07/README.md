# 재고 관리 시스템 (Inventory Management System)

이 프로젝트는 간단한 재고 관리 시스템을 C#으로 구현한 예제 코드이다. 재고 품목을 추가, 제거, 수량 변경 및 목록 조회하는 기능을 포함하고 있다.

## 프로젝트 구조

프로젝트는 다음 파일들로 구성되어 있다:

*   `README.md`: 프로젝트에 대한 설명, 코드 구조 및 실행 방법을 담고 있다.
*   `Program.cs`: 애플리케이션의 진입점이다. `InventoryManager`를 사용하여 재고 관리 기능을 시연한다.
*   `InventoryItem.cs`: 재고 품목의 데이터 구조를 정의하는 클래스이다. 각 품목은 고유 ID, 이름, 수량 및 가격을 가진다.
*   `InventoryManager.cs`: 재고 품목들을 관리하는 비즈니스 로직을 포함하는 클래스이다. 품목 추가, 제거, 업데이트 및 조회 기능을 제공한다.

## 코드 설명

### `InventoryItem.cs`

```csharp
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
```

### `InventoryManager.cs`

```csharp
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
```

### `Program.cs`

```csharp
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
```

## 실행 방법

이 프로젝트를 실행하려면 .NET SDK가 설치되어 있어야 한다.

1.  **프로젝트 디렉토리로 이동**:
    명령 프롬프트 또는 터미널을 열고 이 `README.md` 파일이 있는 디렉토리(`C:\server\gpt-code-diary\codes\202606\07`)로 이동한다.

    ```bash
    cd C:\server\gpt-code-diary\codes\202606\07
    ```

2.  **새 C# 프로젝트 초기화 (최초 1회)**:
    아직 .NET 프로젝트가 초기화되지 않았다면, 다음 명령을 실행하여 프로젝트 파일을 생성한다.
    (이미 `.csproj` 파일이 있다면 이 단계는 건너뛴다.)

    ```bash
    dotnet new console
    ```

    `InventoryManagementSystem.csproj` 파일이 생성될 것이다.

3.  **애플리케이션 실행**:
    다음 명령을 사용하여 애플리케이션을 컴파일하고 실행한다.

    ```bash
    dotnet run
    ```

위 단계를 따르면 콘솔에 재고 관리 시스템의 동작 결과가 출력될 것이다.
