package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * REST API에서 받아오는 'Post' 리소스의 데이터 모델 클래스입니다.
 * Jackson 라이브러리를 사용하여 JSON 응답을 Java 객체로 매핑합니다.
 */
public class Post {
    private final int userId;
    private final int id;
    private final String title;
    private final String body;

    // Jackson이 JSON을 객체로 변환할 때 사용할 생성자입니다.
    // @JsonProperty 어노테이션은 JSON 필드 이름과 Java 필드 이름을 매핑합니다.
    @JsonCreator
    public Post(
            @JsonProperty("userId") int userId,
            @JsonProperty("id") int id,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    // 각 필드에 대한 Getter 메소드입니다.
    // Jackson이 객체를 JSON으로 변환할 때도 사용될 수 있습니다.
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" +
               "userId=" + userId +
               ", id=" + id +
               ", title='" + title + ''' +
               ", body='" + body + ''' +
               '}';
    }
}
