// SentimentAnalyzer.java
// 텍스트의 감성을 분석하는 주요 로직을 담고 있는 클래스입니다.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentimentAnalyzer {
    private final SentimentLexicon lexicon;

    public SentimentAnalyzer(SentimentLexicon lexicon) {
        this.lexicon = lexicon;
    }

    /**
     * 입력된 텍스트의 감성을 분석하여 점수를 반환합니다.
     * 점수가 양수이면 긍정, 음수이면 부정, 0이면 중립으로 판단합니다.
     * @param text 분석할 텍스트
     * @return 감성 점수
     */
    public int analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0; // 빈 텍스트는 중립으로 처리합니다.
        }

        int sentimentScore = 0;
        // 한글, 영어, 숫자를 포함하는 단어 단위로 분리하기 위한 정규식.
        // 여기서는 간단하게 공백으로 분리하지만, 실제로는 형태소 분석기가 필요합니다.
        Pattern p = Pattern.compile("[가-힣A-Za-z0-9]+");
        Matcher m = p.matcher(text);

        while (m.find()) {
            String word = m.group();
            // 단어의 감성 확인
            if (lexicon.isPositive(word)) {
                sentimentScore++;
            } else if (lexicon.isNegative(word)) {
                sentimentScore--;
            }
        }
        return sentimentScore;
    }

    /**
     * 감성 점수를 기반으로 감성 레이블을 반환합니다.
     * @param score 감성 점수
     * @return "긍정", "부정", "중립" 중 하나
     */
    public String getSentimentLabel(int score) {
        if (score > 0) {
            return "긍정";
        } else if (score < 0) {
            return "부정";
        } else {
            return "중립";
        }
    }
}
