package com.karister;

/**
 * @author karister
 * @create 2021-08-23 7:07
 */
public class Jay {
    private final String type = "草莓味";
    private final boolean ice = false;

    public void order(){
        MilkTea milkTea = new MilkTea();
        milkTea.setType(type);
        milkTea.setIce(ice);
        showInfo();
    }

    public String getType() {
        return type;
    }

    public boolean isIce() {
        return ice;
    }

    private void showInfo(){
        System.out.println(
            "Jay买了杯" + type + "奶茶"+(isIce()?"加糖":"不加糖")
        );
    }
}
