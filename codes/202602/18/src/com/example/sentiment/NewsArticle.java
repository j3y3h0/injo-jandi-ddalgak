// NewsArticle.java
package com.example.sentiment;

/**
 * 뉴스 기사 정보를 담는 간단한 POJO (Plain Old Java Object) 클래스이다.
 */
public class NewsArticle {
    private String title;
    private String content;

    public NewsArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "제목: '" + title + ''' +
               "
내용 (일부): '" + (content.length() > 100 ? content.substring(0, 100) + "..." : content) + ''';
    }
}