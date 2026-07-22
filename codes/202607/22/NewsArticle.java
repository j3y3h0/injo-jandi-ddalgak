// NewsArticle.java
// 뉴스 기사 정보를 담는 데이터 클래스이다.

import java.util.List;
import java.util.Objects;

public class NewsArticle {
    private final String id;
    private final String title;
    private final String content;
    private final List<String> topics; // 기사의 주제 목록

    public NewsArticle(String id, String title, String content, List<String> topics) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.topics = topics;
    }

    // 기사 ID를 반환한다.
    public String getId() {
        return id;
    }

    // 기사 제목을 반환한다.
    public String getTitle() {
        return title;
    }

    // 기사 내용을 반환한다.
    public String getContent() {
        return content;
    }

    // 기사 주제 목록을 반환한다.
    public List<String> getTopics() {
        return topics;
    }

    @Override
    public String toString() {
        return "뉴스 기사 [ID='" + id + ''' +
               ", 제목='" + title + ''' +
               ", 주제=" + topics +
               ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsArticle that = (NewsArticle) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
