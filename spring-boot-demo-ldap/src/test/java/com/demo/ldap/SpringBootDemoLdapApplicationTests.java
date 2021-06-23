package com.demo.ldap;

import com.demo.ldap.dao.Person;
import com.demo.ldap.dao.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SpringBootDemoLdapApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findAll() {
        personRepository.findAll().forEach(p -> {
            System.out.println(p);
        });
    }

    @Test
    public void save() {
        Person person = new Person();
        person.setUid("uid:1");
        person.setUserName("AAA");
        person.setCommonName("aaa");
        person.setUserPassword("666");
        personRepository.save(person);

        personRepository.findAll().forEach(p -> {
            System.out.println(p);
        });
    }


}
