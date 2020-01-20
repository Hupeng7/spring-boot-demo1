package com.demo.ratelimit.redis.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName IpUtil
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/9 16:38
 * @Version 1.0
 */
@Slf4j
public class IpUtil {

    private final static String UNKNOWN = "unknown";
    private final static int MAX_LENGTH = 15;

    /**
     * 获取IP地址
     * 使用功能Nginx 等反向代理软件，则不能通过 request.getRemoteAddr() 获取IP 地址
     * 如果使员工了多集反向代理的话，X-Forwarded-For 的值并不止一个，而是一串IP 地址，X-Forwarded-For 中第一个非unknown 的有效IP字符串，则为真是IP地址
     *
     * @return
     */
    public static String getIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR", e);
        }
        // 使用代理，则获取第一个IP地址
        if (!StrUtil.isEmpty(ip) && ip.length() > MAX_LENGTH) {
            if (ip.indexOf(StrUtil.COMMA) > 0) {
                ip = ip.substring(0, ip.indexOf(StrUtil.COMMA));
            }
        }
        return ip;
    }


}
