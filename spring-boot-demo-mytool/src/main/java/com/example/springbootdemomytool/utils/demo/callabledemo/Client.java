package com.example.springbootdemomytool.utils.demo.callabledemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Client
 * @Description
 * @Author hup
 * @Date 2020/8/20 16:52
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<Integer> future = service.submit(new TaxCalculator(100));

        while (!future.isDone()){
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.print("#");
        }
        System.out.println("\n计算完成，金额是："+future.get()+" 元");
        service.shutdown();
    }

}
