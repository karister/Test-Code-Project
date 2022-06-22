package com.karister;

/**
 * @author karister
 * @create 2021-08-23 5:49
 * 建造型模式用于创建过程稳定，但配置多变的对象。在《设计模式》一书中的定义是：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示
 */
public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.buyMilkTea();
    }
}
