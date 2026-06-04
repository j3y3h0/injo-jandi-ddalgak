package com.example.dataprocessor;

/**
 * 간단한 데이터 모델을 정의합니다.
 * 이 모델은 이름과 값을 가집니다.
 */
public class DataModel {
    private String name;
    private double value;

    public DataModel(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataModel{" +
               "name='" + name + ''' +
               ", value=" + value +
               '}';
    }
}
