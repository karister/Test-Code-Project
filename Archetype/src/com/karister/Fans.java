package com.karister;

/**
 * @author karister
 * @create 2021-08-23 7:10
 */
public class Fans {
    public String type;
    public boolean ice;

    public void order(){
        MilkTea milkTea = new MilkTea();
        milkTea.setType(type);
        milkTea.setIce(ice);
        showInfo();
    }

    private void showInfo(){
        System.out.println(
                "粉丝买了杯" + type + "奶茶"+(ice?"加糖":"不加糖")
        );
    }
}
