// Main.java
public class Main {
    public static void main(String[] args) {
        // SentimentAnalyzer 객체를 생성한다.
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        // 분석할 텍스트 예시들이다.
        String text1 = "이 영화 정말 좋다! 최고이다.";
        String text2 = "서비스가 너무 나빴다. 정말 실망스럽다.";
        String text3 = "그냥 그렇다. 특별히 좋지도 나쁘지도 않다.";
        String text4 = "정말 훌륭한 경험이었다. 다시 방문하고 싶다.";
        String text5 = "최악의 하루였다. 모든 것이 문제였다.";

        // 각 텍스트의 감성을 분석하고 결과를 출력한다.
        System.out.println("텍스트: "" + text1 + "" -> 감성: " + analyzer.analyze(text1)); // POSITIVE
        System.out.println("텍스트: "" + text2 + "" -> 감성: " + analyzer.analyze(text2)); // NEGATIVE
        System.out.println("텍스트: "" + text3 + "" -> 감성: " + analyzer.analyze(text3)); // NEUTRAL
        System.out.println("텍스트: "" + text4 + "" -> 감성: " + analyzer.analyze(text4)); // POSITIVE
        System.out.println("텍스트: "" + text5 + "" -> 감성: " + analyzer.analyze(text5)); // NEGATIVE
    }
}
