import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author karister
 * @create 2021-09-07 20:48
 */
public class Server {
    public static int connect_num;//连接数（客户端数）
    private static final int port = 55555;//server端口

    public static void main(String[] args) {
        try{
            // 监听端口
            ServerSocket server = new ServerSocket(port);
            System.out.println("服务器" + server.getLocalSocketAddress() + "已启动，");
            // 创建固定大小的线程池
            ExecutorService threadPool = Executors.newFixedThreadPool(100);
            Thread thread = new Thread(new InfoPrint());
            thread.setDaemon(true);
            thread.start();
            while (true){
                // 产生阻塞，等待client连接
                Socket socket = server.accept();
                connect_num ++;
                System.out.println(connect_num + "" + socket.getRemoteSocketAddress()+"已上线");
                // 提交线程池分配线程运行
                threadPool.submit(new Thread(new ServerListenner(socket)));
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("Welcome Client!".getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

class ServerListenner implements Runnable{
    private final Socket socket;

    public ServerListenner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()){
            byte[] bytes = new byte[1024];
            int length;
            while (true){
                length = inputStream.read(bytes);
                System.out.println("----->Server");
                String str = "receive" +
                        "(" +
                        socket.getRemoteSocketAddress().toString() +
                        "): " +
                        new String(bytes, 0, length, StandardCharsets.UTF_8);
                System.out.println(str);
            }
        } catch (SocketException e){
            String str = socket.getRemoteSocketAddress().toString() +
                    "已离线！";
            Server.connect_num --;
            System.out.println(str);
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            String str = socket.getRemoteSocketAddress().toString() +
                    "已离线！";
            Server.connect_num --;
            System.out.println(str);
        }
    }
}

class InfoPrint implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("目前在线人数：" +
                    Server.connect_num +
                    "人");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}