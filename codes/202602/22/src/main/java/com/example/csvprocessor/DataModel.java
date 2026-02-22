package com.example.csvprocessor;

/**
 * 학생 성적 데이터를 나타내는 모델 클래스이다.
 * 각 인스턴스는 CSV 파일의 한 행에 해당한다.
 */
public class DataModel {
    private String name;    // 학생 이름
    private String subject; // 과목
    private int score;      // 점수

    public DataModel(String name, String subject, int score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    // Getter 메서드들
    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public int getScore() {
        return score;
    }

    // Setter 메서드들 (필요하다면 추가)
    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DataModel{" +
               "name='" + name + ''' +
               ", subject='" + subject + ''' +
               ", score=" + score +
               '}';
    }
}
