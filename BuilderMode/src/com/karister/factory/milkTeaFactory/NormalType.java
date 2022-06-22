package com.karister.factory.milkTeaFactory;

import com.karister.factory.MilkTea;

/**
 * @author karister
 * @create 2021-08-23 6:01
 */
public class NormalType implements MilkTeaFactory{
    @Override
    public MilkTea create() {
        return new MilkTea("原味");
    }
}
