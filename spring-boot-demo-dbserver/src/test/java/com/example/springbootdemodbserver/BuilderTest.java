package com.example.springbootdemodbserver;

import com.example.springbootdemodbserver.utils.Builder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @ClassName BuilderTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 14:13
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuilderTest {

    class Person {
        private String name;
        private Integer age;
        private String address;
        private String email;
        private int bust;
        private int waist;
        private int hips;
        private List<String> hobby;
        private Map<String, String> gift;

        public void addHobby(String hobby) {
            this.hobby = Optional.ofNullable(this.hobby).orElse(new ArrayList<>());
            this.hobby.add(hobby);
        }

        public void addGift(String day, String gift) {
            this.gift = Optional.ofNullable(this.gift).orElse(new HashMap<>());
            this.gift.put(day, gift);
        }

        public void setVitalStatistics(int bust, int waist, int hips) {
            this.bust = bust;
            this.waist = waist;
            this.hips = hips;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getBust() {
            return bust;
        }

        public void setBust(int bust) {
            this.bust = bust;
        }

        public int getWaist() {
            return waist;
        }

        public void setWaist(int waist) {
            this.waist = waist;
        }

        public int getHips() {
            return hips;
        }

        public void setHips(int hips) {
            this.hips = hips;
        }

        public List<String> getHobby() {
            return hobby;
        }

        public void setHobby(List<String> hobby) {
            this.hobby = hobby;
        }

        public Map<String, String> getGift() {
            return gift;
        }

        public void setGift(Map<String, String> gift) {
            this.gift = gift;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    ", email='" + email + '\'' +
                    ", bust=" + bust +
                    ", waist=" + waist +
                    ", hips=" + hips +
                    ", hobby=" + hobby +
                    ", gift=" + gift +
                    '}';
        }
    }

    /**
     * 实现链式 set 属性
     */
    @Test
    public void builderTest() {
        Person person = Builder.of(Person::new)
                .with(Person::setName, "a")
                .with(Person::setAddress, "shanghai")
                .with(Person::setAge, 20)
                .with(Person::setEmail, "123456789@qq.com")
                .with(Person::addHobby, "play basketball")
                .with(Person::addHobby, "movie")
                .with(Person::addGift, "first day", "a book")
                .with(Person::addGift, "birthday", "do some special")
                .build();
        System.out.println(person.toString());
    }
}
