package com.example.data.model;

/**
 * CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다.
 * 여기서는 예시로 "이름", "나이", "점수" 세 필드를 가집니다.
 */
public class DataRecord {
    private String name; // 이름
    private int age;     // 나이
    private double score; // 점수

    public DataRecord(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
               "name='" + name + ''' +
               ", age=" + age +
               ", score=" + score +
               '}';
    }
}
