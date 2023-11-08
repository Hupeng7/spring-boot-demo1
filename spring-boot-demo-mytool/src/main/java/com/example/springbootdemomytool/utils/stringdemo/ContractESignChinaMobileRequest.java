package com.example.springbootdemomytool.utils.stringdemo;

import lombok.Data;

import java.util.Map;

/**
 * 中国移动5G随e签签章请求参数
 */
@Data
public class ContractESignChinaMobileRequest {
    String contractId;
    String contractName;
    Map<String, Object> contractContent;
    UserInfo userInfo;

    // 平台合同流水号
    String serialNumber;

    @Data
    public static class UserInfo {
        // 创建用户
        Byte userType;
        String userId;
        String username;
        // 证件类型：0身份证 7统一社会信用代码 10其他证件
        Byte cardType;
        String idno;
        String mobile;
    }

    private Integer sealPage;
    private Float sealX;
    private Float sealY;


}
