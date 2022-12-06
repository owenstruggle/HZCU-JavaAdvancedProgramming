import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Owem
 * @date 2022/12/6 15:58
 * @description TODO
 **/
public class ReadDataFromServerThread extends Thread {
    private final Socket client;

    public ReadDataFromServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream clientInput = this.client.getInputStream();
            Scanner scanner = new Scanner(clientInput);
            while (true) {
                String data = scanner.nextLine();//按行读数据
                if (data.startsWith("<")) {
                    System.out.println(data);
                } else {
                    System.out.println("<服务端>" + data);
                }
                if (data.equals("您已下线...")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
