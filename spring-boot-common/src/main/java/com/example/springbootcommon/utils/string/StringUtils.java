package com.example.springbootcommon.utils.string;

import java.util.Collection;

/**
 * @ClassName isEmpty
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 16:43
 * @Version 1.0
 */
public class StringUtils {
    /**
     * 总结：
     * 思路是抽象的思想，主要是修改参数类型，方法就是往上找父类/接口，一直找到顶为止，记得修改参数名。
     * 通用，健壮性
     */

    /**
     * @param sc
     * @return
     */
    public static boolean isEmpty(CharSequence sc) {
        // 可以在此修改不同实现
        return org.apache.commons.lang3.StringUtils.isEmpty(sc);
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
