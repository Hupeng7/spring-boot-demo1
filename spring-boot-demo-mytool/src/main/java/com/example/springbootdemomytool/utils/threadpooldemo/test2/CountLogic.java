package com.example.springbootdemomytool.utils.threadpooldemo.test2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountLogic {
    private static final Logger logger = LoggerFactory.getLogger(CountLogic.class);

    public Integer method0(String userId) {
        try {
           // System.out.println(" >> " + MD5Util.getSign("", "", "", userId));
            System.out.println("finish, 1s");
        } catch (Exception e) {
            //TODO: handle exception
            logger.error("error:",  e);
        }
        return 1;
    }

    public Integer method1(String userId) {
        try {
            //System.out.println(" >> " + MD5Util.getSign("", "", "", userId));
            System.out.println("finish, 2s");
        } catch (Exception e) {
            //TODO: handle exception
            logger.error("error:",  e);
        }
        return 2;
    }

    public Integer method2(String userId) {
        try {
            //System.out.println(" >> " + MD5Util.getSign("", "", "", userId));
            System.out.println("finish, 5s");
        } catch (Exception e) {
            //TODO: handle exception
            logger.error("error:",  e);
        }
        return 5;
    }

}