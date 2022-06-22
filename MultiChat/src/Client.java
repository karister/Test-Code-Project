import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author karister
 * @create 2021-09-07 21:09
 */
public class Client {
    public static boolean connect_statu = false;//连接状态
    private static int port = 55555;
    private static String host = "localhost";

    public static void main(String[] args) {
        while (!connect_statu){
            connect();
        }
    }

    private static void connect() {
        try {
            Socket socket = new Socket(host, port);
            connect_statu = true;
            System.out.println("连接成功！");
            OutputStream outputStream = socket.getOutputStream();
            new Thread(new SendMessage(outputStream,socket)).start();//开启发送消息进程
            new Thread(new ClientListenner(socket)).start();//开启消息监听进程
            new Thread(new heartPackage(outputStream)).start();//开启心跳包进程
        } catch (IOException e) {
            e.printStackTrace();
            connect_statu = false;
            System.out.println("连接服务器失败！");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reconnect(){
        while (!connect_statu){
            System.out.println("正在尝试重新链接.....");
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

class SendMessage implements Runnable{
    private final OutputStream outputStream;
    private final Socket socket;

    public SendMessage(OutputStream outputStream,Socket socket) {
        this.outputStream = outputStream;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("Client----->");
                String msg = scanner.nextLine();
                System.out.println(socket.isClosed());
                System.out.println(socket.getKeepAlive());
                outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }
        } catch (SocketException se){
            System.out.println(socket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientListenner implements Runnable{
    private final Socket socket;

    public ClientListenner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()){
            byte[] bytes = new byte[1024];
            int length;
            while (true){
                length = inputStream.read(bytes);
                System.out.println("<-----Server");
                String str = "receive" +
                        "(" +
                        socket.getRemoteSocketAddress().toString() +
                        "): " +
                        new String(bytes, 0, length, StandardCharsets.UTF_8);
                System.out.println(str);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

class heartPackage implements Runnable{
    private final OutputStream outputStream;

    public heartPackage(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        String heart = "heart";
        try {
            while (true) {
                outputStream.write(heart.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                //System.out.println("心跳包已发送");
                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
            try {
                outputStream.close();
                Client.connect_statu = false;
                Client.reconnect();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }

    }
}