package com.example.springbootdemomytool.utils.java8;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @ClassName NullDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/16 15:09
 * @Version 1.0
 */
public class NullDemo {

    class Outer {
        Nested nested;

        Nested getNested() {
            return nested;
        }
    }

    class Nested {
        Inner inner;

        Inner getInner() {
            return inner;
        }
    }

    class Inner {
        String foo;

        String getFoo() {
            return foo;
        }
    }

    @Test
    public void test1() {
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
    }

    @Test
    public void test2() {
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    @Test
    public void test3() {
        Outer obj = new Outer();
        resolve(() -> obj.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

}
