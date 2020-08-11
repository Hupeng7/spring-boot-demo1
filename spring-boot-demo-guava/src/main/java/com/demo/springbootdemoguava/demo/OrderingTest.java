package com.demo.springbootdemoguava.demo;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * @ClassName OrderingTest
 * @Description
 * @Author Leo
 * @Date 2020/7/16 11:31
 * @Version 1.0
 */
@Slf4j
public class OrderingTest {
    private Integer age;
    private Integer age2;

    public OrderingTest(Integer age, Integer age2) {
        this.age = age;
        this.age2 = age2;
    }

    public static void main(String[] args) {
        Ordering<OrderingTest> ordering = Ordering.natural().nullsFirst().onResultOf((Function<OrderingTest, Integer>) input -> input.age)
                .compound(Ordering.natural().nullsFirst().onResultOf((Function<OrderingTest, Integer>) input -> input.age2));
        List<OrderingTest> list = Lists.newArrayList(
                new OrderingTest(1, 1),
                new OrderingTest(1, 1),
                new OrderingTest(null, 1),
                new OrderingTest(5, 1),
                new OrderingTest(5, null),
                new OrderingTest(3, 2),
                new OrderingTest(3, 1),
                new OrderingTest(1, 2)
        );
        log.info("list : {}", list);
        list.removeIf(o -> o.age == null || o.age2 == null);
        list.sort(ordering);
        log.info("list : {}", list);
    }

    @Override
    public String toString() {
        return "OrderingTest{" +
                "age=" + age +
                ", age2=" + age2 +
                '}';
    }
}
