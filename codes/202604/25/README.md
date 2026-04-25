# 고객 데이터 처리 프로젝트 (Customer Data Processing Project)

이 프로젝트는 기업의 고객 데이터를 CSV 파일로부터 읽어와 특정 조건(예: 회원 등급)에 따라 필터링하고, 필터링된 데이터를 새로운 CSV 파일로 저장하는 자바 애플리케이션입니다. "기업 고객 데이터 분석을 통한 마케팅 전략 수립"이라는 가상의 뉴스 주제에 기반하여, 마케팅 부서에서 VIP 고객 리스트를 추출하는 시나리오를 상정하여 개발되었습니다.

## 기능

*   CSV 파일에서 고객 정보(ID, 이름, 이메일, 회원 등급)를 읽어옵니다.
*   특정 회원 등급(예: VIP)을 가진 고객만을 필터링합니다.
*   필터링된 고객 데이터를 새로운 CSV 파일로 저장합니다.
*   Apache Commons CSV 라이브러리를 사용하여 CSV 파일 처리를 효율적으로 수행합니다.

## 프로젝트 구조

```
.
├── pom.xml                     # Maven 빌드 설정 파일
├── README.md                   # 프로젝트 설명 (현재 파일)
└── src
    └── main
        └── java
            └── com
                └── example
                    └── customerdata
                        ├── Customer.java               # 고객 정보를 담는 데이터 모델 (POJO)
                        ├── CustomerDataProcessor.java  # CSV 파일 읽기, 필터링, 쓰기 로직 처리
                        └── Main.java                   # 애플리케이션의 메인 진입점 및 실행 로직
```

## 사용 기술

*   **언어:** Java 8 이상
*   **빌드 도구:** Apache Maven
*   **라이브러리:** Apache Commons CSV (CSV 파일 파싱 및 생성)

## 빌드 및 실행 방법

이 프로젝트는 Maven을 사용하여 빌드됩니다.

1.  **프로젝트 클론 및 이동:**
    이 프로젝트는 이미 주어진 디렉터리에 생성되어 있습니다. `C:\server\gpt-code-diary\codes\202604\25` 경로에서 작업을 진행합니다.

2.  **Maven으로 빌드:**
    프로젝트 루트 디렉터리(`C:\server\gpt-code-diary\codes\202604\25`)에서 다음 명령어를 실행하여 프로젝트를 빌드합니다.
    ```bash
    mvn clean install
    ```
    이 명령어는 필요한 의존성을 다운로드하고, 소스 코드를 컴파일하여 실행 가능한 JAR 파일을 `target` 디렉터리에 생성합니다.

3.  **애플리케이션 실행:**
    빌드가 성공하면, `target` 디렉터리에 생성된 JAR 파일을 사용하여 애플리케이션을 실행할 수 있습니다.
    ```bash
    java -jar target/customer-data-processor-1.0-SNAPSHOT.jar
    ```
    애플리케이션은 실행 시 `input_customers.csv` 파일을 자동으로 생성(파일이 없을 경우)하고, 이를 읽어 `VIP` 등급 고객을 필터링하여 `vip_customers.csv` 파일로 저장합니다.

## 코드 설명

### `Customer.java`

고객 한 명의 정보를 나타내는 간단한 자바 객체(POJO)입니다. `ID`, `이름`, `이메일`, `회원등급` 필드를 가집니다.

### `CustomerDataProcessor.java`

CSV 파일 처리의 핵심 로직을 담당합니다.

*   `readCustomersFromCsv(String filePath)`: 지정된 CSV 파일에서 고객 데이터를 읽어 `Customer` 객체 리스트로 반환합니다.
*   `filterCustomersByMembershipLevel(List<Customer> allCustomers, String targetMembershipLevel)`: 전체 고객 리스트에서 특정 `회원등급`을 가진 고객만을 필터링하여 반환합니다.
*   `writeCustomersToCsv(List<Customer> customers, String filePath)`: `Customer` 객체 리스트를 지정된 CSV 파일로 저장합니다.

### `Main.java`

애플리케이션의 시작점입니다.

1.  `createSampleInputCsv()` 메서드를 호출하여 `input_customers.csv`라는 샘플 CSV 파일을 생성합니다. 이 파일은 프로젝트를 테스트하기 위한 가상의 고객 데이터를 포함합니다.
2.  `CustomerDataProcessor` 인스턴스를 생성합니다.
3.  `input_customers.csv` 파일에서 모든 고객 데이터를 읽어옵니다.
4.  읽어온 고객 데이터 중 `VIP` 등급의 고객만을 필터링합니다.
5.  필터링된 `VIP` 고객 리스트를 `vip_customers.csv` 파일로 저장합니다.
