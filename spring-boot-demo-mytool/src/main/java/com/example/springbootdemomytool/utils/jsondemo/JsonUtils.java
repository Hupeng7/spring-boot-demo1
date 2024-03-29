package com.example.springbootdemomytool.utils.jsondemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtils
 * @Description
 * @Author H
 * @Date 2022/6/28 15:01
 * @Version 1.0
 */
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String toString(Object object) {
        if (object == null) {
            return null;
        }

        if (object.getClass() == String.class) {
            return (String) object;
        }

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错: " + object, e);
            return null;
        }
    }

    public static <T> T toBean(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json解析出错: " + json, e);
            return null;
        }
    }

    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("json解析出错: " + json, e);
            return null;
        }
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("json解析出错: " + json, e);
            return null;
        }
    }

    public static <T> T nativeRead(String json,TypeReference<T> typeReference){
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("json解析出错: " + json, e);
            return null;
        }
    }

}
