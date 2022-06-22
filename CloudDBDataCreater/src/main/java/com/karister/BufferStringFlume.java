package com.karister;

import cn.hutool.core.util.RandomUtil;

import java.io.*;

/**
 * @author karister
 * @create 2021-08-24 11:54
 * 字符缓冲流
 */
public class BufferStringFlume {
    public static void main(String[] args) throws IOException {
        System.out.println(RandomUtil.randomNumbers(11));

//        File file = new File("D:/test.txt");
//        write(file,"");
//        System.out.println(read(file));
    }
    public static void write(File file, String text) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(text);
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
