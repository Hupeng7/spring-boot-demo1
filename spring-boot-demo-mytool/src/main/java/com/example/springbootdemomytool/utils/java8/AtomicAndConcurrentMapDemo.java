package com.example.springbootdemomytool.utils.java8;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.*;

/**
 * @ClassName AtomicAndConcurrentMapDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/15 16:44
 * @Version 1.0
 */
public class AtomicAndConcurrentMapDemo {

    @Test
    public void testAtomic() {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(atomicInteger::incrementAndGet));

        stop(executor);
        System.out.println(atomicInteger.get()); // 10000
    }

    @Test
    public void testAtomic2() {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInteger.updateAndGet(n -> n + 2);
                    executor.submit(task);
                });
        stop(executor);
        System.out.println(atomicInteger.get());  // 20000
    }

    @Test
    public void testAtomic3() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () -> atomicInteger.accumulateAndGet(i, (n, m) -> n + m);
                    executor.submit(task);
                });
        stop(executor);
        System.out.println(atomicInteger);
    }

    @Test
    public void testLongAdder() {
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 55000)
                .forEach(i -> executor.submit(adder::increment));
        stop(executor);
        System.out.println(adder.sumThenReset()); // 55000
    }

    @Test
    public void testLongAccumulator() {
        LongBinaryOperator op = (x, y) -> 2 * x + y;

        LongAccumulator accumulator = new LongAccumulator(op, 1L);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        stop(executor);
        System.out.println(accumulator.getThenReset()); // 2539[2037,3050]
    }

    @Test
    public void testConcurrentMap() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("k3", "v3");
        map.put("k4", "k4");
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        String value4 = map.putIfAbsent("k4", "v44");
        System.out.println("value4: " + value4);

        String value5 = map.getOrDefault("hi", "there");
        System.out.println("value5: " + value5);

        map.replaceAll((key, value) -> "k3".equals(key) ? "d3" : value);
        System.out.println(map.get("k3"));

        map.compute("foo", (key, value) -> value + value);
        System.out.println(map.get("foo"));

        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));
    }

    @Test
    public void testConcurrentHashMap() {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach(1, (key, value) ->
                System.out.printf("key: %s;value: %s;thread: %s\n",
                        key, value, Thread.currentThread().getName())
        );

        System.out.println("search======================================");
        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equals(key)) {
                return value;
            }
            return null;
        });
        System.out.println("Result: " + result);


        System.out.println("search 22222======================================");
        String result2 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });

        System.out.println("Result2: " + result2);

        System.out.println("reduce ======================================");
        String result3 = map.reduce(1, (key, value) -> {
                    System.out.println("Transform: " + Thread.currentThread().getName());
                    return key + " = " + value;
                },
                (s1, s2) -> {
                    System.out.println("Reduce: " + Thread.currentThread().getName());
                    return s1 + "," + s2;
                });
        System.out.println("Result3: " + result3);

    }


}
