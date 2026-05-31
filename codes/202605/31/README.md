# 재고 관리 시스템 (Inventory Management System)

이 프로젝트는 간단한 재고 관리 시스템을 자바(Java) 언어로 구현한 것입니다. 제품을 추가하고, 조회하며, 수량을 업데이트하고, 제거하는 기능을 포함하고 있습니다.

## 프로젝트 구조

프로젝트는 다음의 세 가지 주요 자바 클래스로 구성되어 있습니다.

*   `Product.java`: 개별 제품의 정보를 담는 클래스입니다. 제품 ID, 이름, 가격, 재고 수량을 속성으로 가집니다.
*   `Inventory.java`: 재고 관리의 핵심 로직을 담당하는 클래스입니다. `HashMap`을 사용하여 여러 `Product` 객체를 관리하며, 제품 추가, 제거, 조회, 수량 업데이트 및 전체 목록 출력 기능을 제공합니다.
*   `InventoryManagementSystem.java`: 이 시스템의 메인 실행 파일입니다. `main` 메서드를 포함하고 있으며, `Inventory` 클래스의 인스턴스를 생성하고 다양한 재고 관리 작업을 시연합니다.

## 사용된 산업 표준 라이브러리/알고리즘

*   **`java.util.HashMap`**: `Inventory` 클래스에서 제품 ID를 기반으로 `Product` 객체를 효율적으로 저장하고 검색하기 위해 사용되었습니다. 이는 키-값 쌍을 저장하는 데 널리 사용되는 자료구조이며, 평균적으로 O(1)의 시간 복잡도로 데이터 접근이 가능합니다.

## 컴파일 및 실행 방법

이 프로젝트는 표준 자바 개발 키트(JDK)를 사용하여 컴파일하고 실행할 수 있습니다.

### 1. 컴파일

프로젝트 파일들이 위치한 디렉터리(예: `C:\server\gpt-code-diary\codes\202605\31`)로 이동한 후, 다음 명령어를 사용하여 자바 소스 파일들을 컴파일합니다.

```bash
javac Product.java Inventory.java InventoryManagementSystem.java
```

이 명령어는 `.java` 파일들을 `.class` 파일로 변환합니다.

### 2. 실행

모든 클래스 파일이 성공적으로 컴파일되면, 다음 명령어를 사용하여 `InventoryManagementSystem`을 실행할 수 있습니다.

```bash
java InventoryManagementSystem
```

이 명령어를 실행하면 `InventoryManagementSystem.java` 내의 `main` 메서드가 실행되어 재고 관리 시스템의 동작을 콘솔에 출력하게 됩니다. 출력되는 메시지를 통해 제품 추가, 조회, 업데이트, 제거 등의 작업 결과를 확인할 수 있습니다.
