package com.example.springbootdemomytool.utils.testdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestDemo9
 * @Description
 * @Author H
 * @Date 2023/1/10 15:58
 * @Version 1.0
 */
public class TestDemo9 {

    public static void main(String[] args) {
        int a = test1("414");
        System.out.println("a======>" + a);

        String loanNo = "HXHT1002892026081210042156001";
        System.out.println(loanNo.substring(2));

        int i = 0;
        while (true) {
            System.out.println("do something ....,i is:" + i);

            if (i++ >= 2) {
                System.out.println("current i is :" + i);
                break;
            }
        }

        String tableName = "log_inout_common_a1t1";
        String sql = " SELECT " +
                "user_id as userId,service_id as serviceId,response_time as responseTime,error_code as errorCode,id,3 AS operatorOut,product_name as productName,raw_data_status as rawDataStatus  " +
                " FROM " + tableName +
                " WHERE create_time >= :start  AND create_time < :end  AND raw_data_service != 'hz'  AND error_code = '0000' ";

        // sql.replace("#tableName#", tableName);
        System.out.println(sql);

    }

    private static int test1(String item) {
        String[] a = new String[]{"44", "22", "33", "11"};

//        List<String> b = new ArrayList<String>() {
//            {
//                add("44");
//                add("22");
//                add("33");
//                add("11");
//            }
//        };
        List<String> b = Arrays.asList(a);
        List<String> c = new ArrayList<>(Arrays.asList(a));

        int a1 = b.indexOf(item);
        return a1;
    }


}
