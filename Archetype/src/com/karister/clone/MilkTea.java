package com.karister.clone;

/**
 * @author karister
 * @create 2021-08-23 7:08
 * 运用原型模式，新增clone方法
 */
public class MilkTea {
    public String type;
    public boolean ice;

    public MilkTea clone(){
        MilkTea milkTea = new MilkTea();
        milkTea.type = this.type;
        milkTea.ice = this.ice;
        return milkTea;
    }

    public void showInfo(){
        System.out.println(
                "买了杯" + type + "奶茶"+(ice?"加糖":"不加糖")
        );
    }
}
