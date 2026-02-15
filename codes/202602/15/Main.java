public class Main {
    public static void main(String[] args) {
        // URL Shortener 서비스 인스턴스 생성
        UrlShortener shortener = new UrlShortener();

        // 긴 URL들 정의
        String longUrl1 = "https://www.example.com/very/very/very/long/url/path/to/resource/12345";
        String longUrl2 = "https://www.google.com/search?q=java+url+shortener+example&oq=java+url+shortener+example&aqs=chrome..69i57j0l7.7471j0j7&sourceid=chrome&ie=UTF-8";
        String longUrl3 = "https://dev.gemini.google.com/";

        System.out.println("URL 단축 서비스 데모 시작합니다.");
        System.out.println("------------------------------------");

        // 첫 번째 URL 단축
        String shortCode1 = shortener.shortenURL(longUrl1);
        System.out.println("원본 URL 1: " + longUrl1);
        System.out.println("단축 코드 1: " + shortCode1);
        System.out.println("확장된 URL 1: " + shortener.expandURL(shortCode1));
        System.out.println("------------------------------------");

        // 두 번째 URL 단축
        String shortCode2 = shortener.shortenURL(longUrl2);
        System.out.println("원본 URL 2: " + longUrl2);
        System.out.println("단축 코드 2: " + shortCode2);
        System.out.println("확장된 URL 2: " + shortener.expandURL(shortCode2));
        System.out.println("------------------------------------");

        // 세 번째 URL 단축
        String shortCode3 = shortener.shortenURL(longUrl3);
        System.out.println("원본 URL 3: " + longUrl3);
        System.out.println("단축 코드 3: " + shortCode3);
        System.out.println("확장된 URL 3: " + shortener.expandURL(shortCode3));
        System.out.println("------------------------------------");

        // 이미 단축된 URL을 다시 단축 시도 (기존 코드 반환 예상)
        System.out.println("이미 단축된 URL 다시 단축 시도:");
        String shortCode1Again = shortener.shortenURL(longUrl1);
        System.out.println("원본 URL 1: " + longUrl1);
        System.out.println("단축 코드 1 (다시): " + shortCode1Again + " (이전과 동일해야 함)");
        System.out.println("------------------------------------");

        // 존재하지 않는 짧은 코드 확장 시도
        System.out.println("존재하지 않는 짧은 코드 확장 시도:");
        String nonExistentUrl = shortener.expandURL("nonexist");
        System.out.println("존재하지 않는 코드 'nonexist' 확장: " + (nonExistentUrl == null ? "찾을 수 없음" : nonExistentUrl));
        System.out.println("------------------------------------");

        System.out.println("URL 단축 서비스 데모 완료.");
    }
}
