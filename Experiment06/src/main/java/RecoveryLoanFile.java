import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Owem
 * @date 2022/10/25 16:16
 * @description TODO
 **/
public class RecoveryLoanFile {
    public static void main(String[] args) {
        String path = "./Experiment06/";
        double sum = 0;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path + "LoanInfo.dat"))) {
            while (true) {
                Loan[] newLoans = (Loan[]) (input.readObject());
                for (Loan newLoan : newLoans) {
                    System.out.println(newLoan.toString());
                    sum += newLoan.getDeposit();
                }
            }
        } catch (Exception ex) {
            System.out.println("完成, 总金额: " + sum);
        }
    }
}
