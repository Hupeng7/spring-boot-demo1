package com.demo.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit5测试
 * https://mp.weixin.qq.com/s/cWUdtoKxlQ-20bKn09F3tw
 */
@SpringBootTest(classes = SpringBootTestApplication.class)
public class JUnit5Tests {

    @Test
    @DisplayName("测试断言equals")
    public void testEquals() {
        assertTrue(3 < 4, "不相等");
        //assertTrue(3 < 4);
    }

    @Test
    @DisplayName("测试断言NotNull")
    public void testNotNull() {
        assertNotNull(new Object());
    }

    @Test
    @DisplayName("测试断言抛异常")
    public void testThrows() {
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> {
            int m = 5 / 0;
        });
        assertEquals("/ by zero", arithmeticException.getMessage());
    }

    @Test
    @DisplayName("测试断言超时")
    public void testTimeOut() {
        String actualResult = assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(3000);
            return "a result";
        });
    }

    @Test
    @DisplayName("测试组合断言")
    public void testAll() {
        assertAll("测试item商品下单",
                () -> {
                    // 模拟用户余额扣减
                    assertTrue(1 < 2, "余额不足");
                },
                () -> {
                    // 模拟item数据库扣减库存
                    assertTrue(3 < 4);
                },
                () -> {
                    // 模拟交易流水落库
                    assertNotNull(new Object());
                });
    }

    @RepeatedTest(3)
    @DisplayName("重复测试")
    public void repeatedTest() {
        System.out.println("调用");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("参数化测试")
    public void paramTest(int a) {
        assertTrue(a > 0 & a < 4);
    }

}
