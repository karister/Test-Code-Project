package com.karister;

import java.io.*;

/**
 * @author karister
 * @create 2021-08-24 11:38
 * 字符流适用于文本文件的读写，`OutputStreamWriter`类其实也是借助`FileOutputStream`类实现的
 */
public class StringFlume {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        write(file);
        System.out.println(read(file));
    }
    public static void write(File file) throws IOException {
        //OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
        //字符便捷流
        FileWriter osw = new FileWriter(file,true);
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        osw.write(string);
        osw.close();
    }

    public static String read(File file) throws IOException {
        //InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        //字符便捷流
        FileReader isr = new FileReader(file);
        char[] chars = new char[1024];
        StringBuilder sb = new StringBuilder();
        int length;
        while ((length = isr.read(chars)) != -1) {
            sb.append(chars, 0, length);
        }
        isr.close();
        return sb.toString();
    }
}
