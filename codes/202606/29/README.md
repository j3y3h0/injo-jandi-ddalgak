# 데이터 분석 미니 프로젝트

## 프로젝트 개요

이 프로젝트는 CSV 파일을 읽어 기본적인 데이터 처리 및 분석을 수행하는 간단한 자바 애플리케이션입니다. Apache Commons CSV 라이브러리를 사용하여 CSV 파일을 파싱하고, 데이터를 객체로 변환한 뒤, 간단한 통계 정보를 계산하는 예제를 제공합니다. 산업에서 널리 사용되는 라이브러리와 기본적인 데이터 처리 로직을 이해하는 데 도움이 될 것입니다.

## 코드 구조

*   `Main.java`: 애플리케이션의 진입점입니다. `DataProcessor`를 초기화하고 CSV 파일을 처리합니다.
*   `DataModel.java`: CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다.
*   `DataProcessor.java`: CSV 파일 읽기, 데이터 파싱, 통계 계산 등 핵심 비즈니스 로직을 포함합니다.

## 실행 방법

이 프로젝트는 Maven 또는 Gradle을 사용하여 빌드 및 실행할 수 있습니다. 여기서는 Maven을 기준으로 설명합니다.

### 1. `pom.xml` 파일 생성

프로젝트 루트 디렉터리에 `pom.xml` 파일을 생성하여 Apache Commons CSV 의존성을 추가해야 합니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>csv-analyzer</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Apache Commons CSV -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-csv</artifactId>
            <version>1.9.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <mainClass>Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2. 컴파일 및 실행

`pom.xml` 파일이 준비되었다면, 다음 명령어를 사용하여 프로젝트를 컴파일하고 실행할 수 있습니다.

```bash
# 의존성 다운로드 및 컴파일
mvn clean install

# 애플리케이션 실행
mvn exec:java
```

### 3. 샘플 CSV 파일

프로젝트 루트 디렉터리에 `sample.csv` 파일을 생성하여 테스트할 수 있습니다. 예시:

```csv
Name,Age,Score
Alice,30,85.5
Bob,24,92.0
Charlie,35,78.2
David,29,88.1
Eve,22,95.3
```

## 코드 설명

각 자바 파일에 대한 자세한 내용은 해당 파일의 주석을 참조하여 주십시오.
