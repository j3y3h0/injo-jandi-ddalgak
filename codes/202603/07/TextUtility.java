// TextUtility.java
// 텍스트 정규화 및 정리 기능을 제공하는 유틸리티 클래스이다.

import java.util.regex.Pattern;

public class TextUtility {

    /**
     * 입력된 텍스트를 소문자로 변환하고, 알파벳과 공백을 제외한 모든 문자를 제거하여 정규화한다.
     * 이 메서드는 텍스트 분석 전에 데이터를 깨끗하게 준비하는 데 사용된다.
     *
     * @param text 정규화할 원본 텍스트이다.
     * @return 정규화된 텍스트를 반환한다.
     */
    public static String normalizeText(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        // 텍스트를 소문자로 변환한다.
        String lowerCaseText = text.toLowerCase();
        // 알파벳(a-z), 한글(가-힣), 공백만 남기고 모든 문자를 제거한다. (한글을 포함할 경우)
        // 여기서는 예시로 영어 알파벳과 공백만 처리하도록 한다.
        // 정규식 [^a-z\s]는 'a-z' 범위의 알파벳과 공백('\s')이 아닌 모든 문자를 의미한다.
        return Pattern.compile("[^a-z\s]").matcher(lowerCaseText).replaceAll(" ");
    }

    /**
     * 텍스트에서 여러 개의 공백을 하나의 공백으로 줄인다.
     *
     * @param text 공백을 줄일 원본 텍스트이다.
     * @return 공백이 정규화된 텍스트를 반환한다.
     */
    public static String consolidateSpaces(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        // 하나 이상의 공백을 하나의 공백으로 대체한다.
        return Pattern.compile("\s+").matcher(text).replaceAll(" ").trim();
    }
}
