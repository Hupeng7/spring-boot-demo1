package com.example.springbootdemomytool.utils.annootationdemo;

/**
 * @ClassName Client
 * @Description
 * @Author hup
 * @Date 2020/8/18 10:44
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        Bird bird = BirdNest.Sparrow1.reproduce();
        Desc.Color color = bird.getColor();
        System.out.println("Bird's color is : " + color);


    }
}
