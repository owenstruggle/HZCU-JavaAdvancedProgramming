import java.io.IOException;
import java.net.Socket;


/**
 * @author Owem
 * @date 2022/12/6 15:56
 * @description TODO
 **/
public class Client {
    public static void main(String[] args) {
        try {
            //读取地址
            String host = "127.0.0.1";
            //读取端口号
            int port = 6666;

            Socket client = new Socket(host, port); //先写数据再读数据,读写线程分离
            new ReadDataFromServerThread(client).start();//启动读线程
            new WriteDataToServerThread(client).start();//启动写线程
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
