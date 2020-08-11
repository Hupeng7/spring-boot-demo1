package com.demo.springbootdemoguava;

import com.google.common.base.Function;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GuavaTests {
    private String a;
    private Integer b;
    private String name;
    private Integer age;

    @Test
    public void preconditionsTest() {
        // 一般这样用
        a = "hello";
        this.a = checkNotNull(a);
        log.info("a : {} ", a);
        b = 2;
        this.b = checkNotNull(b);
        log.info("b : {}", b);
        checkArgument(a.equals("world"), "not equals Exception");

        checkState(b == 1, "some Exception");
    }

    @Test
    public void optionalTest() {
        String a = null;
        String c = Optional.ofNullable(a).orElse("b");
        log.info("c : {}", c);
        //String d = Optional.ofNullable(a).orElseThrow(() -> new RuntimeException("not null"));
        //log.info("d : {}", d);
        String c1 = checkNotNull(a) + "fake";
        log.info("c1 : {}", c1);
    }

    @Test
    public void objectsTest() {
//        GuavaTests guavaTests = new GuavaTests();
//        guavaTests.age = 1;
//        guavaTests.name = "aaa";
//
//        //
//        log.info("guavaTests : {}", guavaTests.toString());
//
//        // 想要调试一个对象属性信息，自定义toString信息可以这样做
//        log.info(MoreObjects.toStringHelper(guavaTests)
//                .add("age", guavaTests.age)
//                .add("name", guavaTests.name)
//                .toString());
//
//        // 一般的非空赋值
//        String value = MoreObjects.firstNonNull("first param maybe is null", "first param is null,second param will be return");
    }

    @Test
    public void compareToTest() {
        Integer age1 = 10;
        Integer age2 = 40;
        Integer cAge1 = 20;
        Integer cAge2 = 30;
        int result = age1.compareTo(cAge1) == 0 ? age2.compareTo(cAge2) : age1.compareTo(cAge1);
        log.info("result : {}", result);
        // 这是新的写法，比以前的写法更简单明了
        int resultNew = ComparisonChain.start().compare(age1, cAge1).compare(age2, cAge2).result();
        log.info("resultNew : {}", resultNew);

        //
        int result1 = ComparisonChain.start().compare(age2, age1).result();
        log.info("result1 : {}", result1);
    }

    private Integer age11;
    private Integer age22;

    public GuavaTests() {
    }


    public GuavaTests(Integer age11, Integer age22) {
        this.age11 = age11;
        this.age22 = age22;
    }

    @Test
    public void orderingTest() {
        //
        Ordering<GuavaTests> ordering = Ordering.natural()
                .nullsFirst().onResultOf((Function<GuavaTests, Integer>) input -> input.age11)
                .compound(Ordering.natural().nullsFirst().onResultOf((Function<GuavaTests, Integer>) input -> input.age22));
        List<GuavaTests> list = Lists.newArrayList(
                new GuavaTests(1, 1),
                new GuavaTests(1, 1),
                new GuavaTests(null, 1),
                new GuavaTests(5, 1),
                new GuavaTests(5, null),
                new GuavaTests(3, 2),
                new GuavaTests(3, 1),
                new GuavaTests(1, 2)
        );

        log.info("list : {}", list);
        list.sort(ordering);
        log.info("list2 : {}", list);
    }

    @Override
    public String toString() {
        return "GuavaTests{" +
                "age11=" + age11 +
                ", age22=" + age22 +
                '}';
    }
}
