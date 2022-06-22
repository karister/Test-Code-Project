package com.karister;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author karister
 * @create 2021-08-28 11:54
 */
public class Test2 {
    public static void main(String[] args) {
        /*int[] moneyCountIn = new int[7];
        int[] moneyCountOut = new int[7];*/
        HashMap<Integer, Integer> moneyCountIn = new HashMap<>();
        HashMap<Integer, Integer> moneyCountOut = new HashMap<>();
        int price = 0;
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            moneyCountIn.put(1,in.nextInt());
            moneyCountIn.put(2,in.nextInt());
            moneyCountIn.put(5,in.nextInt());
            moneyCountIn.put(10,in.nextInt());
            moneyCountIn.put(20,in.nextInt());
            moneyCountIn.put(50,in.nextInt());
            moneyCountIn.put(100,in.nextInt());
            price = in.nextInt();
            break;
        }
        moneyCountOut.put(1,0);
        moneyCountOut.put(2,0);
        moneyCountOut.put(5,0);
        moneyCountOut.put(10,0);
        moneyCountOut.put(20,0);
        moneyCountOut.put(50,0);
        moneyCountOut.put(100,0);
        handle(moneyCountIn,moneyCountOut, price);
    }

    private static void handle(HashMap<Integer, Integer> moneyCountIn,HashMap<Integer, Integer> moneyCountOut, int price) {
        int priceTemp = 100;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        priceTemp = 50;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        priceTemp = 20;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        priceTemp = 10;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        priceTemp = 5;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        priceTemp = 2;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        priceTemp = 1;
        price = payMoney(moneyCountIn, moneyCountOut, price, priceTemp);
        System.out.print(moneyCountOut.get(1)+" ");
        System.out.print(moneyCountOut.get(2)+" ");
        System.out.print(moneyCountOut.get(5)+" ");
        System.out.print(moneyCountOut.get(10)+" ");
        System.out.print(moneyCountOut.get(20)+" ");
        System.out.print(moneyCountOut.get(50)+" ");
        System.out.print(moneyCountOut.get(100)+" ");
    }

    private static int payMoney(HashMap<Integer, Integer> moneyCountIn, HashMap<Integer, Integer> moneyCountOut, int price, int priceTemp) {
        if(price > priceTemp){
            if(moneyCountIn.get(priceTemp) != 0){
                for (int i = moneyCountIn.get(priceTemp); i >=0; i--) {
                    if(i* priceTemp <= price){
                        moneyCountOut.put(priceTemp, i);
                        price -= i* priceTemp;
                        return price;
                    }
                }
            }
        }
        return price;
    }
}
