package com.example.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * REST API 호출을 담당하는 클라이언트 클래스입니다.
 * HttpURLConnection을 사용하여 HTTP GET 요청을 보내고 응답을 받습니다.
 */
public class ApiClient {

    private static final int CONNECTION_TIMEOUT = 5000; // 5초
    private static final int READ_TIMEOUT = 5000;       // 5초

    /**
     * 지정된 URL로 HTTP GET 요청을 보내고 응답 본문을 문자열로 반환합니다.
     *
     * @param apiUrl 호출할 REST API의 URL
     * @return API 응답 본문 (JSON 문자열)
     * @throws IOException HTTP 연결 또는 데이터 읽기 중 오류 발생 시
     * @throws InterruptedException 현재 스레드가 인터럽트될 경우
     */
    public String get(String apiUrl) throws IOException, InterruptedException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP 응답 코드: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP OK (200)
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
            } else {
                // 오류 응답 처리 (예: 404 Not Found, 500 Internal Server Error)
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"))) {
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                        System.err.println("오류 응답: " + errorLine);
                    }
                }
                throw new IOException("API 호출 실패: HTTP 응답 코드 " + responseCode);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }
}
