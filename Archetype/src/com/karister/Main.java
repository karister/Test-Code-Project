package com.karister;

/**
 * @author karister
 * @create 2021-08-23 7:14
 * 有一天，周杰伦到奶茶店点了一份不加冰的原味奶茶，你说我是周杰伦的忠实粉，我也要一份跟周杰伦一样的
 */
public class Main {
    public static void main(String[] args) {
        //Jay点了杯奶茶
        Jay jay = new Jay();
        jay.order();

        //粉丝1点了杯奶茶，和Jay一样的
        Fans fans1 = new Fans();
        fans1.type = jay.getType();
        fans1.ice = jay.isIce();
        fans1.order();

        //粉丝2点了杯奶茶，和Jay一样的
        Fans fans2 = new Fans();
        fans2.type = jay.getType();
        fans2.ice = jay.isIce();
        fans2.order();
    }
}
