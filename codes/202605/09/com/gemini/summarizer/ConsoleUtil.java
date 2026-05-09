// ConsoleUtil.java
package com.gemini.summarizer;

import java.util.Scanner;

/**
 * 콘솔 입출력을 위한 유틸리티 클래스입니다.
 * 사용자로부터 입력을 받거나 메시지를 출력하는 기능을 제공합니다.
 */
public class ConsoleUtil {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 사용자로부터 한 줄의 텍스트를 입력받습니다.
     * @param prompt 입력 안내 메시지
     * @return 입력받은 텍스트
     */
    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * 콘솔에 메시지를 출력합니다.
     * @param message 출력할 메시지
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * 리소스를 정리합니다. 애플리케이션 종료 시 호출해야 합니다.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
