package com.karister;

/**
 * @author karister
 * @create 2021-08-28 11:39
 */
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String strNew = str.replaceAll(" + "," ");
        String[] s = strNew.split(" ");
        for (String s1 : s) {
            StringBuffer buffer = new StringBuffer(s1);
            System.out.print(buffer.reverse()+ " ");
        }
    }
}
