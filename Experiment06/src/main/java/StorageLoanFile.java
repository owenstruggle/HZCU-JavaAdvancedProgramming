import java.io.*;

/**
 * @author Owem
 * @date 2022/10/25 16:01
 * @description TODO
 **/
public class StorageLoanFile {
    public static void main(String[] args) {
        Loan[] loanList = new Loan[5];
        for (int i = 0; i < 5; i++) {
            loanList[i] = new Loan("L" + i, Math.random() * 1000);
        }

        String path = "./Experiment06/";
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path + "LoanInfo.dat"))) {
            output.writeObject(loanList);
            System.out.println("写入完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
