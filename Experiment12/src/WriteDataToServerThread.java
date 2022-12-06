import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Owem
 * @date 2022/12/6 15:57
 * @description TODO
 **/
public class WriteDataToServerThread extends Thread{
    private final Socket client;
    public WriteDataToServerThread(Socket client){
        this.client = client;
    }
    @Override
    public void run(){
        try {
            OutputStream clientOutput = this.client.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            Scanner scanner = new Scanner(System.in);  //有客户端输入数据
            while(true){
                System.out.print("请输入>>");
                String data = scanner.nextLine(); //读数据
                writer.write(data+"\n");
                writer.flush();
                if(data.equals("bye")){
                    break;
                }
            }
            Thread.sleep(1000);
            this.client.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
