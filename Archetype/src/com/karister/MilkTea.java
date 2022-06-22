package com.karister;

/**
 * @author karister
 * @create 2021-08-23 7:08
 */
public class MilkTea {
    private String type;
    private boolean ice;

    public MilkTea() {
    }

    public MilkTea(String type, boolean ice) {
        this.type = type;
        this.ice = ice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIce() {
        return ice;
    }

    public void setIce(boolean ice) {
        this.ice = ice;
    }
}
