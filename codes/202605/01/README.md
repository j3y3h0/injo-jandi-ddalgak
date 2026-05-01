# 건강 데이터 이상 징후 탐지 프로젝트 (Health Anomaly Detection Project)

## 1. 프로젝트 개요

이 프로젝트는 환자의 건강 데이터(심박수, 체온)에서 통계적인 이상 징후를 탐지하는 간단한 Java 애플리케이션이다. AI가 헬스케어 분야에서 활용될 수 있는 한 가지 예시를 보여주기 위해 개발되었다. Z-score 방법을 사용하여 데이터 포인트를 분석하고, 설정된 임계값을 벗어나는 데이터를 이상 징후로 판단한다.

## 2. 코드 구조

프로젝트는 다음 세 가지 주요 Java 파일로 구성되어 있다.

*   `PatientData.java`:
    *   환자 한 명의 건강 데이터를 정의하는 클래스이다.
    *   `patientId` (환자 ID), `heartRate` (심박수), `temperature` (체온) 필드를 포함한다.
    *   환자 데이터를 객체 형태로 관리하기 위한 기본적인 구조를 제공한다.

*   `AnomalyDetector.java`:
    *   실제 이상 징후 탐지 로직을 구현한 클래스이다.
    *   `detectAnomalies` 메서드를 통해 환자 데이터 리스트를 입력받아 이상 징후로 판단되는 `PatientData` 객체 리스트를 반환한다.
    *   내부적으로 Z-score 계산을 위해 평균과 표준편차를 직접 계산하는 메서드를 포함하고 있다.
    *   **참고**: 실제 산업 환경에서는 Apache Commons Math와 같은 통계 라이브러리를 사용하여 더 정확하고 효율적인 통계 계산을 수행할 수 있다. 이 프로젝트에서는 기본적인 개념 설명을 위해 직접 구현하였다.

*   `Main.java`:
    *   애플리케이션의 시작점이다.
    *   샘플 환자 데이터를 생성하는 `generateSamplePatientData` 메서드를 포함한다. 이 메서드는 일부러 몇몇 이상치 데이터를 포함하여 이상 징후 탐지 기능을 시연한다.
    *   `AnomalyDetector`를 초기화하고, 샘플 데이터를 이용하여 이상 징후를 탐지한 후 그 결과를 콘솔에 출력한다.

## 3. 실행 방법

이 프로젝트는 일반적인 Java 애플리케이션처럼 컴파일하고 실행할 수 있다.

1.  **소스 파일 준비**:
    `PatientData.java`, `AnomalyDetector.java`, `Main.java` 파일을 동일한 디렉터리(`src/com/gemini/health_anomaly_detector` 와 같은 패키지 구조로)에 배치하거나, 현재 디렉터리에 직접 배치한다. (이 프로젝트는 패키지 구조를 가정하고 작성되었으므로, `com/gemini/health_anomaly_detector` 폴더 내에 각 `.java` 파일을 배치하는 것이 좋다.)

    예시 디렉터리 구조:
    ```
    .
    └── src/
        └── com/
            └── gemini/
                └── health_anomaly_detector/
                    ├── PatientData.java
                    ├── AnomalyDetector.java
                    └── Main.java
    ```

    **현재 작업 디렉터리에 파일을 생성했으므로, 위 패키지 구조를 수동으로 생성하고 파일을 옮겨야 한다.**
    
    `mkdir -p src/com/gemini/health_anomaly_detector`
    `mv PatientData.java AnomalyDetector.java Main.java src/com/gemini/health_anomaly_detector/`

2.  **컴파일**:
    터미널 또는 명령 프롬프트에서 프로젝트 루트 디렉터리로 이동한 후 다음 명령어를 실행하여 Java 소스 코드를 컴파일한다.

    ```bash
    javac src/com/gemini/health_anomaly_detector/*.java -d out
    ```
    (여기서 `out`은 컴파일된 `.class` 파일들이 저장될 디렉터리이다. 원하는 이름으로 변경할 수 있다.)

3.  **실행**:
    컴파일이 성공적으로 완료되면 다음 명령어를 사용하여 애플리케이션을 실행한다.

    ```bash
    java -cp out com.gemini.health_anomaly_detector.Main
    ```
    `-cp out`은 컴파일된 클래스 파일들이 `out` 디렉터리에 있음을 Java 런타임에 알려준다.

## 4. Apache Commons Math 라이브러리 사용 가이드 (선택 사항)

이 프로젝트에서는 통계 계산을 직접 구현하였으나, 더 견고하고 확장 가능한 솔루션을 위해서는 Apache Commons Math 라이브러리 사용을 권장한다.

1.  **라이브러리 다운로드**:
    Apache Commons Math의 JAR 파일을 다운로드한다. (예: `commons-math3-3.6.1.jar`)

2.  **클래스패스에 추가**:
    컴파일 및 실행 시 다운로드한 JAR 파일을 클래스패스에 추가해야 한다.

    **컴파일 시**:
    ```bash
    javac -cp ".:path/to/commons-math3-3.6.1.jar" src/com/gemini/health_anomaly_detector/*.java -d out
    ```
    (Windows의 경우 `;`를 사용하여 클래스패스를 구분한다: `.;path	o\commons-math3-3.6.1.jar`)

    **실행 시**:
    ```bash
    java -cp "out:path/to/commons-math3-3.6.1.jar" com.gemini.health_anomaly_detector.Main
    ```
    (Windows의 경우 `;`를 사용하여 클래스패스를 구분한다: `out;path	o\commons-math3-3.6.1.jar`)

    `AnomalyDetector.java` 내의 `calculateMean` 및 `calculateStandardDeviation` 메서드를 Apache Commons Math의 `DescriptiveStatistics` 또는 `Statistics` 클래스를 활용하도록 수정하면 된다.

## 5. 추가 개선 사항

*   **더 복잡한 모델**: Z-score 외에 머신러닝 기반의 이상 징후 탐지 알고리즘(예: Isolation Forest, One-Class SVM)을 적용할 수 있다.
*   **데이터 소스 확장**: 현재는 임의의 데이터를 생성하지만, 실제 의료 데이터베이스나 파일에서 데이터를 로드하도록 확장할 수 있다.
*   **시각화**: 탐지된 이상 징후를 그래프 등으로 시각화하여 보여주는 기능을 추가할 수 있다.
*   **설정 파일**: 임계값과 같은 파라미터들을 코드 내부에 하드코딩하는 대신 외부 설정 파일에서 읽어오도록 개선할 수 있다.
