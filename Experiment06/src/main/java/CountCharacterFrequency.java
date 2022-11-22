import java.io.*;
import java.util.Scanner;

/**
 * @author Owem
 * @date 2022/10/25 16:21
 * @description TODO
 **/
public class CountCharacterFrequency {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] frequency = new int[128];
        System.out.print("Enter an ASCII file: ");
        String path = "./Experiment06/";
        File file = new File(path + input.nextLine());

        if (!file.exists()) {
            System.out.println("文件不存在，正在创建ascii文件");
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                for (int i = 0; i < 1024; i++) {
                    outputStream.writeUTF((char) ((int) (Math.random() * 128)) + "");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            try {
                while (true) {
                    frequency[inputStream.readUTF().charAt(0)]++;
                }
            } catch (Exception ignored) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < frequency.length; i++) {
            if (i == 10) {
                System.out.print("character:换行符" + " freq: " + frequency[i] + "\t");
            } else {
                System.out.print("character:" + i + " freq: " + frequency[i] + "\t\t");
            }
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}
