package question02;

/**
 * @author Owem
 * @date 2022/9/13 15:57
 * @description TODO
 **/
public class Student extends Person implements Cloneable {
    private String stuID;

    public Student() {
    }

    public Student(String stuID) {
        this.stuID = stuID;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuID='" + stuID + '\'' +
                '}';
    }

    @Override
    public Student clone() {
        return new Student(this.stuID);
    }

    @Override
    public int compareTo(Object o) {
        return this.stuID.compareTo(((Student) o).stuID);
    }
}
