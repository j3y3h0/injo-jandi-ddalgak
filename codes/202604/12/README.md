# 이름 정렬 애플리케이션

## 프로젝트 개요
이 프로젝트는 텍스트 파일에서 이름을 읽어 알파벳순으로 정렬한 후, 정렬된 이름을 다른 텍스트 파일에 쓰는 간단한 자바 애플리케이션입니다. 기본적인 파일 입출력과 자바 컬렉션의 정렬 기능을 사용하여 데이터를 처리하는 방법을 보여줍니다.

## 코드 구조
*   `NameSorter.java`: 애플리케이션의 메인 클래스입니다. `main` 메서드를 포함하며, `NameReader`를 사용하여 이름을 읽고, `Collections.sort`를 사용하여 정렬하며, `NameWriter`를 사용하여 결과를 파일에 씁니다.
*   `NameReader.java`: 텍스트 파일에서 이름을 읽어와 `List<String>` 형태로 반환하는 유틸리티 클래스입니다.
*   `NameWriter.java`: `List<String>` 형태의 이름들을 텍스트 파일에 쓰는 유틸리티 클래스입니다.
*   `data.txt`: 애플리케이션 실행을 위한 샘플 입력 파일입니다. 한 줄에 하나의 이름이 적혀있습니다.

## 실행 방법
1.  **컴파일**: 터미널을 열고 프로젝트 디렉터리로 이동한 후, 다음 명령어를 실행하여 자바 소스 파일들을 컴파일합니다.
    ```bash
    javac NameSorter.java NameReader.java NameWriter.java
    ```
2.  **실행**: 컴파일이 완료되면, 다음 명령어를 사용하여 애플리케이션을 실행합니다. `input.txt`는 읽어올 파일의 경로이며, `output.txt`는 정렬된 이름이 저장될 파일의 경로입니다. `data.txt` 파일을 입력으로 사용할 수 있습니다.
    ```bash
    java NameSorter data.txt sorted_names.txt
    ```
    예를 들어, `data.txt`의 내용을 정렬하여 `sorted_names.txt`에 저장하려면 위와 같이 실행합니다.

## 예시
**data.txt 내용:**
```
Charlie
Alice
Bob
David
```

**실행 명령어:**
```bash
java NameSorter data.txt sorted_names.txt
```

**sorted_names.txt 내용 (실행 후):**
```
Alice
Bob
Charlie
David
```
