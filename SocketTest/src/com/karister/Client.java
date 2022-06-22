package com.karister;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author karister
 * @create 2021-09-07 15:53
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 连接主机
        String host = "127.0.0.1";
        // 连接端口
        int port = 55555;
        // 建立连接
        Socket socket = new Socket(host, port);

        try(OutputStream outputStream = socket.getOutputStream()){
            // 写入输出流
            String message = "Hello Server!\r\n";
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            socket.shutdownOutput();
            outputStream.flush();
            // 读取输入流
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int length;
            StringBuilder stringBuilder = new StringBuilder();
            while ((length = inputStream.read(bytes)) != -1){
                stringBuilder.append(new String(bytes, 0, length, StandardCharsets.UTF_8));
            }

            //打印输入流
            System.out.println("get message from server: " + stringBuilder);
        }
    }
}
