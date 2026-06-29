// DataModel.java
/**
 * CSV 파일의 각 행을 나타내는 데이터 모델 클래스입니다.
 * 이 클래스는 이름, 나이, 점수 정보를 저장합니다.
 */
public class DataModel {
    private String name;
    private int age;
    private double score;

    /**
     * DataModel의 생성자입니다.
     * @param name 이름
     * @param age 나이
     * @param score 점수
     */
    public DataModel(String name, int age, double score) {
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

    /**
     * 객체의 문자열 표현을 반환합니다.
     * @return 객체 정보가 포함된 문자열
     */
    @Override
    public String toString() {
        return "DataModel{" +
               "name='" + name + ''' +
               ", age=" + age +
               ", score=" + score +
               '}';
    }
}
