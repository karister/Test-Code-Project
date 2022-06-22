package com.karister;

import java.util.Scanner;

/**
 * @author karister
 * @create 2021-08-28 11:04
 */
public class Main {
    public static void main(String[] args) {
        int a = 0,b = 0,m = 0;
        int num = 0;
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            a = in.nextInt();
            b = in.nextInt();
            m = in.nextInt();
            break;
        }
        if((a >= m) || (b >= m)) {
            num = 0;
            System.out.println(num);
        }
        else {
            num = handle(a,b,m,num);
            System.out.println(num);
        }
    }
    static int handle(int A, int B, int M,int num){
        int Num = num + 1;
        if((A == 0) && (B < 0))
            return -1;
        if((B == 0) && (A < 0))
            return -1;
        if((B < 0) && (A < 0))
            return -1;
        if((A+B) < M){
            if(A < B)
                return handle(A+B,B,M,Num);
            return handle(A,A+B,M,Num);
        }
        return Num;
    }
}
