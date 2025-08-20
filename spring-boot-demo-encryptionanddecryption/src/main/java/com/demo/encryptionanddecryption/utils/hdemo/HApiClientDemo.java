package com.demo.encryptionanddecryption.utils.hdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class HApiClientDemo {
    // 客户自己生成秘钥对，其中公钥需要提供给恒普，秘钥客户自己保管
    private static String merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCVW8NOEM+1NqaYh0VGZUwRP5/v2P5vjQdk4B4iNtS1Z+zPB4s3bh5hXcuQU7fa4b7RnMjh/gRcmkoZijIBTwM47WOMXvsYlMcBxhRIVIG1OVNB4EvOHl7xeOPAQBncOsSsw6UwcgK8DQvAcjWO8wipE2UBEA0WAdQtrsTzykx8M006FINzbfNEiEMSol77gQu3AZSL8IPoNB26PUPqYbl3BQ5Zktx+i4h3pt5DY22Rp1wa4GRc7pFN6vrkELhBoorsKmdYzyPhvV+U0WyyFgmkoNtDD8TpE3qeFlX+ObvqUoIcre1sjJo0nwb/9C7tUdeVD5pZjgy0CWGiiRMiImsRAgMBAAECggEAesM4zXKd9tmbpyUQOKNMGaVNJUwbHb8yQ6S+v+qHfS+Vgjk2IXJegDMi/VcCeGMyf4G5z58FIMQkgceIAHFcOa9NMnRjOLoYtImF5EmpbzZWC4B7bkiiSyHNORi26MBZJZONtDsRqlWiwiRdh0iVZrstOulhiCQqrP3InoU5xp7144gnY3djeX9CSyPyHgEoZi1P8XVuX45fieK4/8JjtGScPZu7xi+shtGTpnFlCPob2ZbxGJZefxwaxBqFxErld1Kma2Il8BZhp+Wk5tkk42CSZJv/emuVhomFTepbtpYy6RkjgP9oxQtMd7i5R3wqZ9+Ueu9T8sGmaMtMzzEeUQKBgQDSK5yQ2MgVL5vztgo4UhHwR4ypmAo2Ku2XXbt87rUfglmByiqLULF5qlSYVd0n1+s6esfcdU1mgjHH63ZhQJJpVfTKYkKoJmDJLccmI9eKmujvLPVv1mdrNUg2Aaw3Qtxg1UjRq5jKiTkUOADjj1Jh9t61+7zAA3MggolT6yaCVQKBgQC17W8gpJE8x4318BIzvaZM1xg8KeK4m5AWZune4ref9sAMUHw38aooxszvsdG+YjQi3MkZmRfatv2LbDH8fDXB6NNM7oYgtkBTcHBlqa4F7JVxvkabDjMjo9XIKfzf33NPNvEWvnZnn0ArMn6aU/y0G4+Bvvt0TkedaR8cNorZzQKBgAixoyVewlAWCxZ2xk90HafZ3di6IFoDRpbHWtUdLsLh6n/y8/WVXfIReLRggOiABwwBPjlBZPVDl3mUpO369NOEqb6hd+5qwAAyc5WBUA48wDv8mR6KOPb3WkhZo10Z1/HtgLijp4thR8otUuQW7qp8wtO2WSV8PwvuOm4NQSeNAoGAbSDP2DTfKiZJ8YzgXkkUyGL+FpjJdxNZixaQkzJME5hf+wMMgEJmX69gnyd7OUiPHZpyxvgeZwP9cvSjx2B6nqaamTs7GcQ5MdPwmAsAaG1R97i7gacy5d4RDtCg72mKMaozVAFL5TyRsVfIuapHm4IswxdcnpeWPcYg8zuEbsECgYAYiSOhVv0pw5f+wdZvYpiQ4/fV78uKwRqpUdkpotzOGLCkF/Im/9t9J6/yU97MbCvoA5beW9PjzBERRn26HggG8TMD/FsOmcTl++XltC6+Tjzzepqh5SYCt/KvFY7EuCw5bc4oyFOwR/iYG82ImHMMFhOKW47cAaDi7yJWSRRADA==";
    // 恒普提供给客户
    private static String testAccount = "test_account_2";
    // 恒普提供给客户
    private static String hzPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4njdZqZ5AGVQDLH7gqTz8z3i7GPp0XEYleg0swlgIvoXFp1VZ7cvaP3K5eMsFKw/kYJf/aopWnb7Lqj1dIYoQTU0nRVDoDKtQKDQYqf7lScsDgkf6ru1wDu1baJ75V55d2u8Jg2RvA9TCDs+YvWgWP2PvMBlrJqrdq7y27mq2EBFSXkLLoRvkkJ0Lw7ld0KPatZeO38aMbkjdvZUIXIaWpr9OwIzARouhymZ7uB0OWp5eCjR50PnDTVfcUKQR4nbQZNB+18XBurt2rqhzmszdbpvJyS0GcVI8s6s93UlhcBhCc99JrGU8pryY2K2ycy3xzjiERwDhTNS6sSDp5bVpwIDAQAB";
    // 接口调用地址
    private static String url = "http://localhost:10011/api/finance/fraud_score_a1";
    public static void main(String[] args) throws Exception {
//        // 生成打印密钥对demo
//        Map<String, String> keyPair = RSAUtil.generateKeyPair(2048);
//        log.info("privateKey: {}", keyPair.get("privateKey"));
//        log.info("publicKey: {}", keyPair.get("publicKey"));
        // 客户调用demo
        clientCall();
    }

    public static void clientCall() throws Exception {
        // 客户调用
        JSONObject dataObj = new JSONObject();
        dataObj.put("mobile", "13333333333");
        dataObj.put("idNo", "422222199111111212");
        dataObj.put("name", "张三");
        String data = dataObj.toString();
        log.info("client call data: {}", data);
        // 随机加密key
        String aesKey = AESUtil.generateKeyString();
        String encryptBody = AESUtil.encrypt(aesKey, data);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setAccount(testAccount);
        apiRequest.setKey(RSAUtil.encrypt(hzPublicKey, aesKey));
        apiRequest.setReqData(encryptBody);
        apiRequest.setSign(RSAUtil.sign(encryptBody.getBytes(StandardCharsets.UTF_8), merchantPrivateKey));
        String requestStr = JSON.toJSONString(apiRequest);
        log.info("client call body: {}", requestStr);
        // http调用，给demo出去需要替换
        String respBody = HttpsClientUtilPool.getInst().doPostJsonPool(url, requestStr, "UTF-8", 3000, 5000);
        log.info("resp encrypt body: {}", respBody);

        // 客户解析报文
        String decryptResp = clientDecrypt(respBody, aesKey);
        log.info("decrypt resp body: {}", decryptResp);
    }

    public static String clientDecrypt(String respBody, String aesKey) throws Exception {
        if (StringUtils.isEmpty(respBody)) {
            return "";
        }
        ApiResponse apiResponse = JSON.parseObject(respBody, ApiResponse.class);
        if (!"0000".equals(apiResponse.getRespCode())) {
            return "";
        }
        String publicKey = hzPublicKey;
        // 公钥验证签名
        try {
            boolean verifySign = RSAUtil.verify(apiResponse.getRespData().getBytes(StandardCharsets.UTF_8), publicKey, apiResponse.getSign());
            if (!verifySign) {
                log.error("verify resp sign false");
                return "";
            }
        } catch (Exception e) {
            // 验签不过
            log.error("verify resp sign false", e);
            return "";
        }

        try {
            return AESUtil.decrypt(aesKey, apiResponse.getRespData());
        } catch (Exception e) {
            log.error("decrypt resp ", e);
            return "";
        }
    }

}
