package com.example.springbootdemomytool.utils.formatconversion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FormatConversionDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/7 15:29
 * @Version 1.0
 */
public class FormatConversionDemo {

    Gson gson = new Gson();

    class ZBankResponse {
        private String code;
        private String msg;
        private Object result;
        private Map<String, String> bottomRequest;

        public ZBankResponse() {
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getResult() {
            return this.result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Map<String, String> getBottomRequest() {
            return this.bottomRequest;
        }

        public void setBottomRequest(Map<String, String> bottomRequest) {
            this.bottomRequest = bottomRequest;
        }


    }

   // {"code":"000000","msg":"","result":{"result":"00"},"bottomRequest":{"partner":"HPJK","TranNo":"SE0900","TxnDt":"20200507","TxnSrlNo":"154645735","TxnTs":"154645735"}}
    public static void main(String[] args) {

        Gson gson = new Gson();
        String str1 = "{\"code\":\"000000\",\"msg\":\"\",\"result\":{\"result\":\"00\"},\"bottomRequest\":{\"partner\":\"HPJK\",\"TranNo\":\"SE0900\",\"TxnDt\":\"20200507\",\"TxnSrlNo\":\"154645735\",\"TxnTs\":\"154645735\"}}";
        ZBankResponse response = gson.fromJson(str1, ZBankResponse.class);

        Map resultObj = (Map)response.getResult();
        System.out.println(resultObj.get("result"));
        System.out.println(resultObj.getClass().toString());
        System.out.println(resultObj.toString());


//        Map map = new HashMap();
//        map.put("AAA", "1");
//        map.put("BBB", "2");
//        map.put("CCC", "3");
//        System.out.println("map=>" + map);
//
////1.map转string
//        String jsonString = JSON.toJSONString(map);
//        System.out.println("mapToString=>" + jsonString);
////2.map转jsonObject 
//        JSONObject JSONObj = JSONObject.parseObject(JSON.toJSONString(map));
//        System.out.println("mapToJSON=>" + JSONObj);
//
////3.String转jsonObject
//        String jsonString2 = "{\"AAA\":\"1\",\"CCC\":\"3\",\"BBB\":\"2\"}";
//        JSONObject parseObject = JSONObject.parseObject(jsonString2);
//        System.out.println("StringToJson=>" + parseObject);
////4.String转map
//        Map stringToMap = JSONObject.parseObject(jsonString2);
//        System.out.println("StringToMap=>" + stringToMap);
//
//        String str = "{\"age\":\"24\",\"name\":\"summer\"}";
//        JSONObject jsonObject = JSONObject.parseObject(str);
////5.jsonObject转map
//        Map jsonToMap = JSONObject.parseObject(jsonObject.toJSONString());
//        System.out.println("jsonToMap=>" + jsonToMap);
////6.jsonObject转String
//        String jsonString3 = jsonObject.toJSONString();
//        System.out.println("jsonToString=>" + jsonString3);


    }
}
