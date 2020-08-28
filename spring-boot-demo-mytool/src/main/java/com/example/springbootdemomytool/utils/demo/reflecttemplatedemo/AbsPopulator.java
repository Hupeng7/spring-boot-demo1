package com.example.springbootdemomytool.utils.demo.reflecttemplatedemo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName AbsPopulator
 * @Description
 * @Author hup
 * @Date 2020/8/18 18:51
 * @Version 1.0
 */
public abstract class AbsPopulator {
    // 模板方法
    // 主要方法   为了使得public方法执行
    public final void dataInitialing() throws Exception {
        // 获得所有的public 方法
        Method[] methods = getClass().getMethods();
        for (Method m : methods) {
            // 判断是否是数据初始化方法
            if (isInitDataMethod(m)) {
                m.invoke(this);
            }
        }

    }

    // 判断是否是数据初始化方法，基本方法鉴别器
    private boolean isInitDataMethod(Method m) {
        return m.getName().startsWith("init")               // init开始
                && Modifier.isPublic(m.getModifiers())      // 公开方法
                && m.getReturnType().equals(Void.TYPE)      // 返回值是void
                && !m.isVarArgs()                           // 输入参数为空
                && !Modifier.isAbstract(m.getModifiers());  // 不能是抽象方法
    }

}
