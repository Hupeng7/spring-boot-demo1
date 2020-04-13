package com.example.springbootdemomytool.utils.java8;

import com.example.springbootdemomytool.beans.StudentInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName StreamDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/7 16:12
 * @Version 1.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<StudentInfo> studentInfoList = new ArrayList<>();
        studentInfoList.add(new StudentInfo("lixiaoming", true, 18, 1.76, LocalDate.of(2001, 3, 23)));
        studentInfoList.add(new StudentInfo("zhangxiaoli", false, 18, 1.61, LocalDate.of(2001, 6, 3)));
        studentInfoList.add(new StudentInfo("wangdaming", true, 19, 1.82, LocalDate.of(2000, 3, 11)));
        studentInfoList.add(new StudentInfo("chenxiaopao", false, 17, 1.67, LocalDate.of(2002, 10, 18)));
        test1(studentInfoList);

        System.out.println("===============================");
        test2(studentInfoList);
    }

    private static void test1(List<StudentInfo> studentInfoList) {

        StudentInfo.printStudents(studentInfoList);

        System.out.println("使用filter()过滤List");
        List<StudentInfo> boys = studentInfoList.stream().filter(e -> e.getGender() && e.getHeight() >= 1.8).collect(Collectors.toList());
        StudentInfo.printStudents(boys);
    }

    private static void test2(List<StudentInfo> studentInfoList) {
        System.out.println("java8遍历=======================");
        studentInfoList.forEach(e -> System.out.println(e));
        studentInfoList.forEach(e -> System.out.println(e.getName()));

        System.out.println("按照 age 进行排序 方法一 正序");
        studentInfoList.sort((o1, o2) -> o1.getAge().compareTo(o2.getAge())); // 正序  o1 o2 o1 o2前后一致表正序
        StudentInfo.printStudents(studentInfoList);
        System.out.println("按照 age 进行排序 方法一 倒序");
        studentInfoList.sort((o1, o2) -> o2.getAge().compareTo(o1.getAge())); // 倒序  o1 o2 o2 o1前后不一致表倒序
        StudentInfo.printStudents(studentInfoList);

        System.out.println("按照 age 进行排序 方法二 正序");
        studentInfoList.sort(Comparator.comparing(StudentInfo::getAge));
        StudentInfo.printStudents(studentInfoList);
        System.out.println("按照 age 进行排序 方法二 倒序");
        studentInfoList.sort(Comparator.comparing(StudentInfo::getAge).reversed());
        StudentInfo.printStudents(studentInfoList);

        System.out.println("===============================");
        // 遍历
        studentInfoList.forEach(e -> System.out.println(e.getAge()));
        System.out.println("=======================================");

        // 获取最大年龄的人
        StudentInfo maxAgeStudent = studentInfoList.stream().max(Comparator.comparing(StudentInfo::getAge)).get();
        System.out.println("max age student is: " + maxAgeStudent.toString());
        // 获取最小年龄的人
        StudentInfo minAgeStudent = studentInfoList.stream().min(Comparator.comparing(StudentInfo::getAge)).get();
        System.out.println("min age student is: " + minAgeStudent.toString());

        System.out.println("=======================================");
        // 过滤出年龄是18岁的 Student,
        List<StudentInfo> studentInfoListFilter = studentInfoList.stream().filter(e -> e.getAge() == 18).collect(Collectors.toList());
        StudentInfo.printStudents(studentInfoListFilter);

        // 统计出年龄等于18的个数
        long count = studentInfoList.stream().filter(e -> e.getAge() == 18).count();
        System.out.println("年龄等于18的个数是: " + count);

        // 获得年龄的平均值
        double asDouble = studentInfoList.stream().mapToInt(e -> e.getAge()).average().getAsDouble();
        System.out.println("年龄的平均值是: " + asDouble);

        // 获得年龄的求和
        int ageSum = studentInfoList.stream().mapToInt(e -> e.getAge()).sum();
        System.out.println("年龄的总和是: " + ageSum);

        // 按照年龄降序 ，年龄相同按照身高升序
        List<StudentInfo> doubleSorted = studentInfoList.stream()
                .sorted(Comparator.comparing(StudentInfo::getAge).reversed().thenComparing(StudentInfo::getHeight))
                .collect(Collectors.toList());
        StudentInfo.printStudents(doubleSorted);

        // 从对象列表中提取一列
        List<String> nameList = studentInfoList.stream().map(StudentInfo::getName).collect(Collectors.toList());
        nameList.forEach(e -> System.out.println(e));

        // 提取 age 列并去重 （使用distinct()函数）
        List<Integer> ageList = studentInfoList.stream().map(StudentInfo::getAge).distinct().collect(Collectors.toList());
        ageList.forEach(e -> System.out.println(e));

        System.out.println("map 遍历");
        Map<Integer, List<StudentInfo>> collect = studentInfoList.stream().collect(Collectors.groupingBy(StudentInfo::getAge));

        for (Integer key : collect.keySet()) {
            System.out.println(key + "= " + collect.get(key));
        }
    }
}
