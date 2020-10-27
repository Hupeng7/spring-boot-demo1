package com.example.springbootdemomytool.utils.java8;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StringNumberMathDemo
 * @Description
 * @Author Leo
 * @Date 2020/4/15 19:03
 * @Version 1.0
 */
public class StringNumberMathDemo {

    /**
     * 第一个方法使用指定的分隔符，将任何数量的字符串连接为一个字符串。
     */
    @Test
    public void testString() {
        String s1 = String.join(":", "foobar", "foo", "bar");
        System.out.println("s1: " + s1);
    }

    @Test
    public void testString2() {
        String s2 = "foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());
        System.out.println("s2: " + s2); // s2: :abfor
    }

    @Test
    public void testString3() {
        String str = Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .collect(Collectors.joining(":"));
        System.out.println("str: " + str); // str: bar:foobar

        Pattern pattern = Pattern.compile(".*@gmail\\.com");
        long a = Stream.of("bob@gmail.com", "alice@hotmail.com")
                .filter(pattern.asPredicate())
                .count();
        System.out.println("count: " + a); // count: 1
    }

    @Test
    public void testInteger1() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);

        long maxUnsignedInt = (11 << 32) - 1;
        String string = String.valueOf(maxUnsignedInt);
        System.out.println("string: " + string);
        int unsignedInt = Integer.parseUnsignedInt(string, 10);
        String string2 = Integer.toUnsignedString(unsignedInt, 10);
        System.out.println("string2: " + string2);

        try {
            Integer.parseInt(string, 10);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("could not parse signed int of " + maxUnsignedInt);
        }
    }

    @Test
    public void testMath1() {
        try {
            Math.addExact(Integer.MAX_VALUE, 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testMath2() {
        try {
            Math.toIntExact(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFile1() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("List: " + joined);
        }
    }

    @Test
    public void testFile2() throws IOException {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith(".md"))) {
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(": "));
            System.out.println("Found: " + joined);
        }

        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            String joined1 = stream
                    .map(String::valueOf)
                    .filter(path -> path.endsWith(".md"))
                    .sorted()
                    .collect(Collectors.joining(": "));
            System.out.println("walk(): " + joined1);
        }
    }

    @Test
    public void testFile3() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("D:\\project\\spring-boot-demo1\\test.txt"));
        lines.add("print('foobar');");
        Files.write(Paths.get("D:\\project\\spring-boot-demo1\\test1.txt"), lines);

        Path path = Paths.get("D:\\project\\spring-boot-demo1\\test1.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine());
        }

        try (Stream<String> stream = Files.lines(Paths.get("D:\\project\\spring-boot-demo1\\test.txt"))) {
            stream
                    .filter(line -> line.contains("print"))
                    .map(String::trim)
                    .forEach(System.out::println);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("print('Hello World');");
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            long countPrints = reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .count();
            System.out.println(countPrints);
        }

    }


}
