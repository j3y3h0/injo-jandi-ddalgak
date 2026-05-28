package com.example.api;

import com.example.api.model.Post;
import com.example.api.util.JsonProcessor;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) {
        System.out.println("REST API 클라이언트 애플리케이션을 시작합니다.");

        ApiClient apiClient = new ApiClient();
        JsonProcessor jsonProcessor = new JsonProcessor();

        try {
            // 1. API 호출
            System.out.println("
API URL: " + API_URL + " 로 요청을 보냅니다.");
            String jsonResponse = apiClient.get(API_URL);
            System.out.println("API 응답을 받았습니다. (일부 내용): " + jsonResponse.substring(0, Math.min(jsonResponse.length(), 200)) + "...");

            // 2. JSON 파싱
            System.out.println("
받은 JSON 응답을 Post 객체 리스트로 파싱합니다.");
            List<Post> posts = jsonProcessor.parseJsonToList(jsonResponse, Post.class);
            System.out.println("총 " + posts.size() + " 개의 Post 객체를 파싱했습니다.");

            // 3. 파싱된 데이터 출력 (상위 5개만)
            System.out.println("
파싱된 Post 데이터 (상위 5개):");
            posts.stream().limit(5).forEach(post -> {
                System.out.println("  ID: " + post.getId());
                System.out.println("  Title: " + post.getTitle());
                System.out.println("  Body: " + post.getBody().substring(0, Math.min(post.getBody().length(), 50)) + "...");
                System.out.println("  --------------------");
            });

        } catch (IOException e) {
            System.err.println("API 호출 또는 JSON 처리 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("API 호출 스레드 인터럽트: " + e.getMessage());
            Thread.currentThread().interrupt(); // 인터럽트 상태 복원
        }
        System.out.println("
REST API 클라이언트 애플리케이션을 종료합니다.");
    }
}
