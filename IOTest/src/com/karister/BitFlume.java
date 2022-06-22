package com.karister;

import java.io.*;

/**
 * @author karister
 * @create 2021-08-24 11:20
 * 字节流的方式效率较低，不建议使用
 */
public class BitFlume {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        write(file);
        System.out.println(read(file));

    }

    /**
     * 编写输出流，写入字节到文件
     * @param file
     * @throws IOException
     */
    public static void write(File file) throws IOException {
        OutputStream os = new FileOutputStream(file, true);
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        os.write(string.getBytes());
        os.close();
    }

    /**
     * 编写输入流，读取文件中字节
     * @param file
     * @return
     * @throws IOException
     */
    public static String read(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        // StringBuilder不考虑线程安全速度优于StringBuffer
        StringBuilder sb = new StringBuilder();
        int length = 0;
        while ((length = in.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, length));
        }
        in.close();
        return sb.toString();
    }
}
