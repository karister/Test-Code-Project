package com.karister.clone;

import com.sun.istack.internal.NotNull;

/**
 * @author karister
 * @create 2021-08-23 7:33
 * Java 中有一个语法糖，让我们并不需要手写 clone 方法。这个语法糖就是 Cloneable 接口，我们只要让需要拷贝的类实现此接口即可
 */
public class MilkTeaCloneable implements Cloneable{
    public String type;
    public boolean ice;

    /**
     * 值得注意的是，Java 自带的 clone 方法是浅拷贝的。也就是说调用此对象的 clone 方法，只有基本类型的参数会被拷贝一份
     * 非基本类型的对象不会被拷贝一份，而是继续使用传递引用的方式
     * @return
     * @throws CloneNotSupportedException
     */

    @Override
    @NotNull
    protected MilkTeaCloneable clone() throws CloneNotSupportedException {
        return (MilkTeaCloneable)super.clone();
    }
    public void showInfo(){
        System.out.println(
                "买了杯" + type + "奶茶"+(ice?"加糖":"不加糖")
        );
    }
}
