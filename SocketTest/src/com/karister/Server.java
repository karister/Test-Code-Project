package com.karister;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author karister
 * @create 2021-09-07 15:03
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 监听端口
        int port = 55555;
        System.out.println("监听端口："+port);
        ServerSocket server = new ServerSocket(port);
        System.out.println("server等待连接");

        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        //循环等待client连接
        while (true){

            // 产生阻塞，等待客户端连接返回socket
            Socket serverSocket = server.accept();
            System.out.println(serverSocket.getRemoteSocketAddress()+"已上线");

            Runnable task = ()->{
                //从socket中读取输入流
                try {
                    InputStream inputStream = serverSocket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int length;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((length = inputStream.read(bytes)) != -1) {
                        stringBuilder.append(new String(bytes, 0, length, StandardCharsets.UTF_8));
                    }
                    System.out.println("get message from client: " + stringBuilder);
                    OutputStream outputStream = serverSocket.getOutputStream();
                    outputStream.write("hello client!".getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                } catch (Exception e){
                    e.printStackTrace();
                }

            };
            threadPool.submit(task);
        }
    }
}
