package com.karister.factory;


import com.karister.factory.milkTeaFactory.AppleType;
import com.karister.factory.milkTeaFactory.MilkTeaFactory;
import com.karister.factory.milkTeaFactory.NormalType;

/**
 * @author karister
 * @create 2021-08-23 6:10
 */
public class User {
    public void buyMilkTea(){
        MilkTeaFactory milkTeaType = new NormalType();
        MilkTea normal = milkTeaType.create();
        showMilkTea(normal);
        milkTeaType = new AppleType();
        MilkTea apple = milkTeaType.create();
        showMilkTea(apple);
    }

    private void showMilkTea(MilkTea milkTea){
        String pearl;
        if (milkTea.isPearl())
            pearl = "加珍珠";
        else
            pearl = "不加珍珠";
        String ice;
        if (milkTea.isIce()) {
            ice = "加冰";
        } else {
            ice = "不加冰";
        }
        System.out.println("一份" + milkTea.getSize() + "、"
                + pearl + "、"
                + ice + "的"
                + milkTea.getType() + "奶茶");
    }
}
