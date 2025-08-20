package com.demo.encryptionanddecryption.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.encryptionanddecryption.utils.hdemo.AESUtil;
import com.demo.encryptionanddecryption.utils.hdemo.ApiRequest;
import com.demo.encryptionanddecryption.utils.hdemo.ApiResponse;
import com.demo.encryptionanddecryption.utils.hdemo.HzBizCode;
import com.demo.encryptionanddecryption.utils.hdemo.RSAUtil;
import com.demo.encryptionanddecryption.utils.hdemo.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "/api/finance")
@Slf4j
public class HApiController {

    private static String HZ_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDieN1mpnkAZVAMsfuCpPPzPeLsY+nRcRiV6DSzCWAi+hcWnVVnty9o/crl4ywUrD+Rgl/9qiladvsuqPV0hihBNTSdFUOgMq1AoNBip/uVJywOCR/qu7XAO7VtonvlXnl3a7wmDZG8D1MIOz5i9aBY/Y+8wGWsmqt2rvLbuarYQEVJeQsuhG+SQnQvDuV3Qo9q1l47fxoxuSN29lQhchpamv07AjMBGi6HKZnu4HQ5anl4KNHnQ+cNNV9xQpBHidtBk0H7XxcG6u3auqHOazN1um8nJLQZxUjyzqz3dSWFwGEJz30msZTymvJjYrbJzLfHOOIRHAOFM1LqxIOnltWnAgMBAAECggEBAIBEMDE5TAL7XpEyJN7jif9UJ7f3BrDGu0P8Fgzo3Hgy6Oy4A20QhVuTkl0NgyPaQs6o5rEGtRE1niHZM9vhN99mwPvO1VRgH1whOKUHuyfI3dy9MkSCN/g+WQ1VH5LTJTyL2i47NTUsAL0CwNzPfRep1MT0uyE9IKvBfm8QA0EiZMX1/hyUHTQnwt3zv/FSyYzgO74I+t80u88M2vGwpAZbN6sqx1rPgsXsvNfMYqmYpZUNfxi934hSJiykL5YrUYGSLNG4nONI8gqzJ0ly9gNB0yqap+4f3qq1DUtynTh1x822jAWSBE8T44RFCBIqt/3zQ0hhQKqroL2xtzFyE2ECgYEA9u15bv9uWBhUvobnbyUFLbZcLrSsDm0kEwIT0JwOA2Sg9GSsC/RVRBU11oHtYmmBrYYHfe3l+yixKItRgag7aOXt30OnzlQXcsBirkebC1jGjKe9/n9WhU8nzOZHzdHLx0ncfR0bpbhsRUhyhXKzK55+53gTJDr4po7NXKAhZtkCgYEA6sr+BfcKU7nXs4xiBI5Vo5Yzh4CAeEFWKEexDI8lZgxCTOEwb8KUdfzRd3IEZDd6c5kqjhmHBnVQCu6IX8FX6Vnzm5qrhwUUd+QxyKaz6R1206qTwaPQWag7vQqD7AVRhyU4oiDM6ub5+sb8MOeEE4b8Jzy1cJhJWp8f4StJUH8CgYACRpYD6968eRzu1Jd1csXlcfTPiWK8j0zxz+cW2RlX9ql1Fyhuw8W1ZuykGXZynb5QNxgClRq1gd6AHSiXJVHG2bejgyAcyrGcoSFHsek/5YWO7kzV9554hA492TF4OCLEp22cyZPjpxt4V/6WNERqdn986qJihxibkF1cKS2loQKBgQC/z/4emWeSZuzU/gbFehJ0hiyqskxB8f6VAZyeigMfjzxK2l1bsDbg/R2S207DIJ+k+KhPVoN8vzIY/C3lLGrJdHcT1BP1Ri0mlQImzimEdFGzMlNrioyX8lx7X6/OjbnBEqNlsC44aWIngZBd04WO/mPZGjuQNJkYs46zwU0vuQKBgQCfIXrp83ekjuPolbH1FNdgp1JyhP/RL/CtuVfxXIWPs6X9QrnY6grZYh4Jqbr22riwOQmYyShPA6h03yv+pLiG2Gi8o6T1C+nVmDaHu3FYH4qpdq1E1r//WxRAjioUoaNzTvyKQBfFvb6oEUee+Fg6DNVWWzxygz/pqphcu098+Q==";

    @RequestMapping(value = "/fraud_score_a1", method = RequestMethod.POST)
    public Object queryFraudScoreA1(@RequestBody ApiRequest request) {
        ApiResponse response = new ApiResponse();
        // 账户获取客户公钥
        String publicKey = getPublicKeyByAccount(request.getAccount());
        if (StringUtils.isEmpty(publicKey)) {
            response.setRespCode(HzBizCode.ERROR_1005.getCode());
            response.setRespMsg(HzBizCode.ERROR_1005.getDesc());
            return response;
        }

        // 验签
        if (!RSAUtil.safeVerifySign(request.getReqData(), publicKey, request.getSign())) {
            response.setRespCode(HzBizCode.ERROR_1002.getCode());
            response.setRespMsg(HzBizCode.ERROR_1002.getDesc());
            return response;
        }

        // 解密
        String aesKey;
        String decryptReqData;
        try {
            aesKey = RSAUtil.decrypt(HZ_PRIVATE_KEY, request.getKey());
            decryptReqData = AESUtil.decrypt(aesKey, request.getReqData());
        } catch (Exception e) {
            log.error("e");
            // 解密失败，报文可能被篡改
            response.setRespCode(HzBizCode.ERROR_1002.getCode());
            response.setRespMsg(HzBizCode.ERROR_1002.getDesc());
            return response;
        }
        log.info("decrypt req data {}", decryptReqData);

        try {
            JSONObject mockResp = new JSONObject();
            mockResp.put("data", 101.33);
            String encryptData = AESUtil.encrypt(aesKey, mockResp.toJSONString());
            response.setRespMsg(HzBizCode.SUCCESS_0000.getCode());
            response.setRespData(HzBizCode.SUCCESS_0000.getDesc());
            response.setSeqNo(UuidUtil.get32UUID());
            response.setRespData(encryptData);
            response.setSign(RSAUtil.sign(encryptData.getBytes(StandardCharsets.UTF_8), HZ_PRIVATE_KEY));
            return response;
        } catch (Exception e) {
            log.error("e");
            // 解密失败，报文可能被篡改
            response.setRespCode(HzBizCode.ERROR_1004.getCode());
            response.setRespMsg(HzBizCode.ERROR_1004.getDesc());
            return response;
        }
    }

    public String getPublicKeyByAccount(String account) {
        // 根据客户账户，获取客户公钥，可以数据库，配置文件等
        if ("test_account_1".equalsIgnoreCase(account)) {
            return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlVvDThDPtTammIdFRmVMET+f79j+b40HZOAeIjbUtWfszweLN24eYV3LkFO32uG+0ZzI4f4EXJpKGYoyAU8DOO1jjF77GJTHAcYUSFSBtTlTQeBLzh5e8XjjwEAZ3DrErMOlMHICvA0LwHI1jvMIqRNlARANFgHULa7E88pMfDNNOhSDc23zRIhDEqJe+4ELtwGUi/CD6DQduj1D6mG5dwUOWZLcfouId6beQ2NtkadcGuBkXO6RTer65BC4QaKK7CpnWM8j4b1flNFsshYJpKDbQw/E6RN6nhZV/jm76lKCHK3tbIyaNJ8G//Qu7VHXlQ+aWY4MtAlhookTIiJrEQIDAQAB";
        }
        return "";
    }
}
