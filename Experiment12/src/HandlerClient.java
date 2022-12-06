import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Owem
 * @date 2022/12/6 15:56
 * @description TODO
 **/
public class HandlerClient implements Runnable {

    /**
     * 维护所有的连接到服务端的客户端对象
     */
    private static final Map<String, Socket> ONLINE_CLIENT_MAP = new ConcurrentHashMap<>();  //静态是为了不让对象变化,final不让对象被修改,ConcurrentHashMap是线程安全的类
    private final Socket client;

    public HandlerClient(Socket client) {  //HandlerClient在多线程环境下调用,所以会产生资源竞争,用一个并发的HashMap
        this.client = client;          //为了防止变量被修改,用final修饰
    }

    public void run() {
        try {
            InputStream clientInput = client.getInputStream(); //获取客户端的数据流
            Scanner scanner = new Scanner(clientInput); //字节流转字符流

            /*
             消息是按行读取
              1.注册: register:<username> 例如: register:张三
              2.群聊: groupChat:<message> 例如: groupChat:大家好
              3.查询用户: users list
              4.私聊: <user>:<message> 例如: 张三:你好,还钱
              5.退出: bye
             */

            while (true) {
                // 读数据,按行读
                String data = scanner.nextLine();
                if (data.startsWith("register:")) {
                    //注册
                    String userName = data.split(":")[1];
                    register(userName);
                    continue;
                }

                if (data.startsWith("groupChat:")) {
                    String message = data.split(":")[1];
                    groupChat(message);
                    continue;
                }

                if (data.equals("users list")) {
                    usersList();
                    continue;
                }

                if (data.contains(":")) {
                    String[] segments = data.split(":");
                    String targetUserName = segments[0]; //取目标用户名
                    String message = segments[1];   //取发送的消息内容
                    privateChat(targetUserName, message);
                }

                if (data.equals("bye")) {
                    bye();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当前客户端退出
     */
    private void bye() {
        for (Map.Entry<String, Socket> entry : ONLINE_CLIENT_MAP.entrySet()) {
            Socket target = entry.getValue();
            if (target.equals(this.client)) {   //在在线用户中找到自己并且移除
                this.sendMessage(this.client, "您已下线...", false);
                System.out.println("{ " + getCurrentUserName() + " }退出聊天室");
                ONLINE_CLIENT_MAP.remove(entry.getKey());
                break;
            }
        }
        printOnlineClient();//打印当前用户
    }

    private void usersList() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, Socket> entry : ONLINE_CLIENT_MAP.entrySet()) {
            if (entry.getValue().equals(this.client)) {
                continue;
            }
            s.append("{ ").append(entry.getKey()).append(" } ");
        }
        if (!s.isEmpty()) {
            this.sendMessage(this.client, "当前在线用户: " + s, false);
        } else {
            this.sendMessage(this.client, "当前没有其他用户", false);
        }

    }

    private String getCurrentUserName() {
        for (Map.Entry<String, Socket> entry : ONLINE_CLIENT_MAP.entrySet()) {
            Socket target = entry.getValue(); //getvalue得到Socket对象
            if (target.equals(this.client)) { //排除群聊的时候自己给自己发消息的情况
                return entry.getKey();
            }
        }
        return "";
    }

    /**
     * 私聊,给targetUserName发送message消息
     *
     * @param targetUserName
     * @param message
     */
    private void privateChat(String targetUserName, String message) {
        Socket target = ONLINE_CLIENT_MAP.get(targetUserName);//获取目标用户名
        if (target == null) {
            this.sendMessage(this.client, "没有这个人" + targetUserName, false);
        } else {
            this.sendMessage(target, message, true);
        }
    }

    /**
     * 群聊,发送message
     *
     * @param message
     */
    private void groupChat(String message) {
        for (Map.Entry<String, Socket> entery : ONLINE_CLIENT_MAP.entrySet()) {
            Socket target = entery.getValue(); //getvalue得到Socket对象
            if (target.equals(this.client)) {
                continue;            //排除群聊的时候自己给自己发消息的情况
            }
            this.sendMessage(target, message, true);
        }
    }

    /**
     * 以userName为key注册当前用户(Socket client)
     *
     * @param userName
     */
    private void register(String userName) {
        if (ONLINE_CLIENT_MAP.containsKey(userName)) {
            this.sendMessage(this.client, "您已经注册过了,无需重复注册", false);
        } else {
            ONLINE_CLIENT_MAP.put(userName, this.client);
            printOnlineClient();
            this.sendMessage(this.client, "恭喜{ " + userName + " }注册成功\n", false);
        }
    }

    private void sendMessage(Socket target, String message, boolean prefix) {
        OutputStream clientOutput;      //value是每一个客户端
        try {
            clientOutput = target.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            if (prefix) {
                String currentUserName = this.getCurrentUserName();
                writer.write("<" + currentUserName + ">" + message + "\n");
            } else {
                writer.write(message + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印在线客户端
     */
    private void printOnlineClient() {
        System.out.println("当前在线人数:" + ONLINE_CLIENT_MAP.size() + "," + "用户名如下列表:");
        System.out.println("--------------------");
        for (String userName : ONLINE_CLIENT_MAP.keySet()) {  //Map的key为用户名
            System.out.println("{ " + userName + " }");
        }
        System.out.println("--------------------");
    }
}
