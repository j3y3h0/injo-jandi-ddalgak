import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 텍스트 파일에서 이름을 읽어 정렬하고 다른 파일에 쓰는 메인 애플리케이션 클래스입니다.
 * 이 클래스는 NameReader와 NameWriter 유틸리티를 사용하여 전체 프로세스를 관리합니다.
 */
public class NameSorter {

    public static void main(String[] args) {
        // 프로그램 실행 시 입력 파일 경로와 출력 파일 경로 두 개의 인수가 필요합니다.
        if (args.length != 2) {
            System.out.println("사용법: java NameSorter <입력 파일 경로> <출력 파일 경로>");
            System.out.println("예시: java NameSorter input.txt output.txt");
            return;
        }

        String inputFilePath = args[0];  // 첫 번째 인수는 입력 파일 경로입니다.
        String outputFilePath = args[1]; // 두 번째 인수는 출력 파일 경로입니다.

        try {
            // NameReader를 사용하여 입력 파일에서 이름 목록을 읽어옵니다.
            List<String> names = NameReader.readNames(inputFilePath);
            System.out.println("총 " + names.size() + "개의 이름을 "" + inputFilePath + ""에서 읽었습니다.");

            // Collections.sort를 사용하여 이름 목록을 알파벳순으로 정렬합니다.
            Collections.sort(names);
            System.out.println("이름 목록을 성공적으로 정렬했습니다.");

            // NameWriter를 사용하여 정렬된 이름 목록을 출력 파일에 씁니다.
            NameWriter.writeNames(outputFilePath, names);
            System.out.println("정렬된 이름 목록을 "" + outputFilePath + ""에 성공적으로 기록했습니다.");

        } catch (IOException e) {
            // 파일 입출력 오류 발생 시 오류 메시지를 출력합니다.
            System.err.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
        } catch (Exception e) {
            // 그 외 예상치 못한 오류 발생 시 오류 메시지를 출력합니다.
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
