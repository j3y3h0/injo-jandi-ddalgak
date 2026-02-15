import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UrlShortener {

    // 긴 URL을 짧은 코드로 매핑하는 맵
    private final Map<String, String> longUrlToShortCode;
    // 짧은 코드를 긴 URL로 매핑하는 맵
    private final Map<String, String> shortCodeToLongUrl;
    // 고유한 ID를 생성하기 위한 카운터
    private final AtomicLong counter;
    // 짧은 코드를 생성할 때 사용할 문자 집합 (알파벳 소문자, 대문자, 숫자)
    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = BASE62_CHARS.length();

    public UrlShortener() {
        longUrlToShortCode = new HashMap<>();
        shortCodeToLongUrl = new HashMap<>();
        counter = new AtomicLong(1000000000L); // 10억부터 시작하여 짧은 코드가 더 길어 보이게 함
    }

    /**
     * 긴 URL을 짧은 코드로 변환합니다.
     * 이미 변환된 URL인 경우 기존 짧은 코드를 반환합니다.
     * @param longUrl 짧게 만들 원본 URL
     * @return 생성되거나 기존에 존재하는 짧은 코드
     */
    public String shortenURL(String longUrl) {
        // 이미 짧은 코드가 존재하면 반환
        if (longUrlToShortCode.containsKey(longUrl)) {
            return longUrlToShortCode.get(longUrl);
        }

        // 새로운 고유 ID 생성
        long id = counter.getAndIncrement();
        // ID를 짧은 코드로 변환
        String shortCode = idToShortCode(id);

        // 맵에 저장
        longUrlToShortCode.put(longUrl, shortCode);
        shortCodeToLongUrl.put(shortCode, longUrl);

        return shortCode;
    }

    /**
     * 짧은 코드를 원래 긴 URL로 확장합니다.
     * @param shortCode 확장할 짧은 코드
     * @return 원래 긴 URL, 존재하지 않으면 null
     */
    public String expandURL(String shortCode) {
        return shortCodeToLongUrl.get(shortCode);
    }

    /**
     * 고유 ID를 Base62 문자열로 변환하여 짧은 코드를 생성합니다.
     * @param id 변환할 숫자 ID
     * @return Base62로 인코딩된 짧은 코드 문자열
     */
    private String idToShortCode(long id) {
        StringBuilder shortCode = new StringBuilder();
        // ID가 0이 될 때까지 Base62 변환
        while (id > 0) {
            shortCode.insert(0, BASE62_CHARS.charAt((int) (id % BASE)));
            id /= BASE;
        }
        // 짧은 코드가 너무 짧으면 패딩 추가 (선택 사항, 여기서는 최소 길이 보장)
        while (shortCode.length() < 6) { // 최소 6자리 길이 유지 (조절 가능)
            shortCode.insert(0, BASE62_CHARS.charAt(0)); // '0'으로 패딩
        }
        return shortCode.toString();
    }

    /**
     * 짧은 코드를 다시 고유 ID로 변환합니다. (현재 예제에서는 사용되지 않지만, 디버깅 등에 유용)
     * @param shortCode 변환할 짧은 코드 문자열
     * @return 디코딩된 숫자 ID
     */
    private long shortCodeToId(String shortCode) {
        long id = 0;
        for (char c : shortCode.toCharArray()) {
            id = id * BASE + BASE62_CHARS.indexOf(c);
        }
        return id;
    }
}
