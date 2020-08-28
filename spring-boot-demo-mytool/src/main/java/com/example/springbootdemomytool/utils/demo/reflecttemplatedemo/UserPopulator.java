package com.example.springbootdemomytool.utils.demo.reflecttemplatedemo;

/**
 * @ClassName UserPopulator
 * @Description
 * @Author hup
 * @Date 2020/8/18 18:58
 * @Version 1.0
 */
public class UserPopulator extends AbsPopulator {
    public void initUser(){
        System.out.println("init user...");
    }

    public void initPassword(){
        System.out.println("init password...");
    }

    public void initJobs(){
        System.out.println("init jobs...");
    }

    private void privateMethod(){
        System.out.println("private method...");
    }

}
