package com.example.springbootdemodbserver.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @ClassName Builder
 * @Description 利用java8特性，对象set参数 ，不需要一直set，用链式with()即可
 * @Author Leo
 * @Date 2020/3/25 15:14
 * @Version 1.0
 */
public class Builder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    /**
     * 这个示例最多支持三个参数的设置属性方法，也完全够用了。如果要扩展也很容易，依葫芦画瓢，添加多个参数的Consumer。
     *
     * @param consumer
     * @param p1
     * @param <P1>
     * @return
     */
    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    /**
     * 1 参数 Consumer1
     *
     * @param <T>
     * @param <P1>
     */
    @FunctionalInterface
    public interface Consumer1<T, P1> {
        void accept(T t, P1 p1);
    }

    /**
     * 2 参数 Consumer2
     *
     * @param <T>
     * @param <P1, P2>
     */
    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {
        void accept(T t, P1 p1, P2 p2);
    }

    /**
     * 3 参数 Consumer3
     *
     * @param <T>
     * @param <P1, P2, P3>
     */
    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }

    /**
     * @FunctionalInterface
     * 特点
     * 接口有且仅有一个抽象方法
     * 允许定义静态方法
     * 允许定义默认方法
     * 允许java.lang.Object中的public方法
     * 该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。
     * 如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错
     * // 正确的函数式接口
     * @FunctionalInterface
     * public interface TestInterface {
     *     // 抽象方法
     *     public void sub();
     *     // java.lang.Object中的public方法
     *     public boolean equals(Object var1);
     *     // 默认方法
     *     public default void defaultMethod(){
     *     }
     *     // 静态方法
     *     public static void staticMethod(){
     *     }
     * }
     *
     * // 错误的函数式接口(有多个抽象方法)
     * @FunctionalInterface
     * public interface TestInterface2 {
     *     void add();
     *     void sub();
     * }
     */

}
