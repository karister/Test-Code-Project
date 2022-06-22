package com.karister;

import java.io.*;

/**
 * @author karister
 * @create 2021-08-24 11:54
 * 字符缓冲流
 */
public class BufferStringFlume {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        write(file);
        System.out.println(read(file));
    }
    public static void write(File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        bw.write(string);
        bw.close();
    }
    public static String read(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
