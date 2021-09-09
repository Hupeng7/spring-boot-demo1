package com.example.springbootdemomytool.utils.httpclient4.demo2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtils
 * @Description https://mp.weixin.qq.com/s/3fLrWQIL_O4FcfSMk9R9KQ
 * @Author H
 * @Date 2021/8/12 14:19
 * @Version 1.0
 */
public class JsonUtils {
    public static final int TYPE_FASTJSON = 0;
    public static final int TYPE_GSON = 1;

    /**
     * 对象转化为json字符串
     *
     * @param object
     * @return
     */
    public static final String toJson(final Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 对象转化为json字符串
     *
     * @param object
     * @param features
     * @return
     */
    public static final String toJson(final Object object, SerializerFeature... features) {
        return JSON.toJSONString(object, features);
    }

    /**
     * 对象转化为json字符串并格式化
     *
     * @param object
     * @param format 是否要格式化
     * @return
     */
    public static final String toJson(final Object object, final boolean format) {
        return JSON.toJSONString(object, format);
    }

    /**
     * 对象对指定字段进行过滤处理，生成json字符串
     *
     * @param object
     * @param fields
     * @param ignore
     * @param features
     * @return
     */
    public static final String toJson(final Object object, final String[] fields, final boolean ignore, SerializerFeature... features) {
        if (fields == null || fields.length < 1) {
            return toJson(object);
        }

        if (features == null) {
            features = new SerializerFeature[]{SerializerFeature.QuoteFieldNames};
        }
        return JSON.toJSONString(object, new PropertyFilter() {
            @Override
            public boolean apply(Object object1, String name, Object value) {
                for (int i = 0; i < fields.length; i++) {
                    if (name.equals(fields[i])) {
                        return !ignore;
                    }
                }
                return ignore;
            }
        }, features);
    }

    /**
     * 解析json字符串中某路径的值
     *
     * @param json
     * @param path
     * @param <E>
     * @return
     */
    public static final <E> E parse(final String json, final String path) {
        String[] keys = path.split(",");
        JSONObject object = JSON.parseObject(json);
        for (int i = 0; i < keys.length; i++) {
            object = object.getJSONObject(keys[i]);
        }
        return (E) object.get(keys[keys.length - 1]);
    }

    /**
     * json 字符串解析为对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> T parse(final String json, final Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * json字符串解析为对象
     *
     * @param json
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> T parse(final String json, final String path, final Class<T> clazz) {
        String[] keys = path.split(",");
        JSONObject object = JSON.parseObject(json);
        for (int i = 0; i < keys.length; i++) {
            object = object.getJSONObject(keys[i]);
        }
        String inner = object.getString(keys[keys.length - 1]);
        return parse(inner, clazz);
    }

    /**
     * 将制定的对象经过字段过滤处理后，解析成为json集合
     *
     * @param object
     * @param fields
     * @param ignore
     * @param clazz
     * @param features
     * @param <T>
     * @return
     */
    public static final <T> List<T> parseArray(final Object object, final String[] fields, boolean ignore,
                                               final Class<T> clazz, final SerializerFeature... features) {
        String json = toJson(object, fields, ignore, features);
        return parseArray(json, clazz);
    }

    /**
     * 从json字符串中解析出一个对象的集合，被解析字符串要求是合法的集合类型
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> List<T> parseArray(final String json, final Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * <pre>
     * 从json字符串中按照路径寻找，并解析出一个对象的集合，例如：
     * 类Person有一个属性name，要从以下json中解析出其集合：
     * {
     *  "page_info":{
     *   "items":{
     *    "item":[{"name":"KelvinZ"},{"name":"Jobs"},...{"name":"Gates"}]
     *  }
     * }
     * 使用方法：parseArray(json, "page_info,items,item", Person.class)，
     * 将根据指定路径，正确的解析出所需集合，排除外层干扰
     *
     * @param json json字符串
     * @param path 逗号分隔的json层次结构
     * @param clazz 目标类
     * @return
     */
    public static final <T> List<T> parseArray(final String json, final String path, final Class<T> clazz) {
        String[] keys = path.split(",");
        JSONObject object = JSON.parseObject(json);
        for (int i = 0; i < keys.length - 1; i++) {
            object = object.getJSONObject(keys[i]);
        }
        String inner = object.getString(keys[keys.length - 1]);
        List<T> ret = parseArray(inner, clazz);
        return ret;
    }

    /**
     * <pre>
     * 有些json的常见格式错误这里可以处理，以便给后续的方法处理
     * 常见错误：使用了\" 或者 "{ 或者 }"，腾讯的页面中常见这种格式
     *
     * @param invalidJson 包含非法格式的json字符串
     * @return
     */
    public static final String correctJson(final String invalidJson) {
        String content = invalidJson.replace("\\\"", "\"")
                .replace("\"{", "{")
                .replace("}\"", "}");
        return content;
    }

    /**
     * 格式化json
     *
     * @param json
     * @return
     */
    public static final String formatJson(String json) {
        Map<?, ?> map = (Map<?, ?>) JSON.parse(json);
        return JSON.toJSONString(map, true);
    }

    /**
     * 获取json串中的子json
     *
     * @param json
     * @param path
     * @return
     */
    public static final String getSubJson(String json, String path) {
        String[] keys = path.split(",");
        JSONObject object = JSON.parseObject(json);
        for (int i = 0; i < keys.length - 1; i++) {
            object = object.getJSONObject(keys[i]);
            System.out.println(object.toJSONString());
        }
        return object != null ? object.getString(keys[keys.length - 1]) : null;
    }

}
