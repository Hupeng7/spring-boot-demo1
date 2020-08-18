package com.demo.designpatterns.factorymethodpattern.examp04;

/**
 * @ClassName ConcreteCreator
 * @Description TODO
 * @Author hup
 * @Date 2020/8/17 18:21
 * @Version 1.0
 */
public class ConcreteCreator extends Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
