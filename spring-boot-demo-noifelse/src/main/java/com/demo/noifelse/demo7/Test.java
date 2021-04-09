package com.demo.noifelse.demo7;

/**
 * @ClassName Test
 * @Description https://mp.weixin.qq.com/s/vJXcR3TyfcUsWb3KzBSxEw
 * @Author H
 * @Date 2021/4/8 18:37
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        String medalType = "vip";
        IMedalService medalService = MedalServicesFactory.getMedalService(medalType);
        medalService.showMedal();
    }

}
