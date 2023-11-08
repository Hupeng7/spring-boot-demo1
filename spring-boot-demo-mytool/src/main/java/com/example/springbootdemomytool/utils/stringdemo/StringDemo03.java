package com.example.springbootdemomytool.utils.stringdemo;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StringDemo03
 * @Description
 * @Author H
 * @Date 2023/10/30 14:04
 * @Version 1.0
 */
public class StringDemo03 {
    public static void main(String[] args) {

//        String contractName = "user-auth-v10.pdf";
//        String[] split = contractName.split("\\.");
//        System.out.println(split);
//        String contractFileName = split[0] + "_1234567890987654321." + "" + split[1];
//        System.out.println("contractFileName" + contractFileName);

        String originalRequestString = "侯小花\t431021199410126540\t13717348566\t2023年1月9日\n" +
                "张玲玲\t411123198604162048\t15839516539\t2023年2月21日\n" +
                "刘小华\t530402198707250914\t15894284443\t2023年3月1日\n" +
                "林彬彬\t350321199008087366\t18859163969\t2023年4月1日\n" +
                "白东民\t410823198811100330\t13598549210\t2023年5月29日\n" +
                "高延军\t610621198105110412\t13636862652\t2023年1月2日\n" +
                "王星诺\t210304199408032025\t19542714427\t2023年2月27日\n" +
                "刘雪峰\t370831199012243118\t17853772520\t2023年3月3日\n" +
                "孙燕\t372522197008204044\t13589137825\t2023年4月1日\n" +
                "骆小韵\t440184199503080942\t13533391106\t2023年5月16日\n";

        String[] split = originalRequestString.split("\n");

        /**
         * {
         * 	"contractContent": {
         * 		"name": "李天才",
         * 		"id_no": "500236199801267036",
         * 		"id_type": "身份证",
         * 		"date": "2023年11月03日"
         *        },
         * 	"contractId": "30",
         * 	"contractName": "user-auth-v1.pdf",
         * 	"sealPage": 6,
         * 	"sealX": 58,
         * 	"sealY": 92,
         * 	"serialNumber": "",
         * 	"userInfo": {
         * 		"cardType": 0,
         * 		"idno": "500236199801267036",
         * 		"mobile": "13521098212",
         * 		"userId": "",
         * 		"userType": 1,
         * 		"username": "李天才"
         *    }
         * }
         */

        List<ContractESignChinaMobileRequest> list = new ArrayList<>();


        for (String per : split) {
            // System.out.println(per);

            ContractESignChinaMobileRequest request = new ContractESignChinaMobileRequest();
            request.setContractId("30");
            request.setContractName("user-auth-v1.pdf");
            request.setSealPage(6);
            request.setSealX(58F);
            request.setSealY(92F);
            Map<String, Object> contractContent = new HashMap<>();
            ContractESignChinaMobileRequest.UserInfo userInfo = new ContractESignChinaMobileRequest.UserInfo();
            String[] ss = per.split("\t");
            String s1 = ss[0];
            String s2 = ss[1];
            String s3 = ss[2];
            String s4 = ss[3];
            System.out.println(s1 + "," + s2 + "," + s3 + "," + s4);
            contractContent.put("name", s1);
            contractContent.put("id_no", s2);
            contractContent.put("id_type", "身份证");
            contractContent.put("date", s4);

            request.setContractContent(contractContent);

            userInfo.setUsername(s1);
            userInfo.setCardType((byte) 0);
            userInfo.setUserType((byte) 1);
            userInfo.setIdno(s2);
            userInfo.setMobile(s3);

            request.setUserInfo(userInfo);

            list.add(request);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);

        System.out.println("jsonObject===>" + jsonObject.toJSONString());

    }


}
