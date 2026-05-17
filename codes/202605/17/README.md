# 추천 시스템 예제 프로젝트 (Java)

이 프로젝트는 협업 필터링(Collaborative Filtering) 알고리즘 중 사용자 기반(User-based) 추천 방식을 간단하게 구현한 Java 예제입니다. 사용자 간의 유사도를 계산하여 특정 사용자가 아직 평가하지 않은 아이템을 추천합니다.

## 주제 및 활용 라이브러리/알고리즘

*   **주제**: 협업 필터링 기반의 간단한 추천 시스템
*   **활용 알고리즘**: 피어슨 상관 계수(Pearson Correlation Coefficient)를 이용한 사용자 간 유사도 계산
*   **활용 라이브러리**: 표준 Java Collection Framework (Map, List, Set 등) 및 기본 Math 함수

## 코드 구조 설명

*   `User.java`:
    *   사용자 정보를 나타내는 클래스입니다.
    *   `userId` (사용자 고유 ID)와 `ratings` (아이템 ID와 해당 아이템에 대한 평점을 매핑하는 `Map`) 필드를 가집니다.

*   `Item.java`:
    *   아이템 정보를 나타내는 클래스입니다.
    *   `itemId` (아이템 고유 ID)와 `name` (아이템 이름) 필드를 가집니다.

*   `RecommendationEngine.java`:
    *   추천 시스템의 핵심 로직을 담고 있는 클래스입니다.
    *   `calculatePearsonCorrelation(User user1, User user2)` 메서드를 통해 두 사용자 간의 피어슨 상관 계수를 계산합니다. 이는 사용자 간의 평점 경향 유사도를 측정하는 데 사용됩니다.
    *   `getRecommendations(User targetUser)` 메서드를 통해 특정 사용자에게 아이템을 추천합니다. 이 메서드는 다른 사용자들과의 유사도를 기반으로 타겟 사용자가 아직 평가하지 않은 아이템에 대한 가중 평균 평점을 계산하여 가장 점수가 높은 아이템들을 추천합니다.
    *   내부 클래스 `RecommendedItem`은 추천된 아이템과 그 추천 점수를 캡슐화합니다.

*   `Main.java`:
    *   프로젝트의 메인 실행 클래스입니다.
    *   샘플 사용자 및 아이템 데이터를 생성하고, `RecommendationEngine`을 초기화하여 특정 사용자(`userA`, `userC`)에게 아이템을 추천하는 과정을 보여줍니다.
    *   콘솔에 추천 결과를 출력합니다.

## 프로젝트 실행 방법

이 프로젝트는 별도의 외부 라이브러리 없이 표준 Java만을 사용합니다. 따라서 일반적인 Java 컴파일 및 실행 절차를 따릅니다.

1.  **자바 소스 코드 컴파일**:
    터미널을 현재 프로젝트 디렉터리(`C:\server\gpt-code-diary\codes\202605\17`)로 이동한 후, 다음 명령어를 실행하여 모든 `.java` 파일을 컴파일합니다.

    ```bash
    javac *.java
    ```

2.  **프로그램 실행**:
    컴파일이 성공적으로 완료되면, 다음 명령어를 실행하여 `Main` 클래스를 통해 프로그램을 실행합니다.

    ```bash
    java Main
    ```

    프로그램이 실행되면 `userA`와 `userC`에게 추천되는 아이템 목록과 예상 점수가 콘솔에 출력될 것입니다.
