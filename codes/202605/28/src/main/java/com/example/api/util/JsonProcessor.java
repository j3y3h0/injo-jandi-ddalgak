package com.example.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;

/**
 * JSON 문자열을 Java 객체로 변환하거나 그 반대로 변환하는 유틸리티 클래스입니다.
 * Jackson ObjectMapper를 사용합니다.
 */
public class JsonProcessor {
    private final ObjectMapper objectMapper;

    public JsonProcessor() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * JSON 문자열을 단일 Java 객체로 파싱합니다.
     *
     * @param <T> 반환될 객체의 타입
     * @param jsonString 파싱할 JSON 문자열
     * @param valueType 변환할 Java 클래스 타입
     * @return 파싱된 Java 객체
     * @throws IOException JSON 처리 중 오류 발생 시
     */
    public <T> T parseJsonToObject(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }

    /**
     * JSON 배열 문자열을 Java 객체 리스트로 파싱합니다.
     *
     * @param <T> 리스트 요소의 타입
     * @param jsonString 파싱할 JSON 배열 문자열
     * @param valueTypeRef 변환할 Java 리스트의 TypeReference (예: new TypeReference<List<MyClass>>() {})
     * @return 파싱된 Java 객체 리스트
     * @throws IOException JSON 처리 중 오류 발생 시
     */
    public <T> List<T> parseJsonToList(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

    /**
     * Java 객체를 JSON 문자열로 직렬화합니다.
     *
     * @param object 직렬화할 Java 객체
     * @return JSON 문자열
     * @throws IOException JSON 처리 중 오류 발생 시
     */
    public String writeObjectToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
