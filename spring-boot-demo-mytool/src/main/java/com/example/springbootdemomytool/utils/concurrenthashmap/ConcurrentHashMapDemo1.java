package com.example.springbootdemomytool.utils.concurrenthashmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName ConcurrentHashMapDemo1
 * @Description since https://mp.weixin.qq.com/s/DtksQ_ljKlH8xlHHYMjVmA
 * @Author H
 * @Date 2021/4/8 10:22
 * @Version 1.0
 */
public class ConcurrentHashMapDemo1 {
    public static void main(String[] args) {
        gooduse();
    }

    public static Map<String, Long> gooduse() {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(900);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(900);
            freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
            System.out.println(i + ": {" + key + ": " + freqs.get(key)+"}");
        }));
        forkJoinPool.shutdown();

        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //
        return freqs.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().longValue()
                ));
    }
}
