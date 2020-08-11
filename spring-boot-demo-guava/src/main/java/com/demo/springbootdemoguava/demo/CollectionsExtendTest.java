package com.demo.springbootdemoguava.demo;

import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName CollectionsExtendTest
 * @Description Forwarding, PeekingIterator, AbstractIterator
 * 对集合类做的拓展封装,用于定制化集合对象.
 * @Author hup
 * @Date 2020/7/16 14:28
 * @Version 1.0
 */
@Slf4j
public class CollectionsExtendTest {

    public static void main(String[] args) {
        forwarding();
        peekingIterator();
        abstractIterator();
    }

    private static void abstractIterator() {
        log.info("abstractIterator------------");
        // 自定义迭代器
        Iterator<String> in = Iterators.forArray("a", null, "b", "c");
        Iterator<String> iterator = new AbstractIterator<String>() {
            @Override
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
        iterator.forEachRemaining(System.out::print);
    }

    private static void peekingIterator() {
        log.info("peekingIterator------------");
        // 可进行窥探下一个值的迭代器
        PeekingIterator<String> peekingIterator = Iterators.peekingIterator(Lists.newArrayList("a", "b", "b", "c", "c").iterator());

        List<String> result = new ArrayList<>();
        while (peekingIterator.hasNext()) {
            String current = peekingIterator.next();
            while (peekingIterator.hasNext() && peekingIterator.peek().equals(current)) {
                peekingIterator.next();
            }
            result.add(current);
        }
        log.info("result : {}", result);
    }

    private static void forwarding() {
        log.info("forwarding ------------");
        // 快速创建包装类拓展
        List<String> list = new ForwardingList<String>() {
            private final List<String> delegate = new ArrayList<>();

            @Override
            protected List<String> delegate() {
                log.info("delegate......");
                return delegate;
            }

            @Override
            public boolean add(String element) {
                log.info("add..." + element);
                return super.add(element);
            }
        };
        list.add("1");
        list.add("2");
        log.info(list.toString());
    }

}
