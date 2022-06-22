package com.karister.factory;

/**
 * @author karister
 * @create 2021-08-23 5:57
 */
public class MilkTea {
    private final String type;
    private String size = "中杯";
    private boolean pearl = false;
    private boolean ice = false;

    public MilkTea(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isPearl() {
        return pearl;
    }

    public void setPearl(boolean pearl) {
        this.pearl = pearl;
    }

    public boolean isIce() {
        return ice;
    }

    public void setIce(boolean ice) {
        this.ice = ice;
    }
}
