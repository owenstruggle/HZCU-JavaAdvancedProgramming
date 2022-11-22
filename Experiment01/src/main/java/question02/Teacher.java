package question02;

/**
 * @author Owem
 * @date 2022/9/13 15:57
 * @description TODO
 **/
public class Teacher extends Person implements Cloneable{
    private String teaID;

    public Teacher() {
    }

    public Teacher(String teaID) {
        this.teaID = teaID;
    }

    public String getTeaID() {
        return teaID;
    }

    public void setTeaID(String teaID) {
        this.teaID = teaID;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaID='" + teaID + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.teaID.compareTo(((Teacher) o).getTeaID());
    }

    @Override
    public Teacher clone() {
        return new Teacher(this.teaID);
    }
}
