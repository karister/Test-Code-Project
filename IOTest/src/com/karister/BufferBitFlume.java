package com.karister;

import java.io.*;

/**
 * @author karister
 * @create 2021-08-24 11:30
 * 缓冲字节流是为高效率而设计的，真正的读写操作还是靠`FileOutputStream`和`FileInputStream`
 */
public class BufferBitFlume {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        write(file);
        System.out.println(read(file));
    }
    public static void write(File file) throws IOException {
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(file, true));
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        bis.write(string.getBytes());
        bis.close();
    }

    /**
     * 输入流由节点流`FileInputStream`改成了装饰后的处理流`BufferedInputStream`缓冲处理输入流提高效率
     * @param file
     * @return
     * @throws IOException
     */
    public static String read(File file) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int length = 0;
        while ((length = fis.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, length));
        }
        fis.close();
        return sb.toString();
    }
}
