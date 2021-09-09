package com.example.springbootdemomytool;

import com.example.springbootdemomytool.utils.strategyandreflectdemo.GetPointAdvertisePageReflectFactory;
import com.example.springbootdemomytool.utils.strategyandreflectdemo.GetPointAdvertisePageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName ItemRecommendReflectFactoryTest
 * @Description
 * @Author H
 * @Date 2021/9/9 18:00
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemRecommendReflectFactoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRecommendReflectFactoryTest.class);
    @Autowired
    private GetPointAdvertisePageReflectFactory getPointAdvertisePageReflectFactory;

    @Test
    public void reflectFactoryTest() {
        String path = "/itemrecommend.properties";
        GetPointAdvertisePageStrategy getPointAdvertisePageStrategy = getPointAdvertisePageReflectFactory.getPointAdvertisePageStrategy(path, "plp.item.advertise");
        if (getPointAdvertisePageStrategy != null) {
            LOGGER.info("通过配置文件和反射机制，在运行时动态获取指定的执行类，测试成功");

            LOGGER.info(getPointAdvertisePageStrategy.getAdvertisePage(new Model() {
                @Override
                public Model addAttribute(String attributeName, Object attributeValue) {
                    return null;
                }

                @Override
                public Model addAttribute(Object attributeValue) {
                    return null;
                }

                @Override
                public Model addAllAttributes(Collection<?> attributeValues) {
                    return null;
                }

                @Override
                public Model addAllAttributes(Map<String, ?> attributes) {
                    return null;
                }

                @Override
                public Model mergeAttributes(Map<String, ?> attributes) {
                    return null;
                }

                @Override
                public boolean containsAttribute(String attributeName) {
                    return false;
                }

                @Override
                public Map<String, Object> asMap() {
                    return null;
                }
            }));
        }

    }


}
