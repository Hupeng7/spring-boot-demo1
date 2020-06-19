package com.example.springbootdemomytool.utils.httpclient4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/27 17:42
 * @Version 1.0
 */
@Slf4j
@Component
@RequestMapping("httptest")
public class Test {
    @Autowired
    private HttpAPIService httpAPIService;

    @GetMapping("/t1")
    public String test() {
        String[] strings = {"a", "b"};
        String bodyJsonStr = "{\"request\":\"WSKM23Ud4Ag+IqOXJIxBS0n33Tn08e72qMjkWw/g5sFK7UKFxYOfIZGv0REfySpVZQFVRLUvADuuIW0V0bPtA6oFBIUuqxUZQYHei3abnjIFVdlX8RLZGR0P0IZepO/eNRaXSguDw8MaSKOfPg92+JjubaIRCvWVE9+/A8az+YLWNn2viRie0t9PX1GRJKPv6hXrQCNH+T/5boV/svs0lb0J6cLTMA9TVsxzhmxw78zTv8oKpz4MuU+lrLgDrjeaHbgEjvIH0Amfvx7o3MZTD/u6uo10iqj/Nj7L30rnwxM=\",\"sign\":\"D8D7B477D5C5C2FEFCAB83ACEC65184FDAA65E306891F677FB2CF4E641B2C5B5\",\"aeskey\":\"ZIn6zt6GpsELMCgb4MG7Q62BACm56lnB54nBUnzOoGq+OMHAxMMaoZjY+ypv+vIyH5DcKhrHeaD6xIG9f/cAw+wKPxxkGSO6IHeSOfCkm2GEO50cW8V88upTKZg1jodxYcJPRPqmNhGccX5Mhj+lJpAPd3lL1o+bCldR8Ekbhsk=\"}";


        Map<String, Object> map = new HashMap<>();
        map.put("request", "WSKM23Ud4Ag+IqOXJIxBS0n33Tn08e72qMjkWw/g5sFK7UKFxYOfIZGv0REfySpVZQFVRLUvADuuIW0V0bPtA6oFBIUuqxUZQYHei3abnjIFVdlX8RLZGR0P0IZepO/eNRaXSguDw8MaSKOfPg92+JjubaIRCvWVE9+/A8az+YLWNn2viRie0t9PX1GRJKPv6hXrQCNH+T/5boV/svs0lb0J6cLTMA9TVsxzhmxw78zTv8oKpz4MuU+lrLgDrjeaHbgEjvIH0Amfvx7o3MZTD/u6uo10iqj/Nj7L30rnwxM=");
        map.put("sign", "D8D7B477D5C5C2FEFCAB83ACEC65184FDAA65E306891F677FB2CF4E641B2C5B5");
        map.put("aeskey", "ZIn6zt6GpsELMCgb4MG7Q62BACm56lnB54nBUnzOoGq+OMHAxMMaoZjY+ypv+vIyH5DcKhrHeaD6xIG9f/cAw+wKPxxkGSO6IHeSOfCkm2GEO50cW8V88upTKZg1jodxYcJPRPqmNhGccX5Mhj+lJpAPd3lL1o+bCldR8Ekbhsk=");
        String url = "http://localhost:9909/zbank";

//        try {
//            String httpResult = httpAPIService.doPost2(url, bodyJsonStr);
//            System.out.println("result: " + httpResult.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception: " + e.getMessage());
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int a = 0; a < 10; a++) {
            executorService.submit(() -> {
                System.out.println("Thread id is: " + Thread.currentThread().getId());
                try {
                    // str = httpAPIService.doGet("http://www.baidu.com");
                    String httpResult = httpAPIService.doPost2(url, bodyJsonStr);
                    System.out.println("result: " + httpResult.toString());
                    // log.info("httpResult:{}", httpResult.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Exception: " + e.getMessage());
                }
//                Thread.sleep(1000L);
            });
        }

        return "success";
    }


}
