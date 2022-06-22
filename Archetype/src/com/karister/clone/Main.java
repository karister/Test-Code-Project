package com.karister.clone;

/**
 * @author karister
 * @create 2021-08-23 7:28
 */
public class Main {
    public static void main(String[] args) {
        /*MilkTea milkTeaOfJay = new MilkTea();
        milkTeaOfJay.type = "原味";
        milkTeaOfJay.ice = true;
        milkTeaOfJay.showInfo();

        MilkTea milkTeaOfFans1 = new MilkTea();
        milkTeaOfFans1 = milkTeaOfJay.clone();//复制Jay的原型
        milkTeaOfFans1.showInfo();

        MilkTea milkTeaOfFans2 = new MilkTea();
        milkTeaOfFans2 = milkTeaOfJay.clone();//复制Jay的原型
        milkTeaOfFans2.showInfo();*/

        MilkTeaCloneable milkTeaOfJay = new MilkTeaCloneable();
        milkTeaOfJay.type = "原味";
        milkTeaOfJay.ice = false;
        milkTeaOfJay.showInfo();

        MilkTeaCloneable milkTeaOfFans1 = new MilkTeaCloneable();
        MilkTeaCloneable milkTeaOfFans2 = new MilkTeaCloneable();
        try {
            milkTeaOfFans1 = milkTeaOfJay.clone();//复制Jay的原型
            milkTeaOfFans2 = milkTeaOfJay.clone();//复制Jay的原型
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        milkTeaOfFans1.showInfo();
        milkTeaOfFans2.showInfo();
    }
}
