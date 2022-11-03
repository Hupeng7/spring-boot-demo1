package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test4;

/**
 * 定义回调接口
 * @param <T>
 */
public interface TaskCallable<T> {
    T callable(T t);
}
