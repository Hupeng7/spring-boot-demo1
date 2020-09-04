package com.example.springbootdemomytool.utils.enumdemo;

/**
 * @author hup
 */

public enum EnumDemo2 implements food, sport {
    FOOD,
    SPORT;

    @Override
    public void eat() {
        System.out.println("eat...");
    }

    @Override
    public void run() {
        System.out.println("run...");
    }
}

interface food {
    void eat();
}

interface sport {
    void run();
}