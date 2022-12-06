import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Owem
 * @date 2022/12/6 15:55
 * @description TODO
 **/
public class Server {
    public static void main(String[] args) {
        try {
            int port = 6666;

            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("服务器启动..." + serverSocket.getLocalSocketAddress());  //服务器启动,打印本地地址

            //线程池
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

            while (true) {  //死循环
                Socket client = serverSocket.accept();
                System.out.println("有客户端连接到服务器:" + client.getRemoteSocketAddress());
                executorService.execute(new HandlerClient(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
