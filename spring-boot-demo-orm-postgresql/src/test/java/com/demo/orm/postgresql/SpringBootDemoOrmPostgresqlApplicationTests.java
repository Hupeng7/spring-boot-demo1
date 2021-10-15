package com.demo.orm.postgresql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SpringBootDemoOrmPostgresqlApplicationTests {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void test() throws Exception {
        // 创建10条记录
        userInfoRepository.save(new UserInfo("AAA", 10));
        userInfoRepository.save(new UserInfo("BBB", 20));
        userInfoRepository.save(new UserInfo("CCC", 30));
        userInfoRepository.save(new UserInfo("DDD", 40));
        userInfoRepository.save(new UserInfo("EEE", 50));
        userInfoRepository.save(new UserInfo("FFF", 60));
        userInfoRepository.save(new UserInfo("GGG", 70));
        userInfoRepository.save(new UserInfo("HHH", 80));
        userInfoRepository.save(new UserInfo("III", 90));
        userInfoRepository.save(new UserInfo("JJJ", 100));

        // 测试findAll,查询所有记录
        Assertions.assertEquals(10, userInfoRepository.findAll().size());

        // 测试findByName,查询姓名为FFF的User
        Assertions.assertEquals(60, userInfoRepository.findByName("FFF").getAge().longValue());

        // 测试findUser,查询姓名为FFF的User
        Assertions.assertEquals(60, userInfoRepository.findUser("FFF").getAge().longValue());

        // 测试findByNameAndAge,查询姓名为FFF并且年龄为60的User
        Assertions.assertEquals("FFF", userInfoRepository.findByNameAndAge("FFF", 60).getName());

        // 测试删除姓名为AAA的User
        userInfoRepository.delete(userInfoRepository.findByName("AAA"));

        // 测试findAll,查询所有记录，验证上面的删除是否成功
        Assertions.assertEquals(9, userInfoRepository.findAll().size());
    }


    @Test
    public void contextLoads() {
    }

}
