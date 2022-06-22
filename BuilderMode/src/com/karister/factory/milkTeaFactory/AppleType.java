package com.karister.factory.milkTeaFactory;

import com.karister.factory.MilkTea;

/**
 * @author karister
 * @create 2021-08-23 6:07
 */
public class AppleType implements MilkTeaFactory{
    @Override
    public MilkTea create() {
        return new MilkTea("苹果味");
    }
}
