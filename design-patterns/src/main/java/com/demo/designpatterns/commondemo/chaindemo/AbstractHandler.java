package com.demo.designpatterns.commondemo.chaindemo;

import sun.misc.Request;

import javax.xml.ws.Response;

/**
 * @ClassName AbstractHandler
 * @Description
 * @Author H
 * @Date 2021/12/16 14:55
 * @Version 1.0
 */
public abstract class AbstractHandler {
    // 责任链中的下一个对象
    private AbstractHandler nextHandler;

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 具体参数拦截逻辑，给子类去实现
     *
     * @param request
     * @param response
     */
    public void filter(Request request, Response response) {
        doFilter(request, response);
        if (getNextHandler() != null) {
            getNextHandler().filter(request, response);
        }
    }

    protected abstract AbstractHandler getNextHandler();

    protected abstract void doFilter(Request request, Response response);

}
