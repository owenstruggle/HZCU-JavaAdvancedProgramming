import java.io.Serializable;

/**
 * @author Owem
 * @date 2022/10/25 16:02
 * @description TODO
 **/
public class Loan implements Serializable {
    private String id;
    private double deposit;

    public Loan() {
    }

    public Loan(String id, double deposit) {
        this.id = id;
        this.deposit = deposit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id='" + id + '\'' +
                ", deposit=" + deposit +
                '}';
    }
}
