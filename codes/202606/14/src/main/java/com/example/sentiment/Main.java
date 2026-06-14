package com.example.sentiment;

import java.util.Scanner;

/**
 * 감성 분석 프로그램의 메인 진입점입니다.
 * 사용자로부터 텍스트를 입력받아 감성을 분석하고 결과를 출력합니다.
 */
public class Main {
    public static void main(String[] args) {
        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("감성 분석기입니다. 분석할 텍스트를 입력해주세요 (종료하려면 'exit' 입력):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input.trim())) {
                System.out.println("감성 분석기를 종료합니다.");
                break;
            }

            SentimentResult result = analyzer.analyze(input);
            System.out.println("분석 결과: " + getKoreanSentiment(result));
            System.out.println();
        }

        scanner.close();
    }

    /**
     * SentimentResult 열거형 값을 한글 문자열로 변환합니다.
     *
     * @param result SentimentResult 값
     * @return 해당 감성의 한글 표현
     */
    private static String getKoreanSentiment(SentimentResult result) {
        switch (result) {
            case POSITIVE:
                return "긍정적";
            case NEGATIVE:
                return "부정적";
            case NEUTRAL:
                return "중립적";
            default:
                return "알 수 없음";
        }
    }
}