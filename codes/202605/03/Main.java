// Main.java
// 감성 분석기 프로그램을 실행하는 메인 클래스입니다.

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 간단한 텍스트 감성 분석기 ---");

        // 감성 사전을 초기화합니다.
        SentimentLexicon lexicon = new SentimentLexicon();
        // 감성 분석기를 초기화합니다.
        SentimentAnalyzer analyzer = new SentimentAnalyzer(lexicon);

        // 분석할 예시 문장들입니다.
        String[] sentences = {
            "이 영화 정말 좋다! 최고였다.",
            "오늘 날씨가 너무 나쁘다. 기분이 슬프다.",
            "점심으로 뭘 먹을까? 그냥 그렇다.",
            "정말 훌륭한 아이디어입니다. 성공할 것입니다.",
            "문제가 발생했다. 모든 것이 어렵다.",
            "사랑스러운 하루였습니다. 행복합니다."
        };

        // 각 문장의 감성을 분석하고 결과를 출력합니다.
        for (String sentence : sentences) {
            int score = analyzer.analyze(sentence);
            String label = analyzer.getSentimentLabel(score);
            System.out.printf("문장: "%s"
", sentence);
            System.out.printf("  감성 점수: %d, 감성: %s
", score, label);
            System.out.println("------------------------------------");
        }

        System.out.println("--- 감성 분석 종료 ---");
    }
}
