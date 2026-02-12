// MergeSort.java
// 합병 정렬 알고리즘을 구현하는 클래스이다.

public class MergeSort {

    /**
     * 배열을 합병 정렬로 정렬하는 메인 메서드이다.
     * @param arr 정렬할 정수 배열
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 배열이 비어있거나 요소가 하나이면 정렬할 필요가 없다.
        }
        int[] temp = new int[arr.length]; // 합병 과정에서 사용할 임시 배열
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * 배열의 특정 부분을 재귀적으로 합병 정렬하는 메서드이다.
     * @param arr 원본 배열
     * @param temp 임시 배열
     * @param left 배열의 시작 인덱스
     * @param right 배열의 끝 인덱스
     */
    private void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // 중간 인덱스 계산
            mergeSort(arr, temp, left, mid);      // 왼쪽 절반 정렬
            mergeSort(arr, temp, mid + 1, right); // 오른쪽 절반 정렬
            merge(arr, temp, left, mid, right);   // 정렬된 두 절반을 합병
        }
    }

    /**
     * 두 개의 정렬된 부분 배열을 하나로 합병하는 메서드이다.
     * @param arr 원본 배열
     * @param temp 임시 배열
     * @param left 왼쪽 부분 배열의 시작 인덱스
     * @param mid 왼쪽 부분 배열의 끝 인덱스 (mid + 1이 오른쪽 부분 배열의 시작 인덱스)
     * @param right 오른쪽 부분 배열의 끝 인덱스
     */
    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 원본 배열의 해당 부분을 임시 배열로 복사한다.
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;      // 왼쪽 부분 배열의 현재 인덱스
        int j = mid + 1;   // 오른쪽 부분 배열의 현재 인덱스
        int k = left;      // 원본 배열에 요소를 삽입할 현재 인덱스

        // 임시 배열의 두 부분 배열을 비교하여 원본 배열에 병합한다.
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // 왼쪽 부분 배열에 남은 요소들을 원본 배열에 복사한다.
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }

        // 오른쪽 부분 배열에 남은 요소들은 이미 제자리에 있거나 (왼쪽이 모두 복사된 후)
        // 위 while 문에서 처리되었으므로 추가 복사가 필요 없다.
        // 하지만 혹시 모를 경우를 대비하여 명시적으로 작성할 수도 있다 (현재는 생략).
        // while (j <= right) {
        //     arr[k] = temp[j];
        //     j++;
        //     k++;
        // }
    }
}
