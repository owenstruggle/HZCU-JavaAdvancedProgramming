import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author Owem
 * @date 2022/10/25 15:39
 * @description TODO
 **/
public class CreateTextFile {
    private static void addNumbers(File file) {
        int number;
        try (FileWriter fw = new FileWriter(file, true)) {
            for (int i = 0; i < 100; i++) {
                number = (int)(Math.random() * (100 + 1));
                fw.write(number + " ");
            }
            fw.write("\n");
            System.out.println("写入完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String path = "./Experiment06/";
        File file = new File(path + "高级程序设计.txt");

        if (!file.exists()) {
            System.out.println("文件不存在, 正在创建...");
            try {
                if (file.createNewFile()) {
                    System.out.println("文件创建成功");
                } else {
                    System.out.println("文件创建失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        addNumbers(file);
    }
}
