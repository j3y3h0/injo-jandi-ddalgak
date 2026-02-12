// Main.java
// 합병 정렬 알고리즘의 동작을 시연하고 성능을 측정하는 메인 클래스이다.

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("자바 합병 정렬 데모 프로젝트이다.");

        // 정렬할 배열의 크기를 정의한다.
        int arraySize = 100000; // 예시로 10만 개의 숫자를 사용한다.
        int[] numbers = new int[arraySize];
        Random random = new Random();

        // 무작위 숫자로 배열을 채운다.
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = random.nextInt(arraySize * 10); // 0부터 (arraySize * 10 - 1)까지의 무작위 숫자
        }

        System.out.println("정렬 전 배열의 일부 (처음 10개):");
        printArrayPartial(numbers, 10);

        // MergeSort 객체를 생성한다.
        MergeSort mergeSorter = new MergeSort();

        // 정렬 시간 측정을 시작한다.
        long startTime = System.nanoTime();

        // 합병 정렬을 수행한다.
        mergeSorter.sort(numbers);

        // 정렬 시간 측정을 종료한다.
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // 밀리초 단위

        System.out.println("
정렬 후 배열의 일부 (처음 10개):");
        printArrayPartial(numbers, 10);

        // 배열이 올바르게 정렬되었는지 확인 (선택 사항)
        boolean isSorted = true;
        for (int i = 0; i < arraySize - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                isSorted = false;
                break;
            }
        }

        System.out.println("
배열 정렬 확인: " + (isSorted ? "성공적으로 정렬되었다." : "정렬 실패했다."));
        System.out.println("정렬에 걸린 시간: " + duration + " 밀리초이다.");
    }

    /**
     * 배열의 처음 'count'개 요소를 출력하는 헬퍼 메서드이다.
     * @param arr 출력할 배열
     * @param count 출력할 요소의 개수
     */
    private static void printArrayPartial(int[] arr, int count) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < Math.min(arr.length, count); i++) {
            sb.append(arr[i]);
            if (i < Math.min(arr.length, count) - 1) {
                sb.append(", ");
            }
        }
        if (arr.length > count) {
            sb.append(", ...");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
