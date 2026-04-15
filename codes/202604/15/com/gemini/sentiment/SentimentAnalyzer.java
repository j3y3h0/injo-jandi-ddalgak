// SentimentAnalyzer.java
// 문장의 감성을 분석하는 핵심 로직을 담은 클래스 (산업용 알고리즘 개념 활용)

package com.gemini.sentiment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SentimentAnalyzer {

    // 긍정적인 단어 집합 (산업용 라이브러리/데이터베이스에서 가져온다고 가정)
    private static final Set<String> POSITIVE_WORDS = new HashSet<>(Arrays.asList(
            "좋다", "최고", "훌륭하다", "만족", "기쁘다", "행복", "사랑", "성공", "매우 좋다", "아름답다"
    ));

    // 부정적인 단어 집합 (산업용 라이브러리/데이터베이스에서 가져온다고 가정)
    private static final Set<String> NEGATIVE_WORDS = new HashSet<>(Arrays.asList(
            "나쁘다", "최악", "끔찍하다", "불만", "슬프다", "고통", "실패", "문제", "싫다", "역겹다"
    ));

    public SentimentAnalyzer() {
        // 실제 시스템에서는 감성 사전 또는 머신러닝 모델을 로드하는 로직이 들어갈 수 있습니다.
        System.out.println("감성 분석기 초기화 중...");
    }

    /**
     * 주어진 문장의 감성을 분석합니다.
     * 이 메서드는 실제 산업에서 사용되는 복잡한 NLP(자연어 처리) 알고리즘의
     * 간소화된 버전을 나타냅니다. 여기서는 키워드 매칭을 사용하지만,
     * 실제로는 통계적 모델(예: 나이브 베이즈), 머신러닝 모델(예: SVM, 딥러닝),
     * 또는 렉시콘 기반(감성 사전) 접근 방식이 사용됩니다.
     *
     * @param sentence 분석할 문장 객체
     * @return 감성이 설정된 문장 객체
     */
    public Sentence analyze(Sentence sentence) {
        String text = sentence.getText().toLowerCase(); // 소문자로 변환하여 분석 효율성 증대
        int positiveScore = 0;
        int negativeScore = 0;

        // 문장을 단어 단위로 분리 (간단한 공백 기준 분리, 실제로는 형태소 분석기가 필요)
        String[] words = text.split("\s+");

        for (String word : words) {
            // 단어의 불필요한 구두점 제거 (예: 마침표, 쉼표 등)
            word = word.replaceAll("[.,!?]", "");

            if (POSITIVE_WORDS.contains(word)) {
                positiveScore++;
            } else if (NEGATIVE_WORDS.contains(word)) {
                negativeScore++;
            }
        }

        if (positiveScore > negativeScore) {
            sentence.setSentiment(Sentiment.POSITIVE);
        } else if (negativeScore > positiveScore) {
            sentence.setSentiment(Sentiment.NEGATIVE);
        } else {
            sentence.setSentiment(Sentiment.NEUTRAL); // 점수가 같거나 키워드가 없으면 중립
        }
        return sentence;
    }
}
