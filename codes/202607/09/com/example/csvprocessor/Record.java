package com.example.csvprocessor;

/**
 * CSV 레코드 데이터를 나타내는 클래스입니다.
 * 각 행의 데이터를 객체 형태로 관리합니다.
 */
public class Record {
    private String name;
    private String category;
    private double value;

    public Record(String name, String category, double value) {
        this.name = name;
        this.category = category;
        this.value = value;
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Record{" +
               "name='" + name + ''' +
               ", category='" + category + ''' +
               ", value=" + value +
               '}';
    }
}
