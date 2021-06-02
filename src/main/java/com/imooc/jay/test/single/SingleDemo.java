package com.imooc.jay.test.single;

public class SingleDemo {
    // volatile可以禁止语句重排序，保证new instance的有序性，即创建实例完成后，才能被其他线程读取
    // 没有volatile，指令可能会重排序，导致对象创建到一半，对象就被赋值到instance变量，导致被其他线程读取到半成品对象
    private static volatile SingleDemo instance;

    private SingleDemo() {}

    public static SingleDemo getInstance() {
        // DCL（double check lock）双重检查
        if (instance == null) {
            synchronized (SingleDemo.class) {
                if (instance == null) {
                    instance = new SingleDemo();
                }
            }
        }

        return instance;
    }
}
